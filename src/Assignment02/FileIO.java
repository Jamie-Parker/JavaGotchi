/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class FileIO {//Handles text file writing and reading 

    private static final String Filename = "JavaGotchi_Pet_Data.txt";
   
    public void savePetToFile(int slot, String petType, String petColour, String petName, int petHunger, int petTired, int petBored, int petHygiene, int petIllness, String timeStamp, String petCreated) {
        List<String> petData = loadAllPetsFromFile();

        while (petData.size() <= slot) {
            petData.add(""); // Add empty slots if necessary
        }

        String petInfo = petType + "," + petColour + "," + petName + "," + petHunger + "," + petTired + "," + petBored + "," + petHygiene + "," + petIllness + "," + timeStamp + "," + petCreated;
        petData.set(slot, petInfo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Filename))) {
            for (String pet : petData) {
                writer.write(pet);
                writer.newLine();
            }
            System.out.println("Pet has been saved to Slot " + slot+1);
        } catch (IOException e) {
            System.out.println("Error saving pet to file: " + e.getMessage());
        }
    }
    public String loadPetFromFile(int slot) {
        List<String> petData = loadAllPetsFromFile();
        
            if (slot >= 0 && slot < petData.size()) {
                return petData.get(slot);
            } else {
                System.out.println("Invalid slot number.");
            }
        return null;
    }

    private List<String> loadAllPetsFromFile() {
        List<String> petData = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(Filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                petData.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading pet data from file: " + e.getMessage());
        }

        return petData;
    }
}
