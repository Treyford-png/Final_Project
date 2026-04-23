package bsu.edu.cs.cs222.menues;


import bsu.edu.cs.cs222.menues.log_in.LogInController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the launch screen for when the user first enters the game
 *
 * @author Holden Hankins
 * @author Laura Romero
 * @author Treyfor Mercer
 */
public class LaunchController {

    @FXML
    private AnchorPane anchorPane;


    public void goToLogIn() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxmls/menues/log_in_book.fxml")));
        Parent root = loader.load();

        LogInController controller = loader.getController();
        controller.setMainStage((Stage) anchorPane.getScene().getWindow());

        Stage stage2 = new Stage();
        stage2.setTitle("Logbook");
        stage2.setScene(new Scene(root));
        stage2.show();
    }

    public void goToSignUp() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/menues/sign_up_book.fxml")));
        Stage stage2 = new Stage();
        stage2.setTitle("Logbook");
        stage2.setScene(new Scene(root));
        stage2.show();

    }

    public void goToCredits() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/menues/credits.fxml")));
        Stage stage2 = new Stage();
        stage2.setTitle("Credits");
        stage2.setScene(new Scene(root));
        stage2.show();
    }

    public void exit() throws IOException {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    public void loadMainMenu() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/menues/main_menu.fxml")));
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        System.exit(0);
    }
}