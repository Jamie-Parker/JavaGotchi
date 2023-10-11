/*
*Jamie Parker
*20101511
 */
package Assignment02;

//Handles loading and saving from GameManager to Database and back
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileController {
    //Once database is connected, loads pet info
    //save function - save pet info to database
    //load function - load pet info from database

    private PetStatus petStatus;
    private final Database database;

    public FileController() throws SQLException, Throwable {
        database = new Database();
       
    }

    public PetStatus loadPetInfo(int petID) throws SQLException {
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
System.out.println(petStatus.toString());
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

    return null; // Return null if no matching pet is found
}

    public void closeDatabaseConnection(){
        database.closeConnection();
    }
    
    
    public void savePetInfo(int petID, String petType, String petColour, String petName, Timestamp petCreated, Timestamp petSaved, int petHunger, int petTired, int petBored, int petHygiene, int petIll) throws SQLException {
        if (petID > 0) {

            database.openConnection();
            
            Map<String, Object> updates = new LinkedHashMap<>();

            updates.put("ID",petID);
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
            
            database.closeConnection();
        }
    }

}
