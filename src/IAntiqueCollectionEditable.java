/**
 * The IAntiqueCollectionEditable interface provides methods for editing antique collections.
 */
public interface IAntiqueCollectionEditable {

    /**
     * Approves an antique collection by setting its base price.
     *
     * @param id        the ID of the antique collection
     * @param basePrice the base price to be set for the antique collection
     */
    void approveAntiqueCollection(int id, double basePrice);

    /**
     * Disapproves an antique collection.
     *
     * @param id the ID of the antique collection
     */
    void disapproveAntiqueCollection(int id);

    /**
     * Sets the base price for an antique collection.
     *
     * @param id        the ID of the antique collection
     * @param basePrice the base price to be set for the antique collection
     */
    void setBasePrice(int id, double basePrice);
}
