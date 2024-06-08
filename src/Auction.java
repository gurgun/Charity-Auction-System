import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Auction class represents the auction process in the auction system.
 * It manages the bidding process, records bids, and handles the sale of antique collections.
 */
public class Auction {
    private ArrayList<AntiqueCollection> antiqueCollections;
    private ArrayList<Bid> bids;
    private ArrayList<AntiqueCollection> soldCollections;
    private BuyerList buyerList;
    private double totalRevenue;
    private static final String AUCTION_RESULTS_FILE = "auction_results.txt";

    /**
     * Constructs an Auction object with the specified list of buyers.
     *
     * @param buyerList the list of buyers participating in the auction
     */
    public Auction(BuyerList buyerList) {
        this.antiqueCollections = new ArrayList<>();
        this.bids = new ArrayList<>();
        this.soldCollections = new ArrayList<>();
        this.buyerList = buyerList;
        this.totalRevenue = 0.0;
    }

    /**
     * Starts the auction with the given list of collections.
     *
     * @param collections the list of antique collections to be auctioned
     */
    public void startAuction(ArrayList<AntiqueCollection> collections) {
        antiqueCollections.addAll(collections);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to start the auction? Type 'yes' to begin.");
        String start = scanner.nextLine();

        if (start.equalsIgnoreCase("yes")) {
            openBidding();
        }
    }

    /**
     * Opens the bidding process for each collection in the auction.
     */
    private void openBidding() {
        Scanner scanner = new Scanner(System.in);

        for (AntiqueCollection collection : antiqueCollections) {
            if (collection.getStatus() == Status.SOLD) {
                continue; // Skip already sold collections
            }

            System.out.println("Bidding for Collection ID: " + collection.getAntiqueCollectionID());
            System.out.println(collection);

            Bid highestBid = null;

            while (true) {
                System.out.println("Buyers can place bids. Type 'NEXT' to proceed to the next collection or 'END' to finish the auction.");
                System.out.print("Enter bid (format: [username] bids [amount], e.g., 'buyer1 bids 200.00'): ");
                String input = scanner.nextLine();

                if ("NEXT".equalsIgnoreCase(input)) {
                    if (highestBid != null) {
                        System.out.println("-------------------------------------");
                        System.out.println("Collection sold!!! Purchased by: " + highestBid.getBuyerUsername() + " for $" + highestBid.getAmount());
                        System.out.println("-------------------------------------");
                    }
                    break;
                } else if ("END".equalsIgnoreCase(input)) {
                    saveResultsToFile();
                    displayResults();
                    return;
                }

                // Parse bid input
                String[] parts = input.split(" bids ");
                if (parts.length == 2) {
                    String buyerUsername = parts[0];
                    double amount = Double.parseDouble(parts[1]);

                    // Check if the buyer exists
                    if (!buyerList.usernameExists(buyerUsername)) {
                        System.out.println("Buyer with username " + buyerUsername + " does not exist. Please enter a valid username.");
                        continue;
                    }

                    // Check if the bid is greater than or equal to the base price
                    if (amount >= collection.getBasePrice()) {
                        // Create and place bid
                        Bid bid = new Bid(buyerUsername, collection.getAntiqueCollectionID(), amount);
                        bids.add(bid);

                        // Check if this bid is the highest bid
                        if (highestBid == null || bid.getAmount() > highestBid.getAmount()) {
                            highestBid = bid;
                        }
                        System.out.println("Bid placed successfully.");
                    } else {
                        System.out.println("Bid is below the base price. Please enter a valid bid.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid bid.");
                }
            }

            if (highestBid != null) {
                // Update collection status to "Sold" and set the sold price to the highest bid amount
                collection.setStatus(Status.SOLD);
                collection.setSoldPrice(highestBid.getAmount());
                collection.setBuyerUsername(highestBid.getBuyerUsername()); // Set buyer username

                // Add sold collection to the list of sold collections
                soldCollections.add(collection);

                // Update the buyer's purchased items and debt
                Buyer highestBidder = buyerList.getBuyerByUsername(highestBid.getBuyerUsername());
                if (highestBidder != null) {
                    highestBidder.addPurchasedItem(collection);
                    highestBidder.addToDebt(highestBid.getAmount());
                }

                // Update total revenue
                totalRevenue += highestBid.getAmount();
            }
        }

        System.out.println("All collections auctioned.");
        saveResultsToFile();
        displayResults();
    }

    /**
     * Saves the auction results to a file.
     */
    private void saveResultsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AUCTION_RESULTS_FILE))) {
            writer.write("Auction Results:\n");
            writer.write("----------------\n");

            for (AntiqueCollection soldCollection : soldCollections) {
                writer.write("Collection ID: " + soldCollection.getAntiqueCollectionID() + "\n");
                writer.write("Collection name: " + soldCollection.getName() + "\n");
                writer.write("Base Price: $" + soldCollection.getBasePrice() + "\n");
                writer.write("Sold Price: $" + soldCollection.getSoldPrice() + "\n");
                writer.write("Purchased by: " + soldCollection.getBuyerUsername() + "\n");
                writer.write("-------------------------------------\n");
            }

            writer.write("Total Revenue: $" + totalRevenue + "\n");
        } catch (IOException e) {
            System.out.println("Error writing auction results to file: " + e.getMessage());
        }
    }

    /**
     * Displays the auction results.
     */
    public void displayResults() {
        System.out.println("Auction Results:");
        System.out.println("----------------");

        for (AntiqueCollection soldCollection : soldCollections) {
            System.out.println("Collection ID: " + soldCollection.getAntiqueCollectionID());
            System.out.println("Collection name: " + soldCollection.getName());
            System.out.println("Base Price: $" + soldCollection.getBasePrice());
            System.out.println("Sold Price: $" + soldCollection.getSoldPrice());
            System.out.println("Purchased by: " + soldCollection.getBuyerUsername());
            System.out.println("-------------------------------------");
        }

        System.out.println("Total Revenue: $" + totalRevenue); // Display total revenue
    }

    /**
     * Finds a bid by the collection ID.
     *
     * @param collectionId the ID of the collection to find the bid for
     * @return the bid for the specified collection ID, or null if not found
     */
    private Bid findBidByCollectionId(int collectionId) {
        for (Bid bid : bids) {
            if (bid.getCollectionID() == collectionId) {
                return bid;
            }
        }
        return null;
    }
}
