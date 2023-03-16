package Classes;

import org.apache.log4j.Level;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Admin {
    String firstName;
    String lastName;
    String mobileNumber;
    String userName;
    String password;
    static HashSet<Item> itemSet = new HashSet<>();
    static ArrayList<Customer> customersList = new ArrayList<>();
    static ArrayList<DeliveryMan> deliveryManList = new ArrayList<>();
    static int deliveryManCount = 0;
    static synchronized void assignWork(Customer customer) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering assignWork method ");
        for(int i=0;i<Admin.getDeliveryManList().size();i++) {
            if(i==deliveryManCount) {
                customer.getCart().setStatus(CartStatus.YET_TO_DELIVER);
                customer.getCart().setOrderDate(java.time.LocalDate.now());
                customer.setDeliveryManPhoneNumber(Admin.getDeliveryManList().get(i).getMobileNumber());
                customer.getCart().setDeliveryMan(Admin.getDeliveryManList().get(i));
                Admin.getDeliveryManList().get(i).setCustomerPhoneNumber(customer.getMobileNumber());
                Admin.getDeliveryManList().get(i).getOrder().add(customer.getCart());
                //customer.getHistory().add( "\n" + java.time.LocalDate.now() + "--> "+customer.getCart().toString());
                customer.getHistory().add( "\n" + java.time.LocalDate.now() + "--> "+ customer.getCart());
                try {
                    PreparedStatement ps = Main.dbConnection.prepareStatement("update Customer_Details set history=? where mobileNumber=?");
                    ps.setString(1,(customer.getHistory().toString().replace('[',' ')).replace(']',' ').replace("null",""));
                    ps.setString(2, customer.getMobileNumber());
                    ps.executeUpdate();
                }
                catch(Exception ex) {
                    Main.log.log(Level.DEBUG, LocalDateTime.now() + " Exception " + ex);
                    ex.getMessage();
                }
                ++deliveryManCount;
                if(deliveryManCount==Admin.getDeliveryManList().size()) {
                    deliveryManCount = 0;
                }
                break;
            }
        }
    }

    public static ArrayList<DeliveryMan> getDeliveryManList() {
        return deliveryManList;
    }

    public static void setDeliveryManList(ArrayList<DeliveryMan> deliveryManList) {
        Admin.deliveryManList = deliveryManList;
    }

    public Admin(String firstName, String lastName, String mobileNumber, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        this.password = password;
    }

    void stockList() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering stockList method ");
        for(Item item : Admin.itemSet) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println(item.getCategory());
            Set set = item.getMap().keySet();
            for(Object str : set) {
                String dash ="";
                for(int z=0;z<((String)str).length();z++) {
                    dash+="-";
                }
                System.out.println("---------------------------------------------------------------------");
                System.out.println(str);
                System.out.println(dash);
                ArrayList al = (ArrayList) item.getMap().get((String)str);
                for(Object pdt : al) {
                    System.out.println((Product)pdt);
                }
            }
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println();
    }

    Item getCurrentItem(Category category) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering getCurrentItem method");
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
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering selectType method");
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

    void showCustomerList() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showCustomerList method");
        System.out.println("-------------CUSTOMER LIST-------------------");
        for(Customer customer : Admin.getCustomersList()) {
            System.out.println(customer + "  -  " + customer.getMobileNumber());
        }
        System.out.println("---------------------------------------------");
        System.out.println();
    }

    void showDeliveryManList() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showDeliveryManList method ");
        System.out.println("-------------DELIVERY MAN LIST-------------------");
        for(DeliveryMan deliveryMan : Admin.getDeliveryManList()) {
            System.out.println(deliveryMan.getFirstName() + " " + deliveryMan.getLastName() + "  -  " + deliveryMan.getMobileNumber());
        }
        System.out.println("-------------------------------------------------");
        System.out.println();
    }

    Product normalProduct(String type) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering normalProduct Method ");
        int productQuantity = 50;
        Category productCategory = Category.GROCERY;
        String productName = UserInput.productNameInput();
        int productPrice = UserInput.productPriceInput();
        Product grocery = new Product(productName,productCategory,productPrice,productQuantity);
        try {
            PreparedStatement productDetail = Main.dbConnection.prepareStatement("insert into Product_Details values(?,NULL,?,?,?,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,?)");
            productDetail.setString(1,productCategory.toString());
            productDetail.setString(2,productName);
            productDetail.setInt(3,productPrice);
            productDetail.setInt(4,productQuantity);
            productDetail.setString(5,type);
            productDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return grocery;
    }

    WiredProduct wiredProduct(String type) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering wiredProduct method ");
        int productQuantity = 50;
        Category productCategory = Category.ELECTRONICS;
        ElectronicType electronicType = ElectronicType.WIRED;
        String productName = UserInput.productNameInput();
        int productPrice = UserInput.productPriceInput();
        String brand = UserInput.brandInput();
        String model = UserInput.electronicModelInput();
        WiredProduct wp = new WiredProduct(productName,productCategory,productPrice,productQuantity,brand,model,electronicType);
        try {
            PreparedStatement productDetail = Main.dbConnection.prepareStatement("insert into Product_Details values(?,?,?,?,?,?,?,NULL,NULL,NULL,NULL,NULL,NULL,NULL,?)");
            productDetail.setString(1,productCategory.toString());
            productDetail.setString(2,electronicType.toString());
            productDetail.setString(3,productName);
            productDetail.setInt(4,productPrice);
            productDetail.setInt(5,productQuantity);
            productDetail.setString(6,brand);
            productDetail.setString(7,model);
            productDetail.setString(8,type);
            productDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return wp;
    }

    WirelessProduct wirelessProduct(String type) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering wirelessProduct method ");
        int productQuantity = 50;
        Category productCategory = Category.ELECTRONICS;
        ElectronicType electronicType = ElectronicType.WIRELESS;
        String productName = UserInput.productNameInput();
        int productPrice = UserInput.productPriceInput();
        String brand = UserInput.brandInput();
        String model = UserInput.electronicModelInput();
        int battery = UserInput.batteryCapacity();
        WirelessProduct wp = new WirelessProduct(productName,productCategory,productPrice,productQuantity,brand,model,battery,electronicType);
        try {
            PreparedStatement productDetail = Main.dbConnection.prepareStatement("insert into Product_Details values(?,?,?,?,?,?,?,?,NULL,NULL,NULL,NULL,NULL,NULL,?)");
            productDetail.setString(1,productCategory.toString());
            productDetail.setString(2,electronicType.toString());
            productDetail.setString(3,productName);
            productDetail.setInt(4,productPrice);
            productDetail.setInt(5,productQuantity);
            productDetail.setString(6,brand);
            productDetail.setString(7,model);
            productDetail.setInt(8,battery);
            productDetail.setString(9,type);
            productDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return wp;
    }

    PortableElectronicProduct portableElectronicProduct(String type) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering portableElectronicProduct method");
        int productQuantity = 50;
        Category productCategory = Category.ELECTRONICS;
        ElectronicType electronicType = ElectronicType.PORTABLE;
        String productName = UserInput.productNameInput();
        int productPrice = UserInput.productPriceInput();
        String brand = UserInput.brandInput();
        String model = UserInput.electronicModelInput();
        int battery = UserInput.batteryCapacity();
        int ram = UserInput.ramInput();
        int storage = UserInput.storageInput();
        int camera = UserInput.cameraInput();
        float screenSize = UserInput.screenSizeInput();
        PortableElectronicProduct pep = new PortableElectronicProduct(productName,productCategory,productPrice,productQuantity,brand,model,battery,ram,storage,screenSize,camera,electronicType);
        try {
            PreparedStatement productDetail = Main.dbConnection.prepareStatement("insert into Product_Details values(?,?,?,?,?,?,?,?,?,?,?,?,NULL,NULL,?)");
            productDetail.setString(1,productCategory.toString());
            productDetail.setString(2,electronicType.toString());
            productDetail.setString(3,productName);
            productDetail.setInt(4,productPrice);
            productDetail.setInt(5,productQuantity);
            productDetail.setString(6,brand);
            productDetail.setString(7,model);
            productDetail.setInt(8,battery);
            productDetail.setInt(9,ram);
            productDetail.setInt(10,storage);
            productDetail.setInt(11,camera);
            productDetail.setString(12,String.valueOf(screenSize));
            productDetail.setString(13,type);
            productDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return pep;
    }

    FashionProduct fashionProduct(String type) {
        int productQuantity = 50;
        Category productCategory = Category.FASHION;
        String productName = UserInput.productNameInput();
        int productPrice = UserInput.productPriceInput();
        int size = UserInput.sizeInput();
        String brand = UserInput.brandInput();
        FashionProduct fashionProduct = new FashionProduct(productName,productCategory,productPrice,productQuantity,size,brand);
        try {
            PreparedStatement productDetail = Main.dbConnection.prepareStatement("insert into Product_Details values(?,NULL,?,?,?,?,NULL,NULL,NULL,NULL,NULL,NULL,?,NULL,?)");
            productDetail.setString(1,productCategory.toString());
            productDetail.setString(2,productName);
            productDetail.setInt(3,productPrice);
            productDetail.setInt(4,productQuantity);
            productDetail.setString(5,brand);
            productDetail.setInt(6,size);
            productDetail.setString(7,type);
            productDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return fashionProduct;
    }

    ApplianceProduct applianceProduct(String type) {
        int productQuantity = 50;
        Category productCategory = Category.APPLIANCE;
        String productName = UserInput.productNameInput();
        int productPrice = UserInput.productPriceInput();
        int warranty = UserInput.warrantyInput();
        String brand = UserInput.brandInput();
        ApplianceProduct applianceProduct = new ApplianceProduct(productName,productCategory,productPrice,productQuantity,brand,warranty);
        try {
            PreparedStatement productDetail = Main.dbConnection.prepareStatement("insert into Product_Details values(?,NULL,?,?,?,?,NULL,NULL,NULL,NULL,NULL,NULL,NULL,?,?)");
            productDetail.setString(1,productCategory.toString());
            productDetail.setString(2,productName);
            productDetail.setInt(3,productPrice);
            productDetail.setInt(4,productQuantity);
            productDetail.setString(5,brand);
            productDetail.setInt(5,warranty);
            productDetail.setString(6,type);
            productDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return applianceProduct;
    }

    static Customer customerSignUp() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering customerSignup method ");
        String firstName = UserInput.firstNameInput();
        String lastName = UserInput.lastNameInput();
        String mobileNumber = UserInput.customerMobileNumberInput();
        String userName = UserInput.userNameInput();
        String password = UserInput.passwordInput();
        Customer customer = new Customer(firstName,lastName,mobileNumber,userName,password);
        try {
            PreparedStatement customerDetail = Main.dbConnection.prepareStatement("insert into Customer_Details values(?,?,?,?,?)");
            customerDetail.setString(1,firstName);
            customerDetail.setString(2,lastName);
            customerDetail.setString(3,mobileNumber);
            customerDetail.setString(4,userName);
            customerDetail.setString(5,password);
            customerDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return customer;
    }

    static DeliveryMan deliveryManSignUp() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering deliveryManSignUp method ");
        String firstName = UserInput.firstNameInput();
        String lastName = UserInput.lastNameInput();
        String mobileNumber = UserInput.deliveryManMobileNumberInput();
        String userName = UserInput.userNameInput();
        String password = UserInput.passwordInput();
        DeliveryMan deliveryMan = new DeliveryMan(firstName,lastName,mobileNumber,userName,password);
        try {
            PreparedStatement deliveryManDetail = Main.dbConnection.prepareStatement("insert into Delivery_Man_Details values(?,?,?,?,?)");
            deliveryManDetail.setString(1,firstName);
            deliveryManDetail.setString(2,lastName);
            deliveryManDetail.setString(3,mobileNumber);
            deliveryManDetail.setString(4,userName);
            deliveryManDetail.setString(5,password);
            deliveryManDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return deliveryMan;
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

    public static HashSet<Item> getItemSet() {
        return itemSet;
    }

    public static void setItemSet(HashSet<Item> itemSet) {
        Admin.itemSet = itemSet;
    }

    public static ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public static void setCustomersList(ArrayList<Customer> customersList) {
        Admin.customersList = customersList;
    }

}
