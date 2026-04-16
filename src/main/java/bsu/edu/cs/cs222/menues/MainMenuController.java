package bsu.edu.cs.cs222.menues;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.characters.NPCs.DisplayNPCs;
import bsu.edu.cs.cs222.games.faro.Faro;
import bsu.edu.cs.cs222.games.horse_race.HorseRace;
import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellGame;
import bsu.edu.cs.cs222.games.roulette.Roulette;
import bsu.edu.cs.cs222.games.vingt_un.VingtUn;
import bsu.edu.cs.cs222.menues.leaderboard.RunLeaderboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public void faro(ActionEvent event) {
        System.out.println("Running Faro...");
        Faro faro = new Faro(user);
        faro.runGame();
    }

    public void horseRace(ActionEvent event) {
        System.out.println("Running Horse Race...");

        HorseRace horseRace = new HorseRace(user);
        horseRace.horseRace();
    }


    public void libby(ActionEvent event) {
        LibertyBellGame libertyBell = new LibertyBellGame(user);
        libertyBell.playGame();
    }


    public void roulette(ActionEvent event) {
        Roulette roulette = new Roulette(user);
        roulette.runRoulette();
    }


    public void vu(ActionEvent event) {
        VingtUn vingtUn = new VingtUn(user);
        vingtUn.runGame();
    }

    public void leaderboard(ActionEvent event) {
        new RunLeaderboard();
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