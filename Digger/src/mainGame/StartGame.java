package mainGame;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class StartGame {
	static GameManager manager;
	public static Player player;
	public static int lives = 3;
	public static int points = 0;
	public static int level;

	public static void main(String[] args) {
		// Start a new game (& only a new game, use MainMenu.java to start the whole program)
		player = new Player(lives, points, 1);
		manager = GameManager.getInstance();
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
		manager.createBoard(player);
		GameManager.setUpBoard();
	}
	
	//for testing purposes, delete before release
	public static void cheat() {
		player = new Player(1, 5, 10);
		manager = GameManager.getInstance();
		manager.createBoard(player);
		GameManager.setUpBoard();
	}
}