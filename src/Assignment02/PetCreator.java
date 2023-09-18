/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetCreator {//Pet Object Creator / Sets Type, Colour, Name / State Control

    public static int state = 2;
    Display display = new Display();
    Scanner scanner = new Scanner(System.in);

    public int State() {
        return state;
    }

    public void petType(Pet pet) {

        try {

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
    
                case 1://Dog
                    pet.setPetType("Dog");
                    state = 2;
                    break;

                case 2://Cat
                    pet.setPetType("Cat");
                    state = 2;
                    break;

                case 3://Rabbit
                    pet.setPetType("Rabbit");
                    state = 2;
                    break;

                case 4://Mouse
                    pet.setPetType("Mouse");
                    state = 2;
                    break;

                case 5://Back
                    state = 1;
                    break;

                case 6://Quit
                    System.out.println("Quitting program...");
                    System.exit(0);
                    break;

            }
        } catch (InputMismatchException e) {//Non Integer capture
            System.out.println("Please Type a Number (1 to 5)");
            display.DisplayMenu(4);//Redisplay Pet Type Menu
            scanner.nextLine();
        }
    }

    public void petColour(Pet pet) {

        try {

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1://Black
                    pet.setPetColour("Black");
                    state = 2;
                    break;

                case 2://White
                    pet.setPetColour("White");
                    state = 2;
                    break;

                case 3://Grey
                    pet.setPetColour("Grey");
                    state = 2;
                    break;

                case 4://Brown
                    pet.setPetColour("Brown");
                    state = 2;
                    break;

                case 5://Back
                    state = 1;
                    break;

                case 6://Quit
                    System.out.println("Quitting program...");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {//Non Integer capture
            System.out.println("Please Type a Number (1 to 5)");
            display.DisplayMenu(5);//Redisplay Pet Type Menu
            scanner.nextLine();
        }
    }

    public void petName(Pet pet) {
        pet.setPetName(scanner.nextLine());
    }
}
