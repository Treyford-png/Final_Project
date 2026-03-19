package bsu.edu.cs.cs222.games.horse_race;

import bsu.edu.cs.cs222.characters.User;

public class RunHorseRace {
    public static void main() {
        HorseRace horseRace = new HorseRace(new User("Test", "test", 5000));
        horseRace.horseRace();
    }
}
