package mainGame;

public class Items {
	static int level = 1;

	public static int[][] CreateCollectables(int boardSize) {
		int posX, posY;

		int[][] collectables = new int[level + 4][2];
		for (int i = 0; i < collectables.length; i++) {
			posX = (int) (Math.random() * 20);
			posY = (int) (Math.random() * 20);
			// while collectable x & y same as player position, reroll
			while (posX == boardSize - 1 && posX == posY) {
				posY = (int) (Math.random() * 20);
			}
			collectables[i][0] = posX;
			collectables[i][1] = posY;
			System.out.print("Tomate auf: (\tx=" + collectables[i][0] + "\t|\ty=" + collectables[i][1] + "\t)\n");
		}
		return collectables;
	}

	public static int[][] CreateSolids(int boardSize, int[][] collectableArray) {
		int[][] solids = { { 1 }, { 1 } };

		return solids;
	}
}
