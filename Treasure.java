import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Treasure extends GamerObject{
    public static ArrayList<Treasure> treasures;
    private int worth;
    public Treasure(int row, int col, int gold, BufferedImage sprt) {
        super(row, col, sprt);
        worth = gold;
        if(treasures == null) { treasures = new ArrayList<Treasure>(); }
        treasures.add(this);
    }
    public int getWorth() { return worth; }
    public void setWorth(int gold) { worth = gold; }
}
