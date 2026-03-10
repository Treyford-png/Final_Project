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

    static boolean containsValidChars(String str) {
        int hasSpace = str.indexOf(' ');
        int hasDot = str.indexOf('.');
        int hasSlash = str.indexOf('\\');
        int hasComma = str.indexOf(',');

        return hasSpace == -1 && hasDot == -1 && hasSlash == -1 && hasComma == -1;
    }

    public static void signIn() {
        Scanner scanner = new Scanner(System.in);
        int input;
        for (int i = 0; i < 10; i++) {
            System.out.println("Would you like to\n(1) Sign In\n(2) Sign Up\n");
            input = scanner.nextInt();
            if (input == 1) {
                LogIn.loginPrompt();
                break;
            }
            else if (input == 2) {
                SignUp.signUpPrompt();
            }
            else {
                System.out.println("Invalid input");
            }
        }
    }
}