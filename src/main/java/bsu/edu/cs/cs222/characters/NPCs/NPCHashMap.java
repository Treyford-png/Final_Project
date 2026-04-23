package bsu.edu.cs.cs222.characters.NPCs;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Hash map with an NPC and a String key
 * Only exists to make HashMap serializable
 *
 * @author Holden Hankins
 */
public class NPCHashMap extends HashMap<String, NPC> implements Serializable {}