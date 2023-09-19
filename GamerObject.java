public class GamerObject {
    private int row, col;
    public GamerObject(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public void setRow(int r) { row = r; }
    public void setCol(int c) { col = c; }
    public void increaseRow() { row++; }
    public void decreaseRow() { row--; }
    public void increaseCol() { col++; }
    public void decreaseCol() { col--; }
}
