/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetSaver {//Uses FileIO to save pet to text file

    private static int state;

    FileIO fileIO = new FileIO();
    
    TimeStamp time = new TimeStamp();

    Scanner scanner = new Scanner(System.in);
    Display display = new Display();

    public int State() {
        if (state != 1) {
            state = 5;
        }

        return this.state;
    }

    public void savePet(Pet activePet, PetStatus activeStatus) {

        if (activePet.getPetType() == "") {//Empty Pet Checker
            System.out.println("No active pet to save! Please Create or Load a valid pet to save!");
            state = 1;//Go to Main Menu
        } else {

            String petType = activePet.getPetType();
            String petColour = activePet.getPetColour();
            String petName = activePet.getPetName();

            int petHunger = activeStatus.getHunger();
            int petTired = activeStatus.getTired();
            int petBored = activeStatus.getBored();
            int petHygiene = activeStatus.getHygiene();
            int petIllness = activeStatus.getIllness();

            String petCreated = activePet.getFirstCreated();
            String timeStamp = time.getTime();

            try {

                int option = scanner.nextInt();

                switch (option) {

                    case 1://Save to Slot 1
                        fileIO.savePetToFile(0, petType, petColour, petName, petHunger, petTired, petBored, petHygiene, petIllness, timeStamp, petCreated);
                        break;

                    case 2://Save to Slot 2
                        fileIO.savePetToFile(1, petType, petColour, petName, petHunger, petTired, petBored, petHygiene, petIllness, timeStamp, petCreated);
                        break;

                    case 3://Save to Slot 3
                        fileIO.savePetToFile(2, petType, petColour, petName, petHunger, petTired, petBored, petHygiene, petIllness, timeStamp, petCreated);
                        break;

                    case 4://Back
                        state = 1;//Go to Main Menu
                        break;

                    case 5://Quit
                        System.out.println("Quitting program...");
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException e) {//Non Integer capture
                System.out.println("Please Type a Number (1 to 5)");
                display.DisplayMenu(8);//Redisplay saver menu
                scanner.nextLine();
            }
        }
    }
}
