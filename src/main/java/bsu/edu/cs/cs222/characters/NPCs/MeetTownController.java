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

/**
 * Controller that allows a user to see all 16 members of the town
 *
 * @author Holden Hankins
 */
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

    /**
     * Go back to main menu
     * @throws IOException exception
     */
    public void back() throws IOException {
        FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/menues/main_menu.fxml"));
        Parent root = mmLoader.load();
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        MainMenuController mmController = mmLoader.getController();
        mmController.setUser(user);
    }
}
