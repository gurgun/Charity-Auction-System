import java.util.ArrayList;

/**
 * The AntiqueCollectionList class represents a singleton list of antique collections.
 * It provides methods to add, remove, and retrieve antique collections based on their status or ID.
 */
public class AntiqueCollectionList {
    private static AntiqueCollectionList instance;
    private ArrayList<AntiqueCollection> antiqueCollections;

    /**
     * Private constructor to create the singleton instance of AntiqueCollectionList.
     */
    private AntiqueCollectionList() {
        antiqueCollections = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of AntiqueCollectionList.
     *
     * @return the singleton instance of AntiqueCollectionList
     */
    public static AntiqueCollectionList getInstance() {
        if (instance == null) {
            instance = new AntiqueCollectionList();
        }
        return instance;
    }

    /**
     * Adds an antique collection to the list.
     *
     * @param collection the antique collection to add
     */
    public void addCollection(AntiqueCollection collection) {
        antiqueCollections.add(collection);
    }

    /**
     * Gets a list of approved antique collections.
     *
     * @return a list of approved antique collections
     */
    public ArrayList<AntiqueCollection> getApprovedCollections() {
        ArrayList<AntiqueCollection> approvedCollections = new ArrayList<>();
        for (AntiqueCollection collection : antiqueCollections) {
            if (collection.getStatus().equals(Status.APPROVED)) {
                approvedCollections.add(collection);
            }
        }
        return approvedCollections;
    }

    /**
     * Gets a list of antique collections with the specified status.
     *
     * @param status the status of the antique collections to retrieve
     * @return a list of antique collections with the specified status
     */
    public ArrayList<AntiqueCollection> getCollectionsByStatus(Status status) {
        ArrayList<AntiqueCollection> result = new ArrayList<>();
        for (AntiqueCollection collection : antiqueCollections) {
            if (collection.getStatus() == status) {
                result.add(collection);
            }
        }
        return result;
    }

    /**
     * Removes an antique collection from the list.
     *
     * @param antiqueCollection the antique collection to remove
     */
    public void removeCollection(AntiqueCollection antiqueCollection) {
        antiqueCollections.remove(antiqueCollection);
    }

    /**
     * Gets the list of all antique collections.
     *
     * @return the list of all antique collections
     */
    public ArrayList<AntiqueCollection> getAntiqueCollections() {
        return antiqueCollections;
    }

    /**
     * Gets an antique collection by its ID.
     *
     * @param id the ID of the antique collection to retrieve
     * @return the antique collection with the specified ID, or null if not found
     */
    public AntiqueCollection getCollectionById(int id) {
        for (AntiqueCollection antiqueCollection : antiqueCollections) {
            if (antiqueCollection.getAntiqueCollectionID() == id) {
                return antiqueCollection;
            }
        }
        return null; // Collection not found
    }

    /**
     * Displays the names of all antique collections in the list.
     */
    public void displayCollections() {
        for (AntiqueCollection antiqueCollection : antiqueCollections) {
            System.out.println(antiqueCollection.getName());
        }
    }
}
