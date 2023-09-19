import java.util.ArrayList;
public class ItemShop {
    private String title;
    private ArrayList<Product> products;
    private ArrayList<Integer> prices;
    private int x, y;
    public ItemShop(String title, ArrayList<Product> products, ArrayList<Integer> prices, int x, int y) {
        this.title = title;
        this.products = products;
        this.prices = prices;
        this.x = x;
        this.y = y;
    }
    public String getTitle() { return title; }
    public ArrayList<Product> getProducts() { return products; }
    public ArrayList<Integer> getPrices() { return prices; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void addProduct(Product product) { 
        if(products == null) {
            products = new ArrayList<Product>();
        }
        products.add(product); 
    }
    public void addPrice(int price) {
        if(prices == null) {
            prices = new ArrayList<Integer>();
        }
        prices.add(price);
    }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void purchase(int item) {
        if(item > products.size()) {
            return;
        } else {

        }
    }
}