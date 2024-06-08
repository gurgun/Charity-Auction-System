import java.util.ArrayList;

/**
 * The SellerList class manages a list of Seller objects.
 * It provides methods to add, remove, and query sellers.
 */
public class SellerList {
    private ArrayList<Seller> sellers;

    /**
     * Constructs a SellerList object.
     * Initializes the list of sellers.
     */
    public SellerList() {
        this.sellers = new ArrayList<>();
    }

    /**
     * Adds a seller to the list.
     *
     * @param seller the seller to be added
     */
    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    /**
     * Removes a seller from the list.
     *
     * @param seller the seller to be removed
     */
    public void removeSeller(Seller seller) {
        sellers.remove(seller);
    }

    /**
     * Returns the number of sellers in the list.
     *
     * @return the number of sellers
     */
    public int size() {
        return sellers.size();
    }

    /**
     * Checks if a username exists in the list of sellers.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        for (Seller seller : sellers) {
            if (seller.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the list of sellers.
     *
     * @return the list of sellers
     */
    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    /**
     * Displays the names of all sellers in the list.
     */
    public void displaySellers() {
        for (Seller seller : sellers) {
            System.out.println(seller.getName());
        }
    }
}
