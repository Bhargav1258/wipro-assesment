package restaurent;

import java.sql.*;

public class DBConnection {
    static final String url = "jdbc:mysql://localhost:3306/restaurantdb1";
    static final String user = "root";
    static final String password = "root";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
}