/*
*Jamie Parker
*20101511
 */
package Assignment02;

import javax.swing.ImageIcon;

public class MyImage {

    private final java.awt.Image dogPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\dogholder.png").getImage();
    private final java.awt.Image catPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\catholder.png").getImage();
    private final java.awt.Image rabPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\rabtholder.png").getImage();
    private final java.awt.Image mouPic = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\mouholder.png").getImage();
    private final java.awt.Image questionMark = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\markholder.png").getImage();
    private final java.awt.Image emptyHeart = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\emptyheart.png").getImage();
    private final java.awt.Image fullHeart = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\redheart.png").getImage();
    private final java.awt.Image homeScreen = new ImageIcon("C:\\Users\\Moose\\Documents\\NetBeansProjects\\JavaGotchi\\images\\imageplaceholder.png").getImage();

    public java.awt.Image getDog() {
        return dogPic;
    }

    public java.awt.Image getCat() {
        return catPic;
    }

    public java.awt.Image getRab() {
        return rabPic;
    }

    public java.awt.Image getMou() {
        return mouPic;
    }

    public java.awt.Image getEmpty() {
        return emptyHeart;
    }

    public java.awt.Image getFull() {
        return fullHeart;
    }

    public java.awt.Image getHome() {
        return homeScreen;
    }
    
    public java.awt.Image getQuestion(){
        return questionMark;
    }
            
}