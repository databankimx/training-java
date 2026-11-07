package com.databankimx.training.runner;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;

/**
 * The runner's window: a JCEF browser, drawn off-screen into a Java window, that loads the page in web/. Java routes
 * the mouse and keyboard input to the browser. The page talks to the host through the query channel, and the host
 * answers through window.__hostMessage, as described in web/bridge.js.
 *
 * Windowed rendering (a native Chromium window inside the Java window) did not respond to input on this machine, so
 * off-screen rendering is used. It needs the JOGL export flags in run.bat.
 */
public final class Window {

    private Window() {
    }

    /**
     * The digit or symbol each numpad key types. With Num Lock on, these keys arrive as navigation keys (End, the arrows,
     * and so on), and the browser cannot type them, so they are turned into characters here.
     */
    private static final Map<Integer, String> NUMPAD_CHARACTERS = new HashMap<>();

    static {
        String[] navigation = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
        int[] keys = {
            KeyEvent.VK_INSERT, KeyEvent.VK_END, KeyEvent.VK_DOWN, KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_LEFT,
            KeyEvent.VK_CLEAR, KeyEvent.VK_RIGHT, KeyEvent.VK_HOME, KeyEvent.VK_UP, KeyEvent.VK_PAGE_UP,
            KeyEvent.VK_DELETE,
        };
        for (int i = 0; i < keys.length; i++) {
            NUMPAD_CHARACTERS.put(keys[i], navigation[i]);
        }
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD0, "0");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD1, "1");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD2, "2");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD3, "3");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD4, "4");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD5, "5");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD6, "6");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD7, "7");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD8, "8");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_NUMPAD9, "9");
        NUMPAD_CHARACTERS.put(KeyEvent.VK_DECIMAL, ".");
    }

    /**
     * Starts the Chromium runtime from the natives folder, opens the window, and loads the page.
     * The natives must already be fetched and verified, so no download happens here.
     */
    public static void open(Path natives, Path web) throws Exception {
        CefAppBuilder builder = new CefAppBuilder();
        builder.setInstallDir(natives.toFile());
        // RUNNER_BROWSER_LOG=1 makes Chromium write its own log to the terminal, to show why a window is blank.
        if ("1".equals(System.getenv("RUNNER_BROWSER_LOG"))) {
            builder.addJcefArgs("--enable-logging=stderr", "--v=0");
        }
        builder.getCefSettings().windowless_rendering_enabled = true;
        // The browser's cache lives in its own folder, which CEF needs to be set explicitly to avoid a warning and
        // shared process state.
        builder.getCefSettings().root_cache_path = Paths.get("jcef-cache").toAbsolutePath().toString();
        builder.setAppHandler(new MavenCefAppHandlerAdapter() {
            @Override
            public void stateHasChanged(CefApp.CefAppState state) {
                if (state == CefApp.CefAppState.TERMINATED) {
                    System.exit(0);
                }
            }
        });
        CefApp app = builder.build();
        CefClient client = app.createClient();

        // The page is served over HTTP on the loopback address, so its ES modules load.
        WebServer pages = new WebServer(web);

        Commands commands = new Commands();
        CefMessageRouter router = CefMessageRouter.create();
        Router mainRouter = new Router(commands);
        router.addHandler(mainRouter, true);
        client.addMessageRouter(router);

        CefBrowser browser = client.createBrowser(pages.pageUrl("index.html"), true, false);

        // With Num Lock on, the numpad sends navigation keys, which the browser cannot type. Those keys are caught here,
        // before the browser sees them, and the digit is typed in the page instead. With Num Lock off, nothing changes.
        // The press is handled once, so the typed and released events for the same key are consumed too.
        boolean[] numpadPressed = {false};
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            if (event.getComponent() != browser.getUIComponent()) {
                return false;
            }
            if (event.getID() == KeyEvent.KEY_TYPED) {
                return numpadPressed[0];
            }
            if (event.getID() == KeyEvent.KEY_RELEASED) {
                boolean consumed = numpadPressed[0];
                numpadPressed[0] = false;
                return consumed;
            }
            if (event.getID() != KeyEvent.KEY_PRESSED || event.getKeyLocation() != KeyEvent.KEY_LOCATION_NUMPAD) {
                return false;
            }
            String character = NUMPAD_CHARACTERS.get(event.getKeyCode());
            if (character == null || !Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK)) {
                return false;
            }
            numpadPressed[0] = true;
            browser.executeJavaScript("window.typeCharacter('" + character + "');", browser.getURL(), 0);
            return true;
        });

        // Events for the main window, such as run output, reach its browser on the window's thread.
        commands.setMainSink(Runner.onWindowThread(message -> mainRouter.send(browser, message)));
        commands.setLessonWindow(new LessonWindow(app, commands, pages));

        JFrame frame = new JFrame("Training");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exit(frame);
            }
        });
        frame.add(browser.getUIComponent(), BorderLayout.CENTER);
        BrowserSizing.follow(frame, browser);
        // The Databank icon on the title bar and the taskbar. If the file is missing, the Java icon is kept.
        Path icon = Paths.get("resources", "icon_databank_blue.png");
        if (Files.exists(icon)) {
            frame.setIconImage(ImageIO.read(icon.toFile()));
        }
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Ends the program. The window is disposed first, then the JVM exits from a background thread. A watchdog ends
     * the process if the exit stalls. The Chromium runtime is not disposed here, because its shutdown can wait on the
     * window's event thread, which is still handling this close.
     */
    private static void exit(JFrame frame) {
        frame.dispose();
        Thread exit = new Thread(() -> System.exit(0), "exit");
        exit.setDaemon(true);
        exit.start();
        Thread watchdog = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException _) {
                Thread.currentThread().interrupt();
            }
            Runtime.getRuntime().halt(0);
        }, "exit-watchdog");
        watchdog.setDaemon(true);
        watchdog.start();
    }
}
