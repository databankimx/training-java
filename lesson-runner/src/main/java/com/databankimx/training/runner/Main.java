package com.databankimx.training.runner;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The runner's entry point. With no arguments it opens the window. The fetch mode is run once, to download and
 * record the natives, and it isn't needed again unless the natives change.
 */
public final class Main {

    /** The page, relative to lesson-runner, where the runner starts. */
    static final Path WEB = Paths.get("web");

    /**
     * Writes every uncaught error to runner.log, including errors thrown while the window handles events.
     */
    private static void installErrorLog() {
        ErrorLog.installEventQueue();
        Thread.setDefaultUncaughtExceptionHandler((thread, error) -> ErrorLog.write(thread.getName(), error));
    }

    private Main() {
    }

    public static void main(String[] args) throws Exception {
        installErrorLog();
        if (args.length == 0) {
            // Swing components are created and shown on the event dispatch thread, as the sample app does.
            javax.swing.SwingUtilities.invokeAndWait(() -> {
                try {
                    Window.open(FetchNatives.DEFAULT_DIR, WEB);
                } catch (Exception error) {
                    throw new RuntimeException(error);
                }
            });
            return;
        }
        if (args.length == 1 && args[0].equals("--fetch-natives")) {
            FetchNatives.run(FetchNatives.DEFAULT_DIR, false);
            return;
        }
        if (args.length == 2 && args[0].equals("--fetch-natives") && args[1].equals("--rewrite-manifest")) {
            FetchNatives.run(FetchNatives.DEFAULT_DIR, true);
            return;
        }
        System.out.println("Usage: lesson-runner [--fetch-natives [--rewrite-manifest]]");
        System.exit(2);
    }
}
