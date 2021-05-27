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
	//Test
	public static void main(String[] args) {
		// Start a new game
		manager = new GameManager();
		manager.setUpBoard();
		manager.setUpItems();
		// TODO: Savegames & Highscore
	}

	public static void nextLevel() {
		System.out.println("Gewonnen!");
		GameManager.graphic.dispose();
		manager.setUpBoard();
		manager.setUpItems();
	}
}
