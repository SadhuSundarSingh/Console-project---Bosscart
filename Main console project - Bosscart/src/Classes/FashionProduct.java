package Classes;
public class FashionProduct extends Product {
    int size;
    String brand;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public FashionProduct(String name, Category category, int price, int quantity, int size, String brand) {
        super(name, category, price, quantity);
        this.size = size;
        this.brand = brand;
    }

    public String toString() {
        return "Item :" + this.getName() + " , Brand : " + this.getBrand() + " , Size : " + this.getSize() + " , Available quantity : " + quantity;
    }
}
