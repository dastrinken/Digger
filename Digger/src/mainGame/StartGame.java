package mainGame;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import npcOrganizer.NpcManager;

abstract public class StartGame {
	static GameManager manager;
	static NpcManager npc;
	public static Player player;
	public static int lives = 3;
	public static int points = 0;
	public static int level;
	
	//disable cheat before release!
	private static Cheat cheat = new Cheat();

	public static void newGame() {
		player = new Player(lives, points, 1);
		manager = GameManager.getInstance();
		npc = NpcManager.getInstance();
		manager.createBoard(player);
		GameManager.setUpBoard();
	}

	public static void loadSaveGame() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.bin"))) {
			player = (Player) in.readObject();
			System.out.println("Successfully loaded game");
			System.out.println();
		} catch (Exception e) {
			player = new Player(lives, points, 1);
			System.out.println("Failed loading game");
			System.out.println();
		}
		manager = GameManager.getInstance();
		npc = NpcManager.getInstance();
		manager.createBoard(player);
		GameManager.setUpBoard();
	}
	
	//for testing purposes, delete before release
	public static void cheat() {
		cheat.cheat();
	}
}