package bsu.edu.cs.cs222.menues;
import java.util.Scanner;

public class SignIn {
    static String getUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        return scanner.nextLine();
    }

    static String getPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Password: ");
        return scanner.nextLine();
    }
}