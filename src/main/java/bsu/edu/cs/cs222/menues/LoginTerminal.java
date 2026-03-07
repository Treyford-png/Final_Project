package bsu.edu.cs.cs222.menues;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LoginTerminal {
    public static boolean loginPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String Username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        return login(Username, password);
    }

    public static boolean login(String username, String password) {
        // Tries to open file with username
        Path path = Path.of("src/main/resources/users/" + username + ".csv");
        try {
            BufferedReader userFileReader = Files.newBufferedReader(path);
            String userLine = userFileReader.readLine();
            String[] parsedUserLine = userLine.split(",");
            if (parsedUserLine[0].equals(password)) {
                System.out.println("Welcome Back!");
                return true;
            }
            else {
                System.out.println("Password incorrect");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Username could not be found");
            return false;
        }
    }
}