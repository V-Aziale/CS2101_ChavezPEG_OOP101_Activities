import java.util.Scanner;

public class Canteen {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // menu
        String[] itemNames = { "Shoyu Ramen", "Miso Ramen", "Tonkotsu Ramen", "Spicy Ramen", "Gyoza",
                "Iced Green Tea" };
        double[] itemPrices = { 120.00, 130.00, 150.00, 140.00, 90.00, 50.00 };

        System.out.println("=====  M E N U   =====");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-12s - $%.2f%n", i + 1, itemNames[i], itemPrices[i]);
        }
        System.out.println();

        // totals for order
        int totalItemsPurchased = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;

        boolean orderAgain = true;

        while (orderAgain) {
            System.out.print("Enter item number: ");
            int itemNumber = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Are you a student? (Y/N): ");
            String studentInput = sc.nextLine().trim();
            boolean isStudent = studentInput.equalsIgnoreCase("Y");

            System.out.println();

            // validate
            boolean validItem = (itemNumber >= 1 && itemNumber <= itemNames.length);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);

            if (!validItem || !validQuantity) {
                System.out.println("Invalid order! Please enter a valid item and quantity.");
            } else {
                double subtotal = itemPrices[itemNumber - 1] * quantity;

                // discount
                double discountRate;
                if (isStudent && subtotal >= 500) {
                    discountRate = 0.15;
                } else if (isStudent) {
                    discountRate = 0.10;
                } else if (subtotal >= 500) {
                    discountRate = 0.05;
                } else {
                    discountRate = 0.0;
                }

                double discount = subtotal * discountRate;
                double orderTotal = subtotal - discount;

                System.out.printf("Subtotal: $%.2f%n", subtotal);
                System.out.printf("Discount: $%.2f%n", discount);
                System.out.printf("Order total: $%.2f%n", orderTotal);

                // totals
                totalItemsPurchased += quantity;
                totalBeforeDiscount += subtotal;
                totalDiscount += discount;
            }

            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            String again = sc.nextLine().trim();
            orderAgain = again.equalsIgnoreCase("Y");
            System.out.println();
        }

        // final order summary
        double finalAmount = totalBeforeDiscount - totalDiscount;

        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalItemsPurchased);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thank you for ordering!");

        sc.close();
    }
}