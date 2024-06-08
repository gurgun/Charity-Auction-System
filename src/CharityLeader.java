import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The CharityLeader class represents a charity leader in the auction system.
 * It allows the charity leader to approve or disapprove antique collections, set base prices,
 * start auctions, and view auction results. This class is implemented as a singleton.
 */
public class CharityLeader extends User implements IAntiqueCollectionEditable, UserActions {
    private static CharityLeader instance;
    private AntiqueCollectionList antiqueCollectionList;
    private Auction auction;
    private static final String AUCTION_RESULTS_FILE = "auction_results.txt";

    /**
     * Private constructor to create the singleton instance of CharityLeader.
     *
     * @param userId      the user ID
     * @param username    the username
     * @param password    the password
     * @param name        the name of the charity leader
     * @param address     the address of the charity leader
     * @param contactInfo the contact information of the charity leader
     * @param buyerList   the list of buyers
     */
    private CharityLeader(int userId, String username, String password, String name, String address, String contactInfo, BuyerList buyerList) {
        super(userId, username, password, "Charity Leader", name, address, contactInfo);
        this.antiqueCollectionList = AntiqueCollectionList.getInstance();
        this.auction = new Auction(buyerList);
    }

    /**
     * Gets the singleton instance of CharityLeader.
     *
     * @param buyerList the list of buyers
     * @return the singleton instance of CharityLeader
     */
    public static CharityLeader getInstance(BuyerList buyerList) {
        if (instance == null) {
            instance = new CharityLeader(100, "z", "z", "Zeynep", "10 Charity Ave", "zeynep@example.com", buyerList);
        }
        return instance;
    }

    /**
     * Displays the charity leader's menu options.
     */
    @Override
    public void showMenu() {
        System.out.println("a) View Collection List");
        System.out.println("b) Approve or Disapprove Collections and Set Base Price");
        System.out.println("c) Start Auction");
        System.out.println("d) View Auction Results");
        System.out.println("-1 to Logout");
    }

    /**
     * Handles the charity leader's menu option selection.
     *
     * @param option               the selected option
     * @param scanner              the scanner for user input
     * @param antiqueCollectionList the list of antique collections
     * @param sellerList           the list of sellers
     */
    @Override
    public void handleOption(String option, Scanner scanner, AntiqueCollectionList antiqueCollectionList, SellerList sellerList) {
        switch (option) {
            case "a":
                viewCollections(sellerList.getSellers());
                break;
            case "b":
                approveOrDisapproveCollections();
                break;
            case "c":
                startAuction();
                break;
            case "d":
                displayAuctionResultsFromFile();
                break;
            case "-1":
                logout();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    /**
     * Displays the auction results from the results file.
     */
    private void displayAuctionResultsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(AUCTION_RESULTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading auction results from file: " + e.getMessage());
        }
    }

    /**
     * Starts an auction for approved collections.
     */
    public void startAuction() {
        ArrayList<AntiqueCollection> approvedCollections = antiqueCollectionList.getApprovedCollections();
        if (approvedCollections.isEmpty()) {
            System.out.println("No approved collections available for auction.");
            return;
        } else {
            auction.startAuction(approvedCollections);
        }
    }

    /**
     * Approves an antique collection by setting its base price.
     *
     * @param id        the ID of the antique collection
     * @param basePrice the base price to be set for the antique collection
     */
    @Override
    public void approveAntiqueCollection(int id, double basePrice) {
        AntiqueCollection antiqueCollectionToApprove = antiqueCollectionList.getCollectionById(id);
        if (antiqueCollectionToApprove == null || !antiqueCollectionToApprove.getStatus().equals(Status.PENDING)) {
            System.out.println("Invalid collection ID or collection is not pending approval.");
            return;
        }

        antiqueCollectionToApprove.setStatus(Status.APPROVED);
        antiqueCollectionToApprove.setBasePrice(basePrice);
        System.out.println("Collection approved successfully.");
    }

    /**
     * Disapproves an antique collection.
     *
     * @param id the ID of the antique collection
     */
    @Override
    public void disapproveAntiqueCollection(int id) {
        AntiqueCollection antiqueCollectionToDisapprove = antiqueCollectionList.getCollectionById(id);
        if (antiqueCollectionToDisapprove == null || !antiqueCollectionToDisapprove.getStatus().equals(Status.PENDING)) {
            System.out.println("Invalid collection ID or collection is not pending approval.");
            return;
        }

        antiqueCollectionToDisapprove.setStatus(Status.DISAPPROVED);
        System.out.println("Collection disapproved successfully.");
    }

    /**
     * Sets the base price for an antique collection.
     *
     * @param id        the ID of the antique collection
     * @param basePrice the base price to be set for the antique collection
     */
    @Override
    public void setBasePrice(int id, double basePrice) {
        AntiqueCollection antiqueCollectionToUpdate = antiqueCollectionList.getCollectionById(id);
        if (antiqueCollectionToUpdate == null || !antiqueCollectionToUpdate.getStatus().equals(Status.APPROVED)) {
            System.out.println("Invalid collection ID or collection is not approved.");
            return;
        }

        antiqueCollectionToUpdate.setBasePrice(basePrice);
        System.out.println("Base price updated successfully.");
    }

    /**
     * Approves or disapproves pending collections by taking input from the charity leader.
     */
    public void approveOrDisapproveCollections() {
        Scanner scanner = new Scanner(System.in);

        while (hasPendingCollections()) {
            System.out.println("Pending Collections:");
            for (AntiqueCollection antiqueCollection : antiqueCollectionList.getAntiqueCollections()) {
                if (antiqueCollection.getStatus().equals(Status.PENDING)) {
                    System.out.println(antiqueCollection);
                }
            }

            System.out.println("Enter the ID number of the collection to approve or disapprove (or enter -1 to exit):");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id == -1) { // Exit if -1 is entered
                break;
            }

            AntiqueCollection antiqueCollectionToApprove = antiqueCollectionList.getCollectionById(id);
            if (antiqueCollectionToApprove == null || !antiqueCollectionToApprove.getStatus().equals(Status.PENDING)) {
                System.out.println("Invalid collection ID or collection is not pending approval.");
                continue;
            }

            System.out.println("Do you want to approve or disapprove this collection? (A for approve / D for disapprove)");
            String decision = scanner.nextLine().toUpperCase();

            switch (decision) {
                case "A":
                    System.out.println("Enter the base price for the approved collection:");
                    double basePrice = scanner.nextDouble();
                    scanner.nextLine();
                    approveAntiqueCollection(id, basePrice);
                    break;
                case "D":
                    disapproveAntiqueCollection(id);
                    break;
                default:
                    System.out.println("Invalid decision. Please enter 'A' for approve or 'D' for disapprove.");
                    break;
            }
        }
        System.out.println("No more pending collections to approve or disapprove.");
    }

    /**
     * Checks if there are any pending collections.
     *
     * @return true if there are pending collections, otherwise false
     */
    private boolean hasPendingCollections() {
        for (AntiqueCollection antiqueCollection : antiqueCollectionList.getAntiqueCollections()) {
            if (antiqueCollection.getStatus() == Status.PENDING) {
                return true;
            }
        }
        return false;
    }
}
