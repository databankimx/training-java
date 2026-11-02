package com.databankimx.training.runner;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
     * Starts the Chromium runtime from the natives folder, opens the window, and loads the page.
     * The natives must already be fetched and verified, so no download happens here.
     */
    public static void open(Path natives, Path web) throws Exception {
        CefAppBuilder builder = new CefAppBuilder();
        builder.setInstallDir(natives.toFile());
        builder.getCefSettings().windowless_rendering_enabled = true;
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
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
            }
            Runtime.getRuntime().halt(0);
        }, "exit-watchdog");
        watchdog.setDaemon(true);
        watchdog.start();
    }
}
