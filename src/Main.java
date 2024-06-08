import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Main class is the entry point for the Charity Auction System.
 * It initializes data, handles user login and signup, and manages the main menu loop.
 */
public class Main {
    /**
     * The main method is the entry point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Clear the auction results file at the start of the program
        try (FileWriter fileWriter = new FileWriter("auction_results.txt", false)) {
            fileWriter.write("");
        } catch (IOException e) {
            System.out.println("Error clearing auction results file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println();
        System.out.println("***************************************************************");
        System.out.println("******************* CHARITY AUCTION SYSTEM ********************");
        System.out.println("***************************************************************");
        System.out.println();

        // Create buyer list
        BuyerList buyerList = new BuyerList();

        // Get instances of AntiqueCollectionList and CharityLeader
        AntiqueCollectionList antiqueCollectionList = AntiqueCollectionList.getInstance();
        CharityLeader charityLeader = CharityLeader.getInstance(buyerList);

        System.out.println();
        System.out.println("Charity Leader is " + charityLeader.getName());
        System.out.println();

        // Create seller list
        SellerList sellerList = new SellerList();

        // Create some sellers
        Seller seller1 = new Seller(1, "seller1", "pass1", "John Doe", "123 Main St", "john@example.com");
        Seller seller2 = new Seller(2, "seller2", "pass2", "Jane Smith", "456 Elm St", "jane@example.com");
        Seller seller3 = new Seller(3, "seller3", "pass3", "Alice Johnson", "789 Oak St", "alice@example.com");

        sellerList.addSeller(seller1);
        sellerList.addSeller(seller2);
        sellerList.addSeller(seller3);

        // Create some buyers
        Buyer buyer1 = new Buyer(4, "buyer1", "p1", "Bob Brown", "321 Pine St", "bob@example.com");
        Buyer buyer2 = new Buyer(5, "buyer2", "p2", "Charlie Davis", "654 Cedar St", "charlie@example.com");

        buyerList.addBuyer(buyer1);
        buyerList.addBuyer(buyer2);

        // Create user manager
        UserManager userManager = new UserManager();

        // Create some collections
        AntiqueCollection antiqueCollection1 = new AntiqueCollection(1, "Antique Chair", "Furniture", 1, "Good condition", 100.0, Status.PENDING, 1);
        AntiqueCollection antiqueCollection2 = new AntiqueCollection(2, "Vintage Painting", "Artwork", 1, "Excellent condition", 200.0, Status.APPROVED, 2);
        AntiqueCollection antiqueCollection3 = new AntiqueCollection(3, "Rare Coin Collection", "Collectibles", 1, "Mint condition", 300.0, Status.APPROVED, 1);
        AntiqueCollection antiqueCollection4 = new AntiqueCollection(4, "Antique Vase", "Pottery", 1, "Fair condition", 150.0, Status.PENDING, 3);

        // Add collections to seller's registered collections
        seller1.registerCollection(antiqueCollection1);
        seller2.registerCollection(antiqueCollection2);
        seller1.registerCollection(antiqueCollection3);
        seller3.registerCollection(antiqueCollection4);

        // Add collections to the collection list
        antiqueCollectionList.addCollection(antiqueCollection1);
        antiqueCollectionList.addCollection(antiqueCollection2);
        antiqueCollectionList.addCollection(antiqueCollection3);
        antiqueCollectionList.addCollection(antiqueCollection4);

        // Variable to hold the logged-in user
        User loggedInUser = null;

        // Menu loop
        while (true) {
            try {
                // If no user is logged in, display login or sign up options
                if (loggedInUser == null) {
                    System.out.println("1. Login");
                    System.out.println("2. Sign up");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            loggedInUser = userManager.login(sellerList, buyerList, charityLeader);
                            if (loggedInUser != null) {
                                System.out.println("Logged in as: " + loggedInUser.getName());
                            }
                            break;
                        case 2:
                            userManager.createUser(sellerList, buyerList);
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                            break;
                    }
                } else {
                    // User is logged in, display menu based on user role
                    System.out.println("***************************************************************");
                    System.out.println("Menu:");
                    ((UserActions) loggedInUser).showMenu();
                    System.out.println("***************************************************************");
                    System.out.print("Enter your choice: ");
                    String option = scanner.nextLine();

                    // Handle menu options
                    ((UserActions) loggedInUser).handleOption(option, scanner, antiqueCollectionList, sellerList);
                    if (option.equals("-1")) {
                        loggedInUser = null; // Logout and reset the loggedInUser
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
