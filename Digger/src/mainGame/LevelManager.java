package mainGame;

public class LevelManager {
	int boardSize;

	public LevelManager(int boardSize) {
		super();
		this.boardSize = boardSize;
	}

	public int[][] getTomatoPos(int level) {
		int[][] tomatos = new int[5][2];
		switch (level) {
		case 1:
			tomatos[0][0] = 9;
			tomatos[0][1] = 18;

//			tomatos[1][0] = 16;
//			tomatos[1][1] = 8;
//
//			tomatos[2][0] = 8;
//			tomatos[2][1] = 4;
//
//			tomatos[3][0] = 2;
//			tomatos[3][1] = 2;
//
//			tomatos[4][0] = 2;
//			tomatos[4][1] = 1;
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
				System.out.print("Tomate auf: (\tx=" + tomatos[i][0] + "\t|\ty=" + tomatos[i][1] + "\t)\n");
			}
			break;
		}
		return tomatos;
	}
	
	public int[][] getSolidsPos(int level) {
		int[][] solids = new int[level+6][2];
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
				System.out.print("Solid auf: (\tx=" + solids[i][0] + "\t|\ty=" + solids[i][1] + "\t)\n");
			}
			break;
		}
		return solids;
	}
	
	public int[][] getOnionPos(int level) {
		int[][] onions = new int[3][2];
		return onions;
	}
}
