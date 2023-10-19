/*
*Jamie Parker
*20101511
 */
package Assignment02;

//Handles Actions of GUI
//Controller
//Mediates between model and view changes to status updates view
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController implements ActionListener {

    public GUI gui;
    public PetStatus petStatus;
    public int state;
    public FileController fileController;
    public String animalPic1;
    public String animalPic2;
    public String animalPic3;

    public GameController(GUI gui) throws SQLException, Throwable {
        this.gui = gui;
        this.petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);
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
        this.gui.options.addActionListener(actionListener);
        Runtime.getRuntime().addShutdownHook(new Thread(this::closeDatabaseConnection));
    }

    private void closeDatabaseConnection() {
        if (fileController != null) {
            fileController.closeDatabaseConnection();
        }
    }

    public void updateStatus() {
        TimeStamp timeStamp = new TimeStamp();
        double hours = timeStamp.timePassed(this.petStatus.getSavedTime());
        int intPassed = (int) (hours / 3);
        int x = 1;
        int updatedHunger = this.petStatus.getHunger() - (x * intPassed);
        if (updatedHunger < 0) {
            updatedHunger = 0;
        }
        int updatedTired = this.petStatus.getTired() - (x * intPassed);
        if (updatedTired < 0) {
            updatedTired = 0;
        }
        int updatedBored = this.petStatus.getBored() - (x * intPassed);
        if (updatedBored < 0) {
            updatedBored = 0;
        }
        int updatedHygiene = this.petStatus.getHygiene() - (x * intPassed);
        if (updatedHygiene < 0) {
            updatedHygiene = 0;
        }
        int updatedIll = this.petStatus.getIllness() - (x * intPassed);
        if (updatedIll < 0) {
            updatedIll = 0;
        }
        this.petStatus.setHunger(updatedHunger);
        this.petStatus.setTired(updatedTired);
        this.petStatus.setBored(updatedBored);
        this.petStatus.setHygiene(updatedHygiene);
        this.petStatus.setIllness(updatedIll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.start) {
            if (fileController.freshStart()) {
                gui.load.setEnabled(false);
            }
            gui.backButton.setEnabled(false);
            gui.optionScreen();
            state = 1;
        } else if (e.getSource() == gui.load) {
            try {
                gui.backButton.setEnabled(true);
                fileController.checkTable();
                gui.getAnimalPic(fileController.getAnimal1(), fileController.getAnimal2(), fileController.getAnimal3());
                gui.loadButtons();
                gui.petActionsScreen(-5, -5, -5, -5, -5);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            if (fileController.slot1()) {
                gui.loadSlot1.setEnabled(false);
            } else {
                gui.loadSlot1.setEnabled(true);
            }

            if (fileController.slot2()) {
                gui.loadSlot2.setEnabled(false);
            } else {
                gui.loadSlot2.setEnabled(true);
            }

            if (fileController.slot3()) {
                gui.loadSlot3.setEnabled(false);
            } else {
                gui.loadSlot3.setEnabled(true);
            }
            gui.loadScreen();
            state = 2;
        } else if (e.getSource() == gui.create) {
            gui.backButton.setEnabled(true);
            petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);
            gui.createAnimalScreen();
            gui.text.setText("");
            state = 3;
        } else if (e.getSource() == gui.loadSlot1) {
            try {
                this.petStatus = fileController.loadPetInfo(1);
                updateStatus();
                gui.backButton.setEnabled(false);
                gui.petStatusScreen(this.petStatus);
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == gui.loadSlot2) {
            try {
                this.petStatus = fileController.loadPetInfo(2);
                updateStatus();
                gui.backButton.setEnabled(false);
                gui.petStatusScreen(this.petStatus);
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == gui.loadSlot3) {
            try {
                this.petStatus = fileController.loadPetInfo(3);
                updateStatus();
                gui.backButton.setEnabled(false);
                gui.petStatusScreen(this.petStatus);
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        } else if (e.getSource() == gui.text) {
            this.petStatus.setPetName(gui.text.getText());
            state = 6;
            TimeStamp currentTime = new TimeStamp();
            this.petStatus.setFirstCreated(currentTime.getTimeStamp());
            state = 7;
            gui.backButton.setEnabled(false);
            gui.petStatusScreen(this.petStatus);
        } else if (e.getSource() == gui.action) {
            state = 8;
            gui.backButton.setEnabled(true);
            gui.petActionsScreen(this.petStatus.getHunger(), this.petStatus.getTired(), this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
        } else if (e.getSource()== gui.feed) {
            gui.petActionsScreen(2, -1, 0, -1, -1);
            this.petStatus.setStatus(2, -1, 0, -1, -1);
        } else if (e.getSource()== gui.sleep) {
            gui.petActionsScreen(0, 5, 0, -2, 0);
            this.petStatus.setStatus(0, 5, 0, -2, 0);
        } else if (e.getSource()== gui.play) {
            gui.petActionsScreen(-1, -1, 2, -1, 0);
            this.petStatus.setStatus(-1, -1, 2, -1, 0);
        } else if (e.getSource() == gui.clean) {
            gui.petActionsScreen(0, 0, 0, 3, 0);
            this.petStatus.setStatus(0, 0, 0, 3, 0);
        } else if (e.getSource()== gui.heal) {
            gui.petActionsScreen(0, 0, 0, 0, 2);
            this.petStatus.setStatus(0, 0, 0, 0, 2);
        } else if (e.getSource()== gui.save) {
            state = 9;
            try {
                fileController.checkTable();
            } catch (SQLException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.getAnimalPic(fileController.getAnimal1(), fileController.getAnimal2(), fileController.getAnimal3());
            gui.loadButtons();
            gui.petSaveScreen();
        } else if (e.getSource()== gui.saveSlot1) {
            state = 8;
            try {
                TimeStamp currentTime = new TimeStamp();
                this.petStatus.setSavedTime(currentTime.getTimeStamp());
                fileController.savePetInfo(1, this.petStatus.getPetType(), this.petStatus.getPetColour(), this.petStatus.getPetName(),
                        this.petStatus.getFirstCreated(), this.petStatus.getSavedTime(), this.petStatus.getHunger(), this.petStatus.getTired(),
                        this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
                gui.backButton.setEnabled(false);
                gui.petStatusScreen(this.petStatus);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource() == gui.saveSlot2) {
            state = 8;
            try {
                TimeStamp currentTime = new TimeStamp();
                this.petStatus.setSavedTime(currentTime.getTimeStamp());
                fileController.savePetInfo(2, this.petStatus.getPetType(), this.petStatus.getPetColour(), this.petStatus.getPetName(),
                        this.petStatus.getFirstCreated(), this.petStatus.getSavedTime(), this.petStatus.getHunger(), this.petStatus.getTired(),
                        this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
                gui.backButton.setEnabled(false);
                gui.petStatusScreen(this.petStatus);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource()== gui.saveSlot3) {
            state = 8;
            try {
                TimeStamp currentTime = new TimeStamp();
                this.petStatus.setSavedTime(currentTime.getTimeStamp());
                fileController.savePetInfo(3, this.petStatus.getPetType(), this.petStatus.getPetColour(), this.petStatus.getPetName(),
                        this.petStatus.getFirstCreated(), this.petStatus.getSavedTime(), this.petStatus.getHunger(), this.petStatus.getTired(),
                        this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
                gui.backButton.setEnabled(false);
                gui.petStatusScreen(this.petStatus);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (e.getSource()== gui.options) {
            if (fileController.freshStart()) {
                gui.load.setEnabled(true);
            }
            gui.petActionsScreen(-5, -5, -5, -5, -5);
            gui.backButton.setEnabled(true);
            gui.optionScreen();
            state = 10;
        } else if (e.getSource() == gui.backButton) {
            switch (state) {
                case 2://Load
                    gui.backButton.setEnabled(false);
                    gui.optionScreen();
                    state = 1;
                    break;
                case 3://Create
                    gui.backButton.setEnabled(false);
                    gui.optionScreen();
                    state = 1;
                    break;
                case 4://Type
                    gui.createAnimalScreen();
                    state -= 1;
                    break;
                case 5://Colour
                    gui.createColourScreen();
                    state -= 1;
                    break;
                case 6://Name
                    gui.createNameScreen();
                    state -= 1;
                    break;
                case 7://Status
                    gui.backButton.setEnabled(false);
                    gui.petStatusScreen(this.petStatus);
                    break;
                case 8://Actions
                    gui.backButton.setEnabled(false);
                    gui.petStatusScreen(this.petStatus);
                    state = 7;
                    break;
                case 9://Save
                    gui.petActionsScreen(this.petStatus.getHunger(), this.petStatus.getTired(), this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
                    state -= 1;
                    break;
                case 10://Options - Redo
                    gui.backButton.setEnabled(false);
                    gui.petStatusScreen(this.petStatus);
                    state = 7;
                    break;
            }

            if (this.petStatus.getHunger() == 5) {
                gui.feed.setEnabled(false);
            } else {
                gui.feed.setEnabled(true);
            }

            if (this.petStatus.getTired() == 5) {
                gui.sleep.setEnabled(false);
            } else {
                gui.sleep.setEnabled(true);
            }

            if (this.petStatus.getBored() == 5) {
                gui.play.setEnabled(false);
            } else {
                gui.play.setEnabled(true);
            }

            if (this.petStatus.getHygiene() == 5) {
                gui.clean.setEnabled(false);
            } else {
                gui.clean.setEnabled(true);
            }

            if (this.petStatus.getIllness() == 5) {
                gui.heal.setEnabled(false);
            } else {
                gui.heal.setEnabled(true);
            }
        }
    }
    
    public static void main(String[] args) throws SQLException, Throwable {
        GUI gui = new GUI();
        GameController gameController = new GameController(gui);

    }
}
