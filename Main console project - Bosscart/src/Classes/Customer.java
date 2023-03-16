package Classes;
import org.apache.log4j.Level;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer {
    String firstName;
    String lastName;
    String mobileNumber;
    String userName;
    String password;
    String deliveryManPhoneNumber;
    Cart cart = new Cart();
    ArrayList<String> history = new ArrayList<>();

    public ArrayList<String> getHistory() {
        return history;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    ArrayList wishlist = new ArrayList<>();

    public String getDeliveryManPhoneNumber() {
        return deliveryManPhoneNumber;
    }

    public void setDeliveryManPhoneNumber(String deliveryManPhoneNumber) {
        this.deliveryManPhoneNumber = deliveryManPhoneNumber;
    }

    Item getCurrentItem(Category category) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering getCurrentItem method ");
        Item currentItem = null;
        for(Item item : Admin.itemSet) {
            if(item.getCategory().equals(category)) {
                currentItem = item;
                break;
            }
        }
        if(currentItem==null) {
            System.out.println("Product not found");
            Main.main(new String[]{});
        }
        return currentItem;
    }

    String selectTypeName(Item currentItem ,int typeChoice ) {
        int count = 0;
        String type = null;
        for(Object string : currentItem.getMap().keySet()) {
            if(count==typeChoice-1) {
                type = (String)string;
                break;
            }
            ++count;
        }
        if(type==null) {
            System.out.println("No type matched");
            Main.main(new String[]{});
        }
        return type;
    }

    void addWishlist(Product buyProduct) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering addWishlist method ");
        Product cloneObj = null;
        try {
            cloneObj = buyProduct.cloning(buyProduct);
        }
        catch (Exception ex) {
            ex.getMessage();
        }
        cloneObj.setQuantity(0);
        this.getWishlist().add(cloneObj);
        System.out.println("Successfully added");
    }

    void showWishList() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showWishlist method ");
        System.out.println("--------------WISHLIST--------------");
        for(Object pr : this.getWishlist()) {
            System.out.println(((Product)pr).getName());
        }
        System.out.println("------------------------------------");
        System.out.println();
    }

    void showCartList() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showCartlist method ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|                                    BOSS-CART                                 |");
        System.out.println("|                                                                              |");
        System.out.println("----------|Product|------------------|Quantity|------------------|Price|--------");
        int amount = 0;
        for(Object cartProduct : this.getCart().getProductsList()) {
            amount += ((Product)cartProduct).getPrice() * ((Product)cartProduct).getQuantity();
            String space = "";
            for(int i=((Product)cartProduct).getName().length();i<29;i++) {
                space+=" ";
            }
            String space1 = "";
            for(int j=1;j<=27-String.valueOf(((Product)cartProduct).getQuantity()).length();j++) {
                space1+=" ";
            }
            String space2="";
            for(int k=1;k<=12 - (String.valueOf(((Product)cartProduct).getPrice() * ((Product)cartProduct).getQuantity() ).length());k++) {
                space2+=" ";
            }
            System.out.println("|          " + ((Product)cartProduct).getName() + space + ((Product)cartProduct).getQuantity() + space1 + ((Product)cartProduct).getPrice() * ((Product)cartProduct).getQuantity() + space2 + "|");
        }
        String space3="";
        for(int k=1;k<=12 - (String.valueOf(amount)).length();k++) {
            space3+=" ";
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|                                       Total" + "                      " + amount + space3 + "|");
        System.out.println("--------------------------------------------------------------------------------");
    }
    void showHistory() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showHistory method ");
        System.out.println("--------------HISTORY--------------");
//        for (Cart al : this.getHistory()) {
//            System.out.println(al.getOrderDate() + "--> " + al.getProductsList());
//        }
        System.out.println( ((this.getHistory().toString().replace('[',' ')).replace(']',' ')).replaceAll("null",""));
        System.out.println("-----------------------------------");
    }

    public ArrayList getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList wishlist) {
        this.wishlist = wishlist;
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

    public Customer(String firstName, String lastName, String mobileNumber, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        this.password = password;
    }

    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
