/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

    private JFrame frame;

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
                createScreen();
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

        Image dogPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\dogholder.png").getImage();
        Image catPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\catholder.png").getImage();
        Image rabPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\rabtholder.png").getImage();
        Image mouPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\mouholder.png").getImage();
        Image blankPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\blankholder.png").getImage();
        
        
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

    public void createScreen() {
        frame.getContentPane().removeAll();
        JLabel title = new JLabel("Creator Menu");

        JButton backButton = new JButton("Back");
        JPanel backPanel = new JPanel();

        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        backPanel.add(backButton);
        backPanel.add(title);

        frame.setLayout(new BorderLayout());
        frame.add(backPanel, BorderLayout.NORTH);

        frame.revalidate();
        frame.repaint();

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

    public static void main(String[] args) {

        GUI gui = new GUI();

    }

}
