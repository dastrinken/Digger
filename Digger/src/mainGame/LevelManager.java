package mainGame;

abstract class LevelManager extends GameManager {

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
		case 4:
			tomatos = new int[5][2];
			tomatos[0][0] = 0;
			tomatos[0][1] = 15;

			tomatos[1][0] = 0;
			tomatos[1][1] = 5;

			tomatos[2][0] = 7;
			tomatos[2][1] = 13;

			tomatos[3][0] = 12;
			tomatos[3][1] = 0;

			tomatos[4][0] = 18;
			tomatos[4][1] = 3;
			break;
		// Hier neue Level einfügen (tomato array)

		// default case
		default:
			tomatos = new int[level + 4][2];
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
		switch (level) {
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
		case 4:
			solids = new int[85][2];
			for (int i = 0; i <= 17; i++) {
				solids[i][0] = 13;
				solids[i][1] = i;
			}
			for (int i = 1; i <= 7; i++) {
				solids[i + 17][0] = 1;
				solids[i + 17][1] = i + 11;
			}
			for (int i = 2; i <= 7; i++) {
				solids[i + 22][0] = i;
				solids[i + 22][1] = 9;
			}
			for (int i = 0; i <= 9; i++) {
				solids[i + 30][0] = 3;
				solids[i + 30][1] = i;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 40][0] = i + 5;
				solids[i + 40][1] = 1;
			}
			for (int i = 0; i <= 5; i++) {
				solids[i + 44][0] = 5;
				solids[i + 44][1] = i + 12;
			}

			solids[50][0] = 7;
			solids[50][1] = 12;

			solids[51][0] = 14;
			solids[51][1] = 2;

			solids[52][0] = 15;
			solids[52][1] = 2;

			solids[53][0] = 12;
			solids[53][1] = 1;

			solids[54][0] = 18;
			solids[54][1] = 2;

			solids[55][0] = 18;
			solids[55][1] = 10;

			solids[56][0] = 19;
			solids[56][1] = 8;

			solids[57][0] = 19;
			solids[57][1] = 12;

			solids[58][0] = 19;
			solids[58][1] = 16;

			solids[59][0] = 18;
			solids[59][1] = 14;

			for (int i = 0; i <= 16; i++) {
				solids[i + 60][0] = 17;
				solids[i + 60][1] = i + 2;
			}
			for (int i = 0; i <= 4; i++) {
				solids[i + 77][0] = i + 6;
				solids[i + 77][1] = 14;
			}
			solids[82][0] = 8;
			solids[82][1] = 13;

			solids[83][0] = 8;
			solids[83][1] = 12;

			solids[84][0] = 8;
			solids[84][1] = 11;

			/*
			 * checking array (already formatted for better view) for(int i = 0;
			 * i<solids.length; i++) {
			 * System.out.println("Stelle "+i+"\t X:"+solids[i][0]+"||Y:"+solids[i][1]); }
			 */
			break;
		// Hier neue Level einfügen (solid array)

		// default case
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
		switch (level) {
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
		case 4:
			onions = new int[3][2];
			onions[0][0] = 4;
			onions[0][1] = 0;

			onions[1][0] = 17;
			onions[1][1] = 0;

			onions[2][0] = 5;
			onions[2][1] = 5;

			break;
		// Hier neue Level einfügen (onion array)

		// default case
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
		switch (level) {
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
		case 4:
			lava = new int[11][2];

			lava[0][0] = 7;
			lava[0][1] = 18;

			lava[1][0] = 7;
			lava[1][1] = 16;

			lava[2][0] = 8;
			lava[2][1] = 17;

			lava[3][0] = 0;
			lava[3][1] = 16;

			lava[4][0] = 5;
			lava[4][1] = 6;

			lava[5][0] = 5;
			lava[5][1] = 4;

			lava[6][0] = 6;
			lava[6][1] = 5;

			lava[7][0] = 10;
			lava[7][1] = 0;

			lava[8][0] = 12;
			lava[8][1] = 2;

			lava[9][0] = 14;
			lava[9][1] = 0;

			lava[10][0] = 18;
			lava[10][1] = 0;
			break;

		// Hier neue Level einfügen (lava array)

		// default case
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

	public static int[][] getHealthPos(int level) {
		int[][] health = null;
		switch (level) {
		case 1:
			health = new int[0][2];
			break;
		case 2:
			health = new int[0][2];
			break;
		case 3:
			health = new int[0][2];
			break;
		case 4:
			health = new int[1][2];
			
			health[0][0] = 7;
			health[0][1] = 17;
			
			break;
		default:
			health = new int[1][2];
			int posX, posY;
			for (int i = 0; i < health.length; i++) {
				posX = (int) (Math.random() * 20);
				posY = (int) (Math.random() * 20);
				// while health x & y same as player position, reroll position
				while (posX == boardSize - 1 && posX == posY) {
					posY = (int) (Math.random() * 20);
				}
				health[i][0] = posX;
				health[i][1] = posY;
			}
			break;
		}
		return health;
	}

	public static int[][] getFrostPos(int level) {
		int[][] frosties = null;
		switch(level) {
		case 1:
			frosties = new int[0][2];
			break;
		case 2:
			frosties = new int[0][2];
			break;
		case 3:
			frosties = new int[0][2];
			break;
		case 4:
			frosties = new int[4][2];
			
			frosties[0][0] = 9;
			frosties[0][1] = 19;
			
			frosties[1][0] = 0;
			frosties[1][1] = 6;
			
			frosties[2][0] = 4;
			frosties[2][1] = 5;
			
			frosties[3][0] = 7;
			frosties[3][1] = 7;
			break;
		default:
			frosties = new int[1][2];
			int posX, posY;
			for (int i = 0; i < frosties.length; i++) {
				posX = (int) (Math.random() * 20);
				posY = (int) (Math.random() * 20);
				// while frosties x & y same as player position, reroll position
				while (posX == boardSize - 1 && posX == posY) {
					posY = (int) (Math.random() * 20);
				}
				frosties[i][0] = posX;
				frosties[i][1] = posY;
			}
			break;
		}
		return frosties;
	}
}