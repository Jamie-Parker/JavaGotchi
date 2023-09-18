/*
*Jamie Parker
*20101511
 */
package Assignment02;


public class PetStatus {//Pet Object Creator/ Status hunger, tired, bored, hygiene, illness / Boundary Control X/5

    private int hunger;
    private int tiredness;
    private int boredness;
    private int hygiene;
    private int illness;

    public PetStatus(int hunger, int tiredness, int boredness, int hygiene, int illness) {
        this.hunger = hunger;
        this.tiredness = tiredness;
        this.boredness = boredness;
        this.hygiene = hygiene;
        this.illness = illness;
    }

    public void setHunger(int hunger) {
        if (hunger > 5) {//5 Hungry / 0 Full
            hunger = 5;
        }

        if (hunger < 0) {
            hunger = 0;
        }

        this.hunger = hunger;
    }

    public int getHunger() {
        return this.hunger;
    }

    public void setTired(int tiredness) {
        if (tiredness > 5) {//5 Tired / 0 Rested
            tiredness = 5;
        }

        if (tiredness < 0) {
            tiredness = 0;
        }

        this.tiredness = tiredness;
    }

    public int getTired() {
        return this.tiredness;
    }

    public void setBored(int boredness) {
        if (boredness > 5) {//5 Bored / 0 Content
            boredness = 5;
        }

        if (boredness < 0) {
            boredness = 0;
        }

        this.boredness = boredness;
    }

    public int getBored() {
        return this.boredness;
    }

    public void setHygiene(int hygiene) {
        if (hygiene > 5) {//5 Clean / 0 Dirty
            hygiene = 5;
        }

        if (hygiene < 0) {
            hygiene = 0;
        }

        this.hygiene = hygiene;
    }

    public int getHygiene() {
        return this.hygiene;
    }

    public void setIllness(int illness) {
        if (illness > 5) {//5 Sick / 0 Healthy
            illness = 5;
        }

        if (illness < 0) {
            illness = 0;
        }

        this.illness = illness;
    }

    public int getIllness() {
        return this.illness;
    }

    @Override
    public String toString() {
        return "\nHunger: " + this.hunger
                + "\nTiredness: " + this.tiredness
                + "\nBoredness: " + this.boredness
                + "\nHygiene: " + this.hygiene
                + "\nIllness: " + this.illness + "\n";
    }

}
