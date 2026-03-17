package bsu.edu.cs.cs222.games;
import bsu.edu.cs.cs222.games.roulette.Roulette;
import org.junit.jupiter.api.Test;
import bsu.edu.cs.cs222.characters.*;

import static org.junit.jupiter.api.Assertions.*;

public class RouletteTest {

    User user = new User("test", "test", 1000);
    @Test
    void testColor(){
        Roulette roulette = new Roulette(user);
        assert roulette.getColor("8").equals("black");
    }
}
