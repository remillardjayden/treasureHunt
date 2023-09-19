public class Player extends GamerObject{
    private int totalTreasure;
    private int totalGold, maxGold, counter;
    public Player(int row, int col) {
        super(row, col);
        totalTreasure = 0;
        totalGold = 5;
        maxGold = totalGold;
        counter = 0;
    }
    public int getTreasure() { return totalTreasure; }
    public void increaseTreasure() { totalTreasure++; }
    public void setTreasure(int t) { totalTreasure = t; }
    public int getGold() { return totalGold; }
    public void increaseGold(int g) {
        totalGold += g;
        if(totalGold > maxGold) { maxGold = totalGold; }
        counter += g;
        if(counter > 5) { counter = 5; }
    }
    public void setGold(int g) { totalGold = g; }
    public int getMaxGold() { return maxGold; }
    public int getCounter() { return counter; }
    public void resetCounter() { counter = totalGold % 5; }
}