/*
*Jamie Parker
*20101511
 */
package Assignment02;

public class StateMachine {

    private int currentState;
    private GUI gui;
    private GameController gameController;

    public StateMachine(GUI gui) {
        this.gui = gui;
        this.gameController = gameController;
        currentState = 0;
    }

    public void setState(int newState) {
        currentState = newState;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void handleBackButton() {
        switch (currentState) {
            case 2:
                gui.backButton.setEnabled(false);//
                gameController.optionsGame();
                setState(1);
                break;
            case 3:
                gui.backButton.setEnabled(false);//
                gameController.optionsGame();
                setState(2);
                break;
            case 4:
                gameController.createPet();
                gameController.selectPetType("");
                setState(3);
                break;
            case 5:
                gameController.selectPetColour("");
                setState(4);
                break;
            case 6:
                gameController.setPetName("");
                setState(5);
                break;
            case 7:
                gameController.goBackToStatus();
                break;
            case 8:
                gameController.goBackToStatus();
                setState(7);
                break;
            case 9:
                gameController.actionGame();
                setState(8);
                break;
            case 10:
                gameController.goBackToStatus();
                setState(7);
                break;
        }
    }
}
