package bsu.edu.cs.cs222.characters.NPCs;

import java.io.*;

public class AllNPCs implements Serializable {
    private NPCHashMap npcs;
    private String username;

    public AllNPCs(String username) {
        this.username = username;
        npcs = new NPCHashMap();
        loadNPCs();
        saveNPCs();
    }

    public void generateNPCs() {
        npcs.put("HOM", new NPC("HOM", "Homesteader Lyle Kerland", 2000, 15));
        npcs.put("FRM", new NPC("FRM", "Farmer Sarah McMillan", 1500, 14));
        npcs.put("COW", new NPC("COW", "Cowhand Mateo Hernandez", 2000, 17));
        npcs.put("MNR", new NPC("MNR", "Miner Charlie Wilmington", 7500, 15));
        npcs.put("MNO", new NPC("MNO", "Mine Owner Lawrence DuPonte", 500000, 17));
        npcs.put("RRW", new NPC("RRW", "Railroad Worker John Ah Song", 1000, 13));
        npcs.put("MRC", new NPC("MRC", "Merchant Clint Fletcher", 8000, 16));
        npcs.put("SLN", new NPC("SLN", "Saloon Keeper Solomon Adams", 14000, 16));
        npcs.put("BRN", new NPC("BRN", "Cattle Baron Sylvester Steep", 300000, 17));
        npcs.put("SHR", new NPC("SHR", "Sheriff Emmett Elroy", 6000, 16));
        npcs.put("OUT", new NPC("OUT", "Outlaw Kansas Kate", 1750, 18));
        npcs.put("GNG", new NPC("GNG", "Gang Leader Bucky Beau Boone", 6000, 17));
        npcs.put("VET", new NPC("VET", "Veteran Noah Freeman", 1600, 13));
        npcs.put("DOC", new NPC("DOC", "Doc Henry Morgan", 6000, 14));
        npcs.put("WDW", new NPC("WDW", "Widow Sarah Carter", 1250, 14));
        npcs.put("BGR", new NPC("BGR", "Beggar Richard", 300, 17));
    }

    public NPC getNPC(String key) {
        return npcs.get(key);
    }

    public int addPoints(String key, int points) {
        NPC npc = npcs.get(key);
        if (npc == null) {
            return -1;
        }

        npc.addPoints(points);
        return npc.getPoints();
    }

    public void saveNPCs() {
        try {
            FileOutputStream fOut = new FileOutputStream("src/main/resources/users/" + username + ".dat");
            ObjectOutputStream objOut = new ObjectOutputStream(fOut);
            objOut.writeObject(npcs);
            objOut.writeObject(username);
            objOut.close();
        } catch (Exception e) {
            System.out.println("Error occurred while saving NPC data");
        }
    }

    public void loadNPCs() {
        File file = new File("src/main/resources/users/" + username + ".dat");
        if (!file.exists()) {
            generateNPCs();
            saveNPCs();
            return;
        }

        try {
            FileInputStream fIn = new FileInputStream("src/main/resources/users/" + username + ".dat");
            ObjectInputStream objIn = new ObjectInputStream(fIn);
            npcs = (NPCHashMap) objIn.readObject();
            username = (String) objIn.readObject();
            objIn.close();
            fIn.close();
        } catch (Exception e) {
            System.out.println("Error occurred while loading NPC data");
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (String key : npcs.keySet()) {
            NPC npc = npcs.get(key);
            output.append(npc.getName()).append(" - ").append(npc.getPoints()).append("p\n");
        }
        return output.toString();
    }
}