package bsu.edu.cs.cs222.libraries.horses;

import static org.junit.jupiter.api.Assertions.*;

import bsu.edu.cs.cs222.Horse;
import org.junit.jupiter.api.Test;

public class HorseTest {

    @Test
    void testConstructor() {
        Horse horse = new Horse(1, 50);
        assertEquals(1, horse.getName());
    }

    @Test
    void testAdvance() {
        Horse horse = new Horse(1, 50);

        horse.advance();
        horse.advance();

        assertFalse(horse.hasWon());
    }

    @Test
    void testMoveToStart() {
        Horse horse = new Horse(1, 50);

        horse.advance();
        horse.moveToStart();

        assertFalse(horse.hasWon());
    }

    @Test
    void testHasWon() {
        Horse horse = new Horse(1, 50);

        for (int i = 0; i < Horse.getTrackLen(); i++) {
            horse.advance();
        }

        assertTrue(horse.hasWon());
    }

    @Test
    void testTurnAdvance() {
        Horse horse = new Horse(1, 50);

        horse.turn(50);

        assertTrue(horse.hasWon());
    }

    @Test
    void testTurnNoAdvance() {
        Horse horse = new Horse(1, 50);

        horse.turn(50);

        assertFalse(horse.hasWon());
    }

    @Test
    void testMoveToEnd() {
        Horse horse = new Horse(1, 50);

        horse.advance();
        horse.advance();

        assertFalse(horse.hasWon());
    }

    @Test
    void testMoveToEndNoAdvance() {
        Horse horse = new Horse(1, 50);

        horse.advance();
        horse.advance();

        assertFalse(horse.hasWon());
    }
}
