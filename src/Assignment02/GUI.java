/*
*Jamie Parker
*20101511
 */
package Assignment02;

//Creates the frames and items for the program
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

    private JFrame frame;
    private Image dogPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\dogholder.png").getImage();
    private Image catPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\catholder.png").getImage();
    private Image rabPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\rabtholder.png").getImage();
    private Image mouPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\mouholder.png").getImage();

    private Image emptyHeart = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\emptyheart.png").getImage();
    private Image fullHeart = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\redheart.png").getImage();

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
    protected JButton createNew;

    protected JButton play;
    protected JButton feed;
    protected JButton sleep;
    protected JButton clean;
    protected JButton heal;

    protected JTextField text;

    protected JPanel backPanel;
    protected JPanel centerPanel;
    protected JPanel titlePanel;
    protected JLabel title;

    protected JLabel labelSlot1;
    protected JLabel labelSlot2;
    protected JLabel labelSlot3;

    protected JPanel slotPanel1;
    protected JPanel slotPanel2;
    protected JPanel slotPanel3;

    int heart1 = 0;
    int heart2 = 0;
    int heart3 = 0;
    int heart4 = 0;
    int heart5 = 0;

    ImageIcon icon = null;

    public GUI() {

        frame = new JFrame("JavaGotchi");
        frame.setSize(860, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        start = new JButton("Start");
        create = new JButton("Create");
        load = new JButton("Load");
        backButton = new JButton("Back");

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
        createNew = new JButton("Create New Pet");

        loadSlot1 = new JButton(new ImageIcon(dogPic));// change to get from load
        loadSlot2 = new JButton(new ImageIcon(catPic));// change to get from load
        loadSlot3 = new JButton(new ImageIcon(rabPic));// change to get from load

        saveSlot1 = new JButton(new ImageIcon(dogPic));// change to get from load
        saveSlot2 = new JButton(new ImageIcon(catPic));// change to get from load
        saveSlot3 = new JButton(new ImageIcon(rabPic));// change to get from load

        labelSlot1 = new JLabel("Slot 1", SwingConstants.CENTER);// change to get from load
        labelSlot2 = new JLabel("Slot 2", SwingConstants.CENTER);// change to get from load
        labelSlot3 = new JLabel("Slot 3", SwingConstants.CENTER);// change to get from load

        slotPanel1 = new JPanel(new BorderLayout());
        slotPanel2 = new JPanel(new BorderLayout());
        slotPanel3 = new JPanel(new BorderLayout());

        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        title = new JLabel();

        homeScreen();
        frame.setVisible(true);
    }

    public void homeScreen() {
        clear();
        Image homescreen = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\imageplaceholder.png").getImage();
        JLabel label = new JLabel(new ImageIcon(homescreen));

        backPanel.add(label);
        start.setPreferredSize(new Dimension(100, 30));
        titlePanel.add(start);
        centerPanel.add(titlePanel);

        set();
        System.out.println("Home Screen");
    }

    public void optionScreen() {
        clear();

        title.setText("Options Menu");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        centerPanel.add(load, gbc);
        gbc.gridy = 1;
        centerPanel.add(create, gbc);

        set();

        System.out.println("Options Screen");
    }

    public void loadScreen() {
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
        System.out.println("Load Screen");
    }

    public void createAnimalScreen() {
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
        System.out.println("Type Screen");
    }

    public void createColourScreen() {
        clear();

        title.setText("Creator Menu: Colour");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10));

        black.setBackground(Color.BLACK);
        white.setBackground(Color.WHITE);
        grey.setBackground(Color.GRAY);
        Color brownColour = new Color(139, 69, 19);
        brown.setBackground(brownColour);

        centerPanel.add(black);
        centerPanel.add(white);
        centerPanel.add(grey);
        centerPanel.add(brown);

        set();
        System.out.println("Colour Screen");
    }

    public void createNameScreen() {
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
        System.out.println("Name Screen");
    }

    public void petStatusScreen(Pet pet) {
        clear();

        title.setText("Pet Status");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        titlePanel.add(createNew);
        backPanel.add(action, BorderLayout.EAST);
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));

        JPanel slotPanel1 = new JPanel();

        switch (pet.getPetType()) {
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

        JTextArea statusText = new JTextArea();

        statusText.setText(pet.toString());
        System.out.println("pet is an instance of Pet: " + (pet instanceof Pet));
        statusText.setEditable(false);
        statusText.setWrapStyleWord(true);
        statusText.setLineWrap(true);
        statusText.setPreferredSize(new Dimension(250, 250));

        centerPanel.add(slotPanel1);
        centerPanel.add(statusText);

        set();
        System.out.println("Status Screen");
    }

    public void petActionsScreen(int feedNo, int sleepNo, int playNo, int cleanNo, int healNo) {
        clear();

        title.setText("Pet Actions");
        titlePanel.add(title);
        backPanel.add(titlePanel, BorderLayout.CENTER);
        backPanel.add(backButton, BorderLayout.WEST);
        backPanel.add(save, BorderLayout.EAST);
        centerPanel.setLayout(new GridBagLayout());

        JPanel picturePanel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        JLabel pictureLabel = new JLabel(icon);
        picturePanel.add(pictureLabel, BorderLayout.CENTER);

        buttonPanel.add(feed);
        buttonPanel.add(sleep);
        buttonPanel.add(play);
        buttonPanel.add(clean);
        buttonPanel.add(heal);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel[][] heartLabels = new JLabel[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                heartLabels[i][j] = new JLabel(new ImageIcon(emptyHeart));
                gbc.gridx = j;
                gbc.gridy = i;
                gridPanel.add(heartLabels[i][j], gbc);
            }
        }

        heart1 += feedNo;
        if(heart1 < 0)
            heart1 = 0;
        if (heart1 > 5) 
            heart1 = 5;
        for (int i = 0; i < heart1; i++) {
            heartLabels[0][i].setIcon(new ImageIcon(fullHeart));

        }

        heart2 += sleepNo;
        if(heart2 < 0)
            heart2 = 0;
        if (heart2 > 5)
            heart2 = 5;
        for (int i = 0; i < heart2; i++) {
            heartLabels[1][i].setIcon(new ImageIcon(fullHeart));

        }

        heart3 += playNo;
        if(heart3 < 0)
            heart3 = 0;
        if (heart3 > 5) 
            heart3 = 5;
        for (int i = 0; i < heart3; i++) {
            heartLabels[2][i].setIcon(new ImageIcon(fullHeart));

        }

        heart4 += cleanNo;
        if(heart4 < 0)
            heart4 = 0;
        if (heart4 > 5) 
            heart4 = 5;
        for (int i = 0; i < heart4; i++) {
            heartLabels[3][i].setIcon(new ImageIcon(fullHeart));

        }

        heart5 += healNo;
        if(heart5 < 0)
            heart5 = 0;
        if (heart5 > 5) 
            heart5 = 5;
        for (int i = 0; i < heart5; i++) {
            heartLabels[4][i].setIcon(new ImageIcon(fullHeart));

        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(picturePanel, gbc);
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        centerPanel.add(gridPanel, gbc);
        gbc.gridx = 3;
        centerPanel.add(buttonPanel, gbc);

        set();
        System.out.println("Action Screen");

    }

    public void petSaveScreen() {
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
        System.out.println("Save Screen");
    }

    public void clear() {
        frame.getContentPane().removeAll();
        centerPanel.removeAll();
        titlePanel.removeAll();
        title.removeAll();
        backPanel.removeAll();
    }

    public void set() {
        frame.setLayout(new BorderLayout());
        frame.add(backPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
