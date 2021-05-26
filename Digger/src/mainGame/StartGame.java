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
		// Neues Spiel starten.
		manager = new GameManager(1);
		manager.setUpBoard();
		manager.setUpItems();

		// TODO: Spiel fortsetzen und Highscore
	}

	public static void nextLevel() {
		System.out.println("Gewonnen!");
		manager.graphic.dispose();
		manager.setUpBoard();
		manager.setUpItems();
	}
}
