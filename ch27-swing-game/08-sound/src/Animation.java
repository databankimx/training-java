import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

// A short animation: a folder of pictures, shown in order from the assets folder. The pictures are named frame_0,
// frame_1, and so on, and the number of frames is found by looking for them until one is missing. A looping animation
// starts again after its last picture; otherwise it plays once and stops.
public class Animation {
    private final List<ImageIcon> frames = new ArrayList<>();
    private final boolean loop;
    private int index = 0;
    private int ticks = 0;
    private boolean playing = false;

    public Animation(String folder, boolean loop) {
        this.loop = loop;
        for (int i = 0; ; i++) {
            Path file = Path.of("assets", "images", folder, "frame_" + i + "_delay-0.08s.gif");
            if (!Files.exists(file)) {
                break;
            }
            frames.add(new ImageIcon(file.toString()));
        }
    }

    // Starts the animation from its first picture, even if it was already playing.
    public void start() {
        index = 0;
        ticks = 0;
        playing = true;
    }

    // Moves the animation on by one frame of the game.
    public void update() {
        if (!playing) {
            return;
        }
        ticks++;
        if (ticks >= Settings.ANIMATION_TICKS_PER_FRAME) {
            ticks = 0;
            index++;
            if (index >= frames.size()) {
                index = 0;
                if (!loop) {
                    playing = false;
                }
            }
        }
    }

    public boolean isPlaying() {
        return playing;
    }

    // The number of the picture showing now, counting from 0.
    public int currentIndex() {
        return index;
    }

    // The picture to show now. When a one-time animation has finished, this is its first picture.
    public ImageIcon currentFrame() {
        return frames.get(index);
    }
}
