public class Treasure extends GamerObject{
    private int worth;
    public Treasure(int row, int col, int gold) {
        super(row, col);
        worth = gold;
    }
    public int getWorth() { return worth; }
    public void setWorth(int gold) { worth = gold; }
}
