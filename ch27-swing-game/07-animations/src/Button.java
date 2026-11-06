import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

// A button drawn on the game's screen. It is clicked by the mouse, and the panel decides what the click does.
public class Button {
    private final String label;
    private final Rectangle area;

    public Button(String label, int centreX, int centreY) {
        this.label = label;
        area = new Rectangle(centreX - Settings.BUTTON_WIDTH / 2, centreY - Settings.BUTTON_HEIGHT / 2,
                Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT);
    }

    public boolean contains(Point point) {
        return area.contains(point);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(60, 100, 160));
        g.fillRoundRect(area.x, area.y, area.width, area.height, 15, 15);

        Font font = new Font("SansSerif", Font.BOLD, 20);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int textX = area.x + (area.width - metrics.stringWidth(label)) / 2;
        int textY = area.y + (area.height + metrics.getAscent() - metrics.getDescent()) / 2;
        g.setColor(Settings.TEXT);
        g.drawString(label, textX, textY);
    }
}
