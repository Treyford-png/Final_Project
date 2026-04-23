package bsu.edu.cs.cs222.games;
import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.games.liberty_bell.*;
import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellMachine;
import bsu.edu.cs.cs222.games.liberty_bell.LibertyBellSymbols;
import org.junit.jupiter.api.Test;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class LibertyBellMachineTest {

    User user = new User("Test", "test", 1000);
    /**
     *  Returns a fake random from index 0 if the strip is {BELL,BELL,BELL} will return BELL BELL BELL
     */
    private LibertyBellMachine machineWithFixed(LibertyBellSymbols... symbols) {
        Random fakeRng = new Random(0);
        return new LibertyBellMachine(fakeRng, symbols);
    }

    @Test
    public void enumPointValues_areCorrect() {
        assertEquals(1000, LibertyBellSymbols.LIBERTY_BELL.getPoints());
        assertEquals(500, LibertyBellSymbols.HORSESHOE.getPoints());
        assertEquals(200, LibertyBellSymbols.DIAMOND.getPoints());
        assertEquals(100, LibertyBellSymbols.SPADE.getPoints());
        assertEquals(50,LibertyBellSymbols.HEART.getPoints());
    }

    // calculate payout logic tests

    @Test
    public void calculatePayout_threeOfAKind_returnsFullPoints() {
        LibertyBellMachine  machine = new LibertyBellMachine(user);
        LibertyBellSymbols[] result = {
                LibertyBellSymbols.LIBERTY_BELL,
                LibertyBellSymbols.LIBERTY_BELL,
                LibertyBellSymbols.LIBERTY_BELL
        } ;
        assertEquals(1000, machine.calculatePayout(result));
    }


    @Test
    public void calculatePayout_twoBells_returnsHalfPoints() {
        LibertyBellMachine machine = new LibertyBellMachine(user);
        LibertyBellSymbols[] result = {
                LibertyBellSymbols.LIBERTY_BELL,
                LibertyBellSymbols.LIBERTY_BELL,
                LibertyBellSymbols.HEART

        };
        assertEquals(500, machine.calculatePayout(result));
    }

    @Test
    public void calculatePayout_zeroMatch_returnsZero() {
        LibertyBellMachine machine = new LibertyBellMachine(user);
        LibertyBellSymbols[] result = {
                LibertyBellSymbols.LIBERTY_BELL,
                LibertyBellSymbols.DIAMOND,
                LibertyBellSymbols.HEART
        };
        assertEquals(0, machine.calculatePayout(result));
    }
    @Test
    public void calculatePayout_nullInput_throwsException() {
        LibertyBellMachine machine = new LibertyBellMachine(user);
        assertThrows(IllegalArgumentException.class, () -> machine.calculatePayout(null));
    }

    // spin logic tests
    @Test
    public void spin_returnsThreeSymbols() {
        LibertyBellMachine machine = new LibertyBellMachine(user);
        LibertyBellSymbols[] result = machine.spin();
        assertEquals(3, result.length);
    }

    @Test
    public void spin_SingleSymbolStrip_alwaysReturnsThatSymbol() {
        LibertyBellSymbols [] singleStrip = {LibertyBellSymbols.HEART};
        LibertyBellMachine machine = new LibertyBellMachine(new Random(), singleStrip);
    }

    @Test
    public void setBet_validValue_updatesCorrectly() {
        LibertyBellMachine machine = new LibertyBellMachine(user);
        machine.setBet(10);
        assertEquals(10, machine.getBet());
    }

    @Test
    public void spinAndApply_noMatch() {
        // given a 3 symbol spin , all different symbols a no match is possible.
        // symbol spin cant test a loss, construct payout manually
        LibertyBellMachine machine = new LibertyBellMachine(user);
        machine.setBet(1);

        //verify calculatePayout on a known loss result
        LibertyBellSymbols[] lossResult = {
                LibertyBellSymbols.LIBERTY_BELL,
                LibertyBellSymbols.DIAMOND,
                LibertyBellSymbols.HEART
        };
        assertEquals(0, machine.calculatePayout(lossResult));
        // confirms it's a loss
    }

}