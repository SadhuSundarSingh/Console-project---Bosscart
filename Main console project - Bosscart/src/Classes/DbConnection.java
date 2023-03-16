package Classes;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    static Connection dbConnection = null;

    public static Connection getDbConnection() {
        return dbConnection;
    }

    public static void setDbConnection(Connection dbConnection) {
        DbConnection.dbConnection = dbConnection;
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
}
