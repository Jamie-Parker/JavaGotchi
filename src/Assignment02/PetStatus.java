/*
*Jamie Parker
*20101511
*PetStatus is an extension of pet that provides variables for the status of the object
*Setters ensure that int values stay within bounds 0 to 5
*
*/
package Assignment02;

import java.sql.Timestamp;

public class PetStatus extends Pet {
    private int hunger;
    private int tiredness;
    private int boredness;
    private int hygiene;
    private int illness;

    public PetStatus(String petType, String petColour, String petName, Timestamp savedTime, Timestamp firstCreated, int hunger, int tiredness, int boredness, int hygiene, int illness) {
        super(petType, petColour, petName, savedTime, firstCreated);
        this.hunger = hunger;
        this.tiredness = tiredness;
        this.boredness = boredness;
        this.hygiene = hygiene;
        this.illness = illness;
        setChanged();
        notifyObservers(this);
    }

    public void setStatus(int hunger, int tiredness, int boredness, int hygiene, int illness) {
        setHunger(getHunger() + hunger);
        setTired(getTired() + tiredness);
        setBored(getBored() + boredness);
        setHygiene(getHygiene() + hygiene);
        setIllness(getIllness() + illness);
        setChanged();
        notifyObservers(this);
    }

    public void setHunger(int hunger) {
        if (hunger > 5)//5 Hungry / 0 Full
        {
            hunger = 5;
        }
        if (hunger < 0) {
            hunger = 0;
        }
        this.hunger = hunger;
        setChanged();
        notifyObservers(hunger);
    }

    public int getHunger() {
        return this.hunger;
    }

    public void setTired(int tiredness) {
        if (tiredness > 5)//5 Tired / 0 Rested
        {
            tiredness = 5;
        }
        if (tiredness < 0) {
            tiredness = 0;
        }
        this.tiredness = tiredness;
        setChanged();
        notifyObservers(tiredness);
    }

    public int getTired() {
        return this.tiredness;
    }

    public void setBored(int boredness) {
        if (boredness > 5) //5 Bored / 0 Content
        {
            boredness = 5;
        }
        if (boredness < 0) {
            boredness = 0;
        }
        this.boredness = boredness;
        setChanged();
        notifyObservers(boredness);
    }

    public int getBored() {
        return this.boredness;
    }

    public void setHygiene(int hygiene) {
        if (hygiene > 5)//5 Clean / 0 Dirty
        {
            hygiene = 5;
        }
        if (hygiene < 0) {
            hygiene = 0;
        }
        this.hygiene = hygiene;
        setChanged();
        notifyObservers(hygiene);
    }

    public int getHygiene() {
        return this.hygiene;
    }

    public void setIllness(int illness) {
        if (illness > 5) //5 Sick / 0 Healthy
        {
            illness = 5;
        }
        if (illness < 0) {
            illness = 0;
        }
        this.illness = illness;
        setChanged();
        notifyObservers(illness);
    }

    public int getIllness() {
        return this.illness;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHunger: " + this.hunger
                + "\nTiredness: " + this.tiredness
                + "\nBoredness: " + this.boredness
                + "\nHygiene: " + this.hygiene
                + "\nIllness: " + this.illness + "\n";
    }
}
