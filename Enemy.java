import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Enemy extends GamerObject{
    public static ArrayList<Enemy> allEnemies;
    private boolean anger;
    private int timer;
    public Enemy(int row, int col, BufferedImage sprt) {
        super(row, col, sprt);
        anger = false;
        timer = 0;
        if(allEnemies == null) { allEnemies = new ArrayList<Enemy>(); }
        allEnemies.add(this);
    }
    public int getTime() {
        return timer;
    }
    public void increaseTime() {
        timer++;
    }
    public void setTime(int set) {
        timer = set;
    }
    public boolean isAngry() {
        return anger;
    }
    public void changeAngry() {
        if(anger) {
            anger = false;
        } else {
            anger = true;
        }
    }
}
