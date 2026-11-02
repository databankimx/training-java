package com.databankimx.training.runner;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;

/**
 * The pop-out lesson window: a second browser showing lesson.html, so a long lesson can be read beside the steps.
 * Opening it tells the main window, which shows a note in its Lesson pane. Docking, from the button in the pop-out or
 * by closing it, returns the lesson to the main window.
 */
final class LessonWindow {

    private final CefApp app;
    private final Commands commands;
    private final WebServer pages;
    private JFrame frame;

    LessonWindow(CefApp app, Commands commands, WebServer pages) {
        this.app = app;
        this.commands = commands;
        this.pages = pages;
    }

    /** Opens the pop-out, or brings it to the front if it is already open. Must run on the window's thread. */
    synchronized void open() {
        if (frame != null) {
            frame.toFront();
            return;
        }
        CefClient client = app.createClient();
        Router router = new Router(commands);
        CefMessageRouter messageRouter = CefMessageRouter.create();
        messageRouter.addHandler(router, true);
        client.addMessageRouter(messageRouter);
        CefBrowser browser = client.createBrowser(pages.pageUrl("lesson.html"), true, false);

        // Updates from the main window reach this browser through the router, on the window's thread.
        commands.setPopoutSink(Runner.onWindowThread(message -> router.send(browser, message)));

        JFrame window = new JFrame("Lesson");
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dock();
            }
        });
        window.add(browser.getUIComponent(), BorderLayout.CENTER);
        BrowserSizing.follow(window, browser);
        window.setSize(900, 700);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        frame = window;
        commands.notifyMain("lesson-popped");
    }

    /** Returns the lesson to the main window and closes the pop-out. Safe to call when the pop-out is not open. */
    synchronized void dock() {
        if (frame == null) {
            return;
        }
        commands.setPopoutSink(null);
        JFrame window = frame;
        frame = null;
        SwingUtilities.invokeLater(window::dispose);
        commands.notifyMain("lesson-docked");
    }
}
