import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Buyer class represents a buyer in the auction system.
 * It allows buyers to view collections, edit their profile,
 * view purchased items, and view auction results.
 */
public class Buyer extends User implements IProfileEditable, UserActions {
    private ArrayList<AntiqueCollection> purchasedItems;
    private double totalDebt;
    private static final String AUCTION_RESULTS_FILE = "auction_results.txt";

    /**
     * Constructs a Buyer object with the specified user details.
     *
     * @param userId      the user ID
     * @param username    the username
     * @param password    the password
     * @param name        the name of the buyer
     * @param address     the address of the buyer
     * @param contactInfo the contact information of the buyer
     */
    public Buyer(int userId, String username, String password, String name, String address, String contactInfo) {
        super(userId, username, password, "Buyer", name, address, contactInfo);
        this.purchasedItems = new ArrayList<>();
        this.totalDebt = 0.0;
    }

    /**
     * Displays the buyer's menu options.
     */
    @Override
    public void showMenu() {
        System.out.println("a) View Collection List");
        System.out.println("b) Edit Profile Information Details");
        System.out.println("c) View purchased item(s) and total debt");
        System.out.println("d) View Auction Results");
        System.out.println("-1 Logout");
    }

    /**
     * Handles the user's menu option selection.
     *
     * @param option                the selected option
     * @param scanner               the scanner for user input
     * @param antiqueCollectionList the list of antique collections
     * @param sellerList            the list of sellers
     */
    @Override
    public void handleOption(String option, Scanner scanner, AntiqueCollectionList antiqueCollectionList, SellerList sellerList) {
        switch (option) {
            case "a":
                viewCollections(sellerList.getSellers());
                break;
            case "b":
                editProfile();
                break;
            case "c":
                viewPurchasedItems();
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
     * Adds a purchased item to the buyer's list of purchased items.
     *
     * @param item the purchased item to add
     */
    public void addPurchasedItem(AntiqueCollection item) {
        purchasedItems.add(item);
    }

    /**
     * Adds an amount to the buyer's total debt.
     *
     * @param amount the amount to add to the total debt
     */
    public void addToDebt(double amount) {
        totalDebt += amount;
    }

    /**
     * Returns the list of purchased items.
     *
     * @return the list of purchased items
     */
    public ArrayList<AntiqueCollection> getPurchasedItems() {
        return purchasedItems;
    }

    /**
     * Edits the buyer's profile information.
     */
    @Override
    public void editProfile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Current Profile Information:");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Contact Info: " + getContactInfo());

        System.out.println("Enter new profile information:");

        System.out.print("Name: ");
        String newName = scanner.nextLine();
        setName(newName);

        System.out.print("Address: ");
        String newAddress = scanner.nextLine();
        setAddress(newAddress);

        System.out.print("Contact Info: ");
        String newContactInfo = scanner.nextLine();
        setContactInfo(newContactInfo);

        System.out.println("Profile information updated successfully!");
    }

    /**
     * Displays the buyer's purchased items and total debt.
     */
    public void viewPurchasedItems() {
        System.out.println("Purchased Items:");
        for (AntiqueCollection item : purchasedItems) {
            System.out.println(item);
        }
        System.out.println("Total Debt: $" + totalDebt);
    }

    /**
     * Finds a collection by its ID in the list of antique collections.
     *
     * @param collectionID        the ID of the collection to find
     * @param antiqueCollections the list of antique collections
     * @return the antique collection with the specified ID, or null if not found
     */
    private AntiqueCollection findCollectionById(int collectionID, ArrayList<AntiqueCollection> antiqueCollections) {
        for (AntiqueCollection collection : antiqueCollections) {
            if (collection.getAntiqueCollectionID() == collectionID) {
                return collection;
            }
        }
        return null;
    }
}
