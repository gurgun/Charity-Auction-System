import java.util.ArrayList;

/**
 * The BuyerList class manages a list of Buyer objects.
 * It provides methods to add, remove, and query buyers.
 */
public class BuyerList {
    private ArrayList<Buyer> buyers;

    /**
     * Constructs a BuyerList object.
     * Initializes the list of buyers.
     */
    public BuyerList() {
        this.buyers = new ArrayList<>();
    }

    /**
     * Adds a buyer to the list.
     *
     * @param buyer the buyer to be added
     */
    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    /**
     * Removes a buyer from the list.
     *
     * @param buyer the buyer to be removed
     */
    public void removeBuyer(Buyer buyer) {
        buyers.remove(buyer);
    }

    /**
     * Returns the number of buyers in the list.
     *
     * @return the number of buyers
     */
    public int size() {
        return buyers.size();
    }

    /**
     * Checks if a username exists in the list of buyers.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        for (Buyer buyer : buyers) {
            if (buyer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the buyer with the specified username.
     *
     * @param username the username of the buyer to find
     * @return the buyer with the specified username, or null if not found
     */
    public Buyer getBuyerByUsername(String username) {
        for (Buyer buyer : buyers) {
            if (buyer.getUsername().equals(username)) {
                return buyer;
            }
        }
        return null;
    }

    /**
     * Returns the list of buyers.
     *
     * @return the list of buyers
     */
    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    /**
     * Displays the names of all buyers in the list.
     */
    public void displayBuyers() {
        for (Buyer buyer : buyers) {
            System.out.println(buyer.getName());
        }
    }
}
