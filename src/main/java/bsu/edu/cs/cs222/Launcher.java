package bsu.edu.cs.cs222;

import bsu.edu.cs.cs222.menues.MainMenu;
import bsu.edu.cs.cs222.menues.SignIn;

public class Launcher {
    public static void main(String[] args) {
        boolean signedIn = SignIn.signIn();
        if (signedIn) {
            MainMenu.mainMenu();
        }
    }
}
