package Classes;

public class WiredProduct extends Product {
    String brand;
    String model;
    ElectronicType electronicType;

    public ElectronicType getElectronicType() {
        return electronicType;
    }

    public void setElectronicType(ElectronicType electronicType) {
        this.electronicType = electronicType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public WiredProduct(String name, Category category, int price, int quantity, String brand, String model , ElectronicType electronicType) {
        super(name, category, price, quantity);
        this.brand = brand;
        this.model = model;
        this.electronicType = electronicType;
    }

    public String toString() {
        return "Brand : " + this.getBrand() + " , Model : " + this.getModel() + " , Type : " + this.getElectronicType() + " , Available quantity : " + quantity;
    }
}
