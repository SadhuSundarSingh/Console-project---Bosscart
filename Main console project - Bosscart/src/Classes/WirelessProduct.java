package Classes;

public class WirelessProduct extends WiredProduct {
    int battery;

    public WirelessProduct(String name, Category category, int price, int quantity, String brand, String model, int battery, ElectronicType electronicType) {
        super(name, category, price, quantity, brand, model, electronicType);
        this.battery = battery;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String toString() {
        return "Brand : " + this.getBrand() + " , Model : " + this.getModel() + " , Type : " + this.getElectronicType() + " , Battery : " + this.getBattery() + " , Available quantity : " + quantity;
    }
}
