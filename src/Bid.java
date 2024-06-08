/**
 * The Bid class represents a bid placed by a buyer for an antique collection in the auction system.
 */
public class Bid {
    private static int nextId = 1;

    private int bidID;
    private String buyerUsername;
    private int collectionID;
    private double amount;

    /**
     * Constructs a Bid object with the specified details.
     *
     * @param buyerUsername the username of the buyer placing the bid
     * @param collectionID  the ID of the antique collection being bid on
     * @param amount        the amount of the bid
     */
    public Bid(String buyerUsername, int collectionID, double amount) {
        this.bidID = nextId++;
        this.buyerUsername = buyerUsername;
        this.collectionID = collectionID;
        this.amount = amount;
    }

    /**
     * Gets the next ID to be assigned to a new bid.
     *
     * @return the next ID to be assigned to a new bid
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Sets the next ID to be assigned to a new bid.
     *
     * @param nextId the new next ID to be assigned to a new bid
     */
    public static void setNextId(int nextId) {
        Bid.nextId = nextId;
    }

    /**
     * Gets the ID of the bid.
     *
     * @return the ID of the bid
     */
    public int getBidID() {
        return bidID;
    }

    /**
     * Sets the ID of the bid.
     *
     * @param bidID the new ID of the bid
     */
    public void setBidID(int bidID) {
        this.bidID = bidID;
    }

    /**
     * Gets the username of the buyer placing the bid.
     *
     * @return the username of the buyer placing the bid
     */
    public String getBuyerUsername() {
        return buyerUsername;
    }

    /**
     * Sets the username of the buyer placing the bid.
     *
     * @param buyerUsername the new username of the buyer placing the bid
     */
    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    /**
     * Gets the ID of the antique collection being bid on.
     *
     * @return the ID of the antique collection being bid on
     */
    public int getCollectionID() {
        return collectionID;
    }

    /**
     * Sets the ID of the antique collection being bid on.
     *
     * @param collectionID the new ID of the antique collection being bid on
     */
    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    /**
     * Gets the amount of the bid.
     *
     * @return the amount of the bid
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the bid.
     *
     * @param amount the new amount of the bid
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
