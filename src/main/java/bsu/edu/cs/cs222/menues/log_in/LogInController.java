package bsu.edu.cs.cs222.menues.log_in;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class LogInController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private Stage mainStage;

    public void attemptLogIn(ActionEvent event) throws IOException {
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

    public void gotoMainMenu(User user) throws IOException {
        FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/main_menu.fxml"));
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
