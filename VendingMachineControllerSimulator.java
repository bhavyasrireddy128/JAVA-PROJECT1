import java.util.Scanner;

public class VendingMachineControllerSimulator {

    // Constant
    static final int NUMBER_OF_PRODUCTS = 5;

    // Method to display all products
    static void displayProducts(String[] products, double[] prices, int[] stock) {

        System.out.println("\n==============================================");
        System.out.println("          VENDING MACHINE PRODUCTS");
        System.out.println("==============================================");
        System.out.printf("%-5s %-15s %-10s %-10s%n",
                "ID", "Product", "Price", "Stock");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            System.out.printf("%-5d %-15s ₹%-9.2f %-10d%n",
                    i + 1, products[i], prices[i], stock[i]);
        }

        System.out.println("==============================================");
    }

    // Method to find a product
    static int findProduct(int choice) {

        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {

            if (choice == i + 1) {
                return i;
            }
        }

        return -1;
    }

    // Method to accept money
    static double acceptMoney(Scanner input, double price) {

        double money = 0;

        while (money < price) {

            System.out.printf("\nPrice to pay : ₹%.2f%n", price);
            System.out.printf("Amount paid  : ₹%.2f%n", money);

            System.out.print("Insert money: ₹");
            double amount = input.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid amount!");
                continue;
            }

            money = money + amount;

            if (money < price) {
                System.out.printf("Remaining amount: ₹%.2f%n",
                        price - money);
            }
        }

        return money;
    }

    // Method to calculate change
    static double calculateChange(double money, double price) {
        return money - price;
    }

    // Method to display transaction
    static void displayTransaction(String product, double price,
                                   double money, double change) {

        System.out.println("\n==============================================");
        System.out.println("           TRANSACTION SUCCESSFUL");
        System.out.println("==============================================");

        System.out.println("Product       : " + product);
        System.out.printf("Price         : ₹%.2f%n", price);
        System.out.printf("Amount Paid   : ₹%.2f%n", money);
        System.out.printf("Change        : ₹%.2f%n", change);

        System.out.println("----------------------------------------------");
        System.out.println("Please collect your " + product + ".");
        System.out.println("Thank you!");
        System.out.println("==============================================");
    }

    // Method to display statistics
    static void displayStatistics(String[] products, int[] sold) {

        int totalSold = 0;
        int productsSold = 0;

        System.out.println("\n==============================================");
        System.out.println("             SALES STATISTICS");
        System.out.println("==============================================");

        for (int value : sold) {
            totalSold = totalSold + value;

            if (value > 0) {
                productsSold++;
            }
        }

        System.out.println("Total items sold: " + totalSold);
        System.out.println("Different products sold: " + productsSold);

        if (totalSold > 0) {

            double average = (double) totalSold / NUMBER_OF_PRODUCTS;

            System.out.printf("Average items sold per product: %.2f%n",
                    average);
        } else {
            System.out.println("No products have been sold.");
        }

        System.out.println("\nItems sold by product:");

        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            System.out.println(products[i] + " : " + sold[i]);
        }

        System.out.println("==============================================");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // 1D arrays
        String[] products = {
            "Chips",
            "Chocolate",
            "Soft Drink",
            "Biscuit",
            "Juice"
        };

        double[] prices = {
            20.00,
            30.00,
            40.00,
            15.00,
            25.00
        };

        int[] stock = {
            10,
            8,
            10,
            12,
            10
        };

        int[] sold = {
            0,
            0,
            0,
            0,
            0
        };

        int choice;
        boolean running = true;

        System.out.println("==============================================");
        System.out.println("       VENDING MACHINE CONTROLLER");
        System.out.println("==============================================");

        // Main loop
        while (running) {

            System.out.println("\n--------------- MAIN MENU ----------------");
            System.out.println("1. Display Products");
            System.out.println("2. Buy Product");
            System.out.println("3. Sales Statistics");
            System.out.println("4. Exit");
            System.out.println("------------------------------------------");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            // Switch statement
            switch (choice) {

                case 1:
                    displayProducts(products, prices, stock);
                    break;

                case 2:

                    displayProducts(products, prices, stock);

                    System.out.print("\nEnter product ID: ");
                    int productChoice = input.nextInt();

                    // Search for product
                    int index = findProduct(productChoice);

                    if (index == -1) {

                        System.out.println("Invalid product ID!");

                    } else if (stock[index] == 0) {

                        System.out.println(
                                "Sorry! " + products[index]
                                + " is out of stock.");

                    } else {

                        System.out.println("\nYou selected: "
                                + products[index]);

                        System.out.printf("Price: ₹%.2f%n",
                                prices[index]);

                        // Accept payment
                        double money =
                                acceptMoney(input, prices[index]);

                        // Calculate change
                        double change =
                                calculateChange(money, prices[index]);

                        // Update stock and sales
                        stock[index] = stock[index] - 1;
                        sold[index] = sold[index] + 1;

                        // Display transaction
                        displayTransaction(
                                products[index],
                                prices[index],
                                money,
                                change
                        );
                    }

                    break;

                case 3:
                    displayStatistics(products, sold);
                    break;

                case 4:
                    running = false;
                    System.out.println(
                            "\nThank you for using the vending machine!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1-4.");
            }
        }

        input.close();
    }
}