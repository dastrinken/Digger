package levelOrganizer;

import mainGame.GameManager;

public class ItemPainter extends GameManager {
	private static int xCollectable;
	private static int yCollectable;

	public ItemPainter() {
		super();
	}

	public static void setUpItems(int level) {
		setUpEarth();
		setUpLava();
		setUpDiamonds();
		setUpOnions(level);
		setUpTomatoes(level);
		setUpHealth(level);
		setUpFrosties(level);
		setUpEnemyPath(level);
		setUpEmptyCauldrons();
		setUpSolids();
	}

	private static void setUpEarth() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (i != posX || j != posY) {
					xsend.farbe2(i, j, 0x000);
					board.receiveMessage("image " + i + " " + j + " ./images/earth.png \n");
					symbol = board.getSymbol(i, j);
					symbol.getImageObject().setWorldWidth(0);
				}
			}
		}
	}
	//Enemies use board color to decide which way to go, removing the image (optional) makes enemies way visible from the beginning
	private static void setUpEnemyPath(int level) {
		switch (level) {
		case 1:
			for (int i = 0; i <= 19; i++) {
				xsend.farbe2(i, 16, 0x95612D);
				board.receiveMessage("image " + i + " " + 16 + " - \n");
			}
			break;
		case 2:
			for (int i = 6; i <= 10; i++) {
				xsend.farbe2(i, 18, 0x95612D);
				board.receiveMessage("image " + i + " " + 18 + " - \n");

				xsend.farbe2(i, 11, 0x95612D);
				board.receiveMessage("image " + i + " " + 11 + " - \n");
			}
			for (int i = 11; i <= 18; i++) {
				xsend.farbe2(10, i, 0x95612D);
				board.receiveMessage("image " + 10 + " " + i + " - \n");

				xsend.farbe2(6, i, 0x95612D);
				board.receiveMessage("image " + 6 + " " + i + " - \n");
			}
			break;
		case 4:
			for(int i = 2; i <= 17; i++) {
				xsend.farbe2(i, 13, 0x95612D);
				board.receiveMessage("image " + i + " " + 13 + " - \n");
			}
			
			xsend.farbe2(11, 12, 0x95612D);
			board.receiveMessage("image " + 11 + " " + 12 + " - \n");
			
			for(int i = 2; i <= 17; i++) {
				if(i != 10) {
					xsend.farbe2(i, 11, 0x95612D);
					board.receiveMessage("image " + i + " " + 11 + " - \n");
				}
			}
			
			xsend.farbe2(16, 10, 0x95612D);
			board.receiveMessage("image " + 16 + " " + 10 + " - \n");

			xsend.farbe2(5, 10, 0x95612D);
			board.receiveMessage("image " + 5 + " " + 10 + " - \n");
			
			for(int i = 3; i <= 17; i++) {
				xsend.farbe2(i, 9, 0x95612D);
				board.receiveMessage("image " + i + " " + 9 + " - \n");
			}
			break;
		case 7:
			for (int i = 0; i < 6; i++) {
				xsend.farbe2(9, i + 14, 0x95612D);
				board.receiveMessage("image " + 9 + " " + (i + 14) + " - \n");
			}
			for (int i = 0; i < 3; i++) {
				xsend.farbe2(i + 10, 19, 0x95612D);
				board.receiveMessage("image " + (i + 10) + " " + 19 + " - \n");
			}
			for (int i = 0; i < 4; i++) {
				xsend.farbe2(12, i + 15, 0x95612D);
				board.receiveMessage("image " + 12 + " " + (i + 15) + " - \n");
			}
			for(int i = 0; i < 3; i++) {
				xsend.farbe2(i + 10, 14, 0x95612D);
				board.receiveMessage("image " + (i + 10) + " " + 14 + " - \n");
			}
			break;
		case 10:
			for (int i = 0; i < 9; i++) {
				xsend.farbe2(4, i + 6, 0x95612D);
				board.receiveMessage("image " + 4 + " " + (i + 6) + " - \n");
			}
			for (int i = 0; i < 3; i++) {
				xsend.farbe2(5, i + 6, 0x95612D);
				board.receiveMessage("image " + 5 + " " + (i + 6) + " - \n");
			}
			for (int i = 0; i < 3; i++) {
				xsend.farbe2(5, i + 12, 0x95612D);
				board.receiveMessage("image " + 5 + " " + (i + 12) + " - \n");
			}
			break;
		default:
			break;
		}
	}

	//Switch-cases are just for items painted above hot (fire) earth. Use ItemArrays.java to define new levels.
	//This goes for all painters (setUpOnions, setUpHealth etc...)
	private static void setUpTomatoes(int level) {
		for (int i = 0; i < collectableArray.length; i++) {
			xCollectable = collectableArray[i][0];
			yCollectable = collectableArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " images/earth_tomato.jpg \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		// Using switch-case for lava tomatoes (better performance)
		switch (level) {
		case 4:
			board.receiveMessage("image " + 18 + " " + 11 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 18 + " " + 19 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 18 + " " + 18 + " ./images/fire_tomato.jpg \n");
			break;
		case 5:
			board.receiveMessage("image " + 0 + " " + 15 + " ./images/fire_tomato.jpg \n");
			break;
		case 6:
			board.receiveMessage("image " + 0 + " " + 1 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 0 + " " + 2 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 1 + " " + 0 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 1 + " " + 2 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 2 + " " + 0 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 2 + " " + 1 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 2 + " " + 2 + " ./images/fire_tomato.jpg \n");
			break;
		case 7:
			board.receiveMessage("image " + 14 + " " + 0 + " ./images/fire_tomato.jpg \n");
			board.receiveMessage("image " + 1 + " " + 18 + " ./images/fire_tomato.jpg \n");
			break;
		default:
			break;
		}
	}

	private static void setUpOnions(int level) {
		for (int i = 0; i < onionsArray.length; i++) {
			xCollectable = onionsArray[i][0];
			yCollectable = onionsArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/earth_onion.jpg \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		switch (level) {
		case 4:
			board.receiveMessage("image " + 1 + " " + 9 + " ./images/fire_onion.jpg \n");
			board.receiveMessage("image " + 1 + " " + 13 + " ./images/fire_onion.jpg \n");
			break;
		case 5:
			board.receiveMessage("image " + 5 + " " + 5 + " ./images/fire_onion.jpg \n");
			board.receiveMessage("image " + 17 + " " + 0 + " ./images/fire_onion.jpg \n");
			break;
		case 6:
			board.receiveMessage("image " + 12 + " " + 0 + " ./images/fire_onion.jpg \n");
			board.receiveMessage("image " + 13 + " " + 15 + " ./images/fire_onion.jpg \n");
			break;
		case 7:
			board.receiveMessage("image " + 17 + " " + 0 + " ./images/fire_onion.jpg \n");
			board.receiveMessage("image " + 16 + " " + 0 + " ./images/fire_onion.jpg \n");
			board.receiveMessage("image " + 15 + " " + 0 + " ./images/fire_onion.jpg \n");
			break;
		default:
			break;
		}
	}

	private static void setUpHealth(int level) {
		for (int i = 0; i < healthArray.length; i++) {
			xCollectable = healthArray[i][0];
			yCollectable = healthArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/earth_life.jpg \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		// Using switch-case for lava health for better performance
		switch (level) {
		case 4:
			board.receiveMessage("image " + 0 + " " + 1 + " ./images/fire_life.jpg \n");
			break;
		case 5:
			board.receiveMessage("image " + 7 + " " + 17 + " ./images/fire_life.jpg \n");
			break;
		case 7:
			board.receiveMessage("image " + 19 + " " + 0 + " ./images/fire_life.jpg \n");
			break;
		default:
			break;
		}

	}

	private static void setUpFrosties(int level) {
		for (int i = 0; i < frostArray.length; i++) {
			xCollectable = frostArray[i][0];
			yCollectable = frostArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/earth_frost.jpg \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		switch (level) {
		case 5:
			board.receiveMessage("image " + 4 + " " + 5 + " ./images/frost_fire.jpg \n");
			break;
		case 6:
			board.receiveMessage("image " + 0 + " " + 0 + " ./images/frost_fire.jpg \n");
			break;
		default:
			break;
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
							board.receiveMessage("image " + k + " " + l + " ./images/earth_fire.jpg \n");
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
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/cauldron.jpg \n");
		}
	}

	private static void setUpSolids() {
		for (int i = 0; i < solidsArray.length; i++) {
			xCollectable = solidsArray[i][0];
			yCollectable = solidsArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/solid.jpg \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
	}

	private static void setUpDiamonds() {
		for (int i = 0; i < diamondsArray.length; i++) {
			xCollectable = diamondsArray[i][0];
			yCollectable = diamondsArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/diamond.jpg \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
	}
	
	private static void setUpEmptyCauldrons() {
		for (int i = 0; i < emptyCauldronArray.length; i++) {
			xCollectable = emptyCauldronArray[i][0];
			yCollectable = emptyCauldronArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/cauldron_empty.jpg \n");

			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
	}
	
	public static void activateCauldrons(int level) {
		switch(level) {
		case 7:
			xCollectable = emptyCauldronArray[0][0];
			yCollectable = emptyCauldronArray[0][1];
			
			paintActivatedCauldrons(xCollectable, yCollectable);
			
			break;
		default:
			break;
		}
	}
	
	public static void paintActivatedCauldrons(int x, int y) {
		for (int k = x - 1; k <= x + 1; k++) {
			for (int l = y - 1; l <= y + 1; l++) {
				for (int j = 0; j < emptyCauldronArray.length; j++) {
					if (k < boardSize && k >= 0 && l < boardSize && l >= 0) {
						board.receiveMessage("image " + k + " " + l + " ./images/earth_fire.jpg \n");
					}
				}
			}
		}
		board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/cauldron.jpg \n");
		setUpSolids();
	}
}