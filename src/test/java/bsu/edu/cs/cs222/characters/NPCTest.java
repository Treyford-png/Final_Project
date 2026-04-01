package bsu.edu.cs.cs222.characters;

import bsu.edu.cs.cs222.characters.NPCs.AllNPCs;
import org.junit.jupiter.api.Test;

public class NPCTest {

    @Test
    public void getNPC() {
        AllNPCs allNPCs = new AllNPCs("Test");
        assert allNPCs.getNPC("HOM").getName().equals("Homesteader Lyle Kerland");
    }

    @Test
    public void testLoad() {
        AllNPCs allNPCs = new AllNPCs("Test");
        allNPCs.loadNPCs();
        assert allNPCs.getNPC("HOM").getName().equals("Homesteader Lyle Kerland");
    }

    @Test
    public void testAddPoints() {
        AllNPCs allNPCs = new AllNPCs("Test");
        int points = allNPCs.getNPC("HOM").getPoints();
        allNPCs.getNPC("HOM").addPoints(1000);
        points += 1000;
        assert (allNPCs.getNPC("HOM").getPoints() == points);
        allNPCs.getNPC("HOM").addPoints(-500);
        points -= 500;
        assert (allNPCs.getNPC("HOM").getPoints() == points);
    }

    @Test
    public void testSave() {
        AllNPCs allNPCs = new AllNPCs("Test");
        allNPCs.getNPC("HOM").addPoints(-10000);
        assert allNPCs.getNPC("HOM").getPoints() == 0;
        allNPCs.saveNPCs();

        allNPCs = new AllNPCs("Test");
    }
}
