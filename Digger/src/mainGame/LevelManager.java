package mainGame;

abstract class LevelManager {
	// TODO: Levelkonstruke als Dateien IO? Ermöglicht eventuell Benutzerdefinierte Level.
	private static int boardSize = GameManager.boardSize;

	public LevelManager() {
		super();
	}

	public static int[][] getTomatoPos(int level) {
		int[][] tomatos = new int[level+4][2];
		switch (level) {
		case 1:
			tomatos[0][0] = 9;
			tomatos[0][1] = 18;

			tomatos[1][0] = 16;
			tomatos[1][1] = 8;

			tomatos[2][0] = 8;
			tomatos[2][1] = 4;

			tomatos[3][0] = 2;
			tomatos[3][1] = 2;

			tomatos[4][0] = 2;
			tomatos[4][1] = 1;
			break;
		case 2:
			tomatos[0][0] = 2;
			tomatos[0][1] = 16;

			tomatos[1][0] = 8;
			tomatos[1][1] = 15;

			tomatos[2][0] = 8;
			tomatos[2][1] = 14;

			tomatos[3][0] = 8;
			tomatos[3][1] = 13;

			tomatos[4][0] = 7;
			tomatos[4][1] = 10;
			
			tomatos[5][0] = 13;
			tomatos[5][1] = 5;
			break;
			
		// TODO: Mehr Level ausdenken und einfügen!
		default:
			int posX, posY;
			for (int i = 0; i < tomatos.length; i++) {
				posX = (int) (Math.random() * 20);
				posY = (int) (Math.random() * 20);
				// while collectable x & y same as player position, reroll position
				while (posX == boardSize - 1 && posX == posY) {
					posY = (int) (Math.random() * 20);
				}
				tomatos[i][0] = posX;
				tomatos[i][1] = posY;
			}
			break;
		}
		return tomatos;
	}
	
	public static int[][] getSolidsPos(int level) {
		int[][] solids = new int[level+8][2];
		switch(level) {
		case 1:
			solids[0][0] = 1;
			solids[0][1] = 4;

			solids[1][0] = 2;
			solids[1][1] = 4;

			solids[2][0] = 3;
			solids[2][1] = 4;

			solids[3][0] = 3;
			solids[3][1] = 3;

			solids[4][0] = 3;
			solids[4][1] = 2;

			solids[5][0] = 3;
			solids[5][1] = 1;

			solids[6][0] = 3;
			solids[6][1] = 0;
			break;
		case 2:
			solids[0][0] = 7;
			solids[0][1] = 17;

			solids[1][0] = 8;
			solids[1][1] = 17;

			solids[2][0] = 9;
			solids[2][1] = 17;

			solids[3][0] = 9;
			solids[3][1] = 16;

			solids[4][0] = 9;
			solids[4][1] = 15;

			solids[5][0] = 9;
			solids[5][1] = 14;

			solids[6][0] = 9;
			solids[6][1] = 13;
			
			solids[7][0] = 9;
			solids[7][1] = 12;
			
			solids[8][0] = 8;
			solids[8][1] = 12;
			
			solids[9][0] = 7;
			solids[9][1] = 12;
			break;
		default:
			int posX, posY;
			for (int i = 0; i < solids.length; i++) {
				posX = (int) (Math.random() * 20);
				posY = (int) (Math.random() * 20);
				// while solid x & y same as player position, reroll position
				while (posX == boardSize - 1 && posX == posY) {
					posY = (int) (Math.random() * 20);
				}
				solids[i][0] = posX;
				solids[i][1] = posY;
			}
			break;
		}
		return solids;
	}
	
	public int[][] getOnionPos(int level) {
		int[][] onions = new int[level - 1][2];
		switch(level) {
		case 1:
			break;
		case 2:
			onions[0][0] = 8;
			onions[0][1] = 16;
			break;
		default:
			int posX, posY;
			for (int i = 0; i < onions.length; i++) {
				posX = (int) (Math.random() * 20);
				posY = (int) (Math.random() * 20);
				// while onions x & y same as player position, reroll position
				while (posX == boardSize - 1 && posX == posY) {
					posY = (int) (Math.random() * 20);
				}
				onions[i][0] = posX;
				onions[i][1] = posY;
			}
			break;
		}
		return onions;
	}
}
