# CEN3024C_SWD1_LibraryManagementSystem

Gary Montero 
3024C - Software Development 1
CRN: 24204

This repository contains part 2 of the Module 3 Software Development Life Cycle assignment. 
The software is a library management system that allows a user to enter (either manually or through uploading a .txt file), delete, and view patrons in the system.
Patron data includes an ID number, name, address, and an overdue fine amount. The program checks for correctly formatted and valid data before creating a new patron object. 
ID numbers must be unique and contain 7 digits, and the overdue fine amount must be between 0 and 250. 
Each patron object will be stored in an ArrayList and the main menu will be handled using a switch statement. 
The program will continue to run until the user chooses to quit the application.
