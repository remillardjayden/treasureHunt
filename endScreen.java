import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
public class endScreen extends Screen{
    private Button begin;
    public endScreen(String title, Color color) {
        super(title, color);
        begin = new Button("Retry?", color, Color.BLACK);
    }
    public void update() {}
    public void draw(Graphics pen) {
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        pen.setColor(super.getColor());
        pen.drawString(super.getTitle(), (treasureSearch.SCREEN_WIDTH/2)-125, 40);
        begin.draw(pen, (treasureSearch.SCREEN_WIDTH/2)-100, 700);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        pen.drawString("(Press Enter)", (treasureSearch.SCREEN_WIDTH/2)-75, 825);
    }
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER && treasureSearch.activeScreen == treasureSearch.end) {
            treasureSearch.activeScreen = treasureSearch.start;
            try {
                treasureSearch.hen.resetAudioStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
