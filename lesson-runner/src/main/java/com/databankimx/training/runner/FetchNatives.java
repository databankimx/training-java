package com.databankimx.training.runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.stream.Stream;

import me.friwi.jcefmaven.CefAppBuilder;
import org.cef.CefApp;

/**
 * Fetches the Chromium natives once with jcefmaven, and records the SHA-256 hash of every file in natives.sha256.
 * The build checks that list (scripts/verify-natives.ps1) before the natives are bundled.
 *
 * The manifest is never overwritten silently. A changed file means the hash list must be reviewed and approved,
 * so the fetch refuses to replace an existing manifest unless --rewrite-manifest is given.
 */
public final class FetchNatives {

    /** The folder the natives are fetched into, relative to lesson-runner, where the Maven commands run. */
    public static final Path DEFAULT_DIR = Paths.get("..", "resources", "vendor", "jcef", "natives");

    private static final String MANIFEST_NAME = "natives.sha256";

    private FetchNatives() {
    }

    public static void run(Path natives, boolean rewriteManifest) throws Exception {
        Path manifest = natives.resolve(MANIFEST_NAME);
        // An approved manifest is never overwritten. The fetch installs the natives, then checks them against it.
        boolean approved = Files.exists(manifest) && !rewriteManifest;

        Files.createDirectories(natives);

        // Building the app is what downloads and installs the natives on first run.
        // On later runs, jcefmaven reuses the files it already installed.
        CefAppBuilder builder = new CefAppBuilder();
        builder.setInstallDir(natives.toFile());
        CefApp app = builder.build();
        app.dispose();

        if (approved) {
            verify(manifest, natives);
            System.out.println("The natives match the approved hashes in " + manifest);
            return;
        }

        List<String> lines = new ArrayList<>();
        for (Path file : nativeFiles(natives)) {
            String relative = natives.relativize(file).toString();
            lines.add(sha256(file) + "  " + relative);
        }
        Files.write(manifest, lines);
        System.out.println("Recorded " + lines.size() + " file(s) in " + manifest);
    }

    /** Checks each file named in the approved manifest against its recorded hash. A missing or changed file fails. */
    private static void verify(Path manifest, Path natives) throws Exception {
        List<String> problems = new ArrayList<>();
        for (String line : Files.readAllLines(manifest)) {
            if (line.isBlank()) {
                continue;
            }
            String[] parts = line.split("  ", 2);
            Path file = natives.resolve(parts[1]);
            if (!Files.isRegularFile(file)) {
                problems.add("missing " + parts[1]);
            } else if (!sha256(file).equals(parts[0])) {
                problems.add("changed " + parts[1]);
            }
        }
        if (!problems.isEmpty()) {
            throw new IllegalStateException("The natives do not match the approved hashes: " + problems);
        }
    }

    /** Every regular file under the natives folder, except the manifest itself, in a fixed order. */
    private static List<Path> nativeFiles(Path natives) throws IOException {
        try (Stream<Path> walk = Files.walk(natives)) {
            return walk.filter(Files::isRegularFile)
                    .filter(p -> !p.getFileName().toString().equals(MANIFEST_NAME))
                    .sorted()
                    .toList();
        }
    }

    private static String sha256(Path file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(Files.readAllBytes(file));
        return HexFormat.of().formatHex(digest.digest());
    }
}
