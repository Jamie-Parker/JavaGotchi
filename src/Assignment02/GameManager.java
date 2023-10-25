/*
*Jamie Parker
*20101511
*GameManager is the container for the active model, view and controller for JavaGotchi
 */
package Assignment02;

public class GameManager {

    public static void main(String[] args) throws Throwable {
   
        GUI gui = new GUI();
        GameController gameController = new GameController(gui);
    }
}
