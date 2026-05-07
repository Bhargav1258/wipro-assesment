package restaurent;

import java.sql.*;
import java.util.Scanner;

public class OrdersService {

 
	public static void placeOrder(Scanner sc) throws Exception {

	    Connection con = DBConnection.getConnection();

	    try {

	        System.out.print("Enter user id: ");
	        int userId = Integer.parseInt(sc.nextLine());

	        System.out.print("Enter item id: ");
	        int itemId = Integer.parseInt(sc.nextLine());

	        System.out.print("Enter quantity: ");
	        int qty = Integer.parseInt(sc.nextLine());

	        PreparedStatement ps1 = con.prepareStatement(
	            "SELECT price FROM menu WHERE item_id=?"
	        );

	        ps1.setInt(1, itemId);

	        ResultSet rs = ps1.executeQuery();

	        if (rs.next()) {

	            int price = rs.getInt("price");
	            int total = price * qty;

	            PreparedStatement ps2 = con.prepareStatement(
	                "INSERT INTO orders(item_id, quantity, total_price, user_id) VALUES(?,?,?,?)"
	            );

	            ps2.setInt(1, itemId);
	            ps2.setInt(2, qty);
	            ps2.setInt(3, total);
	            ps2.setInt(4, userId);

	            ps2.executeUpdate();

	            System.out.println("✅ Order placed. Total = ₹" + total);

	        } else {

	            System.out.println("❗ Item not found");
	        }

	    } catch (Exception e) {

	        System.out.println("❌ Invalid input");
	    }

	    con.close();
	}

    // VIEW ORDERS
	public static void viewOrders() throws Exception {

	    Connection con = DBConnection.getConnection();

	    Statement st = con.createStatement();

	    ResultSet rs = st.executeQuery(
	        "SELECT orders.order_id, users.user_name, menu.item_name, " +
	        "orders.quantity, orders.total_price " +
	        "FROM orders " +
	        "JOIN users ON orders.user_id = users.user_id " +
	        "JOIN menu ON orders.item_id = menu.item_id"
	    );

	    while (rs.next()) {

	        System.out.println(
	            rs.getInt("order_id") + " | " +
	            rs.getString("user_name") + " | " +
	            rs.getString("item_name") + " | Qty:" +
	            rs.getInt("quantity") + " | ₹" +
	            rs.getInt("total_price")
	        );
	    }

	    con.close();
	}}