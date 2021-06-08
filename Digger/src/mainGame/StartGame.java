package mainGame;

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
	private static int lives = 3;
	private static int points = 0;
	protected static Player player;
	//Test
	public static void main(String[] args) {
		// Start a new game
		player = new Player(lives, points, 1);
		manager = new GameManager();
		manager.createBoard();
		manager.setUpBoard(player.level);
		// TODO: Savegames & Highscore
	}

	public static void nextLevel() {
		System.out.println("Gewonnen!");
		manager.setUpBoard(player.level);
	}
	
	public static void loadSaveGame(int level) {
		// TODO: Load attributes from File
		player = new Player(lives, points, level);
		manager.setUpBoard(player.level);
	}
}