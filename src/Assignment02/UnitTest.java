/*
*Jamie Parker
*20101511
*UnitTest Verifies pet object Creation, Updates, Saves, Loads, TimeStamp functionality and Event trigger
 */
package Assignment02;

import java.sql.Timestamp;

public class UnitTest {
    
    public Pet testPetCreation() {//Verify Pet object creation with desired Type , Colour, Name
        return new Pet("Type", "Colour", "Name", null, null);
    }

    public PetStatus testStatusUpdate() {//Verify Pet status update
        PetStatus status = new PetStatus("type", "colour", "name", null, null, 0, 0, 0, 0, 0);
        status.setStatus(1, 2, 3, 4, 5);
        return status;
    }

    public PetStatus testSaveAndLoadFromDatabase(){//Verify Pet can be saved and loaded from the database
        FileController fileController = new FileController();
        TimeStamp timeStamp = new TimeStamp();
        fileController.savePetInfo(1, "pettype", "petcolour", "petname", timeStamp.getTimeStamp(), timeStamp.getTimeStamp(), 1, 2, 3, 4, 5);
        PetStatus loadedPet = new PetStatus(" "," "," ",timeStamp.getTimeStamp(),timeStamp.getTimeStamp(),0,0,0,0,0);
        loadedPet = fileController.loadPetInfo(1);
        return loadedPet;
    }
    
    
    public Timestamp testTimeStamp() {//Verify TimeStamp functions
        TimeStamp timeStamp = new TimeStamp();
        return timeStamp.getTimeStamp();
    }
    
    public Event testEvent(){//Verify Event trigger and value
        Event event = new Event();
        return event;
    }

}
