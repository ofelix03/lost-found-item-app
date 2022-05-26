import com.sun.tools.javac.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;



public class MainApp {

    public static void main(String[] args) throws IOException {
        // Signup or Login user
        MainApp app = new MainApp();
        Boolean userExists = app.showLoginPage();
        System.out.println();
        System.out.println();
        if (userExists) {
            System.out.println("WELCOME! YOUR LOGIN IS SUCCESSFUL");
            app.showHomePage();
        } else {
            System.out.println("SORRY! YOUR LOGIN FAILED");
        }
        System.out.println();
        System.out.println();
    }

    public void showHomePage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** The Lost & Found Manager ***");
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("1. Register a lost item");
        System.out.println("2. Claim an item");
        System.out.println("3. Exit: ");
        System.out.println("");
        System.out.print("Enter choice [1 - 3]: ");
        Integer selectedAction = scanner.nextInt();

        if (selectedAction.equals(1)) {
            this.showRegisterLostItemPage();
        } else if (selectedAction.equals(2)) {
            this.showClaimItemPage();
        } else if (selectedAction.equals(3)) {
            this.exitApp();
        }
    }

    public Boolean showLoginPage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** System Login ***");
        System.out.println("--------------------");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        UserAuthentication userAuthentication = new UserAuthentication();
        Boolean userExists = userAuthentication.checkUserExists(username, password);
        return userExists;
    }

    public Boolean showRegisterLostItemPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        System.out.println("*** Register Lost Item ***");
        System.out.println("---------------------------");
        System.out.println();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Student ID  / Staff ID: ");
        String identifcationNumber = scanner.nextLine();
        System.out.print("Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Date Found [YYYY-MM-DD]: ");
        String dateFound = scanner.nextLine();
        System.out.print("Location Found: ");
        String locationFound = scanner.nextLine();
        System.out.print("Telephone Number: ");
        String telephoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Item Description: ");
        String itemDescription = scanner.nextLine();

        System.out.println("Press [y] to save");
        String confimrationAction = scanner.nextLine();
        if (confimrationAction.toLowerCase().equals("y")) {
            FoundItem item = new FoundItem(itemName, itemDescription, locationFound, dateFound, fullName, identifcationNumber, email, telephoneNumber);
            item.registerFoundItem();
        }
        return true;
    }

    public void showClaimItemPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("");
        System.out.println("*** Register Found Item ***");
        System.out.println("---------------------------");
        System.out.println();
        System.out.print("Item Name: ");
        String itemName = scanner.nextLine();

        List<FoundItem> foundItems = FoundItem.searchForItem(itemName.strip());
        this.showSearchResults(foundItems);
    }

    public void showSearchResults(List<FoundItem> foundItems) {
        System.out.println("");
        System.out.println(String.format("Search found %s matching items: ", foundItems.size()));
        Integer counter = 1;
        for (FoundItem foundItem: foundItems) {
            System.out.println(String.format("%s. %s", counter, foundItem.itemName));
            System.out.println(String.format("\tContact: %s, %s, %s", foundItem.fullName, foundItem.email, foundItem.telephoneNumber));
            System.out.println("");
            counter++;
        }
    }

    public void exitApp() {
        Runtime.getRuntime().exit(0);
    }

}
