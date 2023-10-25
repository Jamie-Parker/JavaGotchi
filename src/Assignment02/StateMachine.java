/*
*Jamie Parker
*20101511
*StateMachine is used to handle states so that a back button can be effectively used in the program
 */
package Assignment02;

public class StateMachine {

    private int currentState;
    private GUI gui;
    private GameController gameController;

    public StateMachine(GUI gui, GameController gameController) {
        this.gui = gui;
        this.gameController = gameController;
        currentState = 0;
    }

    public void setState(int newState) {
        this.currentState = newState;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void handleBackButton() {
        switch (currentState) {
            case 1://Home Screen - Uncreachable
                gui.backButton.setEnabled(false);
                break;
            case 2://Options Screen - From Create or Load
                setState(1);
                gameController.optionsGame();
                gui.backButton.setEnabled(false);
                break;
            case 3://Creation Menu - From Type
                setState(1);
                gameController.createPet();
                break;
            case 4://Creation Menu - From Colour
                setState(3);
                gameController.selectPetType("");
                break;
            case 5://Creation Menu - From Name
                setState(4);
                gameController.selectPetColour("");
                break;
            case 6://Status Screen
                gameController.goBackToStatus();
                break;
            case 7://Status Screen - From Saved
                setState(6);
                gameController.goBackToStatus();
                break;
            case 8://Action Screen
                setState(7);
                gameController.actionGame();
                break;
            case 9://Status Screen - From Option Menu - Loop after Loaded or Created Pet
                setState(8);
                gameController.goBackToStatus();
                break;
        }
    }
}
