/*
*Jamie Parker
*20101511
*GameController is the controller interface between the model, controller and view 
*Maintains functions for action Listener to call 
 */
package Assignment02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public Event event;

    public GameController(GUI gui) throws SQLException, Throwable {
        this.petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);
        this.gui = gui;
        this.petStatus.addObserver(this.gui);
        fileController = new FileController();
        stateMachine = new StateMachine(this.gui, this);
        event = new Event();

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

    public void startGame() {
        if (fileController.freshStart()) {//FreshStart is an indicator that the table is empty
            gui.load.setEnabled(false);
        }
        gui.backButton.setEnabled(false);
        gui.optionScreen();
        stateMachine.setState(1);
        System.out.println("Game Started");
    }

    public void loadGame() {
        try {
            gui.backButton.setEnabled(true);
            fileController.checkTable();//Populates the slots with animal pictures related to the type queried
            for (int i = 0; i <= 2; i++) {
                String animalType = fileController.getAnimalType(i);
                gui.setAnimalImage(i, animalType);
            }
            gui.loadButtons();
            gui.loadScreen();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        stateMachine.setState(2);
        System.out.println("Load Screen");
    }

    public void loadPetFromSlot(int slot) {
        try {
            petStatus = fileController.loadPetInfo(slot);//Loads a pet object from the database
            this.petStatus.addObserver(this.gui);//New instance of pet so restablish observer
            event.updatePetStatus(petStatus);//update the petStatus from time passed
            gui.backButton.setEnabled(false);
            gui.petStatusScreen();
        } catch (SQLException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Pet Loaded");
    }

    public void createPet() {
        gui.backButton.setEnabled(true);
        petStatus = new PetStatus("Type", "Colour", "Name", null, null, 0, 0, 0, 0, 0);//Create new pet object
        this.petStatus.addObserver(this.gui);//new instance of pet so restablish observer
        gui.createAnimalScreen();
        gui.text.setText("");
        stateMachine.setState(2);
        System.out.println("Create Screen");
    }

    public void selectPetType(String petType) {
        petStatus.setPetType(petType);
        gui.createColourScreen();
        stateMachine.setState(3);
    }

    public void selectPetColour(String petColour) {
        petStatus.setPetColour(petColour);
        gui.createNameScreen();
        stateMachine.setState(4);
    }

    public void setPetName(String petName) {
        petStatus.setPetName(petName);
        stateMachine.setState(5);
    }

    public void setFirstCreated() {
        TimeStamp currentTime = new TimeStamp();
        petStatus.setFirstCreated(currentTime.getTimeStamp());//time stamp for first created on completion of pet creation
        gui.petStatusScreen();
        gui.backButton.setEnabled(false);
        stateMachine.setState(6);
    }

    public void actionGame() {
        gui.backButton.setEnabled(true);
        gui.petActionsScreen();
        stateMachine.setState(7);
        System.out.println("Action Screen");
    }

    public void setAction(String action) {
        switch (action) {//Handles amount Hearts should change by according to button press
            case "Feed":
                petStatus.setStatus(2, -1, 0, -1, -1);
                break;
            case "Sleep":
                petStatus.setStatus(0, 5, 0, -2, 0);
                break;
            case "Play":
                petStatus.setStatus(-1, -1, 2, -1, 0);
                break;
            case "Clean":
                petStatus.setStatus(0, 0, 0, 3, 0);
                break;
            case "Heal":
                petStatus.setStatus(0, 0, 0, 0, 2);
                break;
        }
        gui.petActionsScreen();
    }

    public void saveGame() {
        gui.backButton.setEnabled(true);
        try {//Loads pictures for slots related to current saved pet types
            fileController.checkTable();
        } catch (SQLException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
            for (int i = 0; i <= 2; i++) {
                String animalType = fileController.getAnimalType(i);
                gui.setAnimalImage(i, animalType);
            }
        gui.loadButtons();
        gui.petSaveScreen();
        stateMachine.setState(8);
        System.out.println("Save Screen");
    }

    public void setSavedSlot(int slot) {
        try {//Saves petStatus information to slot selection
            TimeStamp currentTime = new TimeStamp();
            petStatus.setSavedTime(currentTime.getTimeStamp());//Populates a last saved time stamp
            fileController.savePetInfo(slot, petStatus.getPetType(), petStatus.getPetColour(), petStatus.getPetName(),
                    petStatus.getFirstCreated(), petStatus.getSavedTime(), petStatus.getHunger(), petStatus.getTired(),
                    petStatus.getBored(), petStatus.getHygiene(), petStatus.getIllness());
            gui.petStatusScreen();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        gui.backButton.setEnabled(false);
        System.out.println("Pet Saved");
    }

    public void optionsGame() {
        if (fileController.freshStart()) {
            gui.load.setEnabled(true);//Disables Load button if is a completely new game
        }
        if (stateMachine.getCurrentState() == 1) {//Disable back button if new game (not fresh)
            gui.backButton.setEnabled(false);
        } else {
            gui.backButton.setEnabled(true);
        }
        gui.optionScreen();
        stateMachine.setState(9);
    }

    public void goBackToStatus() {
        gui.backButton.setEnabled(false);
        gui.petStatusScreen();
    }

    public void updateActionButtons() {
        gui.feed.setEnabled(petStatus.getHunger() < 5);//Diasbles action buttons if they reach 5 
        gui.sleep.setEnabled(petStatus.getTired() < 5);
        gui.play.setEnabled(petStatus.getBored() < 5);
        gui.clean.setEnabled(petStatus.getHygiene() < 5);
        gui.heal.setEnabled(petStatus.getIllness() < 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.start) {//ActionListeners for all buttons
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
            stateMachine.handleBackButton();//StatMAchine for back button handling
        }
        updateActionButtons();
    }
}
