import java.util.ArrayList;

/**
 * The User class represents a user in the auction system.
 * It is an abstract class that provides common properties and methods for different types of users.
 */
public abstract class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private String name;
    private String address;
    private String contactInfo;

    /**
     * Constructs a User object with the specified user details.
     *
     * @param userId      the user ID
     * @param username    the username
     * @param password    the password
     * @param role        the role of the user
     * @param name        the name of the user
     * @param address     the address of the user
     * @param contactInfo the contact information of the user
     */
    public User(int userId, String username, String password, String role, String name, String address, String contactInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    /**
     * Logs out the user.
     */
    public void logout() {
        System.out.println("Logging out...");
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role of the user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the user.
     *
     * @return the address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     *
     * @param address the address of the user
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the contact information of the user.
     *
     * @return the contact information of the user
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact information of the user.
     *
     * @param contactInfo the contact information of the user
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * Views the collections for sellers. If the user is a buyer, it calls a different method to view collections for buyers.
     *
     * @param sellers the list of sellers
     */
    public void viewCollections(ArrayList<Seller> sellers) {
        if (this instanceof Buyer) {
            viewCollectionsForBuyers(sellers);
        } else {
            System.out.println();
            System.out.println("Collections:");
            boolean collectionsExist = false;

            for (Seller seller : sellers) {
                ArrayList<AntiqueCollection> antiqueCollections = seller.getRegisteredCollections();
                if (!antiqueCollections.isEmpty()) {
                    collectionsExist = true;
                    System.out.println("Seller: " + seller.getName());
                    for (AntiqueCollection antiqueCollection : antiqueCollections) {
                        System.out.println("Collection ID: " + antiqueCollection.getAntiqueCollectionID());
                        System.out.println("Name: " + antiqueCollection.getName());
                        System.out.println("Type: " + antiqueCollection.getType());
                        System.out.println("Quantity: " + antiqueCollection.getQuantity());
                        System.out.println("Condition Description: " + antiqueCollection.getConditionDescription());
                        System.out.println("Base Price: " + antiqueCollection.getBasePrice());
                        System.out.println("Status: " + antiqueCollection.getStatus());
                        System.out.println("-------------------------------");
                        System.out.println();
                    }
                }
            }

            if (!collectionsExist) {
                System.out.println("No collections available.");
            }
        }
    }

    /**
     * Views the collections available for buyers.
     *
     * @param sellers the list of sellers
     */
    private void viewCollectionsForBuyers(ArrayList<Seller> sellers) {
        System.out.println();
        System.out.println("Collections:");
        boolean collectionsExist = false;

        for (Seller seller : sellers) {
            ArrayList<AntiqueCollection> antiqueCollections = seller.getRegisteredCollections();
            for (AntiqueCollection antiqueCollection : antiqueCollections) {
                if (antiqueCollection.getStatus().equals(Status.APPROVED)) {
                    collectionsExist = true;
                    System.out.println("Seller: " + seller.getName());
                    System.out.println("Collection ID: " + antiqueCollection.getAntiqueCollectionID());
                    System.out.println("Name: " + antiqueCollection.getName());
                    System.out.println("Type: " + antiqueCollection.getType());
                    System.out.println("Base Price: " + antiqueCollection.getBasePrice());
                    System.out.println("-------------------------------");
                    System.out.println();
                }
            }
        }

        if (!collectionsExist) {
            System.out.println("No collections available for purchase.");
        }
    }
}
