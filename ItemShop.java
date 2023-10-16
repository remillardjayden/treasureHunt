import java.util.ArrayList;
public class ItemShop {
    private String title;
    private ArrayList<Product> products;
    private int x, y;
    public ItemShop(String title, ArrayList<Product> products, int x, int y) {
        this.title = title;
        this.products = products;
        this.x = x;
        this.y = y;
    }
    public String getTitle() { return title; }
    public ArrayList<Product> getProducts() { return products; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void addProduct(Product product) { 
        if(products == null) {
            products = new ArrayList<Product>();
        }
        products.add(product); 
    }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}