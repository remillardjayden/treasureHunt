import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
public abstract class Screen {
    private String title;
    private Color textColor;
    public Screen(String t, Color c) {
        title = t;
        textColor = c;
    }
    public String getTitle() {
        return title;
    }
    public Color getColor() {
        return textColor;
    }
    public abstract void update();
    public abstract void draw(Graphics pen);
    public abstract void keyPressed(KeyEvent ke);
}
