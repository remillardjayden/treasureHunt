public class Player extends GamerObject{
    private int totalTreasure;
    private int totalGold;
    public Player(int row, int col) {
        super(row, col);
        totalTreasure = 0;
        totalGold = 5;
    }
    public int getTreasure() { return totalTreasure; }
    public void increaseTreasure() { totalTreasure++; }
    public void setTreasure(int t) { totalTreasure = t; }
    public int getGold() { return totalGold; }
    public void increaseGold(int g) { totalGold += g; }
    public void setGold(int g) { totalGold = g; }
}