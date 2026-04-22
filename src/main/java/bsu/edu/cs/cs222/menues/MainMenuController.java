package bsu.edu.cs.cs222.menues;

import bsu.edu.cs.cs222.characters.NPCs.MeetTownController;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.faro.Faro;
import bsu.edu.cs.cs222.games.horse_race.HorseRaceController;
import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellController;
import bsu.edu.cs.cs222.games.roulette.ControllerRoulette;
import bsu.edu.cs.cs222.games.vingt_un.VUController;
import bsu.edu.cs.cs222.menues.leaderboard.Leaderboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private User user;

    @FXML
    private Label playerInfo;

    public void setUser(User user) {
        this.user = user;
        setLabel();
    }

    public void setLabel() {
        playerInfo.setText(" " + user.getUsername() + " - " + user.getPoints() + "p");
    }

    public void faro() throws IOException {
        FXMLLoader faroLoader = new FXMLLoader(getClass().getResource("/fxmls/games/faro.fxml"));
        Parent root = faroLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        Faro faroController = faroLoader.getController();
        faroController.setUser(user);
        stage.show();
    }

    public void horseRace() throws IOException {
        FXMLLoader hrLoader = new FXMLLoader(getClass().getResource("/fxmls/games/horse_race.fxml"));
        Parent root = hrLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        HorseRaceController hrController = hrLoader.getController();
        hrController.setUser(user);
        stage.show();
    }

    public void libby() throws IOException {
        FXMLLoader lbLoader = new FXMLLoader(getClass().getResource("/fxmls/games/LibertyBellEntryview.fxml"));
        Parent root = lbLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        LibertyBellController lbController = lbLoader.getController();
        lbController.setUser(user);
        stage.show();
    }


    public void roulette() throws IOException {
        FXMLLoader rouletteLoader = new FXMLLoader(getClass().getResource("/fxmls/games/roulette-view.fxml"));
        Parent root = rouletteLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        ControllerRoulette controllerRoulette = rouletteLoader.getController();
        controllerRoulette.setUser(user);
        stage.show();
    }


    public void vu() throws IOException {
        FXMLLoader vuLoader = new FXMLLoader(getClass().getResource("/fxmls/games/vingt_un.fxml"));
        Parent root = vuLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        VUController vuController = vuLoader.getController();
        vuController.setUserVU(user);
        vuController.start();
    }

    public void leaderboard() throws IOException {
        FXMLLoader leaderboardLoader = new FXMLLoader(getClass().getResource("/fxmls/menues/leaderboard.fxml"));
        Parent root = leaderboardLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        Leaderboard leaderboard = leaderboardLoader.getController();
        leaderboard.setUser(user);
    }

    public void meetTown() throws IOException {
        FXMLLoader townLoader = new FXMLLoader(getClass().getResource("/fxmls/menues/meet_town.fxml"));
        Parent root = townLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        MeetTownController meetTownController = townLoader.getController();
        meetTownController.setUser(user);
    }

    public void exit() {
        System.out.println("\nSo long, pardner");
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.close();
    }
}