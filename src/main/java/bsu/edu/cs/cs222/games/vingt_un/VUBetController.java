package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.helpers.HelpersFX;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VUBetController {
    private User user;
    private VUController vuController;
    @FXML
    private Spinner<Integer> betSpinner;
    @FXML
    private Label pointsLabel;
    @FXML
    private AnchorPane anchorPane;

    public VUBetController() {
        user = null;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory
                (25, 25);
        valueFactory.increment(25);
        valueFactory.decrement(25);
    }

    public void setVUController(VUController vuController) {
        this.vuController = vuController;
    }

    public void setUser(User user) {
        this.user = user;
        HelpersFX.setFactory(betSpinner, user.getPoints());
        pointsLabel.setText(String.valueOf(user.getPoints()));
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setOnCloseRequest(event -> {
            try {
                exit();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void placeBet() {
        vuController.placeBet(betSpinner.getValue());
        close();
        vuController.gameTurn();
    }

    public void exit() throws IOException {
        // Create confirmation panel
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("exit");
        alert.setHeaderText("Do you want to exit vingt-un?");
        alert.setHeaderText("All data will be saved?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            close();
            vuController.closeGame();
        }
    }

    public void close() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}