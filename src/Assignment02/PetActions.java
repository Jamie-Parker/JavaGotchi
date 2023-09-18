/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetActions {//Pet actions / Feed, Sleep, Play, Clean, Heal / Time passed - increases Status over time 

    private static int state;

    private static int hungerLevel;
    private static int tiredLevel;
    private static int boredLevel;
    private static int hygieneLevel;
    private static int illLevel;

    TimeStamp time = new TimeStamp();
    Scanner scanner = new Scanner(System.in);

    public int State() {
        if (state != 5) {
            state = 6;
        }

        return this.state;
    }

    public void feed(PetStatus petStatus, Pet pet) {

        hungerLevel = petStatus.getHunger();
        tiredLevel = petStatus.getTired();

        if (hungerLevel <= 0) {
            System.out.println(pet.getPetName() + " is full!\n");
        } else {
            System.out.println(pet.getPetName() + " is eating!\n");
            petStatus.setHunger(hungerLevel - 2);
            petStatus.setTired(tiredLevel + 1);
        }
    }

    public void sleep(PetStatus petStatus, Pet pet) {

        tiredLevel = petStatus.getTired();

        if (tiredLevel <= 0) {
            System.out.println(pet.getPetName() + " is not tired!\n");
        } else {
            System.out.println(pet.getPetName() + " is sleeping!\n");
            petStatus.setTired(0);
        }
    }

    public void play(PetStatus petStatus, Pet pet) {

        boredLevel = petStatus.getBored();
        tiredLevel = petStatus.getTired();
        hygieneLevel = petStatus.getHygiene();
        hungerLevel = petStatus.getHunger();

        if (boredLevel <= 0) {
            System.out.println(pet.getPetName() + " has played enough today!\n");
        } else {
            System.out.println(pet.getPetName() + " is having fun!\n");
            petStatus.setBored(boredLevel - 2);
            petStatus.setTired(tiredLevel + 1);
            petStatus.setHygiene(hygieneLevel - 1);
            petStatus.setHunger(hungerLevel + 1);
        }
    }

    public void clean(PetStatus petStatus, Pet pet) {

        hygieneLevel = petStatus.getHygiene();

        if (hygieneLevel >= 5) {
            System.out.println(pet.getPetName() + " is squeaky clean!\n");
        } else {
            System.out.println(pet.getPetName() + " is bathing!\n");
            petStatus.setHygiene(hygieneLevel + 5);
        }
    }

    public void heal(PetStatus petStatus, Pet pet) {

        illLevel = petStatus.getIllness();
        hygieneLevel = petStatus.getHygiene();

        if (illLevel <= 0) {
            System.out.println(pet.getPetName() + " is feeling healthy!\n");
        } else {
            System.out.println(pet.getPetName() + " is healing!\n");
            petStatus.setIllness(illLevel - 5);
            petStatus.setHygiene(hygieneLevel + 2);
            petStatus.setHygiene(hygieneLevel - 1);
        }
    }

    public void petActivity(PetStatus petStatus, Pet pet) {
        
        try {

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1://Feed
                    feed(petStatus, pet);
                    state = 6;
                    break;

                case 2://Sleep
                    sleep(petStatus, pet);
                    state = 6;
                    break;

                case 3://Play
                    play(petStatus, pet);
                    state = 6;
                    break;

                case 4://Clean
                    clean(petStatus, pet);
                    state = 6;
                    break;

                case 5://Heal
                    heal(petStatus, pet);
                    state = 6;
                    break;

                case 6://Back
                    state = 5;//Go to Pet Action Menu
                    break;

                case 7://Quit
                    System.out.println("Quitting program...");
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {//Non Integer capture
            System.out.println("Please Type a Number (1 to 7)");
            state = 6;
            scanner.nextLine();
        }
       
    }

    public PetStatus timePassedEvent(PetStatus petStatus, Pet pet) {//Changes status from last saved time

        long minutes = time.getTimeDifference(pet.getSavedTime());
        long hoursDifference = minutes / 60;

        if (hoursDifference >= 6) {//Every 6 hours increase values
            int increaseAmount = (int) (hoursDifference / 6);

            for (int i = 0; i < increaseAmount; i++) {
                petStatus.setHunger(petStatus.getHunger() + i);
                petStatus.setTired(petStatus.getTired() + i);
                petStatus.setBored(petStatus.getBored() + i);
                petStatus.setHygiene(petStatus.getHygiene() - i);
                petStatus.setIllness(petStatus.getIllness() + i);
            }
        }
        return petStatus;
    }
}
