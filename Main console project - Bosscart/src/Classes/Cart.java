package Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Cart {
    ArrayList productsList = new ArrayList();
    CartStatus status;
    LocalDate orderDate;
    LocalDate deliveryDate;
    DeliveryMan deliveryMan;

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList productsList) {
        this.productsList = productsList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String toString() {
        String ans = "";
        for(Product pr : this.getProductsList()) {
            ans += " , " + pr;
        }
        return ans.substring(1);
    }
}
