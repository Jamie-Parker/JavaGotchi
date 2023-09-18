/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.InputMismatchException;

public class PetLoader {//Uses FileIO to load text file Strings into a Pet object

    private static int state;
    private static Pet loadedPet;
    private static PetStatus loadedStatus;
    private static int option;

    Scanner scanner = new Scanner(System.in);
    Display display = new Display();
    FileIO fileIO = new FileIO();

    public int State() {
        if (state != 1) {
            state = 5;
        }

        return this.state;
    }

    public Pet getLoadedPet() {
        return loadedPet;
    }

    public PetStatus getLoadedStatus() {
        return loadedStatus;
    }

    public void loadPet() {

        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {//Non Integer capture
            System.out.println("Please Type a Number (1 to 5)");
            display.DisplayMenu(7);//Redisplay Loader Menu
            scanner.nextLine();
        }

        switch (option) {
            case 1:
            case 2:
            case 3:
                String petData = fileIO.loadPetFromFile(option-1);

                if (petData != null) {

                    List<String> petAttributesList = Arrays.asList(petData.split(",", 10));

                    String petType = petAttributesList.get(0);
                    String petColour = petAttributesList.get(1);
                    String petName = petAttributesList.get(2);
                    int petHunger = Integer.parseInt(petAttributesList.get(3));
                    int petTired = Integer.parseInt(petAttributesList.get(4));
                    int petBored = Integer.parseInt(petAttributesList.get(5));
                    int petHygiene = Integer.parseInt(petAttributesList.get(6));
                    int petIllness = Integer.parseInt(petAttributesList.get(7));
                    String firstCreated = petAttributesList.get(8);
                    String savedTime = petAttributesList.get(9);

                    if (petType == "") {//Empty pet checker
                        System.out.println("Error! Loaded pet is empty! Please Create or Load a valid pet!\n");
                        state = 1;
                    } else {
                        loadedPet = new Pet(petType, petColour, petName, savedTime, firstCreated);
                        loadedStatus = new PetStatus(petHunger, petTired, petBored, petHygiene, petIllness);
                    }
                }
                break;

            case 4://Back
                state = 1;//Go to Main Menu
                break;

            case 5://Quit
                System.out.println("Quitting program...");
                System.exit(0);
                break;
        }
    }
}
