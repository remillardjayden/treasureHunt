import java.awt.image.BufferedImage;
public class Product extends GamerObject{
    private String name;
    private int count;
    private int price;
    public Product(String name, int price, BufferedImage sprt, int x, int y) {
        super(x, y, sprt);
        this.name = name;
        count = 0;
        this.price = price;
    }
    public String getName() { return name; }
    public int getCount() { return count; }
    public void increaseCount() { count++; }
    public int getPrice() { return price; }
    public void increasePrice() { price *= 1.5; }
}