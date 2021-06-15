package mainGame;

abstract class LevelManager extends GameManager {
	// TODO: Threads

	public static int[][] getTomatoPos(int level) {
		int[][] tomatos = null;
		switch (level) {
		case 1:
			tomatos = new int[5][2];
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
			tomatos = new int[6][2];
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
		case 3:
			tomatos = new int[6][2];
			tomatos[0][0] = 15;
			tomatos[0][1] = 16;

			tomatos[1][0] = 15;
			tomatos[1][1] = 7;

			tomatos[2][0] = 18;
			tomatos[2][1] = 3;

			tomatos[3][0] = 9;
			tomatos[3][1] = 5;

			tomatos[4][0] = 8;
			tomatos[4][1] = 17;
			
			tomatos[5][0] = 5;
			tomatos[5][1] = 11;
			break;
		// Hier neue Level einfügen (tomato array)
			
		//default case
		default:
			tomatos = new int[level+4][2];
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
		int[][] solids = null;
		switch(level) {
		case 1:
			solids = new int[7][2];
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
			solids = new int[10][2];
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
		case 3:
			solids = new int[24][2];
			solids[0][0] = 16;
			solids[0][1] = 19;

			solids[1][0] = 18;
			solids[1][1] = 15;

			solids[2][0] = 18;
			solids[2][1] = 11;

			solids[3][0] = 18;
			solids[3][1] = 7;

			solids[4][0] = 15;
			solids[4][1] = 0;

			solids[5][0] = 14;
			solids[5][1] = 4;

			solids[6][0] = 14;
			solids[6][1] = 8;

			solids[7][0] = 14;
			solids[7][1] = 12;

			solids[8][0] = 10;
			solids[8][1] = 3;

			solids[9][0] = 10;
			solids[9][1] = 4;

			solids[10][0] = 10;
			solids[10][1] = 5;

			solids[11][0] = 10;
			solids[11][1] = 6;

			solids[12][0] = 9;
			solids[12][1] = 6;

			solids[13][0] = 8;
			solids[13][1] = 6;

			solids[14][0] = 9;
			solids[14][1] = 17;

			solids[15][0] = 9;
			solids[15][1] = 18;

			solids[16][0] = 9;
			solids[16][1] = 19;

			solids[17][0] = 7;
			solids[17][1] = 17;

			solids[18][0] = 7;
			solids[18][1] = 18;

			solids[19][0] = 6;
			solids[19][1] = 10;

			solids[20][0] = 6;
			solids[20][1] = 11;

			solids[21][0] = 6;
			solids[21][1] = 12;

			solids[22][0] = 5;
			solids[22][1] = 12;

			solids[23][0] = 4;
			solids[23][1] = 12;
			break;
		// Hier neue Level einfügen (solid array)
		
		//default case
		default:
			solids = new int[5][2];
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
	
	public static int[][] getOnionsPos(int level) {
		int[][] onions = null;
		switch(level) {
		case 1:
			onions = new int[0][2];
			break;
		case 2:
			onions = new int[1][2];
			onions[0][0] = 8;
			onions[0][1] = 16;
			break;
		case 3:
			onions = new int[2][2];
			onions[0][0] = 15;
			onions[0][1] = 19;
			
			onions[1][0] = 0;
			onions[1][1] = 19;
			break;
		// Hier neue Level einfügen (onion array)
			
		//default case
		default:
			onions = new int[5][2];
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
	
	public static int[][] getLavaPos(int level) {
		int[][] lava = null;
		switch(level) {
		case 1:
			lava = new int[0][2];
			break;
		case 2:
			lava = new int[0][2];
			break;
		case 3:
			lava = new int[14][2];
			
			lava[0][0] = 17;
			lava[0][1] = 17;
			
			lava[1][0] = 17;
			lava[1][1] = 13;
			
			lava[2][0] = 17;
			lava[2][1] = 9;
			
			lava[3][0] = 17;
			lava[3][1] = 5;
			
			lava[4][0] = 17;
			lava[4][1] = 1;
			
			lava[5][0] = 13;
			lava[5][1] = 18;
			
			lava[6][0] = 13;
			lava[6][1] = 14;
			
			lava[7][0] = 13;
			lava[7][1] = 10;
			
			lava[8][0] = 13;
			lava[8][1] = 16;
			
			lava[9][0] = 13;
			lava[9][1] = 2;
			
			lava[10][0] = 8;
			lava[10][1] = 15;
			
			lava[11][0] = 8;
			lava[11][1] = 3;
			
			lava[12][0] = 4;
			lava[12][1] = 9;
			
			lava[13][0] = 1;
			lava[13][1] = 17;
			break;
		// Hier neue Level einfügen (lava array)
			
		//default case
		default:
			lava = new int[5][2];
			int posX, posY;
			for (int i = 0; i < lava.length; i++) {
				posX = (int) (Math.random() * 20);
				posY = (int) (Math.random() * 20);
				// while onions x & y same as player position, reroll position
				while (posX == boardSize - 1 && posX == posY) {
					posY = (int) (Math.random() * 20);
				}
				lava[i][0] = posX;
				lava[i][1] = posY;
			}
			break;
		}
		return lava;
	}
}