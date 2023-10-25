/*
*Jamie Parker
*20101511
*Event is used to determine how much time has passed since pet was last saved and reduce pet status values 1 for every 3 hours passed
*Event is intended to provide growth in the program for more Event related functions 
*/
package Assignment02;

public class Event {

    public static void updatePetStatus(PetStatus petStatus) {
        TimeStamp timeStamp = new TimeStamp();
        double hours = timeStamp.timePassed(petStatus.getSavedTime());
        int x = 1;
        int intPassed = (int) (hours / 3);

        int updatedHunger = petStatus.getHunger() - (x * intPassed);
        int updatedTired = petStatus.getTired() - (x * intPassed);
        int updatedBored = petStatus.getBored() - (x * intPassed);
        int updatedHygiene = petStatus.getHygiene() - (x * intPassed);
        int updatedIllness = petStatus.getIllness() - (x * intPassed);

        petStatus.setHunger(Math.max(updatedHunger, 0));
        petStatus.setTired(Math.max(updatedTired, 0));
        petStatus.setBored(Math.max(updatedBored, 0));
        petStatus.setHygiene(Math.max(updatedHygiene, 0));
        petStatus.setIllness(Math.max(updatedIllness, 0));
    }
}
