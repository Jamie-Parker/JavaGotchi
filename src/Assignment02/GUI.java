/*
*Jamie Parker
*20101511
 */
package Assignment02;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.*;

public class GUI{  
    
    public static Image homescreen;
    
    
    
    
    
        public static void main(String[] args){
            
        JFrame frame = new JFrame("Program");
        
        frame.setSize(860, 550);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        homescreen = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\imageplaceholder.png").getImage();
        
        JLabel label = new JLabel(new ImageIcon(homescreen));
        
        panel.add(label);
        
        frame.add(panel);
        
        frame.setVisible(true);
    }
  
}
