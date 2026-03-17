package bsu.edu.cs.cs222;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.vingt_un.VingtUn;
import bsu.edu.cs.cs222.menues.MainMenu;
import bsu.edu.cs.cs222.menues.SignIn;

public class Launcher {
    public static void main(String[] args) {
        User user = SignIn.signIn();
        if (user != null) {
            MainMenu mainMenu = new MainMenu(user);
            mainMenu.runMainMenu();
        }
    }
}
