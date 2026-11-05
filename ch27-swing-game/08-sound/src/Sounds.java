import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// The game's music and sound effects. The effects are read from their files once, when the game starts. Each play
// of an effect opens its own clip, so a sound that is still playing never stops a new one from starting.
public class Sounds {
    private static final Clip background = loadClip("background.wav");
    private static final byte[] shot = readFile("shoot.wav");
    private static final byte[] explosion = readFile("explosion.wav");
    private static final byte[] womp = readFile("womp.wav");

    // Reads a WAV file from the assets folder. A file that can't be read is reported, and the game runs without it.
    private static byte[] readFile(String file) {
        try {
            return Files.readAllBytes(Path.of("assets", "sounds", file));
        } catch (IOException error) {
            System.err.println("Could not read the sound " + file + ": " + error.getMessage());
            return null;
        }
    }

    // Loads a WAV file as one clip that can be started, stopped, and looped.
    private static Clip loadClip(String file) {
        byte[] data = readFile(file);
        if (data == null) {
            return null;
        }
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(openStream(data));
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException error) {
            System.err.println("Could not load the sound " + file + ": " + error.getMessage());
            return null;
        }
    }

    // Turns the bytes of a WAV file into a stream that a clip can read.
    private static AudioInputStream openStream(byte[] data)
            throws IOException, UnsupportedAudioFileException {
        InputStream bytes = new ByteArrayInputStream(data);
        return AudioSystem.getAudioInputStream(bytes);
    }

    // Starts the background music, which repeats until it's stopped.
    public static void startMusic() {
        if (background != null) {
            background.setFramePosition(0);
            background.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stopMusic() {
        if (background != null) {
            background.stop();
        }
    }

    public static void playShot() {
        playEffect(shot);
    }

    public static void playExplosion() {
        playEffect(explosion);
    }

    public static void playWomp() {
        playEffect(womp);
    }

    // Plays an effect on a new clip. The clip is closed when it stops, so the clips don't pile up in memory.
    private static void playEffect(byte[] data) {
        if (data == null) {
            return;
        }
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            clip.open(openStream(data));
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException error) {
            System.err.println("Could not play a sound: " + error.getMessage());
        }
    }
}
