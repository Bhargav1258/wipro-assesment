package restaurent;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {
        	System.out.println("\n🍽️ Restaurant Menu");
        	System.out.println("1. Add Item");
        	System.out.println("2. View Items");
        	System.out.println("3. Update Item");
        	System.out.println("4. Delete Item");
        	System.out.println("5. Add User");
        	System.out.println("6. View Users");
        	System.out.println("7. Place Order");
        	System.out.println("8. View Orders");
        	System.out.println("9. Exit");

            int ch;

            try {
                ch = Integer.parseInt(sc.nextLine()); 
            } catch (Exception e) {
                System.out.println("❌ Enter valid number");
                continue;
            }

            switch (ch) {

            case 1 -> MenuService.addItem(sc);

            case 2 -> MenuService.viewItems();

            case 3 -> MenuService.updateItem(sc);

            case 4 -> MenuService.deleteItem(sc);

            case 5 -> UserService.addUser(sc);

            case 6 -> UserService.viewUsers();

            case 7 -> OrdersService.placeOrder(sc);

            case 8 -> OrdersService.viewOrders();

            case 9 -> {
                System.out.println("👋 Exit");
                return;
            }

            default -> System.out.println("❓ Invalid choice");
        }
        }
    }
}