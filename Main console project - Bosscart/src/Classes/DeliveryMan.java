package Classes;
import org.apache.log4j.Level;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeliveryMan {
    String firstName;
    String lastName;
    String mobileNumber;
    String userName;
    String password;
    String customerPhoneNumber;
    ArrayList currentOrder = new ArrayList();
    ArrayList<String> history = new ArrayList<>();


    void showHistory() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showHistory method ");
        System.out.println("--------------HISTORY--------------");
//        for(Cart al : this.getHistory()) {
//            System.out.println(al.getDeliveryDate() + "--> " + al.getProductsList());
//        }
        System.out.println( ((this.getHistory().toString().replace('[',' ')).replace(']',' ')).replaceAll("null",""));
        System.out.println("-----------------------------------");
    }


    public ArrayList getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(ArrayList currentOrder) {
        this.currentOrder = currentOrder;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public DeliveryMan(String firstName, String lastName, String mobileNumber, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public ArrayList getOrder() {
        return currentOrder;
    }

    public void setOrder(ArrayList order) {
        this.currentOrder = order;
    }
}
