import java.awt.image.BufferedImage;
public class Product {
    private String name;
    private int count;
    private int price;
    private BufferedImage sprite;
    private int x, y;
    public Product(String name, int price, BufferedImage sprt, int x, int y) {
        this.name = name;
        count = 0;
        this.price = price;
        sprite = sprt;
        this.x = x;
        this.y = y;
    }
    public String getName() { return name; }
    public int getCount() { return count; }
    public void increaseCount() { count++; }
    public int getPrice() { return price; }
    public void increasePrice() { price *= 1.5; }
    public BufferedImage getSprite() { return sprite; }
    public int getX() { return x; }
    public int getY() { return y; }
}