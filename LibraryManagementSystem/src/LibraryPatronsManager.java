/* Gary Montero
 *  CEN3024C - Software Development 1
 *  January 20, 2025
 *  LibraryPatronsManager.java
 *  This class contains the ArrayList that will store the Patron objects as well as
 *  methods for manipulating that ArrayList that the user will initiate from the main menu
 * */

import java.io.*;
import java.util.*;

public class LibraryPatronsManager {


    public ArrayList<Patron> patrons;

    public LibraryPatronsManager() {
        this.patrons = new ArrayList<>();
    }


    /* Method: addPatron
     *  Parameters: none
     *  Return: void
     *  Purpose: Allows the user to manually enter patron data
     *  It then creates a new Patron object and adds it to the patrons ArrayList
     * */
    public void addPatron() {
        Patron patron = new Patron();

        for (int i = 0; i < patrons.size(); i++) {
            //Checks for duplicate ID numbers and if found tell the user and ask again
            if (patrons.get(i).getIdNum().equals(patron.getIdNum())) {
                i = 0;
                System.out.println("Unable to enter patron because this ID already exists.");
                patron.setIdNum();
                //Checks whether the ID number is 7 numbers long or null. If so, tell the user and ask again
            } else if (patron.getIdNum().length() != 7 || patron.getIdNum() == null) {
                System.out.println("Unable to enter patron because this ID is not 7 numbers long.");
                patron.setIdNum();
                //Checks if the input from the user is a numeric value by calling the isNumeric method
            } else if (!isNumeric(patron.getIdNum())) {
                System.out.println("Unable to enter patron. the ID must be 7 numbers");
                patron.setIdNum();
            }
        }
        this.patrons.add(patron); //if all conditions are met then the patron is added to the patrons ArrayList
    }

    /* Method: isNumeric
     *  Parameters: String userInputID
     *  Return: boolean
     *  Purpose: Checks whether the string entered by user is a numeric value by parsing it into a double.
     *  If an exception is caught or the string was null, it returns false, meaning the string was not able to
     *  be parsed into a double and thus, not a number.
     * */
    public static boolean isNumeric(String userInputId) {
        if (userInputId == null) {
            return false;
        }
        try {
            double dub = Double.parseDouble(userInputId);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /* Method: addPatronFromTextFile
     *  Parameters: String filePath
     *  Return: void
     *  Purpose: Allows the user to add patrons from a text file. If there are any duplicated patron IDs or incorrect
     *  overdue fine amounts it tells the user what the problem was and then does not add the incorrectly
     *  formatted patrons.
     * */
    public void addPatronFromTextFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            System.out.println("\nHere is the contents of the text file: \n");

            //Reads in values from a file and stores them in a proton object and checks ID & fine validity
            while ((line = br.readLine()) != null) {
                //splits data from text file using a dash as a delimiter
                String[] values = line.split("-");
                System.out.println(Arrays.toString(values)); //prints out the content of the text file

                //acts as a flag to indicate if patron data is invalid
                boolean issues = false;

                //puts current working line into a new patron object
                Patron patron = new Patron(values[0], values[1].trim(), values[2].trim(), Double.parseDouble(values[3]));

                for (int i = 0; i < patrons.size(); i++) {
                    //Checks for duplicates and if found prompt the user
                    if (patrons.get(i).getIdNum().equals(patron.getIdNum())) {
                        System.out.println("Unable to enter patron because this ID already exists.");
                        issues = true;
                        break;
                        //Checks for correct number of digits
                    } else if (patron.getIdNum().length() != 7 || patron.getIdNum() == null) {
                        System.out.println("Unable to enter patron because this ID is not 7 numbers long.");
                        issues = true;
                        break;
                        //Checks if the input from the user is a numeric value by calling the isNumeric method
                    } else if (!isNumeric(patron.getIdNum())) {
                        System.out.println("Unable to enter patron. the ID must be 7 numbers");
                        issues = true;
                        break;
                    }
                }

                //checks if the fine amount is within the correct range
                if (patron.getFineAmount() < 0 || patron.getFineAmount() > 250) {
                    System.out.print("Could not add patron: '" + patron.getName() + '\'');
                    System.out.println(" Please make sure overdue fine is between 0 and 250");
                    issues = true;
                }

                //if all conditions are met then the patron is added to the patrons ArrayList
                if (!issues) {
                    this.patrons.add(patron);
                }

            }
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }


    /* Method: deletePatron
     *  Parameters: String deleteIdNumber
     *  Return: void
     *  Purpose: deletes a Patron object form the patrons ArrayList using the Patron's ID number
     * */
    public void deletePatron(String deleteIdNumber) {
        boolean patronNotFound = false;
        //Looping patrons ArrayList for ID and then deleting that patron
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getIdNum().equals(deleteIdNumber)) {
                patrons.remove(i);
                System.out.println("\nThe patron with the ID number " + deleteIdNumber + " has been removed.");
                patronNotFound = false;
                break;
            } else {
                patronNotFound = true;
            }
        }
        if (patronNotFound) {
            System.out.println("\nThe patron with the ID number '" + deleteIdNumber + "' does not exist.");
        }
    }


    /* Method: seeAllPatrons
     *  Parameters: none
     *  Return: void
     *  Purpose: Prints all Patron objects form the patrons ArrayList.
     *   If "patrons" is empty then a message is displayed
     * */
    public void seeAllPatrons() {
        if (!patrons.isEmpty()) {
            for (Patron patron : patrons) {
                System.out.println(patron);
            }
        } else {
            System.out.println("There are no patrons currently in the system.");
        }
    }
}
