import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Seller class represents a seller in the auction system.
 * It allows sellers to register, delete, and view antique collections,
 * as well as edit their profile information.
 */
public class Seller extends User implements IProfileEditable, UserActions {
    private ArrayList<AntiqueCollection> registeredAntiqueCollections;
    private static final String AUCTION_RESULTS_FILE = "auction_results.txt";

    /**
     * Constructs a Seller object with the specified user details.
     *
     * @param userId      the user ID
     * @param username    the username
     * @param password    the password
     * @param name        the name of the seller
     * @param address     the address of the seller
     * @param contactInfo the contact information of the seller
     */
    public Seller(int userId, String username, String password, String name, String address, String contactInfo) {
        super(userId, username, password, "Seller", name, address, contactInfo);
        registeredAntiqueCollections = new ArrayList<>();
    }

    /**
     * Displays the seller's menu options.
     */
    @Override
    public void showMenu() {
        System.out.println("a) Register Collection");
        System.out.println("b) Delete Registered Collection");
        System.out.println("c) View Collection List");
        System.out.println("d) Edit Profile Information Details");
        System.out.println("e) View Auction Results");
        System.out.println("-1 to Logout");
    }

    /**
     * Handles the user's menu option selection.
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
                registerCollectionManually(antiqueCollectionList);
                break;
            case "b":
                deleteCollection();
                break;
            case "c":
                viewCollections(sellerList.getSellers());
                break;
            case "d":
                editProfile();
                break;
            case "e":
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
     * Edits the seller's profile information.
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
     * Registers a collection.
     *
     * @param antiqueCollection the antique collection to register
     */
    public void registerCollection(AntiqueCollection antiqueCollection) {
        registeredAntiqueCollections.add(antiqueCollection);
    }

    /**
     * Registers a collection manually by taking input from the seller.
     *
     * @param antiqueCollectionList the list of antique collections
     */
    public void registerCollectionManually(AntiqueCollectionList antiqueCollectionList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Collection Details:");

        int id;
        while (true) {
            System.out.print("ID: ");
            try {
                id = Integer.parseInt(scanner.nextLine());

                // Check if the ID already exists
                boolean idExists = false;
                for (AntiqueCollection collection : antiqueCollectionList.getAntiqueCollections()) {
                    if (collection.getAntiqueCollectionID() == id) {
                        idExists = true;
                        break;
                    }
                }

                if (idExists) {
                    System.out.println("This ID already exists. Please enter a different ID.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a numeric ID.");
            }
        }

        // Get collection details from the user
        String name;
        while (true) {
            try {
                System.out.print("Name: ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String type;
        while (true) {
            try {
                System.out.print("Type: ");
                type = scanner.nextLine();
                if (type.isEmpty()) {
                    throw new IllegalArgumentException("Type cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        int quantity;
        while (true) {
            System.out.print("Quantity: ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    throw new IllegalArgumentException("Quantity must be a positive integer.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity format. Please enter a numeric value.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String conditionDescription;
        while (true) {
            try {
                System.out.print("Condition Description: ");
                conditionDescription = scanner.nextLine();
                if (conditionDescription.isEmpty()) {
                    throw new IllegalArgumentException("Condition Description cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        double basePrice = 0.0; // Initialize base price to 0.0

        AntiqueCollection antiqueCollection = new AntiqueCollection(id, name, type, quantity, conditionDescription, basePrice, Status.PENDING, getUserId());
        antiqueCollectionList.addCollection(antiqueCollection);

        // Register the collection
        registeredAntiqueCollections.add(antiqueCollection);

        System.out.println("Collection registered successfully!");
    }

    /**
     * Deletes a registered collection based on the user's input.
     */
    public void deleteCollection() {
        Scanner scanner = new Scanner(System.in);

        // Display the list of registered collections
        listCollectionDetailsForASeller();

        if (!registeredAntiqueCollections.isEmpty()) {
            // Ask the user to enter the ID of the collection to delete
            System.out.println("Enter the ID of the collection to delete: ");
            int idToDelete = scanner.nextInt();

            // Find the collection with the given ID and delete it
            boolean found = false;
            for (AntiqueCollection antiqueCollection : registeredAntiqueCollections) {
                if (antiqueCollection.getAntiqueCollectionID() == idToDelete) {
                    registeredAntiqueCollections.remove(antiqueCollection);
                    found = true;
                    System.out.println("Collection deleted successfully!");
                    break;
                }
            }

            if (!found) {
                System.out.println("Collection not found.");
            }
        } else {
            System.out.println("No collections to delete.");
        }
    }

    /**
     * Gets the list of registered collections.
     *
     * @return the list of registered collections
     */
    public ArrayList<AntiqueCollection> getRegisteredCollections() {
        return registeredAntiqueCollections;
    }

    /**
     * Lists the details of collections registered by the seller.
     */
    public void listCollectionDetailsForASeller() {
        System.out.println(getName() + "'s Registered Collections:");
        if (registeredAntiqueCollections.isEmpty()) {
            System.out.println("No collections registered yet.");
        } else {
            for (AntiqueCollection antiqueCollection : registeredAntiqueCollections) {
                System.out.println("Collection ID: " + antiqueCollection.getAntiqueCollectionID());
                System.out.println("Name: " + antiqueCollection.getName());
                System.out.println("Type: " + antiqueCollection.getType());
                System.out.println("Quantity: " + antiqueCollection.getQuantity());
                System.out.println("Condition Description: " + antiqueCollection.getConditionDescription());
                System.out.println("Base Price: " + antiqueCollection.getBasePrice());
                System.out.println("Status: " + antiqueCollection.getStatus());
                System.out.println();
            }
        }
    }
}
