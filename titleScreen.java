import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
public class titleScreen extends Screen{
    private Button begin;
    public titleScreen(String title, Color color) {
        super(title, color);
        begin = new Button("Begin", Color.GREEN, Color.RED);
    }
    public void update() {
        if(treasureSearch.activeScreen == this) {
            treasureSearch.title.loop();
        }
    }
    public void draw(Graphics pen) {
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        pen.setColor(super.getColor());
        pen.drawString(super.getTitle(), (treasureSearch.SCREEN_WIDTH/2)-225, 40);
        begin.draw(pen, (treasureSearch.SCREEN_WIDTH/2)-100, 700);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        pen.drawString("(Press Enter)", (treasureSearch.SCREEN_WIDTH/2)-75, 825);
    }
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER && treasureSearch.activeScreen == treasureSearch.start) {
            treasureSearch.activeScreen = treasureSearch.game;
            int song = (int)(Math.random()*6);
            if(song == 0) {
                treasureSearch.main1.loop();
            } else if(song == 1) {
                treasureSearch.main2.loop();
            } else if(song == 2) {
                treasureSearch.main3.loop();
            } else if(song == 3) {
                treasureSearch.main4.loop();
            } else if(song == 4) {
                treasureSearch.main5.loop();
            } else if(song == 5) {
                treasureSearch.main6.loop();
            }
        }
    }
}
