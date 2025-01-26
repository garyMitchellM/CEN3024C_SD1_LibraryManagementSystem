/* Gary Montero
 *  CEN3024C - Software Development 1
 *  January 20, 2025
 *  MainMenu.java
 *  This class contains the
 *  manipulating that ArrayList that the user will initiate from the main menu
 * */

import java.util.Scanner;

public class MainMenu {

    /* Method: displayMainMenu
     *  Parameters: none
     *  Return: void
     *  Purpose: Displays and handles the functionality of the main menu with the use of a switch statement
     * */
    public static void displayMainMenu() {

        LibraryPatronsManager lpm = new LibraryPatronsManager();

        Scanner scanner = new Scanner(System.in);
        //prompts the user to enter a filePath and then calls the addPatronFromTextFile method
        System.out.println("Enter the path of the text file you want to import or enter any key to go to the main menu.");
        String filePathStart = scanner.nextLine();
        lpm.addPatronFromTextFile(filePathStart);
        System.out.println("\nHere are the patrons currently in the system:");
        lpm.seeAllPatrons();


        while (true) {
            System.out.println("\nWhat would you like to do?\n" +
                    "[1. Import patrons from a text file]\n" +
                    "[2. Manually enter a patron.]\n" +
                    "[3. Remove a patron from the system]\n" +
                    "[4. See all patrons] \n" +
                    "[5. Quit.");

            String input = "0";
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Enter the path of the text file you want to import");
                    String path = scanner.nextLine();
                    lpm.addPatronFromTextFile(path);
                    lpm.seeAllPatrons();
                    break;
                case "2":
                    lpm.addPatron();
                    System.out.println("\nHere are the patrons that are currently in the system.");
                    lpm.seeAllPatrons();
                    break;
                case "3":
                    lpm.seeAllPatrons();
                    if (!lpm.patrons.isEmpty()) {
                        System.out.println("Enter the ID number of the patron you want to remove.");
                        String idNumber = scanner.nextLine();
                        lpm.deletePatron(idNumber);
                    } else {
                        System.out.println("There are no patrons currently in the system.");
                    }
                    System.out.println("\nHere are the patrons that are currently in the system.");
                    lpm.seeAllPatrons();
                    break;
                case "4":
                    System.out.println("\nHere are the patrons that are currently in the system.");
                    lpm.seeAllPatrons();
                    break;

                case "5":
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }
}
