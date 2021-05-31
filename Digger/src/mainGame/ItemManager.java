package mainGame;

public class ItemManager extends GameManager {

	public ItemManager() {
		super();
	}
	
	public static void setUpItems(int level) {
		int xCollectable;
		int yCollectable;

		collectableArray = LevelManager.getTomatoPos(level);
		solidsArray = LevelManager.getSolidsPos(level);
		onionsArray = LevelManager.getOnionsPos(level);

		for (int i = 0; i < collectableArray.length; i++) {
			xCollectable = collectableArray[i][0];
			yCollectable = collectableArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/earth_tomato.png \n");
			
			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		for (int i = 0; i < solidsArray.length; i++) {
			xCollectable = solidsArray[i][0];
			yCollectable = solidsArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/solid.png \n");
			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		for (int i = 0; i < onionsArray.length; i++) {
			xCollectable = onionsArray[i][0];
			yCollectable = onionsArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/earth_onion.png \n");
			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}

	}
}
