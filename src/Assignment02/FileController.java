/*
*Jamie Parker
*20101511
*FileController is the controller interface between the database and the GameController
*Used to load and save pet obejcts
 */
package Assignment02;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileController {

    private PetStatus petStatus;
    private final Database database;
    private boolean fresh = false;

    public FileController(){
        database = new Database();
    }

    public PetStatus loadPetInfo(int petID) {
        database.openConnection(); //Gets pet info from the table and loads it into a petStatus obejct
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

    public String getAnimalType(int slot) {
        return database.getAnimalType(slot);
    }

    public void checkTable() {
        database.openConnection();
        database.checkTable();
        database.closeConnection();
    }

    public void savePetInfo(int petID, String petType, String petColour, String petName, Timestamp petCreated, Timestamp petSaved, int petHunger, int petTired, int petBored, int petHygiene, int petIll){
        database.openConnection();//Creates a map for table coloumn names and pet object values
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
            try {
                database.updateData(petID, updates);
            } catch (SQLException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        database.closeConnection();
    }

}
