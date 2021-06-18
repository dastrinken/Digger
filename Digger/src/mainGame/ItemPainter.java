package mainGame;

public class ItemPainter extends GameManager {
	private static int xCollectable;
	private static int yCollectable;

	public ItemPainter() {
		super();
	}

	public static void setUpItems(int level) {
		setUpLava();
		setUpOnions(level);
		setUpTomatoes(level);
		setUpHealth(level);
		setUpFrosties(level);
		setUpSolids();
		
	}

	private static void setUpTomatoes(int level) {
		for (int i = 0; i < collectableArray.length; i++) {
			xCollectable = collectableArray[i][0];
			yCollectable = collectableArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " images/earth_tomato.png \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		// Using switch-case for lava tomatoes for better performance
		switch (level) {
		case 4:
			board.receiveMessage("image " + 0 + " " + 15 + " images/lava_tomato.png \n");
			break;
		default:
			break;
		}
	}

	private static void setUpOnions(int level) {
		for (int i = 0; i < onionsArray.length; i++) {
			xCollectable = onionsArray[i][0];
			yCollectable = onionsArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " images/earth_onion.png \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
	}
	
	private static void setUpHealth(int level) {
		for (int i = 0; i < healthArray.length; i++) {
			xCollectable = healthArray[i][0];
			yCollectable = healthArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " images/earth_life.png \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		// Using switch-case for lava health for better performance
		switch (level) {
		case 4:
			board.receiveMessage("image " + 7 + " " + 17 + " images/lava_life.png \n");
			break;
		default:
			break;
		}
		
	}
	
	private static void setUpFrosties(int level) {
		for (int i = 0; i < frostArray.length; i++) {
			xCollectable = frostArray[i][0];
			yCollectable = frostArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " images/earth_frost.png \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
	}
	
	private static void setUpLava() {
		for (int i = 0; i < lavaArray.length; i++) {
			xCollectable = lavaArray[i][0];
			yCollectable = lavaArray[i][1];

			for (int k = xCollectable - 1; k <= xCollectable + 1; k++) {
				for (int l = yCollectable - 1; l <= yCollectable + 1; l++) {
					for (int j = 0; j < lavaArray.length; j++) {
						if (k < boardSize && k >= 0 && l < boardSize && l >= 0) {
							board.receiveMessage("image " + k + " " + l + " images/earth_lava.png \n");
						}
					}
				}
			}

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		// Double checking and repainting lava source in lava block
		for (int i = 0; i < lavaArray.length; i++) {
			xCollectable = lavaArray[i][0];
			yCollectable = lavaArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " images/lava.png \n");
		}
	}

	private static void setUpSolids() {
		for (int i = 0; i < solidsArray.length; i++) {
			xCollectable = solidsArray[i][0];
			yCollectable = solidsArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " images/solid.png \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
	}
}