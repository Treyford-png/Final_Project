package bsu.edu.cs.cs222.menues.log_in;
import bsu.edu.cs.cs222.characters.User;

import java.util.Scanner;

/**
 * Class that contains basic methods used by both LogIn and SignUp, which extend this
 * Also contains a prompt for terminal usage
 *
 * @author Holden Hankins
 */
public class SignIn {
    /**
     * Prompts the user for their username
     */
    static String getUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for password
     */
    public static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Password: ");
        return scanner.nextLine();
    }

    /**
     * Gets the user's request to either sign up or log in and directs them to the correct menu
     * @return User date for user that just signed in
     */
    public static User signIn() {
        Scanner scanner = new Scanner(System.in);
        int input;
        for (int i = 0; i < 10; i++) { // Loop prevents flood
            System.out.println("Would you like to\n(1) Sign In\n(2) Sign Up");
            input = scanner.nextInt();
            if (input == 1) { // LOG IN
                return LogIn.loginPrompt(); // Similar i < 10 loop used
            }
            else if (input == 2) { // SIGN UP
                SignUp.signUpPrompt();
                // No return as to allow user to log in with new account
            }
            else { // INVALID
                System.out.println("Invalid input");
            }
        } // close for

        return null;
    } // close sign in
}