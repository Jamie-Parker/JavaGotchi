/*
*Jamie Parker
*20101511
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

    public Pet(String petType, String petColour, String petName, Timestamp savedTime, Timestamp firstCreated) {
        this.petType = petType;
        this.petColour = petColour;
        this.petName = petName;
        this.savedTime = savedTime;
        this.firstCreated = firstCreated;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
        setChanged();
        notifyObservers();
    }

    public String getPetColour() {
        return petColour;
    }

    public void setPetColour(String petColour) {
        this.petColour = petColour;
        setChanged();
        notifyObservers();
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
        setChanged();
        notifyObservers();
    }

    public Timestamp getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(Timestamp savedTime) {
        this.savedTime = savedTime;
        setChanged();
        notifyObservers();
    }

    public void setFirstCreated(Timestamp firstCreated) {
        this.firstCreated = firstCreated;
        setChanged();
        notifyObservers();
    }

    public Timestamp getFirstCreated() {
        return firstCreated;
    }

    public String getAge() {
        String age = "No age to calculate";
        if (this.getSavedTime() != null) {
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
