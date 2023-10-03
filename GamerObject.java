import java.awt.image.BufferedImage;
public class GamerObject {
    private int row, col;
    private BufferedImage sprite;
    public GamerObject(int row, int col, BufferedImage sprt) {
        this.row = row;
        this.col = col;
        sprite = sprt;
    }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public void setRow(int r) { row = r; }
    public void setCol(int c) { col = c; }
    public void increaseRow() { row++; }
    public void decreaseRow() { row--; }
    public void increaseCol() { col++; }
    public void decreaseCol() { col--; }
    public BufferedImage getSprite() { return sprite; }
    public void setSprite(BufferedImage sprt) { sprite = sprt; }
}
