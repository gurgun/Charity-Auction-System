/**
 * The AntiqueCollection class represents a collection of antiques in the auction system.
 */
public class AntiqueCollection {
    private int antiqueCollectionID;
    private String name;
    private String type;
    private int quantity;
    private String conditionDescription;
    private double basePrice;
    private Status status;
    private double soldPrice;
    private int sellerID;
    private String buyerUsername;

    /**
     * Constructs an AntiqueCollection object with the specified details.
     *
     * @param antiqueCollectionID   the ID of the antique collection
     * @param name                  the name of the antique collection
     * @param type                  the type of the antique collection
     * @param quantity              the quantity of items in the antique collection
     * @param conditionDescription  the condition description of the antique collection
     * @param basePrice             the base price of the antique collection
     * @param status                the status of the antique collection
     * @param sellerID              the ID of the seller who owns the antique collection
     */
    public AntiqueCollection(int antiqueCollectionID, String name, String type, int quantity, String conditionDescription, double basePrice, Status status, int sellerID) {
        this.antiqueCollectionID = antiqueCollectionID;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.conditionDescription = conditionDescription;
        this.basePrice = basePrice;
        this.status = status;
        this.sellerID = sellerID;
    }

    /**
     * Gets the ID of the antique collection.
     *
     * @return the ID of the antique collection
     */
    public int getAntiqueCollectionID() {
        return antiqueCollectionID;
    }

    /**
     * Sets the ID of the antique collection.
     *
     * @param antiqueCollectionID the new ID of the antique collection
     */
    public void setAntiqueCollectionID(int antiqueCollectionID) {
        this.antiqueCollectionID = antiqueCollectionID;
    }

    /**
     * Gets the name of the antique collection.
     *
     * @return the name of the antique collection
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the antique collection.
     *
     * @param name the new name of the antique collection
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of the antique collection.
     *
     * @return the type of the antique collection
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the antique collection.
     *
     * @param type the new type of the antique collection
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the quantity of items in the antique collection.
     *
     * @return the quantity of items in the antique collection
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of items in the antique collection.
     *
     * @param quantity the new quantity of items in the antique collection
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the condition description of the antique collection.
     *
     * @return the condition description of the antique collection
     */
    public String getConditionDescription() {
        return conditionDescription;
    }

    /**
     * Sets the condition description of the antique collection.
     *
     * @param conditionDescription the new condition description of the antique collection
     */
    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    /**
     * Gets the base price of the antique collection.
     *
     * @return the base price of the antique collection
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price of the antique collection.
     *
     * @param basePrice the new base price of the antique collection
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Gets the status of the antique collection.
     *
     * @return the status of the antique collection
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the antique collection.
     *
     * @param status the new status of the antique collection
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the sold price of the antique collection.
     *
     * @return the sold price of the antique collection
     */
    public double getSoldPrice() {
        return soldPrice;
    }

    /**
     * Sets the sold price of the antique collection.
     *
     * @param soldPrice the new sold price of the antique collection
     */
    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    /**
     * Gets the ID of the seller who owns the antique collection.
     *
     * @return the ID of the seller who owns the antique collection
     */
    public int getSellerID() {
        return sellerID;
    }

    /**
     * Sets the ID of the seller who owns the antique collection.
     *
     * @param sellerID the new ID of the seller who owns the antique collection
     */
    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    /**
     * Gets the username of the buyer who purchased the antique collection.
     *
     * @return the username of the buyer who purchased the antique collection
     */
    public String getBuyerUsername() {
        return buyerUsername;
    }

    /**
     * Sets the username of the buyer who purchased the antique collection.
     *
     * @param buyerUsername the new username of the buyer who purchased the antique collection
     */
    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    /**
     * Returns a string representation of the antique collection.
     *
     * @return a string representation of the antique collection
     */
    @Override
    public String toString() {
        return "Collection ID: " + antiqueCollectionID +
                ", Name: " + name +
                ", Type: " + type +
                ", Quantity: " + quantity +
                ", Condition Description: " + conditionDescription +
                ", Base Price: " + basePrice +
                ", Status: " + status +
                ", Sold Price: " + soldPrice;
    }
}
