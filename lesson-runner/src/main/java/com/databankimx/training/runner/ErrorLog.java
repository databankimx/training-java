package com.databankimx.training.runner;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

/**
 * Writes errors to runner.log in lesson-runner, with the exception's type and message first, then its stack trace.
 *
 * Errors on the window's event thread are caught by wrapping that thread's event queue. Every event, and every task
 * that Swing runs, passes through the queue's dispatch step, so an error thrown there is logged, and the window
 * carries on as it would have done.
 *
 * Logging is guarded, so an error raised while logging does not start another log entry. If the stack is too deep to
 * print a trace, the one-line header is still written.
 */
final class ErrorLog {

    private static final ThreadLocal<Boolean> LOGGING = ThreadLocal.withInitial(() -> false);

    private ErrorLog() {
    }

    /** Wraps the system event queue, so errors thrown while handling events are logged. Call once, at startup. */
    static void installEventQueue() {
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueue() {
            @Override
            protected void dispatchEvent(AWTEvent event) {
                try {
                    super.dispatchEvent(event);
                } catch (Throwable error) {
                    write(Thread.currentThread().getName(), error);
                }
            }
        });
    }

    /** Appends one error to runner.log, then shows it in the terminal too. Logging never stops the program. */
    static void write(String thread, Throwable error) {
        if (LOGGING.get()) {
            return;
        }
        LOGGING.set(true);
        try {
            String header = Instant.now() + " in " + thread + ": " + error;
            System.err.println(header);
            try (PrintWriter writer = new PrintWriter(new FileWriter("runner.log", true))) {
                writer.println(header);
                error.printStackTrace(writer);
                writer.println();
            } catch (IOException ignored) {
                // The log file could not be written, so the error is only shown in the terminal.
            }
        } catch (Throwable ignored) {
            // Logging itself failed, for example because the stack is too deep. Nothing more can be done safely here.
        } finally {
            LOGGING.set(false);
        }
    }
}
