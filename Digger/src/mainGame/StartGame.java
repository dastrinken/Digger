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
	//Test
	public static void main(String[] args) {
		// Start a new game
		GameManager manager = GameManager.getInstance();
		manager.createBoard();
		manager.setUpBoard();
	}
	
	/* TODO: loadSaveGame & HighScore
	 * public static void loadSaveGame(int level) { // TODO: Load attributes from
	 * File manager.setUpBoard(); }
	 */
}