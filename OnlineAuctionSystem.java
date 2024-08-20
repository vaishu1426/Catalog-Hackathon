import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Auction {
    private String item;
    private double startingBid;
    private double highestBid;
    private String highestBidder;

    public Auction(String item, double startingBid) {
        this.item = item;
        this.startingBid = startingBid;
        this.highestBid = startingBid;
        this.highestBidder = "No bids yet";
    }

    public String getItem() {
        return item;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public void placeBid(String bidderName, double bidAmount) {
        if (bidAmount > highestBid) {
            highestBid = bidAmount;
            highestBidder = bidderName;
            System.out.println("Bid placed successfully!");
        } else {
            System.out.println("Bid amount must be higher than the current highest bid.");
        }
    }

    public void displayAuctionDetails() {
        System.out.println("Item: " + item);
        System.out.println("Starting Bid: $" + startingBid);
        System.out.println("Highest Bid: $" + highestBid);
        System.out.println("Highest Bidder: " + highestBidder);
    }
}

public class OnlineAuctionSystem {
    private List<Auction> auctions = new ArrayList<>();

    public void createAuction(String item, double startingBid) {
        Auction auction = new Auction(item, startingBid);
        auctions.add(auction);
        System.out.println("Auction for " + item + " created successfully!");
    }

    public void displayAuctions() {
        if (auctions.isEmpty()) {
            System.out.println("No auctions available.");
        } else {
            for (int i = 0; i < auctions.size(); i++) {
                System.out.println("Auction ID: " + (i + 1));
                auctions.get(i).displayAuctionDetails();
                System.out.println();
            }
        }
    }

    public void placeBid(int auctionId, String bidderName, double bidAmount) {
        if (auctionId > 0 && auctionId <= auctions.size()) {
            auctions.get(auctionId - 1).placeBid(bidderName, bidAmount);
        } else {
            System.out.println("Invalid Auction ID.");
        }
    }

    public static void main(String[] args) {
        OnlineAuctionSystem system = new OnlineAuctionSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Online Auction System");
            System.out.println("1. Create Auction");
            System.out.println("2. View Auctions");
            System.out.println("3. Place Bid");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String item = scanner.nextLine();
                    System.out.print("Enter starting bid: ");
                    double startingBid = scanner.nextDouble();
                    system.createAuction(item, startingBid);
                    break;

                case 2:
                    system.displayAuctions();
                    break;

                case 3:
                    system.displayAuctions();
                    if (!system.auctions.isEmpty()) {
                        System.out.print("Enter Auction ID: ");
                        int auctionId = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        System.out.print("Enter your name: ");
                        String bidderName = scanner.nextLine();
                        System.out.print("Enter your bid amount: ");
                        double bidAmount = scanner.nextDouble();
                        system.placeBid(auctionId, bidderName, bidAmount);
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}