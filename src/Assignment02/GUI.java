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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

    private JFrame frame;
    private Image dogPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\dogholder.png").getImage();
    private Image catPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\catholder.png").getImage();
    private Image rabPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\rabtholder.png").getImage();
    private Image mouPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\mouholder.png").getImage();
    private Image blankPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\blankholder.png").getImage();
    private String petType;
    private String petColour;
    private String petName;
    
    public GUI() {
        frame = new JFrame("JavaGotchi");
        frame.setSize(860, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        homeScreen();
        frame.setVisible(true);
    }

    public void homeScreen() {

        Image homescreen = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\imageplaceholder.png").getImage();
        JLabel label = new JLabel(new ImageIcon(homescreen));
        JPanel panelTop = new JPanel();
        panelTop.add(label);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(100, 30));
        panelBottom.add(start);

        frame.add(panelBottom, BorderLayout.SOUTH);
        frame.add(panelTop, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionScreen();
            }
        });
    }

    public void optionScreen() {
        frame.getContentPane().removeAll();

        JButton createButton = new JButton("Create");
        JButton loadButton = new JButton("Load");
        JButton backButton = new JButton("Back");
        JPanel backPanel = new JPanel();
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JLabel title = new JLabel("Options Menu");
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        centerPanel.add(loadButton, gbc);

        gbc.gridy = 1;
        centerPanel.add(createButton, gbc);

        backPanel.add(backButton);
        backPanel.add(title);
        frame.setLayout(new BorderLayout());
        frame.add(backPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                loadScreen();
                frame.revalidate();
                frame.repaint();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                createAnimalScreen();
                frame.revalidate();
                frame.repaint();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                homeScreen();
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public void loadScreen() {
        frame.getContentPane().removeAll();

        //Read saved slots and identify what type of animal
        //load image into slot button
        //Include name into slot 1 label ie Slot1: BigDoggie
        JButton slot1 = new JButton(new ImageIcon(dogPic));
        JButton slot2 = new JButton(new ImageIcon(catPic));
        JButton slot3 = new JButton(new ImageIcon(rabPic));

        // Labels for each slot
        JLabel labelSlot1 = new JLabel("Slot 1", SwingConstants.CENTER);
        JLabel labelSlot2 = new JLabel("Slot 2", SwingConstants.CENTER);
        JLabel labelSlot3 = new JLabel("Slot 3", SwingConstants.CENTER);

        JLabel title = new JLabel("Load Menu");

        JButton backButton = new JButton("Back");
        JPanel backPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        backPanel.add(backButton);
        backPanel.add(title);

        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100)); // Adjust vertical gap as needed

        // Create separate panels for each slot with a BorderLayout
        JPanel slotPanel1 = new JPanel(new BorderLayout());
        JPanel slotPanel2 = new JPanel(new BorderLayout());
        JPanel slotPanel3 = new JPanel(new BorderLayout());

        // Add labels to the top of each slot panel
        slotPanel1.add(labelSlot1, BorderLayout.NORTH);
        slotPanel2.add(labelSlot2, BorderLayout.NORTH);
        slotPanel3.add(labelSlot3, BorderLayout.NORTH);

        // Add buttons to the center of each slot panel
        slotPanel1.add(slot1, BorderLayout.CENTER);
        slotPanel2.add(slot2, BorderLayout.CENTER);
        slotPanel3.add(slot3, BorderLayout.CENTER);

        // Add slot panels to the centerPanel
        centerPanel.add(slotPanel1);
        centerPanel.add(slotPanel2);
        centerPanel.add(slotPanel3);

        frame.setLayout(new BorderLayout());
        frame.add(backPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();

        slot1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                //slot 1 load
                frame.revalidate();
                frame.repaint();
            }
        });

        slot2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                //slot 2 load
                frame.revalidate();
                frame.repaint();
            }
        });

        slot3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                //slot 3 load
                frame.revalidate();
                frame.repaint();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                optionScreen();
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public void createAnimalScreen() {
        frame.getContentPane().removeAll();
        JLabel title = new JLabel("Creator Menu: Pet Type");

        JButton createDog = new JButton(new ImageIcon(dogPic));
        JButton createCat = new JButton(new ImageIcon(catPic));
        JButton createRabbit = new JButton(new ImageIcon(rabPic));
        JButton createMouse = new JButton(new ImageIcon(mouPic));

        JButton backButton = new JButton("Back");
        JPanel backPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        backPanel.add(backButton);
        backPanel.add(title);

        // Create a panel with GridLayout for the buttons
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, and 10 pixels of horizontal and vertical spacing

        // Add buttons to the grid layout panel
        centerPanel.add(createDog);
        centerPanel.add(createCat);
        centerPanel.add(createRabbit);
        centerPanel.add(createMouse);

        frame.setLayout(new BorderLayout());
        frame.add(backPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();

        createDog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                petType = "Dog";
                createColourScreen(petType);
                frame.revalidate();
                frame.repaint();
            }
        });

        createCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                petType = "Cat";
                createColourScreen(petType);
                frame.revalidate();
                frame.repaint();
            }
        });
        
        createRabbit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                petType = "Rabbit";
                createColourScreen(petType);
                frame.revalidate();
                frame.repaint();
            }
        });
        
        createMouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                petType = "Mouse";
                createColourScreen(petType);
                frame.revalidate();
                frame.repaint();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                optionScreen();
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public void createColourScreen(String petType) {
        frame.getContentPane().removeAll();
        JLabel title = new JLabel("Creator Menu: Colour");
        
        JButton backButton = new JButton("Back");
        JPanel backPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        JButton black = new JButton();
        JButton white = new JButton();
        JButton grey = new JButton();
        JButton brown = new JButton();
        
        black.setBackground(Color.BLACK);
        white.setBackground(Color.WHITE);
        grey.setBackground(Color.GRAY);
        Color brownColour = new Color(139, 69, 19);
        brown.setBackground(brownColour);
        
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        backPanel.add(backButton);
        backPanel.add(title);
        
        
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, and 10 pixels of horizontal and vertical spacing

        // Add buttons to the grid layout panel
        centerPanel.add(black);
        centerPanel.add(white);
        centerPanel.add(grey);
        centerPanel.add(brown);
        
        frame.setLayout(new BorderLayout());
        frame.add(backPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();

         black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
              petColour = "Black";
                createNameScreen(petColour, petType);
                frame.revalidate();
                frame.repaint();
            }
        });

        white.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                 petColour = "White";
                createNameScreen(petColour, petType);
                frame.revalidate();
                frame.repaint();
            }
        });
        
        grey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                petColour = "Grey";
                createNameScreen(petColour, petType);
                frame.revalidate();
                frame.repaint();
            }
        });
        
        brown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                petColour = "Brown";
                createNameScreen(petColour, petType);
                frame.revalidate();
                frame.repaint();
            }
        });
                backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                createAnimalScreen();
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public void createNameScreen(String petColour, String petType){
        
    }
    
    public static void main(String[] args) {

        GUI gui = new GUI();

    }

}
