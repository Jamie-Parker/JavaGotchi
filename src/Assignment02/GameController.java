/*
*Jamie Parker
*20101511
 */
package Assignment02;

//Handles Actions of GUI
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController implements ActionListener {
    //Determines what happens when GUI is interacted with
    //if this button is pressed perform this action

    public GUI gui;
    public PetStatus petStatus;
    private TimeStamp timeStamp;
    public int state;
    public FileController fileController;

    public GameController(GUI gui) throws SQLException, Throwable {
        this.gui = gui;
         this.petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);
        timeStamp = new TimeStamp();
        state = 0;
        fileController = new FileController();
        ActionListener actionListener = this;

        this.gui.start.addActionListener(actionListener);
        this.gui.backButton.addActionListener(actionListener);

        this.gui.load.addActionListener(actionListener);
        this.gui.loadSlot1.addActionListener(actionListener);
        this.gui.loadSlot2.addActionListener(actionListener);
        this.gui.loadSlot3.addActionListener(actionListener);

        this.gui.create.addActionListener(actionListener);
        this.gui.createDog.addActionListener(actionListener);
        this.gui.createCat.addActionListener(actionListener);
        this.gui.createRabbit.addActionListener(actionListener);
        this.gui.createMouse.addActionListener(actionListener);
        this.gui.black.addActionListener(actionListener);
        this.gui.white.addActionListener(actionListener);
        this.gui.grey.addActionListener(actionListener);
        this.gui.brown.addActionListener(actionListener);
        this.gui.text.addActionListener(actionListener);

        this.gui.action.addActionListener(actionListener);
        this.gui.play.addActionListener(actionListener);
        this.gui.feed.addActionListener(actionListener);
        this.gui.sleep.addActionListener(actionListener);
        this.gui.clean.addActionListener(actionListener);
        this.gui.heal.addActionListener(actionListener);

        this.gui.save.addActionListener(actionListener);
        this.gui.saveSlot1.addActionListener(actionListener);
        this.gui.saveSlot2.addActionListener(actionListener);
        this.gui.saveSlot3.addActionListener(actionListener);
        
        this.gui.createNew.addActionListener(actionListener);
        
        Runtime.getRuntime().addShutdownHook(new Thread(this::closeDatabaseConnection));
    }
    private void closeDatabaseConnection() {
        if (fileController != null) {
            fileController.closeDatabaseConnection(); // Close the database connection
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Event: " + e.getActionCommand());
        System.out.println("Button clicked: " + e.getSource());

        if (e.getSource() == gui.start) {
            gui.optionScreen();
            state = 1;
        } else if (e.getSource() == gui.load) {
            gui.loadScreen();
            state = 2;
        } else if (e.getSource() == gui.create) {
            gui.createAnimalScreen();
            state = 3;
        } else if (e.getSource() == gui.loadSlot1) {
            try {
                this.petStatus = fileController.loadPetInfo(1);
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.petStatusScreen(this.petStatus);
        } else if (e.getSource() == gui.loadSlot2) {
            try {
                this.petStatus = fileController.loadPetInfo(2);
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.petStatusScreen(this.petStatus);
        } else if (e.getSource() == gui.loadSlot3) {
            try {
                this.petStatus = fileController.loadPetInfo(3);
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.petStatusScreen(this.petStatus);
        } else if (e.getSource() == gui.createDog) {
            this.petStatus.setPetType("Dog");
            state = 4;
            gui.createColourScreen();
        } else if (e.getSource() == gui.createCat) {
            this.petStatus.setPetType("Cat");
            state = 4;
            gui.createColourScreen();
        } else if (e.getSource() == gui.createRabbit) {
            this.petStatus.setPetType("Rabbit");
            state = 4;
            gui.createColourScreen();
        } else if (e.getSource() == gui.createMouse) {
           this.petStatus.setPetType("Mouse");
            state = 4;
            gui.createColourScreen();
        } else if (e.getSource() == gui.black) {
           this.petStatus.setPetColour("Black");
            state = 5;
            gui.createNameScreen();
        } else if (e.getSource() == gui.white) {
            this.petStatus.setPetColour("White");
            state = 5;
            gui.createNameScreen();
        } else if (e.getSource() == gui.grey) {
            this.petStatus.setPetColour("Grey");
            state = 5;
            gui.createNameScreen();
        } else if (e.getSource() == gui.brown) {
           this.petStatus.setPetColour("Brown");
            state = 5;
            gui.createNameScreen();

        } else if (e.getSource() == gui.backButton) {
            switch (state) {
                case 2:
                    gui.optionScreen();
                    state -= 1;
                    break;
                case 3:
                    gui.optionScreen();
                    state -= 1;
                    break;
                case 4:
                    gui.createAnimalScreen();
                    state -= 1;
                    break;
                case 5:
                    gui.createColourScreen();
                    state -= 1;
                    break;
                case 6:
                    gui.createNameScreen();
                    state -= 1;
                    break;
                case 7:
                    gui.petStatusScreen(this.petStatus);
                    state -= 1;
                    break;
                case 8:
                    gui.petStatusScreen(this.petStatus);
                    state -= 1;
                    break;
            }
        } else if (e.getSource() == gui.text) {
            this.petStatus.setPetName(gui.text.getText());
            state = 6;
            Timestamp currentTime = timeStamp.getTimeStamp();
            this.petStatus.setFirstCreated(currentTime);
            System.out.println(this.petStatus);
            gui.petStatusScreen(this.petStatus);
        } else if (e.getSource() == gui.action) {
            state = 7;
            gui.petActionsScreen(0, 0, 0, 0, 0);
        } else if (e.getSource() == gui.feed) {
            gui.petActionsScreen(2, 0, 0, 0, 0);
            this.petStatus.setStatus(2, 0, 0, 0, 0);
        } else if (e.getSource() == gui.sleep) {
            gui.petActionsScreen(0, 2, 0, 0, 0);
            this.petStatus.setStatus(0, 2, 0, 0, 0);
        } else if (e.getSource() == gui.play) {
            gui.petActionsScreen(0, 0, 2, 0, 0);
            this.petStatus.setStatus(0, 0, 2, 0, 0);
        } else if (e.getSource() == gui.clean) {
            gui.petActionsScreen(0, 0, 0, 2, 0);
            this.petStatus.setStatus(0, 0, 0, 2, 0);
        } else if (e.getSource() == gui.heal) {
            gui.petActionsScreen(0, 0, 0, 0, 2);
           this.petStatus.setStatus(0, 0, 0, 0, 2);
        } else if (e.getSource() == gui.save) {
            state = 8;
            Timestamp currentTime = timeStamp.getTimeStamp();
            this.petStatus.setSavedTime(currentTime);
            gui.petSaveScreen();
        } else if (e.getSource() == gui.saveSlot1) {
            try {
                fileController.savePetInfo(1, this.petStatus.getPetType(), this.petStatus.getPetColour(), this.petStatus.getPetName(),
                        this.petStatus.getFirstCreated(), this.petStatus.getSavedTime(), this.petStatus.getHunger(), this.petStatus.getTired(),
                        this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.petStatusScreen(petStatus);
        } else if (e.getSource() == gui.saveSlot2) {
            try {
                fileController.savePetInfo(2,this.petStatus.getPetType(), this.petStatus.getPetColour(), this.petStatus.getPetName(),
                        this.petStatus.getFirstCreated(), this.petStatus.getSavedTime(), this.petStatus.getHunger(), this.petStatus.getTired(),
                        this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.petStatusScreen(petStatus);
        } else if (e.getSource() == gui.saveSlot3) {
            try {
                fileController.savePetInfo(3, this.petStatus.getPetType(), this.petStatus.getPetColour(), this.petStatus.getPetName(),
                        this.petStatus.getFirstCreated(), this.petStatus.getSavedTime(), this.petStatus.getHunger(), this.petStatus.getTired(),
                        this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.petStatusScreen(this.petStatus);
        }else if(e.getSource() == gui.createNew){
              this.petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);
             gui.petActionsScreen(-5, -5, -5, -5, -5);
             gui.optionScreen();
        }
      System.out.println(this.petStatus.toString());
    }

    public static void main(String[] args) throws SQLException, Throwable {
        GUI gui = new GUI();
        GameController gameController = new GameController(gui);
    }
}
