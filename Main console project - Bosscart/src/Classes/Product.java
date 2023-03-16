package Classes;

public class Product implements Cloneable {
    String name;
    Category category;
    int quantity;
    int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String name, Category category, int price, int quantity) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
    public String toString() {
        return "name : " + name + " , Price : " + price + " , Quantity : " + quantity;
    }

    public Product cloning(Product product) throws CloneNotSupportedException {
        return ((Product)product.clone());
    }
}
