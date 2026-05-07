package restaurent;

import java.sql.*;
import java.util.Scanner;

public class MenuService {

    
    public static void addItem(Scanner sc) throws Exception {
        Connection con = DBConnection.getConnection();

        try {
            System.out.print("Enter item name: ");
            String name = sc.nextLine();

            System.out.print("Enter price: ");
            int price = Integer.parseInt(sc.nextLine());

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO menu(item_name, price) VALUES(?,?)"
            );
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.executeUpdate();

            System.out.println("✅ Item added");

        } catch (Exception e) {
            System.out.println("❌ Invalid input");
        }

        con.close();
    }

  
    public static void viewItems() throws Exception {
        Connection con = DBConnection.getConnection();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM menu");

        while (rs.next()) {
            System.out.println(
                rs.getInt("item_id") + " " +
                rs.getString("item_name") + " ₹" +
                rs.getInt("price")
            );
        }

        con.close();
    }

    
    public static void updateItem(Scanner sc) throws Exception {
        Connection con = DBConnection.getConnection();

        try {
            System.out.print("Enter item id: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter new price: ");
            int price = Integer.parseInt(sc.nextLine());

            PreparedStatement ps = con.prepareStatement(
                "UPDATE menu SET price=? WHERE item_id=?"
            );
            ps.setInt(1, price);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Item updated");
            else
                System.out.println("❗ No item found");

        } catch (Exception e) {
            System.out.println("❌ Invalid input");
        }

        con.close();
    }

    
    public static void deleteItem(Scanner sc) throws Exception {
        Connection con = DBConnection.getConnection();

        try {
            System.out.print("Enter item id: ");
            int id = Integer.parseInt(sc.nextLine());

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM menu WHERE item_id=?"
            );
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("❌ Item deleted");
            else
                System.out.println("❗ No item found");

        } catch (Exception e) {
            System.out.println("❌ Invalid input");
        }

        con.close();
    }
}