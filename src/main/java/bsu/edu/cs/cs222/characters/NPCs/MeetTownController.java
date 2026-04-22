package bsu.edu.cs.cs222.characters.NPCs;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MeetTownController {
    private User user;
    @FXML
    private TextArea textArea;

    public MeetTownController() {}

    public void setUser(User user) {
        this.user = user;
        AllNPCs allNPCs = new AllNPCs(user.getUsername());
        textArea.setText(allNPCs.toString());
    }

    public void back() throws IOException {
        FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/main_menu.fxml"));
        Parent root = mmLoader.load();
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        MainMenuController mmController = mmLoader.getController();
        mmController.setUser(user);
    }
}
