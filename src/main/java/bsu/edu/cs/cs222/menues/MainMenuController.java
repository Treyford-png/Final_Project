package bsu.edu.cs.cs222.menues;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.characters.NPCs.DisplayNPCs;
import bsu.edu.cs.cs222.games.faro.Faro;
import bsu.edu.cs.cs222.games.horse_race.HorseRace;
import bsu.edu.cs.cs222.games.horse_race.HorseRaceController;
import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellGame;
import bsu.edu.cs.cs222.games.roulette.ControllerRoulette;
import bsu.edu.cs.cs222.games.roulette.Roulette;
import bsu.edu.cs.cs222.games.vingt_un.VUController;
import bsu.edu.cs.cs222.games.vingt_un.VingtUn;
import bsu.edu.cs.cs222.menues.leaderboard.RunLeaderboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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

    public void faro(ActionEvent event) throws IOException {
        FXMLLoader faroLoader = new FXMLLoader(getClass().getResource("/fxmls/faro.fxml"));
        Parent root = faroLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        Faro faroController = faroLoader.getController();
        faroController.setUser(user);
        stage.show();
    }

    public void horseRace() throws IOException {
        FXMLLoader hrLoader = new FXMLLoader(getClass().getResource("/fxmls/horse_race.fxml"));
        Parent root = hrLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        HorseRaceController hrController = hrLoader.getController();
        hrController.setUser(user);
        stage.show();
    }


    public void libby(ActionEvent event) {
        LibertyBellGame libertyBell = new LibertyBellGame(user);
        libertyBell.playGame();
    }


    public void roulette(ActionEvent event) throws IOException {
        FXMLLoader rouletteLoader = new FXMLLoader(getClass().getResource("/fxmls/roulette-view.fxml"));
        Parent root = rouletteLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        ControllerRoulette controllerRoulette = rouletteLoader.getController();
        controllerRoulette.setUser(user);
        stage.show();
    }


    public void vu(ActionEvent event) throws IOException {
        FXMLLoader vuLoader = new FXMLLoader(getClass().getResource("/fxmls/vingt_un.fxml"));
        Parent root = vuLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        VUController vuController = vuLoader.getController();
        vuController.setUserVU(user);
        vuController.start();
    }

    public void leaderboard(ActionEvent event) throws IOException {
        FXMLLoader leaderboardLoader = new FXMLLoader(getClass().getResource("/fxmls/leaderboard.fxml"));
        Parent root = leaderboardLoader.load();
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void meetTown() {
        new DisplayNPCs();
    }

    public void exit(ActionEvent event) {
        System.out.println("\nSo long, pardner");
        Stage stage = (Stage) playerInfo.getScene().getWindow();
        stage.close();
    }
}