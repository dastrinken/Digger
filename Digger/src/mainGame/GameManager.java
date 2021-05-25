package mainGame;

// TODO: Unbewegliche Objekte (solids) einfügen,
// TODO: Gegner generieren?

public class GameManager {
	static int[][] collectableArray;
	static int collectableCounter = 5;

	public static int[][] getCollectableArray() {
		return collectableArray;
	}

	public static void setCollectableArray(int[][] getCollectableArray) {
		GameManager.collectableArray = getCollectableArray;
	}

	public static void checkPosition(int posX, int posY) {
		int x = 999, y = 999;
		for (int i = 0; i < collectableArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = collectableArray[i][0];
				y = collectableArray[i][1];
				if (x == posX && y == posY) {
					collectableArray[i][0] = 999;
					collectableArray[i][1] = 999;
					CollectPts(posX, posY);
				}
			}
		}
	}

	private static void CollectPts(int posX, int posY) {
		// TODO: Collectables zählen und nächste Level starten, wenn alle eingesammelt.
		// TODO: Punkte hochzählen und im Panel ausgeben
		// Eventuell lieber mit ArrayList statt Array arbeiten?
		StartGame.board.receiveMessage("image " + posX + " " + posY + " -\n");
		System.out.println("Match!");

		collectableCounter--;
		if (collectableCounter == 0) {
			StartGame.nextLevel();
		}
	}
}
