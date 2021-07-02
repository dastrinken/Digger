package levelOrganizer;

import mainGame.GameManager;

public abstract class ItemArrays extends GameManager {

	public static int[][] getTomatoPos(int level) {
		int[][] tomatos = null;
		switch (level) {
		case 1:
			tomatos = new int[5][2];
			tomatos[0][0] = 12;
			tomatos[0][1] = 18;

			tomatos[1][0] = 19;
			tomatos[1][1] = 14;

			tomatos[2][0] = 12;
			tomatos[2][1] = 4;

			tomatos[3][0] = 14;
			tomatos[3][1] = 4;

			tomatos[4][0] = 3;
			tomatos[4][1] = 6;

			break;
		case 2:
			tomatos = new int[6][2];
			tomatos[0][0] = 16;
			tomatos[0][1] = 15;

			tomatos[1][0] = 8;
			tomatos[1][1] = 16;

			tomatos[2][0] = 1;
			tomatos[2][1] = 12;

			tomatos[3][0] = 8;
			tomatos[3][1] = 13;

			tomatos[4][0] = 5;
			tomatos[4][1] = 3;

			tomatos[5][0] = 17;
			tomatos[5][1] = 6;
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
			tomatos[0][0] = 17;
			tomatos[0][1] = 2;

			tomatos[1][0] = 18;
			tomatos[1][1] = 11;

			tomatos[2][0] = 18;
			tomatos[2][1] = 18;

			tomatos[3][0] = 18;
			tomatos[3][1] = 19;

			tomatos[4][0] = 19;
			tomatos[4][1] = 18;
			break;
		case 5:
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
		case 6:
			tomatos = new int[18][2];

			tomatos[0][0] = 0;
			tomatos[0][1] = 1;

			tomatos[1][0] = 0;
			tomatos[1][1] = 2;

			tomatos[2][0] = 1;
			tomatos[2][1] = 0;

			tomatos[3][0] = 1;
			tomatos[3][1] = 2;

			tomatos[4][0] = 2;
			tomatos[4][1] = 0;

			tomatos[5][0] = 2;
			tomatos[5][1] = 1;

			tomatos[6][0] = 2;
			tomatos[6][1] = 2;

			tomatos[7][0] = 2;
			tomatos[7][1] = 3;

			tomatos[8][0] = 2;
			tomatos[8][1] = 5;

			tomatos[9][0] = 3;
			tomatos[9][1] = 2;

			tomatos[10][0] = 3;
			tomatos[10][1] = 5;

			tomatos[11][0] = 4;
			tomatos[11][1] = 5;

			tomatos[12][0] = 5;
			tomatos[12][1] = 2;

			tomatos[13][0] = 5;
			tomatos[13][1] = 3;

			tomatos[14][0] = 5;
			tomatos[14][1] = 4;

			tomatos[15][0] = 13;
			tomatos[15][1] = 18;

			tomatos[16][0] = 3;
			tomatos[16][1] = 12;

			tomatos[17][0] = 0;
			tomatos[17][1] = 18;

			break;
		case 7:
			tomatos = new int[9][2];

			tomatos[0][0] = 1;
			tomatos[0][1] = 3;

			tomatos[1][0] = 1;
			tomatos[1][1] = 18;

			tomatos[2][0] = 3;
			tomatos[2][1] = 4;

			tomatos[3][0] = 3;
			tomatos[3][1] = 7;

			tomatos[4][0] = 7;
			tomatos[4][1] = 17;

			tomatos[5][0] = 10;
			tomatos[5][1] = 11;

			tomatos[6][0] = 14;
			tomatos[6][1] = 0;

			tomatos[7][0] = 18;
			tomatos[7][1] = 19;

			tomatos[8][0] = 19;
			tomatos[8][1] = 18;
			break;
		case 8:
			tomatos = new int[6][2];
			tomatos[0][0] = 2;
			tomatos[0][1] = 19;

			tomatos[1][0] = 4;
			tomatos[1][1] = 19;

			tomatos[2][0] = 8;
			tomatos[2][1] = 19;

			tomatos[3][0] = 0;
			tomatos[3][1] = 0;

			tomatos[4][0] = 10;
			tomatos[4][1] = 0;

			tomatos[5][0] = 16;
			tomatos[5][1] = 0;

			break;
		case 9:
			tomatos = new int[5][2];

			tomatos[0][0] = 0;
			tomatos[0][1] = 7;

			tomatos[1][0] = 4;
			tomatos[1][1] = 2;

			tomatos[2][0] = 6;
			tomatos[2][1] = 2;

			tomatos[3][0] = 7;
			tomatos[3][1] = 2;

			tomatos[4][0] = 10;
			tomatos[4][1] = 13;
			break;
		case 10:
			tomatos = new int[9][2];

			tomatos[0][0] = 2;
			tomatos[0][1] = 10;

			tomatos[1][0] = 2;
			tomatos[1][1] = 5;

			tomatos[2][0] = 8;
			tomatos[2][1] = 1;

			tomatos[3][0] = 12;
			tomatos[3][1] = 6;

			tomatos[4][0] = 17;
			tomatos[4][1] = 0;

			tomatos[5][0] = 17;
			tomatos[5][1] = 2;

			tomatos[6][0] = 19;
			tomatos[6][1] = 0;

			tomatos[7][0] = 19;
			tomatos[7][1] = 2;

			tomatos[8][0] = 14;
			tomatos[8][1] = 14;
			break;
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
			solids = new int[43][2];
			for (int i = 0; i <= 18; i++) {
				solids[i][0] = i + 1;
				solids[i][1] = 15;
			}

			for (int i = 0; i <= 7; i++) {
				solids[i + 19][0] = i + 10;
				solids[i + 19][1] = 5;
			}

			for (int i = 0; i <= 4; i++) {
				solids[i + 26][0] = 13;
				solids[i + 26][1] = i;
			}

			solids[31][0] = 12;
			solids[31][1] = 17;

			solids[32][0] = 13;
			solids[32][1] = 18;

			solids[33][0] = 12;
			solids[33][1] = 19;

			solids[34][0] = 4;
			solids[34][1] = 6;

			solids[35][0] = 4;
			solids[35][1] = 7;

			solids[36][0] = 3;
			solids[36][1] = 7;

			solids[37][0] = 18;
			solids[37][1] = 14;

			solids[38][0] = 18;
			solids[38][1] = 13;

			solids[39][0] = 10;
			solids[39][1] = 10;

			solids[40][0] = 10;
			solids[40][1] = 11;

			solids[41][0] = 11;
			solids[41][1] = 10;

			solids[42][0] = 11;
			solids[42][1] = 11;

			break;
		case 2:
			solids = new int[52][2];
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
			for (int i = 0; i <= 5; i++) {
				solids[i + 10][0] = i;
				solids[i + 10][1] = 18;
			}

			for (int i = 11; i <= 19; i++) {
				solids[i + 5][0] = i;
				solids[i + 5][1] = 18;
			}

			solids[25][0] = 1;
			solids[25][1] = 13;

			solids[26][0] = 2;
			solids[26][1] = 13;

			solids[27][0] = 2;
			solids[27][1] = 12;

			solids[28][0] = 2;
			solids[28][1] = 11;

			solids[29][0] = 1;
			solids[29][1] = 11;

			for (int i = 3; i <= 7; i++) {
				solids[i + 27][0] = 16;
				solids[i + 27][1] = i;
			}
			for (int i = 2; i <= 7; i++) {
				solids[i + 33][0] = 18;
				solids[i + 33][1] = i;
			}
			solids[41][0] = 17;
			solids[41][1] = 7;

			solids[42][0] = 4;
			solids[42][1] = 4;

			solids[43][0] = 4;
			solids[43][1] = 3;

			solids[44][0] = 4;
			solids[44][1] = 2;

			solids[45][0] = 5;
			solids[45][1] = 2;

			solids[46][0] = 6;
			solids[46][1] = 2;

			solids[47][0] = 6;
			solids[47][1] = 3;

			solids[48][0] = 6;
			solids[48][1] = 4;

			solids[49][0] = 15;
			solids[49][1] = 15;

			solids[50][0] = 15;
			solids[50][1] = 16;

			solids[51][0] = 16;
			solids[51][1] = 16;

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
			solids = new int[102][2];
			for (int i = 0; i <= 1; i++) {
				solids[i][0] = i;
				solids[i][1] = 14;
			}
			for (int i = 0; i <= 16; i++) {
				solids[i + 2][0] = i + 3;
				solids[i + 2][1] = 14;
			}
			for (int i = 0; i <= 10; i++) {
				solids[i + 19][0] = i;
				solids[i + 19][1] = 12;
			}
			for (int i = 0; i <= 8; i++) {
				solids[i + 30][0] = i + 12;
				solids[i + 30][1] = 12;
			}
			for (int i = 0; i <= 4; i++) {
				solids[i + 38][0] = i;
				solids[i + 38][1] = 10;
			}
			for (int i = 0; i <= 9; i++) {
				solids[i + 43][0] = i + 6;
				solids[i + 43][1] = 10;
			}
			for (int i = 0; i <= 2; i++) {
				solids[i + 53][0] = i + 17;
				solids[i + 53][1] = 10;
			}
			for (int i = 0; i <= 7; i++) {
				solids[i + 56][0] = i;
				solids[i + 56][1] = 8;
			}
			for (int i = 0; i <= 10; i++) {
				solids[i + 64][0] = i + 9;
				solids[i + 64][1] = 8;
			}
			for (int i = 0; i <= 5; i++) {
				solids[i + 75][0] = 14;
				solids[i + 75][1] = i;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 81][0] = i + 15;
				solids[i + 81][1] = 5;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 85][0] = 18;
				solids[i + 85][1] = i + 1;
			}

			solids[89][0] = 17;
			solids[89][1] = 1;

			solids[90][0] = 16;
			solids[90][1] = 1;

			solids[91][0] = 16;
			solids[91][1] = 2;

			solids[92][0] = 16;
			solids[92][1] = 3;

			solids[93][0] = 17;
			solids[93][1] = 16;

			solids[94][0] = 19;
			solids[94][1] = 16;

			for (int i = 0; i <= 6; i++) {
				solids[i + 95][0] = 1;
				solids[i + 95][1] = i;
			}

			break;
		case 5:
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

			break;

		case 6:
			solids = new int[32][2];
			for (int i = 0; i <= 2; i++) {
				solids[i][0] = i + 2;
				solids[i][1] = 6;
			}
			for (int i = 0; i <= 2; i++) {
				solids[i + 3][0] = 6;
				solids[i + 3][1] = i + 2;
			}
			for (int i = 0; i <= 4; i++) {
				solids[i + 6][0] = i + 5;
				solids[i + 6][1] = 9;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 11][0] = 9;
				solids[i + 11][1] = i + 5;
			}

			solids[15][0] = 2;
			solids[15][1] = 4;

			solids[16][0] = 3;
			solids[16][1] = 3;

			solids[17][0] = 4;
			solids[17][1] = 2;

			solids[18][0] = 4;
			solids[18][1] = 4;

			solids[19][0] = 5;
			solids[19][1] = 5;

			solids[20][0] = 13;
			solids[20][1] = 19;

			solids[21][0] = 14;
			solids[21][1] = 0;

			for (int i = 9; i <= 18; i++) {
				solids[i + 13][0] = 4;
				solids[i + 13][1] = i;
			}

//			System.out.println("Solid array:");
//			 for(int i = 0; i<solids.length; i++) {
//			 System.out.println("Stelle "+i+"\t X:"+solids[i][0]+"||Y:"+solids[i][1]); }
			break;

		case 7:

			solids = new int[69][2];

			solids[0][0] = 0;
			solids[0][1] = 3;

			for (int i = 0; i <= 2; i++) {
				solids[i + 1][0] = i;
				solids[i + 1][1] = 4;
			}

			for (int i = 0; i <= 2; i++) {
				solids[i + 4][0] = i + 1;
				solids[i + 4][1] = 1;
			}

			for (int i = 0; i <= 2; i++) {
				solids[i + 7][0] = i + 2;
				solids[i + 7][1] = 3;
			}

			solids[10][0] = 2;
			solids[10][1] = 2;

			for (int i = 0; i <= 3; i++) {
				solids[i + 11][0] = 4;
				solids[i + 11][1] = i + 4;
			}

			solids[15][0] = 10;
			solids[15][1] = 10;

			solids[16][0] = 3;
			solids[16][1] = 6;

			solids[17][0] = 5;
			solids[17][1] = 6;

			solids[18][0] = 5;
			solids[18][1] = 0;

			solids[19][0] = 5;
			solids[19][1] = 1;

			for (int i = 0; i <= 3; i++) {
				solids[i + 20][0] = 6;
				solids[i + 20][1] = i + 1;
			}

			for (int i = 0; i <= 5; i++) {
				solids[i + 24][0] = i + 1;
				solids[i + 24][1] = 9;
			}

			solids[30][0] = 1;
			solids[30][1] = 6;

			solids[31][0] = 1;
			solids[31][1] = 7;

			solids[32][0] = 1;
			solids[32][1] = 8;

			solids[33][0] = 3;
			solids[33][1] = 13;

			solids[34][0] = 3;
			solids[34][1] = 14;

			solids[35][0] = 5;
			solids[35][1] = 13;

			solids[36][0] = 5;
			solids[36][1] = 14;

			solids[37][0] = 5;
			solids[37][1] = 15;

			solids[38][0] = 5;
			solids[38][1] = 16;

			solids[39][0] = 6;
			solids[39][1] = 16;

			solids[40][0] = 7;
			solids[40][1] = 16;

			solids[41][0] = 8;
			solids[41][1] = 16;

			solids[42][0] = 8;
			solids[42][1] = 17;

			solids[43][0] = 8;
			solids[43][1] = 18;

			solids[44][0] = 11;
			solids[44][1] = 1;

			solids[45][0] = 13;
			solids[45][1] = 1;

			solids[46][0] = 15;
			solids[46][1] = 1;

			solids[47][0] = 17;
			solids[47][1] = 1;

			solids[48][0] = 19;
			solids[48][1] = 1;

			solids[49][0] = 8;
			solids[49][1] = 13;

			solids[50][0] = 9;
			solids[50][1] = 13;

			solids[51][0] = 10;
			solids[51][1] = 13;

			solids[52][0] = 9;
			solids[52][1] = 10;

			solids[53][0] = 9;
			solids[53][1] = 11;

			for (int i = 0; i <= 7; i++) {
				solids[i + 54][0] = 7;
				solids[i + 54][1] = i + 6;
			}

			for (int i = 0; i <= 7; i++) {
				solids[i + 61][0] = 11;
				solids[i + 61][1] = i + 6;
			}
			break;
		case 8:
			solids = new int[39][2];

			for (int i = 0; i <= 3; i++) {
				solids[i][0] = 3;
				solids[i][1] = i + 16;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 4][0] = 7;
				solids[i + 4][1] = i + 16;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 8][0] = 11;
				solids[i + 8][1] = i + 16;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 12][0] = 15;
				solids[i + 12][1] = i + 16;
			}
			for (int i = 0; i <= 2; i++) {
				solids[i + 16][0] = 19;
				solids[i + 16][1] = i + 16;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 19][0] = 3;
				solids[i + 19][1] = i;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 23][0] = 7;
				solids[i + 23][1] = i;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 27][0] = 11;
				solids[i + 27][1] = i;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 31][0] = 15;
				solids[i + 31][1] = i;

			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 35][0] = 19;
				solids[i + 35][1] = i;
			}

			break;
		case 9:
			solids = new int[61][2];

			for (int i = 0; i <= 4; i++) {
				solids[i][0] = i;
				solids[i][1] = 5;
			}
			for (int i = 0; i <= 4; i++) {
				solids[i + 5][0] = i + 1;
				solids[i + 5][1] = 1;
			}
			for (int i = 0; i <= 5; i++) {
				solids[i + 10][0] = i + 2;
				solids[i + 10][1] = 3;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 16][0] = 9;
				solids[i + 16][1] = i + 9;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 20][0] = 8;
				solids[i + 20][1] = i + 1;
			}
			for (int i = 0; i <= 6; i++) {
				solids[i + 24][0] = i;
				solids[i + 24][1] = 9;
			}
			for (int i = 0; i <= 9; i++) {
				solids[i + 31][0] = 9;
				solids[i + 31][1] = i + 6;
			}
			for (int i = 0; i <= 8; i++) {
				solids[i + 41][0] = 11;
				solids[i + 41][1] = i + 6;
			}

			solids[50][0] = 4;
			solids[50][1] = 4;

			solids[51][0] = 5;
			solids[51][1] = 2;

			for (int i = 11; i <= 19; i++) {
				solids[i + 41][0] = i;
				solids[i + 41][1] = 5;
			}

			break;
		case 10:
			solids = new int[75][2];
			for (int i = 0; i <= 10; i++) {
				solids[i][0] = 3;
				solids[i][1] = i + 5;
			}
			for (int i = 0; i <= 2; i++) {
				solids[i + 11][0] = 6;
				solids[i + 11][1] = i + 6;

				solids[i + 14][0] = 6;
				solids[i + 14][1] = i + 12;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 17][0] = 8;
				solids[i + 17][1] = i + 6;

				solids[i + 21][0] = 11;
				solids[i + 21][1] = i + 6;
			}
			for (int i = 0; i <= 1; i++) {
				solids[i + 25][0] = i + 9;
				solids[i + 25][1] = 5;

				solids[i + 27][0] = i + 9;
				solids[i + 27][1] = 10;
			}
			for (int i = 0; i <= 3; i++) {
				solids[i + 29][0] = 16;
				solids[i + 29][1] = i + 6;

				solids[i + 33][0] = 13;
				solids[i + 33][1] = i + 11;
			}
			for (int i = 0; i <= 1; i++) {
				solids[i + 37][0] = i + 14;
				solids[i + 37][1] = 5;

				solids[i + 39][0] = i + 14;
				solids[i + 39][1] = 10;

				solids[i + 41][0] = i + 14;
				solids[i + 41][1] = 15;
			}
			for (int i = 0; i <= 1; i++) {
				solids[i + 43][0] = i + 4;
				solids[i + 43][1] = 5;

				solids[i + 45][0] = i + 4;
				solids[i + 45][1] = 15;
			}
			for (int i = 0; i <= 13; i++) {
				solids[i + 47][0] = i + 3;
				solids[i + 47][1] = 2;
			}
			int j = 4;
			for (int i = 0; i <= 3; i++) {
				solids[i + 61][0] = j;
				solids[i + 61][1] = 0;

				solids[i + 65][0] = j + 2;
				solids[i + 65][1] = 1;
				j = j + 4;
			}

			solids[69][0] = 5;
			solids[69][1] = 9;

			solids[70][0] = 5;
			solids[70][1] = 11;

			solids[71][0] = 13;
			solids[71][1] = 6;

			solids[72][0] = 16;
			solids[72][1] = 14;

			solids[73][0] = 2;
			solids[73][1] = 3;

			solids[74][0] = 17;
			solids[74][1] = 3;
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
			onions = new int[2][2];
			onions[0][0] = 8;
			onions[0][1] = 15;

			onions[1][0] = 8;
			onions[1][1] = 14;
			break;
		case 3:
			onions = new int[2][2];
			onions[0][0] = 15;
			onions[0][1] = 19;

			onions[1][0] = 0;
			onions[1][1] = 19;
			break;
		case 4:
			onions = new int[2][2];
			onions[0][0] = 1;
			onions[0][1] = 9;

			onions[1][0] = 1;
			onions[1][1] = 13;

			break;
		case 5:
			onions = new int[3][2];
			onions[0][0] = 4;
			onions[0][1] = 0;

			onions[1][0] = 17;
			onions[1][1] = 0;

			onions[2][0] = 5;
			onions[2][1] = 5;
			break;
		case 6:
			onions = new int[5][2];

			onions[0][0] = 3;
			onions[0][1] = 4;

			onions[1][0] = 4;
			onions[1][1] = 3;

			onions[2][0] = 14;
			onions[2][1] = 1;

			onions[3][0] = 13;
			onions[3][1] = 15;

			onions[4][0] = 12;
			onions[4][1] = 0;

			break;
		case 7:
			onions = new int[4][2];

			onions[0][0] = 9;
			onions[0][1] = 12;

			onions[1][0] = 15;
			onions[1][1] = 0;

			onions[2][0] = 16;
			onions[2][1] = 0;

			onions[3][0] = 17;
			onions[3][1] = 0;

			break;
		case 8:
			onions = new int[8][2];

			onions[0][0] = 18;
			onions[0][1] = 9;

			onions[1][0] = 9;
			onions[1][1] = 11;

			onions[2][0] = 12;
			onions[2][1] = 19;

			onions[3][0] = 17;
			onions[3][1] = 0;

			onions[4][0] = 14;
			onions[4][1] = 0;

			onions[5][0] = 16;
			onions[5][1] = 11;

			onions[6][0] = 7;
			onions[6][1] = 8;

			onions[7][0] = 14;
			onions[7][1] = 11;

			break;
		case 9:
			onions = new int[3][2];

			onions[0][0] = 0;
			onions[0][1] = 8;

			onions[1][0] = 2;
			onions[1][1] = 4;

			onions[2][0] = 11;
			onions[2][1] = 15;

			break;
		case 10:
			onions = new int[5][2];

			onions[0][0] = 4;
			onions[0][1] = 1;

			onions[1][0] = 6;
			onions[1][1] = 0;

			onions[2][0] = 10;
			onions[2][1] = 0;

			onions[3][0] = 14;
			onions[3][1] = 0;

			onions[4][0] = 16;
			onions[4][1] = 1;

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
			lava = new int[12][2];
			lava[0][0] = 0;
			lava[0][1] = 9;

			lava[1][0] = 0;
			lava[1][1] = 11;

			lava[2][0] = 0;
			lava[2][1] = 13;

			lava[3][0] = 9;
			lava[3][1] = 6;

			lava[4][0] = 13;
			lava[4][1] = 5;

			lava[5][0] = 17;
			lava[5][1] = 17;

			lava[6][0] = 17;
			lava[6][1] = 18;

			lava[7][0] = 17;
			lava[7][1] = 19;

			lava[8][0] = 19;
			lava[8][1] = 9;

			lava[9][0] = 19;
			lava[9][1] = 11;

			lava[10][0] = 19;
			lava[10][1] = 13;

			lava[11][0] = 0;
			lava[11][1] = 0;
			break;
		case 5:
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
		case 6:
			lava = new int[17][2];

			lava[0][0] = 1;
			lava[0][1] = 1;

			lava[1][0] = 6;
			lava[1][1] = 6;

			lava[2][0] = 12;
			lava[2][1] = 1;

			lava[3][0] = 12;
			lava[3][1] = 4;

			lava[4][0] = 12;
			lava[4][1] = 7;

			lava[5][0] = 12;
			lava[5][1] = 10;

			lava[6][0] = 12;
			lava[6][1] = 13;

			lava[7][0] = 12;
			lava[7][1] = 16;

			lava[8][0] = 15;
			lava[8][1] = 3;

			lava[9][0] = 15;
			lava[9][1] = 6;

			lava[10][0] = 15;
			lava[10][1] = 9;

			lava[11][0] = 15;
			lava[11][1] = 12;

			lava[12][0] = 15;
			lava[12][1] = 15;

			lava[13][0] = 15;
			lava[13][1] = 18;

			lava[14][0] = 3;
			lava[14][1] = 18;

			lava[15][0] = 1;
			lava[15][1] = 10;

			lava[16][0] = 2;
			lava[16][1] = 10;
			break;

		case 7:
			lava = new int[7][2];

			lava[0][0] = 0;
			lava[0][1] = 19;

			lava[1][0] = 1;
			lava[1][1] = 14;

			lava[2][0] = 5;
			lava[2][1] = 18;

			lava[3][0] = 12;
			lava[3][1] = 1;

			lava[4][0] = 14;
			lava[4][1] = 1;

			lava[5][0] = 16;
			lava[5][1] = 1;

			lava[6][0] = 18;
			lava[6][1] = 1;

			break;
		case 8:
			lava = new int[12][2];

			int j = 1;
			for (int i = 0; i <= 4; i++) {
				lava[i][0] = j;
				lava[i][1] = 17;

				lava[i + 5][0] = j;
				lava[i + 5][1] = 2;

				j = j + 4;
			}
			lava[10][0] = 6;
			lava[10][1] = 9;

			lava[11][0] = 15;
			lava[11][1] = 12;

			break;
		case 9:
			lava = new int[6][2];

			lava[0][0] = 2;
			lava[0][1] = 6;

			lava[1][0] = 2;
			lava[1][1] = 8;

			lava[2][0] = 0;
			lava[2][1] = 3;

			lava[3][0] = 10;
			lava[3][1] = 14;

			lava[4][0] = 7;
			lava[4][1] = 9;

			lava[5][0] = 7;
			lava[5][1] = 11;

			break;
		case 10:
			lava = new int[7][2];

			lava[0][0] = 1;
			lava[0][1] = 14;

			lava[1][0] = 6;
			lava[1][1] = 5;

			lava[2][0] = 8;
			lava[2][1] = 5;

			lava[3][0] = 13;
			lava[3][1] = 8;

			lava[4][0] = 18;
			lava[4][1] = 5;

			lava[5][0] = 18;
			lava[5][1] = 14;

			lava[6][0] = 10;
			lava[6][1] = 3;

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

			health[0][0] = 0;
			health[0][1] = 1;
			break;
		case 5:
			health = new int[1][2];

			health[0][0] = 7;
			health[0][1] = 17;
			break;
		case 6:
			health = new int[0][2];

			break;
		case 7:
			health = new int[1][2];

			health[0][0] = 19;
			health[0][1] = 0;

			break;
		case 8:
			health = new int[1][2];

			health[0][0] = 4;
			health[0][1] = 0;
			break;
		case 9:
			health = new int[3][2];
			health[0][0] = 0;
			health[0][1] = 6;

			health[1][0] = 3;
			health[1][1] = 4;

			health[2][0] = 8;
			health[2][1] = 12;

			break;
		case 10:
			health = new int[9][2];

			health[0][0] = 8;
			health[0][1] = 19;

			for (int i = 0; i <= 3; i++) {
				health[i + 1][0] = 9;
				health[i + 1][1] = i + 6;

				health[i + 5][0] = 10;
				health[i + 5][1] = i + 6;
			}

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
		switch (level) {
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
			frosties = new int[3][2];

			frosties[0][0] = 19;
			frosties[0][1] = 17;

			frosties[1][0] = 10;
			frosties[1][1] = 11;

			frosties[2][0] = 2;
			frosties[2][1] = 9;
			break;
		case 5:
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
		case 6:
			frosties = new int[3][2];

			frosties[0][0] = 0;
			frosties[0][1] = 0;

			frosties[1][0] = 17;
			frosties[1][1] = 15;

			frosties[2][0] = 6;
			frosties[2][1] = 19;
			break;
		case 7:

			frosties = new int[3][2];

			frosties[0][0] = 6;
			frosties[0][1] = 5;

			frosties[1][0] = 6;
			frosties[1][1] = 6;

			frosties[2][0] = 7;
			frosties[2][1] = 5;

			break;
		case 8:
			frosties = new int[10][2];

			frosties[0][0] = 16;
			frosties[0][1] = 19;

			frosties[1][0] = 14;
			frosties[1][1] = 19;

			frosties[2][0] = 2;
			frosties[2][1] = 15;

			frosties[3][0] = 3;
			frosties[3][1] = 15;

			frosties[4][0] = 7;
			frosties[4][1] = 15;

			frosties[5][0] = 0;
			frosties[5][1] = 4;

			frosties[6][0] = 3;
			frosties[6][1] = 4;

			frosties[7][0] = 11;
			frosties[7][1] = 15;

			frosties[8][0] = 15;
			frosties[8][1] = 4;

			frosties[9][0] = 10;
			frosties[9][1] = 4;

			break;
		case 9:
			frosties = new int[3][2];

			frosties[0][0] = 4;
			frosties[0][1] = 7;

			frosties[1][0] = 0;
			frosties[1][1] = 1;

			frosties[2][0] = 2;
			frosties[2][1] = 10;
			break;
		case 10:
			frosties = new int[3][2];

			frosties[0][0] = 19;
			frosties[0][1] = 3;

			frosties[1][0] = 5;
			frosties[1][1] = 10;

			frosties[2][0] = 16;
			frosties[2][1] = 5;

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

	public static int[][] getDiamondPos(int level) {
		int[][] diamond = null;
		switch (level) {
		case 7:
			diamond = new int[1][2];

			diamond[0][0] = 10;
			diamond[0][1] = 12;
			break;
		default:
			diamond = new int[0][0];
			break;
		}
		return diamond;
	}

	public static int[][] getEmptyCauldronPos(int level) {
		int[][] emptyCauldron = null;
		switch (level) {
		case 7:
			emptyCauldron = new int[1][2];

			emptyCauldron[0][0] = 9;
			emptyCauldron[0][1] = 7;
			break;
		default:
			emptyCauldron = new int[0][0];
			break;
		}
		return emptyCauldron;
	}
}