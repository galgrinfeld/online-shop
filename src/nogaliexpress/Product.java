package nogaliexpress;

public class Product {
    private final Category category;
    private String productName;
    private double price;
    private Seller seller = null;
    private static int counter = 0;
    private final int productId;
    public enum Category {KIDS, ELECTRICITY, OFFICE, CLOTHING};



    public Product(String productName, double price, Seller seller, Category category) {
        this.productName = productName;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.productId = ++counter;
    }

    public Category getCategory() {
        return category;
    }

    public int getProductId() {
        return productId;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getProductName() {
        return productName;

    }
    public double getPrice() {
        return price;
    }

    public String toString() {

        return "Product Name: " + productName + ", Price: " + price + ", Seller: " + seller.getUserName() + ", Category: " + category + ", Product ID: " + productId;
    }

}
