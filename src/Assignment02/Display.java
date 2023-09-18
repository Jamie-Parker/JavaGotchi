/*
*Jamie Parker
*20101511
*/
package Assignment02;

public class Display {//Maintains menu and ASCII strings

    private static String header;
    private static String mainMenu;
    private static String actionMenu;
    private static String activityMenu;

    private static String creatorMenuType;
    private static String creatorMenuColour;
    private static String creatorMenuName;

    private static String loadMenu;
    private static String saveMenu;

    private static String dogASCII;
    private static String catASCII;
    private static String rabbitASCII;
    private static String mouseASCII;

    public void DisplayMenu(int menu) {

        header = "\nJavaGotchi\n";

        mainMenu
                = "Main Menu\n"
                + "(1) Pet Action Menu\n"
                + "(2) Create new Pet\n"
                + "(3) Load saved Pet\n"
                + "(4) Save current Pet\n"
                + "(5) Quit Program\n";

        actionMenu
                = "Pet Action Menu\n"
                + "(1) Show Pet Details\n"
                + "(2) Show Pet Status\n"
                + "(3) Pet Activity\n"
                + "(4) Main Menu\n"
                + "(5) Quit\n";

        activityMenu
                = "Pet Activity Menu\n"
                + "(1) Feed\n"
                + "(2) Sleep\n"
                + "(3) Play\n"
                + "(4) Clean\n"
                + "(5) Heal\n"
                + "(6) Back\n"
                + "(7) Quit\n";

        creatorMenuType
                = "Pet Creation Menu\n"
                + "Pet Type:\n"
                + "(1) Dog\n"
                + "(2) Cat\n"
                + "(3) Rabbit\n"
                + "(4) Mouse\n"
                + "(5) Main Menu\n"
                + "(6) Quit\n";

        creatorMenuColour
                = "Pet Creation Menu\n"
                + "Pet Colour:\n"
                + "(1) Black\n"
                + "(2) White\n"
                + "(3) Grey\n"
                + "(4) Brown\n"
                + "(5) Main Menu\n"
                + "(6) Quit\n";

        creatorMenuName
                = "Pet Creation Menu\n"
                + "Please enter a name for your pet: \n";

        loadMenu
                = "\nPet Loader Menu\n"
                + "Select a Pet to Load: \n"
                + "(1) Saved Pet 1\n"
                + "(2) Saved Pet 2\n"
                + "(3) Saved Pet 3\n"
                + "(4) Main Menu\n"
                + "(5) Quit\n";

        saveMenu 
                = "\nPlease Select a Slot to Overwrite: \n"
                + "(1) Save Slot 1\n"
                + "(2) Save Slot 2\n"
                + "(3) Save Slot 3\n"
                + "(4) Back\n"
                + "(5) Quit\n";

        switch (menu) {
            
            case 0:
                System.out.println(header);
                break;
                
            case 1:
                System.out.println(mainMenu);
                break;

            case 2:
                System.out.println(actionMenu);
                break;

            case 3:
                System.out.println(activityMenu);
                break;

            case 4:
                System.out.println(creatorMenuType);
                break;

            case 5:
                System.out.println(creatorMenuColour);
                break;

            case 6:
                System.out.println(creatorMenuName);
                break;

            case 7:
                System.out.println(loadMenu);
                break;

            case 8:
                System.out.println(saveMenu);
                break;
        }
    }

    public void DisplayASCII(String type) {

        dogASCII = " /-\\__/-\\ \n"
                 + " | +  + |   \n"
                 + " \\   Y  /  \n"
                 + "  |__U_|    ";

        catASCII = " /\\___/\\ \n"
                 + " | ^  ^ | \n"
                 + " \\__=+=_/ ";

        rabbitASCII = " /-/ /-/\n"
                    + "/ /_/ / \n"
                    + "| +  +| \n"
                    + "(__=Y=_) ";

        mouseASCII = "(  )(  ) \n"
                   + "| +  + | \n"
                   + "(__==Y==) ";

        switch (type) {
            case "Dog":
                System.out.println(dogASCII);
                break;

            case "Cat":
                System.out.println(catASCII);
                break;

            case "Rabbit":
                System.out.println(rabbitASCII);
                break;

            case "Mouse":
                System.out.println(mouseASCII);
                break;

        }
    }
}
