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
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController implements ActionListener {

    public GUI gui;
    public PetStatus petStatus;
    public FileController fileController;
    public String animalPic1;
    public String animalPic2;
    public String animalPic3;
    public StateMachine stateMachine;

    public GameController(GUI gui) throws SQLException, Throwable {
        this.gui = gui;
        this.petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);
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

    public void startGame() {
        if (fileController.freshStart()) {
            gui.load.setEnabled(false);
        }
        gui.backButton.setEnabled(false);
        gui.optionScreen();
        stateMachine.setState(1);
    }

    public void loadGame() {
        try {
            gui.backButton.setEnabled(true);
            fileController.checkTable();
            gui.getAnimalPic(fileController.getAnimal1(), fileController.getAnimal2(), fileController.getAnimal3());
            gui.loadButtons();
            gui.petActionsScreen(-5, -5, -5, -5, -5);

            gui.loadSlot1.setEnabled(!fileController.slot1());
            gui.loadSlot2.setEnabled(!fileController.slot2());
            gui.loadSlot3.setEnabled(!fileController.slot3());

            gui.loadScreen();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        stateMachine.setState(2);
    }

    public void loadPetFromSlot(int slot) {
        try {
            this.petStatus = fileController.loadPetInfo(slot);
            updateStatus();
            gui.backButton.setEnabled(false);
            gui.petStatusScreen(this.petStatus);
        } catch (SQLException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createPet() {
        gui.backButton.setEnabled(true);
        petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);
        gui.createAnimalScreen();
        gui.text.setText("");
        stateMachine.setState(3);
    }

    public void selectPetType(String petType) {
        this.petStatus.setPetType(petType);
        gui.createColourScreen();
        stateMachine.setState(4);
    }

    public void selectPetColour(String petColour) {
        this.petStatus.setPetColour(petColour);
        gui.createNameScreen();
        stateMachine.setState(5);
    }

    public void setPetName(String petName) {
        this.petStatus.setPetName(petName);
        stateMachine.setState(6);
    }

    public void setFirstCreated() {
        TimeStamp currentTime = new TimeStamp();
        this.petStatus.setFirstCreated(currentTime.getTimeStamp());
        gui.petStatusScreen(petStatus);
    }

    public void actionGame() {
        gui.petActionsScreen(this.petStatus.getHunger(), this.petStatus.getTired(), this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
        stateMachine.setState(8);
    }

    public void setAction(String action) {
        switch (action) {
            case "Feed":
                gui.petActionsScreen(2, -1, 0, -1, -1);
                this.petStatus.setStatus(2, -1, 0, -1, -1);
                break;
            case "Play":
                gui.petActionsScreen(-1, -1, 2, -1, 0);
                this.petStatus.setStatus(-1, -1, 2, -1, 0);
                break;
            case "Sleep":
                gui.petActionsScreen(0, 5, 0, -2, 0);
                this.petStatus.setStatus(0, 5, 0, -2, 0);
                break;
            case "Clean":
                gui.petActionsScreen(0, 0, 0, 3, 0);
                this.petStatus.setStatus(0, 0, 0, 3, 0);
                break;
            case "Heal":
                gui.petActionsScreen(0, 0, 0, 0, 2);
                this.petStatus.setStatus(0, 0, 0, 0, 2);
                break;
        }
    }

    public void saveGame() {
        try {
            fileController.checkTable();
        } catch (SQLException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gui.getAnimalPic(fileController.getAnimal1(), fileController.getAnimal2(), fileController.getAnimal3());
        gui.loadButtons();
        gui.petSaveScreen();
        stateMachine.setState(9);
    }

    public void setSavedSlot(int slot) {
        try {
            TimeStamp currentTime = new TimeStamp();
            this.petStatus.setSavedTime(currentTime.getTimeStamp());
            fileController.savePetInfo(slot, this.petStatus.getPetType(), this.petStatus.getPetColour(), this.petStatus.getPetName(),
                    this.petStatus.getFirstCreated(), this.petStatus.getSavedTime(), this.petStatus.getHunger(), this.petStatus.getTired(),
                    this.petStatus.getBored(), this.petStatus.getHygiene(), this.petStatus.getIllness());
            gui.backButton.setEnabled(false);
            gui.petStatusScreen(this.petStatus);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void optionsGame() {
        if (fileController.freshStart()) {
            gui.load.setEnabled(true);
        }
        gui.petActionsScreen(-5, -5, -5, -5, -5);
        gui.backButton.setEnabled(true);
        gui.optionScreen();
        stateMachine.setState(1);
    }

    public void goBackToStatus() {
        gui.backButton.setEnabled(false);
        gui.petStatusScreen(this.petStatus);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.start) {
            startGame();
        } else if (e.getSource() == gui.load) {
            loadGame();
        } else if (e.getSource() == gui.create) {
            createPet();
        } else if (e.getSource() == gui.loadSlot1) {
            loadPetFromSlot(1);
        } else if (e.getSource() == gui.loadSlot2) {
            loadPetFromSlot(2);
        } else if (e.getSource() == gui.loadSlot3) {
            loadPetFromSlot(3);
        } else if (e.getSource() == gui.createDog) {
            selectPetType("Dog");
        } else if (e.getSource() == gui.createCat) {
            selectPetType("Cat");
        } else if (e.getSource() == gui.createRabbit) {
            selectPetType("Rabbit");
        } else if (e.getSource() == gui.createMouse) {
            selectPetType("Mouse");
        } else if (e.getSource() == gui.black) {
            selectPetColour("Black");
        } else if (e.getSource() == gui.white) {
            selectPetColour("White");
        } else if (e.getSource() == gui.grey) {
            selectPetColour("Grey");
        } else if (e.getSource() == gui.brown) {
            selectPetColour("Brown");
        } else if (e.getSource() == gui.text) {
            setPetName(gui.text.getText());
            setFirstCreated();
        } else if (e.getSource() == gui.action) {
            actionGame();
        } else if (e.getSource() == gui.feed) {
            setAction("Feed");
        } else if (e.getSource() == gui.sleep) {
            setAction("Sleep");
        } else if (e.getSource() == gui.play) {
            setAction("Play");
        } else if (e.getSource() == gui.clean) {
            setAction("Clean");
        } else if (e.getSource() == gui.heal) {
            setAction("Heal");
        } else if (e.getSource() == gui.save) {
            saveGame();
        } else if (e.getSource() == gui.saveSlot1) {
            setSavedSlot(1);
        } else if (e.getSource() == gui.saveSlot2) {
            setSavedSlot(2);
        } else if (e.getSource() == gui.saveSlot3) {
            setSavedSlot(3);
        } else if (e.getSource() == gui.options) {
            optionsGame();
        } else if (e.getSource() == gui.backButton) {
            stateMachine.handleBackButton();
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

    public static void main(String[] args) throws SQLException, Throwable {
        GUI gui = new GUI();
        GameController gameController = new GameController(gui);

    }
}
