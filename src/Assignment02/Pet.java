/*
*Jamie Parker
*20101511
 */
package Assignment02;



//Pet Object that holds Pet Details


public class Pet {

    private String petType;
    private String petColour;
    private String petName;
    private String savedTime;
    private String firstCreated;

    public Pet(String petType, String petColour, String petName, String savedTime, String firstCreated) {
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
    }

    public String getPetColour() {
        return petColour;
    }

    public void setPetColour(String petColour) {
        this.petColour = petColour;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSavedTime() {
        return savedTime;
    }

    public void setFirstCreated(String firstCreated) {
        this.firstCreated = firstCreated;
    }

    public String getFirstCreated() {
        return firstCreated;
    }

    @Override
    public String toString() {
        return "\nPet Name: " + this.getPetName()
                + "\nPet Type: " + this.getPetType()
                + "\nPet Colour: " + this.getPetColour()
                + "\nPet Created (Y:M:D:H:M:S): " + this.getFirstCreated()
                + "\nPet last saved (Y:M:D:H:M:S): " + this.getSavedTime();
    }
}
