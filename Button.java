import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
public class Button {
    private String words;
    private Color background;
    private Color border;
    public Button(String words, Color colorBG, Color colorBD) {
        this.words = words;
        background = colorBG;
        border = colorBD;
    }
    public String getWord() { return words; }
    public Color getBackground() { return background; }
    public Color getBorder() { return border; }
    public void setWord(String words) { this.words = words; }
    public void setBG(Color colorBG) { background = colorBG; }
    public void setBD(Color colorBD) { background = colorBD; }
    public void draw(Graphics pen, int x, int y) {
        pen.setColor(background);
        pen.fillRect(x, y, 200, 100);
        pen.setColor(border);
        pen.drawRect(x, y, 200, 100);
        pen.setColor(Color.BLACK);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        pen.drawString(words, x+35, y+70);
    }
}