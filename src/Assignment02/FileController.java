/*
*Jamie Parker
*20101511
 */
package Assignment02;
//Handles loading and saving from GameManager to Database and back
//Controller
//Mediates between model and view changes to status updates view
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileController {

    private PetStatus petStatus;
    private final Database database;
    private boolean fresh = false;

    public FileController() throws SQLException, Throwable {
        database = new Database();
    }

    public PetStatus loadPetInfo(int petID) throws SQLException {
        database.openConnection();
        List<Map<String, Object>> petInfoList = database.getInfo();
        for (Map<String, Object> petInfo : petInfoList) {
            int id = (int) petInfo.get("ID");
            if (id == petID) {
                try {
                    petStatus = new PetStatus(
                            (String) petInfo.get("TYPE"),
                            (String) petInfo.get("COLOUR"),
                            (String) petInfo.get("NAME"),
                            (Timestamp) petInfo.get("CREATED"),
                            (Timestamp) petInfo.get("SAVED"),
                            (int) petInfo.get("HUNGER"),
                            (int) petInfo.get("TIRED"),
                            (int) petInfo.get("BORED"),
                            (int) petInfo.get("HYGIENE"),
                            (int) petInfo.get("ILL")
                    );
                    return petStatus;
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return null;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            }
        }
        database.closeConnection();
        return null; // Return null if no matching pet is found
    }

    public void closeDatabaseConnection() {
        database.closeConnection();
    }

    public boolean freshStart() {
        fresh = database.getFreshStart();
        return fresh;
    }

    public boolean slot1() {
        boolean slot1 = database.getSlot1();
        return slot1;
    }

    public boolean slot2() {
        boolean slot2 = database.getSlot2();
        return slot2;
    }

    public boolean slot3() {
        boolean slot3 = database.getSlot3();
        return slot3;
    }

    public String getAnimal1() {
        String animal1 = database.getAnimal1();
        return animal1;
    }

    public String getAnimal2() {
        String animal2 = database.getAnimal2();
        return animal2;
    }

    public String getAnimal3() {
        String animal3 = database.getAnimal3();
        return animal3;
    }

    public void checkTable() throws SQLException {
        database.openConnection();
        database.checkTable();
        database.closeConnection();
    }

    public void savePetInfo(int petID, String petType, String petColour, String petName, Timestamp petCreated, Timestamp petSaved, int petHunger, int petTired, int petBored, int petHygiene, int petIll) throws SQLException {
        database.openConnection();
        if (petID > 0) {
            Map<String, Object> updates = new LinkedHashMap<>();
            updates.put("ID", petID);
            updates.put("TYPE", petType);
            updates.put("COLOUR", petColour);
            updates.put("NAME", petName);
            updates.put("CREATED", petCreated);
            updates.put("SAVED", petSaved);
            updates.put("HUNGER", petHunger);
            updates.put("TIRED", petTired);
            updates.put("BORED", petBored);
            updates.put("HYGIENE", petHygiene);
            updates.put("ILL", petIll);
            database.updateData(petID, updates);
        }
        database.closeConnection();
    }

}
