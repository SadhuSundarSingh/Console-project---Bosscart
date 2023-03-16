package Classes;
import org.apache.log4j.Level;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Item {
    Category category;
    LinkedHashMap map = new LinkedHashMap<>();

    void addNewType(String typeName) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering addNewType method ");
        map.put(typeName,new ArrayList());
        try {
            PreparedStatement typeDetail = Main.dbConnection.prepareStatement("insert into Type_Name values(?,?)");
            typeDetail.setString(1,String.valueOf(this.getCategory()));
            typeDetail.setString(2,typeName);
            typeDetail.executeUpdate();
        }
        catch(Exception ex) {
            ex.getMessage();
        }
    }

    void addNewProduct(String typeName,Product product) {
        Main.log.log(Level.DEBUG, LocalDateTime.now() + " Entering addNewProduct method ");
        ArrayList al = (ArrayList) this.map.get(typeName);
        al.add(product);
    }

    void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public LinkedHashMap getMap() {
        return map;
    }

    public void setMap(LinkedHashMap map) {
        this.map = map;
    }
}
