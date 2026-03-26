package bsu.edu.cs.cs222.menues;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private TextField textbox;

    @FXML
    public void Method(ActionEvent actionEvent) {
        String output = textbox.getText();
        Label label = new Label(output);
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 300, 200);
        Stage popup = new Stage();
        popup.setTitle("Placeholder");
        popup.setScene(scene);
        popup.show();
    }

    public void getOutput(){

    }
}