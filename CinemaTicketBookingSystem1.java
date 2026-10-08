import java.util.Scanner;

public class CinemaTicketBookingSystem1 {
    private String movieName;
    private double ticketPrice;
    private int numberOfTickets;

    public CinemaTicketBookingSystem1(String name, double price, int count) {
        movieName = name;
        ticketPrice = price;
        numberOfTickets = count;
    }

    public double calculateTotal() {
        return ticketPrice * numberOfTickets;
    }

    public double calculateDiscount() {
        double total = calculateTotal();
        if (numberOfTickets >= 5) {
            return total * 0.10;
        } else {
            return 0.0;
        }
    }

    public double calculateFinalAmount() {
        return calculateTotal() - calculateDiscount();
    }

    public void displayBill() {
        System.out.println("\n----- BOOKING BILL -----");
        System.out.println("Movie Name: " + movieName);
        System.out.println("Ticket Price: Rs" + String.format("%.2f", ticketPrice));
        System.out.println("Number of Tickets: " + numberOfTickets);
        System.out.println("Total Amount: Rs" + String.format("%.2f", calculateTotal()));
        System.out.println("Discount Availed: Rs" + String.format("%.2f", calculateDiscount()));
        System.out.println("Final Amount Paid: Rs" + String.format("%.2f", calculateFinalAmount()));
        System.out.println("------------------------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Movie Name: ");
        String movieName = sc.nextLine();

        System.out.print("Enter Ticket Price: ");
        double ticketPrice = sc.nextDouble();

        System.out.print("Enter Number of Tickets: ");
        int numberOfTickets = sc.nextInt();

        CinemaTicketBookingSystem1 ticket = new CinemaTicketBookingSystem1(movieName, ticketPrice, numberOfTickets);
        ticket.displayBill();

        sc.close();
    }
}
