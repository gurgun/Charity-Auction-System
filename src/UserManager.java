import java.util.Scanner;

/**
 * The UserManager class provides methods for creating and logging in users in the auction system.
 */
public class UserManager {

    /**
     * Creates a new user (Seller or Buyer) based on the input provided by the user.
     *
     * @param sellers the list of sellers
     * @param buyers  the list of buyers
     */
    public void createUser(SellerList sellers, BuyerList buyers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        if (isUsernameAvailable(username, sellers, buyers)) {
            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();
            System.out.println("Please enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Please enter your address: ");
            String address = scanner.nextLine();
            System.out.println("Please enter your contact info: ");
            String contactInfo = scanner.nextLine();
            System.out.println("Please choose your role (Seller/Buyer): ");
            String role = scanner.nextLine();
            while (!role.equalsIgnoreCase("Seller") && !role.equalsIgnoreCase("Buyer")) {
                System.out.println("Invalid role. Please choose either Seller or Buyer.");
                role = scanner.nextLine();
            }
            if (role.equalsIgnoreCase("Seller")) {
                Seller seller = new Seller(sellers.size() + 1, username, password, name, address, contactInfo);
                sellers.addSeller(seller);
            } else {
                Buyer buyer = new Buyer(buyers.size() + 1, username, password, name, address, contactInfo);
                buyers.addBuyer(buyer);
            }
        } else {
            System.out.println("Username already exists. Please choose another username.");
        }
    }

    /**
     * Logs in a user based on the username and password provided by the user.
     *
     * @param sellers        the list of sellers
     * @param buyers         the list of buyers
     * @param charityLeader  the charity leader
     * @return the logged-in user if the credentials are valid, otherwise null
     */
    public User login(SellerList sellers, BuyerList buyers, CharityLeader charityLeader) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();

        // Search for the user with the given username and password
        for (Seller seller : sellers.getSellers()) {
            if (seller.getUsername().equals(username) && seller.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return seller;
            }
        }

        for (Buyer buyer : buyers.getBuyers()) {
            if (buyer.getUsername().equals(username) && buyer.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return buyer;
            }
        }

        if (charityLeader.getUsername().equals(username) && charityLeader.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return charityLeader;
        }

        System.out.println("Invalid username or password.");
        return null;
    }

    /**
     * Checks if a username is available for a new user.
     *
     * @param username the username to check
     * @param sellers  the list of sellers
     * @param buyers   the list of buyers
     * @return true if the username is available, otherwise false
     */
    private boolean isUsernameAvailable(String username, SellerList sellers, BuyerList buyers) {
        return !sellers.usernameExists(username) && !buyers.usernameExists(username);
    }
}
