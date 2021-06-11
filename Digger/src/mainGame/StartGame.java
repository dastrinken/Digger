package mainGame;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * This program is a Remake of the original game "Digger" released in 1983 as
 * part of a study project by Armin Prinz & Sebastian Ziegler
 * 
 * @author Armin Prinz
 * @author Sebastian Ziegler
 * @version 1.0
 * 
 */

public class StartGame {
	static GameManager manager;
	public static Player player;
	public static int lives = 3;
	public static int points = 0;
	public int level;

	public static void main(String[] args) {
		// Start a new game
		player = new Player(lives, points, 1);
		manager = GameManager.getInstance();
		manager.createBoard(player);
		manager.setUpBoard();
	}

	public static void loadSaveGame() {
		player = load();
		manager = GameManager.getInstance();
		manager.createBoard(player);
		manager.setUpBoard();
	}
	
	public static Player load() {
		player = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.bin"))) {
			player = (Player) in.readObject();
			System.out.println("Successfully loaded game");
			System.out.println();
		} catch (Exception e) {
			player = new Player(lives, points, 1);
			System.out.println("Failed loading game");
			System.out.println();
		}
		return player;
	}
}