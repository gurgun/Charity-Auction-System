import java.util.Scanner;

/**
 * The UserActions interface provides methods for displaying a menu and handling user options.
 */
public interface UserActions {

    /**
     * Displays the menu options for the user.
     */
    void showMenu();

    /**
     * Handles the user's menu option selection.
     *
     * @param option                the selected option
     * @param scanner               the scanner for user input
     * @param antiqueCollectionList the list of antique collections
     * @param sellerList            the list of sellers
     */
    void handleOption(String option, Scanner scanner, AntiqueCollectionList antiqueCollectionList, SellerList sellerList);
}
