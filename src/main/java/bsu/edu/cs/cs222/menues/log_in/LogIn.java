package bsu.edu.cs.cs222.menues.log_in;
import bsu.edu.cs.cs222.characters.User;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogIn extends SignIn {

    public static User loginPrompt() {
        String username;
        String password;
        User user;
        for (int i = 0; i < 10; i++) {
            username = getUsername();
            password = getPassword();
            user = login(username, password);
            if (user != null) {
                return user;
            }
        }
        System.out.println("Too many attempts, please try again later");
        return null;
    }

    public static User login(String username, String password) {
        // Tries to open file with username
        Path path = Path.of("src/main/resources/users/" + username + ".csv");
        try {
            BufferedReader userFileReader = Files.newBufferedReader(path);
            String userLine = userFileReader.readLine();
            String[] parsedUserLine = userLine.split(",");
            if (parsedUserLine[0].equals(username) && parsedUserLine[1].equals(password)) {
                System.out.println("Welcome Back!");
                return new User(username, password, Integer.parseInt(parsedUserLine[2]));
            }
            else {
                System.out.println("Password incorrect");
                return null;
            }
        } catch (IOException e) {
            System.out.println("Username could not be found");
            return null;
        } // close try-catch
    } // close login
}