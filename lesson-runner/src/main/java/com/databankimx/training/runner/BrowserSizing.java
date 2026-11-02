package com.databankimx.training.runner;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.cef.browser.CefBrowser;

/**
 * Keeps an off-screen browser's size in step with its window. When a window is moved to a monitor with a different
 * scale, the browser has to be told its new size, or the window is left partly blank until it is clicked.
 *
 * The browser is resized once the window has been still for a moment, because the scale change is not finished while
 * the window is still moving.
 */
final class BrowserSizing {

    /** How long the window must stay still before the browser is resized, in milliseconds. */
    private static final int SETTLE_MILLIS = 250;

    private BrowserSizing() {
    }

    static void follow(JFrame frame, CefBrowser browser) {
        Timer settle = new Timer(SETTLE_MILLIS, event -> resize(browser));
        settle.setRepeats(false);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                settle.restart();
            }

            @Override
            public void componentMoved(ComponentEvent event) {
                settle.restart();
            }
        });
    }

    private static void resize(CefBrowser browser) {
        Component view = browser.getUIComponent();
        // Changing the size, even by a pixel and back, makes the browser recompute its size and scale.
        Dimension size = view.getSize();
        view.setSize(size.width, size.height + 1);
        view.setSize(size);
        view.revalidate();
        view.repaint();
    }
}
