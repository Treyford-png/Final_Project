package bsu.edu.cs.cs222.menues.log_in;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Creates a book for the user to use to log in
 *
 * @author Holden Hankins
 */
public class LogInController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private Stage mainStage;

    /**
     * Tries to log in using the LogIn class
     * @throws IOException exception
     */
    public void attemptLogIn() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = LogIn.login(username, password);
        if (user == null) {
            messageLabel.setText("username or password invalid");
        }
        else {
            gotoMainMenu(user);
        }
    }

    /**
     * Goes to main menu and closes book
     * @param user User passed
     * @throws IOException exception
     */
    public void gotoMainMenu(User user) throws IOException {
        FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/menues/main_menu.fxml"));
        Parent root = mmLoader.load();
        mainStage.setScene(new Scene(root));
        mainStage.show();
        MainMenuController mmController = mmLoader.getController();
        mmController.setUser(user);

        // Closes current stage
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }
}
