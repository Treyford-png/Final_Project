package bsu.edu.cs.cs222.menues;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class SignUp extends SignIn {
    public static void signUpPrompt() {
        String username = "";
        String password = "";
        String confirmedPassword = "";
        for (int i = 0; i < 10; i++) {
            username = getUsername();
            password = getPassword();
            System.out.print("Confirm ");
            confirmedPassword = getPassword();
            String signUpResult = signUp(username, password, confirmedPassword);
            System.out.println(signUpResult);
            if (signUpResult.equals("Account Created")) {
                return;
            }
        }
    }

    public static String signUp(String username, String password, String confirmedPassword) {
        if (usernameExists(username)) {
            return "Account with that username already exists";
        }

        String passwordValidation = validatePassword(password, confirmedPassword);
        if (!passwordValidation.equals("VALID")) {
            return passwordValidation;
        }

        try {
            File file = new File("src/main/resources/users/" + username + ".csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("src/main/resources/users/" + username + ".csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(username + "," + password + ",0");
            bufferedWriter.close();
            return ("Account Created");
        } catch (Exception e) {
            return "Could not create account";
        }
    }

    public static boolean usernameExists(String username) {
        Path path = Path.of("src/main/resources/users/" + username + ".csv");
        return Files.exists(path);
    }

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

    public static boolean isStrongPassword(String password) {
        boolean containsNumber = false;
        boolean containsLowerCase = false;
        boolean containUpperCase = false;
        boolean containsSymbol = false;
        int charAsInt;
        for (int i = 0; i < password.length(); i++) {
            charAsInt = password.charAt(i);
            if (charAsInt >= 49 && charAsInt <= 57) {
                containsNumber = true;
            }
            else if (charAsInt >= 97 && charAsInt <= 122) {
                containsLowerCase = true;
            }
            else if (charAsInt >= 65 && charAsInt <= 90) {
                containUpperCase = true;
            }
            else {
                containsSymbol = true;
            }
        }
        return containsNumber && containsLowerCase && containUpperCase && containsSymbol;
    }
}