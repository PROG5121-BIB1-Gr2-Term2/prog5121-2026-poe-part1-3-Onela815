/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1;

/**
 *
 * @author Dell
 */
public class Part1 {

    private String username;
    private String password;
    private String cellPhoneNumber;

    // Constructor
    public PROJECT(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // ===== USERNAME CHECK =====
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // ===== PASSWORD CHECK =====
    public boolean checkPasswordComplexity() {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$");
    }

    // ===== CELL NUMBER CHECK =====
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber.matches("^\\+27\\d{9}$");
    }

    // ===== REGISTER USER =====
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        return "User has been registered successfully.";
    }

    // ===== LOGIN CHECK =====
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username) && enteredPassword.equals(password);
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<PROJECT> users = new ArrayList<>();

        while (true) {

            // ===== MENU =====
            System.out.println("\n===== MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = input.nextInt();
            input.nextLine(); // fix scanner issue

            if (choice == 1) {

                // ===== REGISTER =====
                System.out.println("\n--- Register ---");

                System.out.print("Enter username: ");
                String username = input.nextLine();

                System.out.print("Enter password: ");
                String password = input.nextLine();

                System.out.print("Enter cell number (+27...): ");
                String cell = input.nextLine();

                PROJECT user = new PROJECT(username, password, cell);
                String result = user.registerUser();

                System.out.println(result);

                // Save only if successful
                if (result.equals("User has been registered successfully.")) {
                    users.add(user);
                }

            } else if (choice == 2) {

                // ===== LOGIN =====
                System.out.println("\n--- Login ---");

                System.out.print("Enter username: ");
                String username = input.nextLine();

                System.out.print("Enter password: ");
                String password = input.nextLine();

                boolean found = false;

                // Check all users
                for (PROJECT user : users) {
                    if (user.loginUser(username, password)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    System.out.println("Welcome! Login successful.");
                } else {
                    System.out.println("Username or password incorrect, please try again.");
                }

            } else if (choice == 3) {

                // ===== EXIT =====
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        input.close();
    }
}
