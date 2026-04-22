package bsu.edu.cs.cs222.helpers;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpersFX {
    public static void gotoMainMenu(User user, Stage mainStage) throws IOException {
        FXMLLoader mmLoader = new FXMLLoader(HelpersFX.class.getResource("/fxmls/menues/main_menu.fxml"));
        Parent root = mmLoader.load();
        mainStage.setScene(new Scene(root));
        mainStage.show();
        MainMenuController mmController = mmLoader.getController();
        mmController.setUser(user);
    }

    public static void setFactory(Spinner<Integer> spinner, int userPoints) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(25, userPoints, 25) {
                    @Override
                    public void increment(int steps) {
                        setValue(Math.min(getValue() + (steps * 25), userPoints));
                    }

                    @Override
                    public void decrement(int steps) {
                        setValue(Math.max(getValue() - (steps * 25), 25));
                    }
                };
        spinner.setValueFactory(valueFactory);
        spinner.setEditable(true);
    }

}