package bsu.edu.cs.cs222.menues.log_in;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class SignUp extends SignIn {
    /**
     * Gets username and password for new account and attempts to create account
     */
    public static void signUpPrompt() {
        String username = "";
        String password = "";
        String confirmedPassword = "";
        for (int i = 0; i < 10; i++) {
            // Prompts
            username = getUsername();
            password = getPassword();
            // Confirm password
            System.out.print("Confirm ");
            confirmedPassword = getPassword();

            // Attempts to sign up
            String signUpResult = signUp(username, password, confirmedPassword);
            System.out.println(signUpResult);
        }
    }

    public static String signUp(String username, String password, String confirmedPassword) {
        if (usernameExists(username)) {
            return "Account with that username already exists";
        }

        // Handle password validation
        String passwordValidation = validatePassword(password, confirmedPassword);
        if (!passwordValidation.equals("VALID")) {
            return passwordValidation;
        }

        // Create a new user file with default data
        try {
            File file = new File("src/main/resources/users/" + username + ".csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("src/main/resources/users/" + username + ".csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(username + "," + password + ",5000");
            bufferedWriter.close();
            return ("Account Created");
        } catch (Exception e) {
            return "Could not create account";
        }
    }

    /**
     * Tries to locate a user on file
     * @return if user file exists in resources/users
     */
    public static boolean usernameExists(String username) {
        Path path = Path.of("src/main/resources/users/" + username + ".csv");
        return Files.exists(path);
    }

    /**
     * Checks if both entered passwords match, are 8 characters, and contain num, upper & lower case, and symbol
     */
    public static String validatePassword(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            return ("Passwords do not match");
        }

        if (password.length() < 8) {
            return "Password is too short";
        }

        boolean meetsRequirements = isStrongPassword(password);
        if (!meetsRequirements) {
            return "Password must contain an upper case and lower case letter, a number, and a symbol";
        }

        return "VALID";
    }

    /**
     * Each password must contain an upper case, lower case, number, and symbol
     */
    public static boolean isStrongPassword(String password) {
        boolean containsNumber = false;
        boolean containsLowerCase = false;
        boolean containUpperCase = false;
        boolean containsSymbol = false;
        int charAsInt;
        // Uses char values as ints to determine if instance exists
        for (int i = 0; i < password.length(); i++) {
            charAsInt = password.charAt(i);
            if (charAsInt >= 49 && charAsInt <= 57) { // 0-9
                containsNumber = true;
            }
            else if (charAsInt >= 97 && charAsInt <= 122) { // a-z
                containsLowerCase = true;
            }
            else if (charAsInt >= 65 && charAsInt <= 90) { // A-Z
                containUpperCase = true;
            }
            else { // If not letter or number, must be a symbol
                containsSymbol = true;
            }
        }
        return containsNumber && containsLowerCase && containUpperCase && containsSymbol;
    }
}