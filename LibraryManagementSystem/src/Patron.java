/* Gary Montero
 *  CEN3024C - Software Development 1
 *  January 20, 2025
 *  Patron.java
 *  This class contains the attributes and methods for assigning those attributes
 *  to a Patron object when the user enters one into the system.
 * */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Patron {

    private String idNum;
    private String name;
    private String address;
    private double fineAmount;

    public static Scanner scanner = new Scanner(System.in);

    public Patron(String idNum, String name, String address, double fineAmount) {
        this.idNum = idNum;
        this.name = name;
        this.address = address;
        this.fineAmount = fineAmount;
    }


    /* Method: Patron
     *  Parameters: none
     *  Return: none
     *  Purpose: overloads the Patrons() constructor to create a Patron object
     * */
    public Patron() {
        setIdNum();
        setName();
        setAddress();
        setFineAmount();
    }


    public String getIdNum() {
        return idNum;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    /* Method: setIdNum
     *  Parameters: none
     *  Return: void
     *  Purpose: sets the ID number for a Patron object
     * */
    public void setIdNum() {
        System.out.println("Enter the patron's ID number");
        this.idNum = scanner.nextLine().trim();
    }

    /* Method: setName
     *  Parameters: none
     *  Return: void
     *  Purpose: sets the name for a Patron object and checks for incorrectly entered data
     * */
    public void setName() {
        System.out.println("Enter the patron's name");
        this.name = scanner.nextLine().trim();
        //Checks for a valid input from user based on these conditions. If non-valid input, prompt user again
        while (this.name == null || this.name.isEmpty() || (this.name.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the patron's name");
            this.name = scanner.nextLine();
        }
    }


    /* Method: setAddress
     *  Parameters: none
     *  Return: void
     *  Purpose: sets the address for a Patron object and checks for incorrectly entered data
     * */
    public void setAddress() {
        System.out.println("Enter the patron's address");
        this.address = scanner.nextLine().trim();
        //Checks for a valid input from user based on these conditions. If non-valid input, prompt user again
        while (this.address == null || this.address.isEmpty() || (this.address.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the patron's address");
            this.address = scanner.nextLine();
        }
    }


    /* Method: setFineAmount
     *  Parameters: none
     *  Return: void
     *  Purpose: sets the overdue fine amount for a Patron object with restrictions.
     * */
    public void setFineAmount() {
        boolean loop = true;
        //Keeps looping until the user enters a valid overdue fine amount
        while (loop) {
            //takes in the user input and parses it into a double to make sure the user enters a number
            try {
                System.out.println("Enter the patron's over due fines.");
                this.fineAmount = Double.parseDouble(scanner.nextLine());
                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please input a valid number.");
            }
            //checks if the fine amount is within the correct range
            if (this.fineAmount < 0 || this.fineAmount > 250) {
                System.out.println("Invalid input!" + " Fine amount must be between 0 and 250");
                loop = true;
            }
        }
    }


    /* Method: toString
     *  Parameters: none
     *  Return: String
     *  Purpose: returns a string containing the listed values separated by a dash
     * */
    @Override
    public String toString() {
        return idNum + '-' + name + "-" + address + "-" + fineAmount;
    }
}
