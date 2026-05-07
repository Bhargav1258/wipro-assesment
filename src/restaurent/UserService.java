package restaurent;


import java.sql.*;
import java.util.Scanner;

public class UserService {

    public static void addUser(Scanner sc) throws Exception {

        Connection con = DBConnection.getConnection();

        try {

            System.out.print("Enter user name: ");
            String name = sc.nextLine();

            System.out.print("Enter phone: ");
            String phone = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(user_name, phone) VALUES(?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, phone);

            ps.executeUpdate();

            System.out.println("✅ User added");

        } catch (Exception e) {
            System.out.println("❌ Error");
        }

        con.close();
    }

    public static void viewUsers() throws Exception {

        Connection con = DBConnection.getConnection();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM users");

        while(rs.next()) {

            System.out.println(
                rs.getInt("user_id") + " " +
                rs.getString("user_name") + " " +
                rs.getString("phone")
            );
        }

        con.close();
    }
}