package Classes;

public class PortableElectronicProduct extends WirelessProduct{
    int ram;
    int storage;
    float screenSize;
    int camera;

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }

    public PortableElectronicProduct(String name, Category category, int price, int quantity, String brand, String model, int battery, int ram, int storage, float screenSize, int camera, ElectronicType electronicType) {
        super(name, category, price, quantity, brand, model, battery,electronicType);
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.camera = camera;
    }

    public String toString() {
        return "Brand : " + this.getBrand() + " , Model : " + this.getModel() + " , Price : " + this.getPrice()  + " Rs , RAM : " + this.getRam() + " GB , Storage : " + this.getStorage() + " GB , Display Size : " + this.getScreenSize() + " inch";
    }
}
