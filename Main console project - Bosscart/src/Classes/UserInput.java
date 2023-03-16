package Classes;

import org.apache.log4j.Level;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInput implements Cloneable {
    static Scanner scanner = new Scanner(System.in);

    static String adminOrCustomerInput() {
        System.out.println("------------------Home Page----------------");
        System.out.println();
        System.out.println("1-->Admin , 2-->Customer , 3-->Delivery man");
        String choice = scanner.next();
        String returnAns = null;
        scanner.nextLine();
        switch (choice) {
            case "1":
                returnAns = "admin";
                break;
            case "2":
                returnAns = "customer";
                break;
            case "3":
                returnAns = "deliveryman";
                break;
            default:
                System.out.println("Please enter a valid input");
                return  adminOrCustomerInput();
        }
        return returnAns;
    }

    static String addNewTypeInput() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering addNewTypeInput method ");
        System.out.println("Enter the new type name :");
        String name = scanner.nextLine();
        if(name.equals("")) {
            System.out.println("Please enter valid input");
            return addNewTypeInput();
        }
        return name;
    }

    static String productNameInput() {
        System.out.println("Enter the product name :");
        String name = scanner.nextLine();
        if(name.equals("")) {
            System.out.println("Please enter valid input");
            return productNameInput();
        }
        return name;
    }

    static int productPriceInput() {
        System.out.println("Enter the product price :");
        int price = 0;
        try {
            price = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return productPriceInput();
        }
        return price;
    }

    static Category productCategoryInput() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering productCategoryInput method");
        System.out.println("1-->Grocery , 2-->Electronics , 3-->Fashion , 4-->Appliance , 5-->Stationery , 6-->Back");
        String choice = scanner.next();
        Category returnAns = null;
        scanner.nextLine();
        switch (choice) {
            case "1":
                returnAns = Category.GROCERY;
                break;
            case "2":
                returnAns = Category.ELECTRONICS;
                break;
            case "3":
                returnAns = Category.FASHION;
                break;
            case "4":
                returnAns = Category.APPLIANCE;
                break;
            case "5":
                returnAns = Category.STATIONERY;
                break;
            case "6":
                returnAns = null;
                break;
            default:
                System.out.println("Please enter valid input");
                return productCategoryInput();
        }
        return returnAns;
    }

    static String addNewTypeOrNewProduct() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering addNewTypeOrNewProduct method");
        System.out.println("1-->New Type , 2-->New Product , 3-->Back");
        String choice = scanner.next();
        String returnAns = null;
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "newType";
                break;
            case "2":
                returnAns = "newProduct";
                break;
            case "3":
                returnAns = "back";
                break;
            default:
                System.out.println("Please enter valid input");
                return addNewTypeOrNewProduct();
        }
        return returnAns;
    }

    static String adminFirstChoice() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering adminFirstChoice method ");
        System.out.println("1--> View stock , 2--> Fill product , 3-->Add new type or add new product , 4-->Customer's list , 5-->Add Delivery Man , 6-->Delivery man list , 7-->Home");
        String returnAns = null;
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "viewstock";
                break;
            case "2":
                returnAns = "fillproduct";
                break;
            case "3":
                returnAns = "addnew";
                break;
            case "4":
                returnAns = "customerlist";
                break;
            case "5":
                returnAns = "adddeliveryman";
                break;
            case "6":
                returnAns = "deliverymanlist";
                break;
            case "7":
                returnAns = "home";
                break;
            default:
                System.out.println("Please enter valid input");
                return adminFirstChoice();
        }
        return returnAns;
    }

    static int typeChoice(Item currentItem) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering typeChoice method");
        String str  = "";
        int count = 1;
        for(Object string : currentItem.getMap().keySet()) {
            str += " , " + count + "--> " + string;
            ++count;
        }
        try {
            System.out.println(str.substring(3));
        }
        catch (Exception ex) {
            System.out.println("Please add one type then add new product..");
            Main.main(new String[]{});
        }
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return typeChoice(currentItem);
        }
        if((choice < 1) && (choice >= count)) {
            System.out.println("Please enter valid input");
            return typeChoice(currentItem);
        }
        return choice;
    }

    static String brandInput() {
        System.out.println("Enter the brand name :");
        String brand = scanner.nextLine();
        if(brand.equals("")) {
            System.out.println("Please enter valid input");
            return brandInput();
        }
        return brand;
    }

    static String electronicModelInput() {
        System.out.println("Enter the model name :");
        String model = scanner.nextLine();
        if(model.equals("")) {
            System.out.println("Please enter valid input");
            return electronicModelInput();
        }
        return model;
    }

    static int batteryCapacity() {
        System.out.println("Enter a battery capacity :");
        int battery;
        try {
            battery = scanner.nextInt();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return batteryCapacity();
        }
        scanner.nextLine();
        return battery;
    }

    static int ramInput() {
        System.out.println("Enter the RAM size :");
        int ram;
        try {
            ram = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return ramInput();
        }
        return ram;
    }

    static int storageInput() {
        System.out.println("Enter the storage size :");
        int storage;
        try {
            storage = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return storageInput();
        }
        return storage;
    }

    static int cameraInput() {
        System.out.println("Enter the camera size :");
        int camera;
        try {
            camera = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return cameraInput();
        }
        return camera;
    }

    static float screenSizeInput() {
        System.out.println("Enter the screen size :");
        float screen;
        try {
            screen = scanner.nextFloat();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return screenSizeInput();
        }
        return screen;
    }

    static ElectronicType electronicTypeChoice() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering electronicTypeChoice method ");
        System.out.println("1-->Wired electronic , 2-->Wireless electronic , 3-->Mobile or laptop , 4-->Accessories");
        ElectronicType returnAns = null;
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = ElectronicType.WIRED;
                break;
            case "2":
                returnAns = ElectronicType.WIRELESS;
                break;
            case "3":
                returnAns = ElectronicType.PORTABLE;
                break;
            case "4":
                returnAns = ElectronicType.ACCESSORY;
                break;
            default:
                System.out.println("Please enter valid input");
                return electronicTypeChoice();
        }
        return returnAns;
    }

    static int sizeInput() {
        System.out.println("Enter the size :");
        int size;
        try {
            size = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return sizeInput();
        }
        return size;
    }

    static int warrantyInput() {
        System.out.println("Enter the warranty :");
        int warranty;
        try {
            warranty = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return sizeInput();
        }
        return warranty;
    }

    static Object fillProduct(ArrayList al) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering fillProduct method");
        String str  = "";
        int count = 1;
        for(Object product : al) {
            str += " , " + count + "--> " + ((Product)product).getName() + " : " + ((Product)product).getQuantity();
            ++count;
        }
        try {
            System.out.println(str.substring(3));
        }
        catch (Exception ex) {
            System.out.println("Please add one type, then add new product..");
            Main.main(new String[]{});
        }
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return fillProduct(al);
        }
        if((choice < 1) && (choice >= count)) {
            System.out.println("Please enter valid input");
            return fillProduct(al);
        }
        return al.get(choice-1);
    }

    static void setQuantity(Product product) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering setQuantity method");
        System.out.println("Enter filling product amount :");
        int ans = 0;
        try {
            ans = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            setQuantity(product);
        }
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Filling count " + ans);
        try {
            PreparedStatement ps = Main.dbConnection.prepareStatement("update Product_Details set quantity=? where name=?");
            ps.setInt(1,product.getQuantity()+ans);
            ps.setString(2, product.getName());
            ps.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " New quantity " + ans + product.getQuantity());
        product.setQuantity( ans + product.getQuantity());
        System.out.println("Successfully added");
        System.out.println();
    }

    static String firstNameInput() {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        System.out.println("Enter your first name :");
        String firstName = scanner.next().toLowerCase();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(firstName);
        if(!matcher.matches()) {
            System.out.println("Please enter valid first name");
            return firstNameInput();
        }
        return firstName.toLowerCase();
    }

    static String lastNameInput() {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        System.out.println("Enter your last name :");
        String lastName = scanner.next().toLowerCase();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(lastName);
        if(!matcher.matches()) {
            System.out.println("Please enter valid last name");
            return lastNameInput();
        }
        return lastName.toLowerCase();
    }

    static String customerMobileNumberInput() {
        Pattern pattern = Pattern.compile("[7-9][0-9]{9}");
        System.out.println("Enter your mobile number :");
        String mobileNumber = scanner.next();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(mobileNumber);
        if(!matcher.matches()) {
            System.out.println("Please enter valid mobile number");
            return customerMobileNumberInput();
        }
        for(Customer acc : Admin.getCustomersList()) {
            if(acc.getMobileNumber().equals(mobileNumber)) {
                System.out.println("This number is already exist");
                return customerMobileNumberInput();
            }
        }
        return mobileNumber;
    }

    static String deliveryManMobileNumberInput() {
        Pattern pattern = Pattern.compile("[7-9][0-9]{9}");
        System.out.println("Enter your mobile number :");
        String mobileNumber = scanner.next();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(mobileNumber);
        if(!matcher.matches()) {
            System.out.println("Please enter valid mobile number");
            return deliveryManMobileNumberInput();
        }
        for(DeliveryMan acc : Admin.getDeliveryManList()) {
            if(acc.getMobileNumber().equals(mobileNumber)) {
                System.out.println("This number is already exist");
                return deliveryManMobileNumberInput();
            }
        }
        return mobileNumber;
    }

    static String userNameInput() {
        Pattern pattern = Pattern.compile("[a-zA-Z]+[a-zA-z0-9_]*");
        System.out.println("Enter your user name :");
        String userName = scanner.next().toLowerCase();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(userName);
        if(!matcher.matches()) {
            System.out.println("Please enter valid user name");
            return userNameInput();
        }
        return userName.toLowerCase();
    }

    static String passwordInput() {
        System.out.println("Enter your password :");
        String password1 = scanner.next();
        scanner.nextLine();
        System.out.println("Enter your confirm password :");
        String password2 = scanner.next();
        scanner.nextLine();
        if(!password1.equals(password2)) {
            System.out.println("Please enter correct password.....");
            return passwordInput();
        }
        return password1;
    }

    static String signinOrSignup() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering signInOrSignUp method ");
        System.out.println("1--> Sign in , 2--> Sign up");
        String returnAns ;
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "signin";
                break;
            case "2":
                returnAns = "signup";
                break;
            default:
                System.out.println("Please enter valid input");
                return signinOrSignup();
        }
        return returnAns;
    }

    static Object accountFind(String choice , String mobileNumber) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering accountFind method ");
        switch(choice) {
            case "customer":
                Customer account = null;
                for (Customer acc : Admin.getCustomersList()) {
                    if (acc.getMobileNumber().equals(mobileNumber)) {
                        account = acc;
                        break;
                    }
                }
                if(account == null) {
                    System.out.println("No account matched");
                    Main.main(new String[]{});
                }
                return account;
            case "admin":
                Admin acc = null;
                for(Admin admin : Main.adminList) {
                    if(admin.getMobileNumber().equals(mobileNumber)) {
                        acc = admin;
                        break;
                    }
                }
                if(acc == null) {
                System.out.println("No account matched");
                Main.main(new String[]{});
            }
                return acc;
            case "deliveryman":
                DeliveryMan ac = null;
                for(DeliveryMan deliveryMan : Admin.getDeliveryManList()) {
                    if(deliveryMan.getMobileNumber().equals(mobileNumber)) {
                        ac = deliveryMan;
                        break;
                    }
                }
                if(ac == null) {
                    System.out.println("No account matched");
                    Main.main(new String[]{});
                }
                return ac;
        }
        return null;
    }

    static String loginNumberInput() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering loginNumberInput method");
        Pattern pattern = Pattern.compile("[7-9][0-9]{9}");
        System.out.println("Enter your mobile number :");
        String mobileNumber = scanner.next();
        scanner.nextLine();
        Matcher matcher = pattern.matcher(mobileNumber);
        if (!matcher.matches()) {
            System.out.println("Please enter valid mobile number");
            return loginNumberInput();
        }
        return mobileNumber;
    }

    static String loginPasswordInput() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering loginPasswordInput method");
        System.out.println("Enter your password :");
        String password = scanner.next();
        scanner.nextLine();
        return password;
    }

    static int showProducts(ArrayList al) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showProducts method ");
        String str = "";
        int count = 1;
        for(Object obj : al) {
            str += " , \n" + count + "--> " + obj ;
            ++count;
        }
        str = str.substring(3);
        System.out.println(str);
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            return showProducts(al);
        }
        if((choice < 1) || (choice >= count)) {
            System.out.println("Please enter valid input");
            return showProducts(al);
        }
        return choice;
    }

    static void purchase(Product obj , Customer customer) throws CloneNotSupportedException {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering purchase method ");
        System.out.println("Enter the quantity :");
        int quantity =0;
        try {
            quantity = scanner.nextInt();
            scanner.nextLine();
        }
        catch(Exception ex) {
            System.out.println("Please enter valid input");
            purchase(obj,customer);
        }
        if(quantity<1 || quantity>obj.getQuantity()) {
            System.out.println("Please enter valid quantity");
            purchase(obj,customer);
        }
        else {
            try {
                PreparedStatement ps = Main.dbConnection.prepareStatement("update Product_Details set quantity=? where name=?");
                ps.setInt(1,obj.getQuantity()-quantity);
                ps.setString(2, obj.getName());
                ps.executeUpdate();
            }
            catch(Exception ex) {
                ex.getMessage();
            }
            obj.setQuantity(obj.getQuantity()-quantity);
            Product cloneObj = obj.cloning(obj);
            cloneObj.setQuantity(quantity);
            customer.getCart().getProductsList().add(cloneObj);
            System.out.println("Successfully added");
        }
    }

    static String customerOption() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering customerOption method");
        System.out.println("1-->Buy , 2-->Cart , 3-->Wishlist , 4-->History , 5-->Home");
        String returnAns;
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "buy";
                break;
            case "2":
                returnAns = "cart";
                break;
            case "3":
                returnAns = "wishlist";
                break;
            case "4":
                returnAns = "history";
                break;
            case "5":
                returnAns = "home";
                break;
            default:
                System.out.println("Please enter valid input");
                return customerOption();
        }
        return returnAns;
    }

    static String continueOrNot() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering continueOrNot method ");
        System.out.println("Do you want to continue your shopping ?");
        String returnAns;
        System.out.println("1-->Yes , 2-->No");
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "yes";
                break;
            case "2":
                returnAns = "no";
                break;
            default:
                System.out.println("Please enter valid input");
                return continueOrNot();
        }
        return returnAns;
    }

    static String cartOrWishlist() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering cartOrWishlist method ");
        String returnAns;
        System.out.println("1-->Add to cart , 2-->Add to wishlist");
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "cart";
                break;
            case "2":
                returnAns = "wishlist";
                break;
            default:
                System.out.println("Please enter valid input");
                return cartOrWishlist();
        }
        return returnAns;
    }

    static String buy(Customer customer) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering buy method ");
        String returnAns = null;
        if(customer.getCart().getProductsList().size() != 0) {
            System.out.println("1-->Buy , 2-->Back");
            String choice = scanner.next();
            scanner.nextLine();
            switch (choice) {
                case "1":
                    returnAns = "buy";
                    break;
                case "2":
                    returnAns = "back";
                    break;
                default:
                    System.out.println("Please enter valid input");
                    return buy(customer);
            }
        }
        else {
            System.out.println("-----Empty-----");
            returnAns = "empty";
        }
        return returnAns;
    }

    static String sortType() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering sortType method ");
        String returnAns = null;
        System.out.println("Choose the sorting option ");
        System.out.println("1-->Name , 2-->Maximum Price to Minimum price , 3-->Minimum price to Maximum price");
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "name";
                break;
            case "2":
                returnAns = "max-min";
                break;
            case "3":
                returnAns = "min-max";
                break;
            default:
                System.out.println("Please enter valid input");
                return sortType();
        }
        return returnAns;
    }

    static String deliveryManOption() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering deliveryManOption method ");
        System.out.println("1-->View work , 2-->History , 3-->Home");
        String returnAns;
        String choice = scanner.next();
        scanner.nextLine();
        switch (choice) {
            case "1":
                returnAns = "work";
                break;
            case "2":
                returnAns = "history";
                break;
            case "3":
                returnAns = "home";
                break;
            default:
                System.out.println("Please enter valid input");
                return deliveryManOption();
        }
        return returnAns;
    }

    static Cart showWork(ArrayList<Cart> order) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering showWork method ");
        System.out.println("--------------WORK--------------");
        int i = 0;
        for(i=0;i<order.size();i++) {
            System.out.println(i+1 + "--> " + order.get(i).getProductsList());
        }
        System.out.println(i+1 + "--> Back");
        ++i;
        System.out.println("--------------------------------");
        System.out.println("Select your option");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        catch (Exception ex) {
            System.out.println("Please enter valid input");
            return showWork(order);
        }
        if(choice==i) {
            return null;
        }
        if(choice<1 || choice > order.size()) {
            System.out.println("Please enter valid input");
            return showWork(order);
        }
        return order.get(choice-1);
    }

    static String deliverOrNot() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering deliveryOrNot method ");
        String returnAns;
        System.out.println("1-->Delivered , 2-->Back");
        String choice = scanner.next();
        scanner.nextLine();
        switch(choice) {
            case "1":
                returnAns = "deliver";
                break;
            case "2":
                returnAns = "back";
                break;
            default:
                System.out.println("Please enter valid input");
                return deliverOrNot();
        }
        return returnAns;
    }

    static String homeOrBack() {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering homeOrBack method ");
        System.out.println("1-->Back , 2-->Home");
        String returnAns;
        String choice = scanner.next();
        scanner.nextLine();
        switch (choice) {
            case "1":
                returnAns = "back";
                break;
            case "2":
                returnAns = "home";
                break;
            default:
                System.out.println("Please enter valid account");
                return homeOrBack();
        }
        return returnAns;
    }
}
