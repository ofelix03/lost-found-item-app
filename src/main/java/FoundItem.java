import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FoundItem {
    public  String identificationNumber;
    public  String fullName;
    public  String email;

    public String telephoneNumber;
    public  String dateFound;
    public  String locationFound;
    public  String itemDescription;
    public  String itemID;
    public String itemName;

    private BufferedReader in = null;
    private BufferedWriter out = null;
    private static String foundItemsFilePath = "/home/felix/IdeaProjects/missing_items_app/src/main/resources/found_items.txt";

    public FoundItem(String itemName, String itemDescription, String locationFound, String dateFound, String fullName, String identificationNumber, String email, String telephoneNumber) {
        this.itemID = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.dateFound = dateFound;
        this.locationFound = locationFound;
        this.fullName = fullName;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public FoundItem(String itemID, String itemName, String itemDescription, String locationFound, String dateFound, String fullName, String identificationNumber, String email, String telephoneNumber) {
        this.itemID = itemID != null? itemID : UUID.randomUUID().toString();
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.dateFound = dateFound;
        this.locationFound = locationFound;
        this.fullName = fullName;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public static List<FoundItem> searchForItem(String itemName) {
        List<String> dbFoundItems;
        ArrayList<FoundItem> foundItems = new ArrayList<FoundItem>();
        FoundItem foundItem;
        Boolean isMatched = false;
        try {
            dbFoundItems = Files.readAllLines(Path.of(FoundItem.foundItemsFilePath));
            for (String item: dbFoundItems) {
                String[] dbItem = item.split(",");
                foundItem = FoundItem.convertToObject(dbItem);
                isMatched = foundItem.itemName.equals(itemName);
                if (isMatched) {
                    System.out.println("isMatched " + isMatched);
                    System.out.println(foundItem.itemName);
                    foundItems.add(foundItem);
                }
                isMatched = false;
            }
            System.out.println(foundItems);
            return foundItems;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerFoundItem() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.foundItemsFilePath, true));
            String itemStr = this.convertToString();
            System.out.println("itemStr == " + itemStr);
            out.append(itemStr);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertToString() {
        String itemID = this.itemID.toString().strip();
        String itemName = this.itemName.strip();
        String itemDescription = this.itemDescription.strip();
        String locationFound = this.locationFound.strip();
        String dateFound = this.dateFound.strip();
        String fullName = this.fullName.strip();
        String identificationNumber = this.identificationNumber.strip();
        String email = this.email.strip();
        String telephoneNumber = this.telephoneNumber.strip();

        String itemStr = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", itemID, itemName, itemDescription, locationFound, dateFound, fullName, identificationNumber, email, telephoneNumber);
        System.out.println("");
        System.out.println("Item successfully registered");
        return itemStr;
    }

    public static FoundItem convertToObject(String[] foundItem) {
        String itemID = foundItem[0];
        String itemName = foundItem[1];
        String itemDescription = foundItem[2];
        String locationFound = foundItem[3];
        String dateFound = foundItem[4];
        String fullName = foundItem[5];
        String identificationNumber = foundItem[6];
        String email = foundItem[7];
        String telephoneNumber = foundItem[8];

        return new FoundItem(itemID, itemName, itemDescription, locationFound, dateFound, fullName, identificationNumber, email, telephoneNumber);

    }
}
