/*
*Jamie Parker
*20101511
*GUI is the View of the program.
*Creates the frame and multiple pages to be displayed
*Provides access to 3 load and save slots for pets stored in the database, pet object information and a pet maintenance page
*/
package Assignment02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class GUI extends JFrame implements Observer {

    public PetStatus petStatus;
    private final JFrame frame;
    private final Image dogPic;
    private final Image catPic;
    private final Image rabPic;
    private final Image mouPic;
    private final Image questionMark;
    private final Image emptyHeart;
    private final Image fullHeart;
    private Image homeScreen;
    protected JButton start;
    protected JButton create;
    protected JButton load;
    protected JButton backButton;
    protected JButton loadSlot1;
    protected JButton loadSlot2;
    protected JButton loadSlot3;
    protected JButton saveSlot1;
    protected JButton saveSlot2;
    protected JButton saveSlot3;
    protected JButton createDog;
    protected JButton createCat;
    protected JButton createRabbit;
    protected JButton createMouse;
    protected JButton black;
    protected JButton white;
    protected JButton grey;
    protected JButton brown;
    protected JButton action;
    protected JButton save;
    protected JButton options;
    protected JButton play;
    protected JButton feed;
    protected JButton sleep;
    protected JButton clean;
    protected JButton heal;
    protected JTextField text;
    protected JPanel backPanel;
    protected JPanel centerPanel;
    protected JPanel titlePanel;
    protected JPanel bottomPanel;
    protected JLabel title;
    protected JLabel labelSlot1;
    protected JLabel labelSlot2;
    protected JLabel labelSlot3;
    protected JPanel slotPanel1;
    protected JPanel slotPanel2;
    protected JPanel slotPanel3;
    protected JTextArea statusText;
    protected JTextArea stringText;
    public String string;
    public String petType;
    public String petName;
    public int hunger;
    public int tired;
    public int bored;
    public int hygiene;
    public int sick;
    public int[] previousValues;
    private Image[] animalPics = new Image[3];
    ImageIcon icon = null;
    MyImage image = new MyImage();

    public GUI() {
        frame = new JFrame("JavaGotchi");
        frame.setSize(860, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        start = new JButton("Start");
        create = new JButton("Create");
        load = new JButton("Load");
        backButton = new JButton("Back");
        dogPic = image.getDog();
        catPic = image.getCat();
        rabPic = image.getRabbit();
        mouPic = image.getMouse();
        emptyHeart = image.getEmptyHeart();
        fullHeart = image.getFullHeart();
        questionMark = image.getQuestionMark();
        createDog = new JButton(new ImageIcon(dogPic));
        createCat = new JButton(new ImageIcon(catPic));
        createRabbit = new JButton(new ImageIcon(rabPic));
        createMouse = new JButton(new ImageIcon(mouPic));
        black = new JButton();
        white = new JButton();
        grey = new JButton();
        brown = new JButton();
        text = new JTextField(20);
        action = new JButton("Actions");
        play = new JButton("Play");
        feed = new JButton("Feed");
        sleep = new JButton("Sleep");
        clean = new JButton("Clean");
        heal = new JButton("Heal");
        save = new JButton("Save Pet");
        options = new JButton("Options Menu");
        loadSlot1 = new JButton(new ImageIcon(questionMark));
        loadSlot2 = new JButton(new ImageIcon(questionMark));
        loadSlot3 = new JButton(new ImageIcon(questionMark));
        saveSlot1 = new JButton(new ImageIcon(questionMark));
        saveSlot2 = new JButton(new ImageIcon(questionMark));
        saveSlot3 = new JButton(new ImageIcon(questionMark));
        labelSlot1 = new JLabel("Slot 1", SwingConstants.CENTER);
        labelSlot2 = new JLabel("Slot 2", SwingConstants.CENTER);
        labelSlot3 = new JLabel("Slot 3", SwingConstants.CENTER);
        slotPanel1 = new JPanel(new BorderLayout());
        slotPanel2 = new JPanel(new BorderLayout());
        slotPanel3 = new JPanel(new BorderLayout());
        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel = new JPanel(new BorderLayout());
        statusText = new JTextArea();
        stringText = new JTextArea();
        string = "";
        title = new JLabel();
        homeScreen();
        frame.setVisible(true);
        previousValues = new int[5];
    }

    public void loadButtons() {//updates the buttons to the current slot picture
        loadSlot1.setIcon(new ImageIcon(animalPics[0]));
        loadSlot2.setIcon(new ImageIcon(animalPics[1]));
        loadSlot3.setIcon(new ImageIcon(animalPics[2]));
        saveSlot1.setIcon(new ImageIcon(animalPics[0]));
        saveSlot2.setIcon(new ImageIcon(animalPics[1]));
        saveSlot3.setIcon(new ImageIcon(animalPics[2]));
    }

    public void setAnimalImage(int slot, String animalType) {//used to set slot picture as animal type
        switch (animalType) {
            case "Dog":
                animalPics[slot] = dogPic;
                break;
            case "Cat":
                animalPics[slot] = catPic;
                break;
            case "Rabbit":
                animalPics[slot] = rabPic;
                break;
            case "Mouse":
                animalPics[slot] = mouPic;
                break;
            case "Type":
                animalPics[slot] = questionMark;
                break;
        }
    }

    public void homeScreen() {//Javagotchi homescreen with start button
        clear();
        homeScreen = image.getHomeScreen();
        JLabel label = new JLabel(new ImageIcon(homeScreen));
        backPanel.add(label);
        start.setPreferredSize(new Dimension(100, 30));
        titlePanel.add(start);
        centerPanel.add(titlePanel);
        set();
    }

    public void optionScreen() {//Pet object initiation menu for either Create or Load
        clear();
        title.setText("Options Menu");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        centerPanel.add(load, gbc);
        gbc.gridy = 1;
        centerPanel.add(create, gbc);
        set();
    }

    public void loadScreen() {//Load slots with picture type
        clear();
        title.setText("Load Menu");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));
        slotPanel1.add(labelSlot1, BorderLayout.NORTH);
        slotPanel2.add(labelSlot2, BorderLayout.NORTH);
        slotPanel3.add(labelSlot3, BorderLayout.NORTH);
        slotPanel1.add(loadSlot1, BorderLayout.CENTER);
        slotPanel2.add(loadSlot2, BorderLayout.CENTER);
        slotPanel3.add(loadSlot3, BorderLayout.CENTER);
        centerPanel.add(slotPanel1);
        centerPanel.add(slotPanel2);
        centerPanel.add(slotPanel3);
        set();
    }

    public void createAnimalScreen() {//Provides Picture filled buttons for type selection
        clear();
        title.setText("Creator Menu: Pet Type");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        centerPanel.add(createDog);
        centerPanel.add(createCat);
        centerPanel.add(createRabbit);
        centerPanel.add(createMouse);
        set();
    }

    public void createColourScreen() {//Creates colour filled buttons for colour selection
        clear();
        title.setText("Creator Menu: Colour");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10));
        black.setBackground(Color.BLACK);
        white.setBackground(Color.WHITE);
        grey.setBackground(Color.GRAY);
        Color brownColour = new Color(139, 69, 19); // BROWN is not a standard
        brown.setBackground(brownColour);
        centerPanel.add(black);
        centerPanel.add(white);
        centerPanel.add(grey);
        centerPanel.add(brown);
        set();
    }

    public void createNameScreen() {//Creates a text box to add a name for pet object
        clear();
        title.setText("Creator Menu: Name");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        centerPanel.setLayout(new GridBagLayout());
        JLabel prompt = new JLabel("Please Type in a Name");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        centerPanel.add(prompt, gbc);
        gbc.gridy = 1;
        centerPanel.add(text, gbc);
        set();
    }

    public void petStatusScreen() {//Displays pet picture and toString() with information about pet object
        clear();
        title.setText("Pet Status");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        titlePanel.add(options);
        backPanel.add(action, BorderLayout.EAST);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));
        switch (petType) {//Changes picture according to current pet object type
            case "Dog":
                icon = new ImageIcon(dogPic);
                break;
            case "Cat":
                icon = new ImageIcon(catPic);
                break;
            case "Rabbit":
                icon = new ImageIcon(rabPic);
                break;
            case "Mouse":
                icon = new ImageIcon(mouPic);
                break;
        }
        JLabel pictureLabel = new JLabel(icon);
        slotPanel1.add(pictureLabel);
        statusText.setEditable(false);
        statusText.setWrapStyleWord(true);
        statusText.setLineWrap(true);
        statusText.setPreferredSize(new Dimension(250, 250));
        centerPanel.add(slotPanel1);
        centerPanel.add(statusText);
        set();
    }

    public void petActionsScreen() {//Pet object maintenance 
        clear();
        title.setText("Pet Actions");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        backPanel.add(save, BorderLayout.EAST);
        centerPanel.setLayout(new GridBagLayout());
        JPanel picturePanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));// GridLayout for button veritcal alignement with GridBag layout
        JLabel pictureLabel = new JLabel(icon);
        picturePanel.add(pictureLabel, BorderLayout.CENTER);
        buttonPanel.add(feed);
        buttonPanel.add(sleep);
        buttonPanel.add(play);
        buttonPanel.add(clean);
        buttonPanel.add(heal);
        GridBagConstraints gbc = new GridBagConstraints();//Uses GridBag to maintain a grid of 5x5 Heart icons
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel[][] heartLabels = new JLabel[5][5];

        for (int i = 0; i < 5; i++) {//Initialises all the hearts icons to empty hearts - fills the grid with icons
            for (int j = 0; j < 5; j++) {
                heartLabels[i][j] = new JLabel(new ImageIcon(emptyHeart));
                gbc.gridx = j;
                gbc.gridy = i;
                gridPanel.add(heartLabels[i][j], gbc);
            }
        }

        int[] values = {hunger, tired, bored, hygiene, sick};//creates and array of status values
        int[] attributeIndexes = {0, 1, 2, 3, 4};
        String[] attributeNames = {"Full", "Not Tired", "Content", "Clean", "Not Sick"};
        String reachedAttribute = "";

        for (int i = 0; i < 5; i++) {//Changes String and Heart Icon for status values
            int value = values[i];
            int previousValue = previousValues[i];
            int attributeIndex = attributeIndexes[i];
            value = Math.min(5, Math.max(0, value));

            for (int j = 0; j < value; j++) {//Changes heart icon determined by status value
                heartLabels[attributeIndex][j].setIcon(new ImageIcon(fullHeart));
            }
            if (value == 5 && previousValue < 5) {
                reachedAttribute = attributeNames[i];
            }
            previousValues[i] = value;
            if (!reachedAttribute.isEmpty()) {//Changes the text string output to indicate when a value max has been reached for each status attribute
                string = petName + " is " + reachedAttribute + "!";
            } else {
                string = "";
            }
        }

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));//Adds text panel to bottom of actions page
        stringText.setText(string);
        stringText.setEditable(false);
        stringText.setWrapStyleWord(true);
        stringText.setLineWrap(true);
        stringText.setPreferredSize(new Dimension(300, 20));
        textPanel.add(stringText);
        bottomPanel.add(textPanel, BorderLayout.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(picturePanel, gbc);// Adds picture to first coloumn
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        centerPanel.add(gridPanel, gbc); // Adds Heart Grid to second coloumn
        gbc.gridx = 3;
        centerPanel.add(buttonPanel, gbc);// Adds button grid to third coloumn
        set();
    }

    public void petSaveScreen() {//Save Slots with picture type
        clear();
        title.setText("Save Menu");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));
        slotPanel1.add(labelSlot1, BorderLayout.NORTH);
        slotPanel2.add(labelSlot2, BorderLayout.NORTH);
        slotPanel3.add(labelSlot3, BorderLayout.NORTH);
        slotPanel1.add(saveSlot1, BorderLayout.CENTER);
        slotPanel2.add(saveSlot2, BorderLayout.CENTER);
        slotPanel3.add(saveSlot3, BorderLayout.CENTER);
        centerPanel.add(slotPanel1);
        centerPanel.add(slotPanel2);
        centerPanel.add(slotPanel3);
        set();
    }

    public void clear() {//Clears all the panels 
        frame.getContentPane().removeAll();
        centerPanel.removeAll();
        titlePanel.removeAll();
        title.removeAll();
        backPanel.removeAll();
        bottomPanel.removeAll();
        slotPanel1.removeAll();
        slotPanel2.removeAll();
        slotPanel3.removeAll();
        string = "";
    }

    public void set() {//Sets the panels to the Frame for revalidation
        frame.setLayout(new BorderLayout());
        frame.add(backPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PetStatus) {//observer for changes in status values and pet type and name
            PetStatus petStatus = (PetStatus) o;
            statusText.setText(petStatus.toString());
            petType = petStatus.getPetType();
            petName = petStatus.getPetName();
            hunger = petStatus.getHunger();
            tired = petStatus.getTired();
            hygiene = petStatus.getHygiene();
            bored = petStatus.getBored();
            sick = petStatus.getIllness();
        }
    }
}
