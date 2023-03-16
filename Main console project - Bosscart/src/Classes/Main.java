package Classes;
import com.sun.xml.internal.ws.resources.WsservletMessages;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import sun.net.httpserver.HttpServerImpl;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static Logger log = Logger.getLogger(Main.class.getName());
    static ArrayList<Admin> adminList = new ArrayList<>();
    public static Connection dbConnection = null;

    static {
        try {
            getDBConnection();
            if(dbConnection == null) {
                System.out.println("FATAL: Please check Data Base Connection Issue");
                System.exit(0);
            }
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Customer_Details");
            while (rs.next()) {
                Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                customer.getHistory().add(rs.getString(6));
                Admin.getCustomersList().add(customer);
            }
            ResultSet rs1 = stmt.executeQuery("select * from Delivery_Man_Details");
            while(rs1.next()) {
                DeliveryMan deliveryMan = new DeliveryMan(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5));
                deliveryMan.getHistory().add(rs1.getString(6));
                Admin.getDeliveryManList().add(deliveryMan);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < Category.values().length; i++) {
            Item item = new Item();
            item.setCategory(Category.values()[i]);
            ResultSet rs;
            try {
                Statement stmt = dbConnection.createStatement();
                rs = stmt.executeQuery("select * from Type_Name");
                while (rs.next()) {
                    if(item.getCategory().equals(Category.valueOf(rs.getString(1)))) {
                        item.getMap().put(rs.getString(2),new ArrayList());
                    }
                }
                rs = stmt.executeQuery("select * from Product_Details");
                while(rs.next()) {
                    if(item.getCategory().equals(Category.valueOf(rs.getString(1)))) {
                        switch (item.getCategory()) {
                            case STATIONERY:
                            case GROCERY:
                                item.addNewProduct(rs.getString(15), new Product(rs.getString(3),Category.valueOf(rs.getString(1)), rs.getInt(4), rs.getInt(5)));
                                break;
                            case ELECTRONICS:
                                switch(ElectronicType.valueOf(rs.getString(2))) {
                                    case WIRED:
                                        item.addNewProduct(rs.getString(15), new WiredProduct(rs.getString(3),Category.valueOf(rs.getString(1)),rs.getInt(4),rs.getInt(5),rs.getString(6), rs.getString(7),ElectronicType.WIRED));
                                        break;
                                    case WIRELESS:
                                        item.addNewProduct(rs.getString(15), new WirelessProduct(rs.getString(3), Category.valueOf(rs.getString(1)), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), ElectronicType.WIRELESS));
                                        break;
                                    case PORTABLE:
                                        item.addNewProduct(rs.getString(15), new PortableElectronicProduct(rs.getString(3), Category.valueOf(rs.getString(1)), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), Float.valueOf(rs.getString(12)), rs.getInt(11), ElectronicType.PORTABLE));
                                        break;
                                }
                                break;
                            case FASHION:
                                item.addNewProduct(rs.getString(15), new FashionProduct(rs.getString(3), Category.valueOf(rs.getString(1)), rs.getInt(4), rs.getInt(5), rs.getInt(13), rs.getString(6)));
                                break;
                            case APPLIANCE:
                                item.addNewProduct(rs.getString(15), new ApplianceProduct(rs.getString(3), Category.valueOf(rs.getString(1)), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(14)));
                                break;
                        }
                    }
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
//            if (item.getCategory() == Category.GROCERY) {
//                item.addNewType("Dairy");
//                item.addNewProduct("Dairy", new Product("Milk", item.getCategory(), 40, 50));
//                item.addNewProduct("Dairy", new Product("Butter", item.getCategory(), 50, 50));
//                item.addNewType("Snacks");
//                item.addNewProduct("Snacks", new Product("Chips", item.getCategory(), 20, 50));
//                item.addNewProduct("Snacks", new Product("Biscuit", item.getCategory(), 10, 50));
//            } else if (item.getCategory() == Category.STATIONERY) {
//                item.addNewType("Maths");
//                item.addNewProduct("Maths", new Product("Scale", item.getCategory(), 10, 50));
//                item.addNewProduct("Maths", new Product("Table", item.getCategory(), 15, 50));
//                item.addNewType("Science");
//                item.addNewProduct("Science", new Product("Guide", item.getCategory(), 200, 50));
//                item.addNewProduct("Science", new Product("Litmas paper", item.getCategory(), 45, 50));
//            } else if (item.getCategory() == Category.FASHION) {
//                item.addNewType("Men");
//                item.addNewProduct("Men", new FashionProduct("Shirt", item.getCategory(), 1500, 50, 32, "Levi"));
//                item.addNewProduct("Men", new FashionProduct("Vesti", item.getCategory(), 500, 50, 30, "Ramraj"));
//                item.addNewType("Women");
//                item.addNewProduct("Women", new FashionProduct("Saree", item.getCategory(), 5000, 50, 30, "Samuthrika pattu"));
//                item.addNewProduct("Women", new FashionProduct("Sudidhar", item.getCategory(), 1500, 50, 32, "Levi"));
//            } else if (item.getCategory() == Category.APPLIANCE) {
//                item.addNewType("Home");
//                item.addNewProduct("Home", new ApplianceProduct("TV stand", item.getCategory(), 200, 50, "Samsung", 3));
//                item.addNewProduct("Home", new ApplianceProduct("Cot", item.getCategory(), 2000, 50, "Samsung", 5));
//                item.addNewType("Office");
//                item.addNewProduct("Office", new ApplianceProduct("Chair", item.getCategory(), 2000, 50, "Samsung", 3));
//                item.addNewProduct("Office", new ApplianceProduct("Table", item.getCategory(), 2000, 50, "Samsung", 3));
//            } else if (item.getCategory() == Category.ELECTRONICS) {
//                item.addNewType("Mobile");
//                item.addNewProduct("Mobile", new PortableElectronicProduct("Nokia 6.1 plus", item.getCategory(), 18000, 50, "Nokia", "6.1 plus", 3200, 4, 64, (float) 5.4, 18, ElectronicType.PORTABLE));
//                item.addNewProduct("Mobile", new PortableElectronicProduct("Realme 10 pro", item.getCategory(), 20000, 50, "Realme", "10 pro", 4800, 6, 128, (float) 6.2, 128, ElectronicType.PORTABLE));
//                item.addNewType("Earpod");
//                item.addNewProduct("Earpod", new WirelessProduct("Oppo new 2", item.getCategory(), 2500, 50, "Oppo", "New 2", 1500, ElectronicType.WIRELESS));
//                item.addNewProduct("Earpod", new WirelessProduct("Boat a6000", item.getCategory(), 1500, 50, "Boat", "a6000", 1200, ElectronicType.WIRELESS));
//            }
            Admin.itemSet.add(item);
        }
        adminList.add(new Admin("Sadhu", "Sadhu", "9003759598", "Sadhu", "17"));
        System.out.println();
        System.out.println("                                        Welcome to Boss-Cart                          ");
        System.out.println();
    }

    public static void main(String[] args) {
        log.log(Level.DEBUG, LocalDateTime.now() + " Entering main method");
        String firstChoice = UserInput.adminOrCustomerInput();
        log.log(Level.DEBUG, LocalDateTime.now() + " User role " + firstChoice);
        switch (firstChoice) {
            case "admin":
                String mobileNumber = UserInput.loginNumberInput();
                log.log(Level.DEBUG, LocalDateTime.now() + " admin's mobile number " + mobileNumber);
                Admin adminAcc = (Admin) UserInput.accountFind(firstChoice, mobileNumber);
                String passwordd = UserInput.loginPasswordInput();
                log.log(Level.DEBUG, LocalDateTime.now() + " admin's password " + passwordd);
                if (passwordd.equals(adminAcc.getPassword())) {
                    adminFlow(adminAcc);
                }
                else {
                    log.log(Level.DEBUG, LocalDateTime.now() + " Incorrect password ");
                    System.out.println("Incorrect password");
                    Main.main(new String[]{});
                }
                break;
            case "customer":
                String customerFirstChoice = UserInput.signinOrSignup();
                log.log(Level.DEBUG, LocalDateTime.now() + " Customer's choice " + customerFirstChoice);
                Customer customer;
                switch (customerFirstChoice) {
                    case "signin":
                        String mobNumber = UserInput.loginNumberInput();
                        log.log(Level.DEBUG, LocalDateTime.now() + " Customer mobile number " + mobNumber);
                        customer = (Customer) UserInput.accountFind(firstChoice, mobNumber);
                        String password = UserInput.loginPasswordInput();
                        log.log(Level.DEBUG, LocalDateTime.now() + " Customer password " + password);
                        if (password.equals(customer.getPassword())) {
                            customerFlow(customer);
                        }
                        else {
                            log.log(Level.DEBUG, LocalDateTime.now() + " Incorrect password ");
                            System.out.println("Incorrect password");
                            Main.main(new String[]{});
                        }
                        break;
                    case "signup":
                        customer = Admin.customerSignUp();
                        Admin.getCustomersList().add(customer);
                        customerFlow(customer);
                        break;
                }
                break;
            case "deliveryman":
                DeliveryMan deliveryMan;
                String mobNumber = UserInput.loginNumberInput();
                log.log(Level.DEBUG, LocalDateTime.now() + " Delivery man mobile number " + mobNumber);
                deliveryMan = (DeliveryMan) UserInput.accountFind(firstChoice, mobNumber);
                String password = UserInput.loginPasswordInput();
                log.log(Level.DEBUG, LocalDateTime.now() + " Delivery man password " + password);
                if (password.equals(deliveryMan.getPassword())) {
                    System.out.println("Welcome @" + deliveryMan.getUserName());
                    deliveryManFlow(deliveryMan);
                }
                else {
                    log.log(Level.DEBUG, LocalDateTime.now() + " Incorrect password ");
                    System.out.println("Incorrect password");
                    Main.main(new String[]{});
                }
        }
    }

    public static void getDBConnection() {
        if (dbConnection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bosscart", "sadhu", "sadhu17");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static void adminFlow(Admin adminAcc) {
        log.log(Level.DEBUG, LocalDateTime.now() + " Entering adminFlow method ");
        int typeChoice;
        String type;
        Product product;
        Category category;
        String userType = UserInput.adminFirstChoice();
        log.log(Level.DEBUG, LocalDateTime.now() + " admin's first choice " + userType);
        Item currentItem;
        switch (userType) {
            case "viewstock":
                adminAcc.stockList();
                break;
            case "fillproduct":
                category = UserInput.productCategoryInput();
                log.log(Level.DEBUG, LocalDateTime.now() + " Product category " + category);
                if(category==null) {
                    adminFlow(adminAcc);
                }
                currentItem = adminAcc.getCurrentItem(category);
                log.log(Level.DEBUG, LocalDateTime.now() + " Item category " + currentItem.getCategory());
                typeChoice = UserInput.typeChoice(currentItem);
                type = adminAcc.selectTypeName(currentItem, typeChoice);
                log.log(Level.DEBUG, LocalDateTime.now() + " Type " + type);
                ArrayList al = (ArrayList) currentItem.getMap().get(type);
                log.log(Level.DEBUG, LocalDateTime.now() + " Products " + al);
                Product pr = (Product) UserInput.fillProduct(al);
                UserInput.setQuantity(pr);
                break;
            case "addnew":
                String typeOrProduct = UserInput.addNewTypeOrNewProduct();
                log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + typeOrProduct);
                if(typeOrProduct.equals("back")) {
                    adminFlow(adminAcc);
                }
                category = UserInput.productCategoryInput();
                log.log(Level.DEBUG, LocalDateTime.now() + " Category " + category);
                if(category==null) {
                    adminFlow(adminAcc);
                }
                currentItem = adminAcc.getCurrentItem(category);
                log.log(Level.DEBUG, LocalDateTime.now() + " Item " + currentItem);
                switch (typeOrProduct) {
                    case "newType":
                        String typeName = UserInput.addNewTypeInput();
                        log.log(Level.DEBUG, LocalDateTime.now() + " Type name " + typeName);
                        currentItem.addNewType(typeName);
                        break;
                    case "newProduct":
                        typeChoice = UserInput.typeChoice(currentItem);
                        type = adminAcc.selectTypeName(currentItem, typeChoice);
                        log.log(Level.DEBUG, LocalDateTime.now() + " Type " + type);
                        switch (category) {
                            case GROCERY:
                            case STATIONERY:
                                product = adminAcc.normalProduct(type);
                                currentItem.addNewProduct(type, product);
                                break;
                            case ELECTRONICS:
                                ElectronicType et = UserInput.electronicTypeChoice();
                                switch (et) {
                                    case WIRED:
                                        product = adminAcc.wiredProduct(type);
                                        currentItem.addNewProduct(type, product);
                                        break;
                                    case WIRELESS:
                                        product = adminAcc.wirelessProduct(type);
                                        currentItem.addNewProduct(type, product);
                                        break;
                                    case PORTABLE:
                                        product = adminAcc.portableElectronicProduct(type);
                                        currentItem.addNewProduct(type, product);
                                        break;
                                    case ACCESSORY:
                                        product = adminAcc.normalProduct(type);
                                        currentItem.addNewProduct(type, product);
                                        break;
                                }
                                break;
                            case FASHION:
                                product = adminAcc.fashionProduct(type);
                                currentItem.addNewProduct(type, product);
                                break;
                            case APPLIANCE:
                                product = adminAcc.applianceProduct(type);
                                currentItem.addNewProduct(type, product);
                                break;
                        }
                        break;
                }
                break;
            case "customerlist":
                adminAcc.showCustomerList();
                break;
            case "deliverymanlist":
                adminAcc.showDeliveryManList();
                break;
            case "adddeliveryman":
                DeliveryMan deliveryMan = Admin.deliveryManSignUp();
                Admin.getDeliveryManList().add(deliveryMan);
                break;
            case "home":
                Main.main(new String[]{});
                break;
        }
        String choice = UserInput.homeOrBack();
        switch(choice) {
            case "back":
                adminFlow(adminAcc);
                break;
            case "home":
                Main.main(new String[]{});
        }
    }

    static void customerFlow(Customer customer) {
        log.log(Level.DEBUG, LocalDateTime.now() + " Entering customerFlow method ");
        int typeChoice;
        String type;
        Category category;
        Item currentItem;
        System.out.println("Welcome @" + customer.getUserName());
        String customerChoice = UserInput.customerOption();
        log.log(Level.DEBUG, LocalDateTime.now() + " Customer's choice " + customerChoice);
        switch (customerChoice) {
            case "buy":
                while (true) {
                    category = UserInput.productCategoryInput();
                    log.log(Level.DEBUG, LocalDateTime.now() + " Category " + category);
                    if(category==null) {
                        customerFlow(customer);
                    }
                    currentItem = customer.getCurrentItem(category);
                    log.log(Level.DEBUG, LocalDateTime.now() + " Item's category " + currentItem.getCategory());
                    typeChoice = UserInput.typeChoice(currentItem);
                    type = customer.selectTypeName(currentItem, typeChoice);
                    ArrayList al = (ArrayList) currentItem.getMap().get(type);
                    log.log(Level.DEBUG, LocalDateTime.now() + " Type " + type);
                    String sortType = UserInput.sortType();
                    log.log(Level.DEBUG, LocalDateTime.now() + " Sort type " + sortType);
                    switch (sortType) {
                        case "name":
                            Collections.sort(al, new NameComparator());
                            break;
                        case "max-min":
                            Collections.sort(al, new MaxToMinComparator());
                            break;
                        case "min-max":
                            Collections.sort(al, new MinToMaxComparator());
                    }
                    int productChoice = UserInput.showProducts(al);
                    String cartOrWishlist = UserInput.cartOrWishlist();
                    log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + cartOrWishlist);
                    Product buyProduct = (Product) al.get(productChoice - 1);
                    log.log(Level.DEBUG, LocalDateTime.now() + " Product " + buyProduct);
                    switch (cartOrWishlist) {
                        case "cart":
                            try {
                                UserInput.purchase(buyProduct, customer);
                            } catch (Exception ex) {
                                log.log(Level.DEBUG, LocalDateTime.now() + " Exception " + ex);
                                ex.getMessage();
                            }
                            break;
                        case "wishlist":
                            customer.addWishlist(buyProduct);
                            break;
                    }
                    String yesOrNo = UserInput.continueOrNot();
                    log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + yesOrNo);
                    if (yesOrNo.equals("no")) {
                        break;
                    }
                }
                break;
            case "cart":
                customer.showCartList();
                String buyChoice = UserInput.buy(customer);
                log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + buyChoice);
                switch (buyChoice) {
                    case "buy":
                        Admin.assignWork(customer);
                        System.out.println("Delivery man contact number : " + customer.getDeliveryManPhoneNumber());
                        customer.setCart(new Cart());
                        break;
                    case "back":
                    case "empty":
                        customerFlow(customer);
                        break;
                }
                break;
            case "wishlist":
                customer.showWishList();
                break;
            case "history":
                customer.showHistory();
                break;
            case "home":
                Main.main(new String[]{});
                break;
        }
        String choice = UserInput.homeOrBack();
        log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + choice);
        switch(choice) {
            case "back":
                customerFlow(customer);
                break;
            case "home":
                Main.main(new String[]{});
        }
    }

    static void deliveryManFlow(DeliveryMan deliveryMan) {
        log.log(Level.DEBUG, LocalDateTime.now() + " Entering deliveryManFlow method ");
        String choice = UserInput.deliveryManOption();
        log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + choice);
        switch (choice) {
            case "work":
                Cart cart = UserInput.showWork(deliveryMan.getOrder());
                if(cart==null) {
                    deliveryManFlow(deliveryMan);
                }
                String deliveryManChoice = UserInput.deliverOrNot();
                log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + deliveryManChoice);
                switch(deliveryManChoice) {
                    case "deliver":
                        System.out.println("Successfully delivered");
                        cart.setStatus(CartStatus.DELIVERED);
                        cart.setDeliveryDate(java.time.LocalDate.now());
                        deliveryMan.getHistory().add( "\n" + java.time.LocalDate.now() + "--> "+ cart.toString());
                        try {
                            PreparedStatement ps = Main.dbConnection.prepareStatement("update Delivery_Man_Details set history=? where mobileNumber=?");
                            ps.setString(1,(deliveryMan.getHistory().toString().replace('[',' ')).replace(']',' ').replace("null",""));
                            ps.setString(2, deliveryMan.getMobileNumber());
                            ps.executeUpdate();
                        }
                        catch(Exception ex) {
                            log.log(Level.DEBUG, LocalDateTime.now() + " Exception " + ex);
                            ex.getMessage();
                        }
                        deliveryMan.getCurrentOrder().remove(cart);
                        break;
                    case "back":
                        deliveryManFlow(deliveryMan);
                        break;
                }
                break;
            case "history":
                deliveryMan.showHistory();
                break;
            case "home":
                Main.main(new String[]{});
                break;
        }
        String choicee = UserInput.homeOrBack();
        log.log(Level.DEBUG, LocalDateTime.now() + " Choice " + choicee);
        switch(choicee) {
            case "back":
                deliveryManFlow(deliveryMan);
                break;
            case "home":
                Main.main(new String[]{});
        }
    }
}