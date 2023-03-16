package Classes;

public class ApplianceProduct extends Product{
    String brand;
    int warranty;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public ApplianceProduct(String name, Category category, int price, int quantity, String brand, int warranty) {
        super(name, category, price, quantity);
        this.brand = brand;
        this.warranty = warranty;
    }
}
