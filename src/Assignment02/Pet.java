/*
*Jamie Parker
*20101511
*Pet Class is a Model Object that sets up the String and Timestamp variables for the object.
*Pet utilises observable to notify GUI of changes to the object
*Pet consists of a constructor, get/setters, an age calculation function and a toString
 */
package Assignment02;

import java.util.Observable;
import java.sql.Timestamp;

public class Pet extends Observable {

    private String petType;
    private String petColour;
    private String petName;
    private Timestamp savedTime;
    private Timestamp firstCreated;

    public Pet(String petType, String petColour, String petName, Timestamp firstCreated, Timestamp savedTime) {
        this.petType = petType;
        this.petColour = petColour;
        this.petName = petName;
        this.firstCreated = firstCreated;
        this.savedTime = savedTime;
        setChanged();
        notifyObservers(this);
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
        setChanged();
        notifyObservers(petType);
    }

    public String getPetColour() {
        return petColour;
    }

    public void setPetColour(String petColour) {
        this.petColour = petColour;
        setChanged();
        notifyObservers(petColour);
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
        setChanged();
        notifyObservers(petName);
    }
    
    public void setFirstCreated(Timestamp firstCreated) {
        this.firstCreated = firstCreated;
        setChanged();
        notifyObservers(firstCreated);
    }

    public Timestamp getFirstCreated() {
        return firstCreated;
    }
    public Timestamp getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(Timestamp savedTime) {
        this.savedTime = savedTime;
        setChanged();
        notifyObservers(savedTime);
    }

    public String getAge() {
        String age = "No age to calculate";
        if (this.getSavedTime() != null) {//Enusres that a pet object has previously been saved before calculating any meaningful age value
            TimeStamp timeStamp = new TimeStamp();
            Timestamp createdTime = this.getFirstCreated();
            Long difference = timeStamp.getTimeDifference(createdTime);
            age = timeStamp.ageCalc(difference);
        }
        return age;
    }

    @Override
    public String toString() {
        return "\nPet Name: " + this.getPetName()
                + "\nPet Type: " + this.getPetType()
                + "\nPet Colour: " + this.getPetColour()
                + "\nPet Created (Y:M:D:H:M:S): " + this.getFirstCreated()
                + "\nPet last saved (Y:M:D:H:M:S): " + this.getSavedTime()
                + "\nPet Age: " + this.getAge();
    }
}
