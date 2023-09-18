/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {//Client / Program Loop / Instance initialising / Error capture / 

    private static int state = 1;
    private static int option;
    private static Pet activePet;
    private static PetStatus activeStatus;
    
    public static void main(String[] args) {

        //Class Initialisers
        PetCreator petCreator = new PetCreator();
        PetLoader petLoader = new PetLoader();
        PetSaver petSaver = new PetSaver();
        PetActions petActions = new PetActions();
        Display display = new Display();
        TimeStamp time = new TimeStamp();

        //Pet Instances
        activePet = new Pet("", "", "", "Not yet saved!\n", "");
        activeStatus = new PetStatus(0, 0, 0, 0, 0);
        
        Scanner scanner = new Scanner(System.in);
        display.DisplayMenu(0);//Header

        while (state > 0) {
            
            switch (state) {
                case 1://Main Menu

                    display.DisplayMenu(1);
                    
                    try {
                        option = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (option) {
                            case 1:
                                state = 5;//Pet Action Menu
                                break;
                            case 2:
                                state = 2;//Pet Creation
                                break;
                            case 3:
                                state = 3;//Pet Loader
                                break;
                            case 4:
                                state = 4;//Pet Saver
                                break;
                            case 5:
                                System.out.print("\nQuitting program...");
                                System.exit(0);
                                break;
                        }
                    } catch (InputMismatchException e) {//Non Integer capture
                        System.out.println("Please Type a Number (1 to 5)");
                        scanner.nextLine();
                    }
                    break;
                
                case 2://Pet Creator 
                    activePet = new Pet("", "", "", "Not yet saved!\n", "");
                    activeStatus = new PetStatus(0, 0, 5, 5, 0);//New Pet - Is Bored and Clean Default
                    
                    try {//Pet Type

                        display.DisplayMenu(4);
                        petCreator.petType(activePet);
                        state = petCreator.State();//Back to Main menu checker
                        if (state == 1) //Enforcer
                        break;  
                    } catch (InputMismatchException e) {//Non Integer capture
                        System.out.println("Please Type a Number (1 to 5)");
                        scanner.nextLine();
                    }
                                        
                    try {//Pet Colour

                        display.DisplayMenu(5);
                        petCreator.petColour(activePet);
                        state = petCreator.State();//Back to Main menu checker
                        if (state == 1)//Enforcer
                        break;
                    } catch (InputMismatchException e) {//Non Integer capture
                        System.out.println("Please Type a Number (1 to 5)");
                        scanner.nextLine();
                    }
                    
                    
                    //Pet Name
                    display.DisplayMenu(6);
                    petCreator.petName(activePet);
                    
                    TimeStamp currTime = new TimeStamp();
                    String currentTime = currTime.getTime();
                    
                    activePet.setFirstCreated(currentTime);
                    
                    System.out.println("New pet Created!");
                    display.DisplayASCII(activePet.getPetType());
                    System.out.println(activePet.toString());
                    state = 5;// Go to Pet Action Menu
                    break;
                
                case 3://Pet Loader

                    display.DisplayMenu(7);
                    
                    petLoader.loadPet();
                    activePet = petLoader.getLoadedPet();
                    activeStatus = petLoader.getLoadedStatus();
                    
                    if (activePet == null) {//If null reanitialise
                        activePet = new Pet("", "", "", "Not yet saved!\n", "");
                        activeStatus = new PetStatus(0, 0, 0, 0, 0);
                    }
                    state = petLoader.State();//Back to Main menu checker
                    if (state == 1) //Enforcer
                    {
                        break;
                    }
                    
                    activeStatus = petActions.timePassedEvent(activeStatus, activePet);
                    state = petLoader.State();
                    break;
                
                case 4://Pet Saver

                    display.DisplayMenu(8);
                    
                    petSaver.savePet(activePet, activeStatus);
                    state = petSaver.State();//Back to Main menu checker
                    if (state == 1) //Enforcer
                    {
                        break;
                    }
                    break;
                
                case 5://Action Menu

                    if (activePet.getPetType() == "") {
                        System.out.println("No Active Pet. Please Create or Load a Pet first.\n");
                        state = 1;
                    } else {
                        
                        display.DisplayMenu(2);
                        
                        try {
                            
                            option = scanner.nextInt();
                            
                            switch (option) {
                                
                                case 1://Show Pet Details / ASCII / Details / Age

                                    display.DisplayASCII(activePet.getPetType());
                                    System.out.println(activePet.toString());
                                    long timeDiff = time.getTimeDifference(activePet.getFirstCreated());
                                    String age = time.ageCalc(timeDiff);
                                    System.out.println(age);
                                    
                                    break;
                                
                                case 2://Show Pet Status
                                    System.out.println(activeStatus.toString());
                                    break;
                                
                                case 3://Pet Activity
                                    state = 6;// Go to pet activity menu
                                    break;
                                
                                case 4://Back
                                    state = 1;//Main Menu
                                    break;
                                
                                case 5://Quit
                                    System.out.print("\nQuitting program...");
                                    System.exit(0);
                                    break;
                            }
                        } catch (InputMismatchException e) {//Non Integer capture
                            System.out.println("Please Type a Number (1 to 5)");
                            scanner.nextLine();
                        }
                    }
                    break;
                
                case 6://Pet Activity
                    display.DisplayMenu(3);
                    petActions.petActivity(activeStatus, activePet);
                    state = petActions.State();//Back to Main menu checker
                    break;
            }
        }
    }
}
