import java.awt.image.BufferedImage;
public class Treasure extends GamerObject{
    private int worth;
    public Treasure(int row, int col, int gold, BufferedImage sprt) {
        super(row, col, sprt);
        worth = gold;
    }
    public int getWorth() { return worth; }
    public void setWorth(int gold) { worth = gold; }
}
