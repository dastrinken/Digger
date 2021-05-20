package mainGame;

// TODO: Unbewegliche Objekte (solids) einfügen,
// TODO: Gegner generieren?

public class LevelManager {
	static int[][] collectableArray;
	static int collectableCounter = 5;

	public static int[][] getCollectableArray() {
		return collectableArray;
	}

	public static void setCollectableArray(int[][] getCollectableArray) {
		LevelManager.collectableArray = getCollectableArray;
	}

	public static void checkPosition(int posX, int posY) {
		int x = 999, y = 999;

		for (int i = 0; i < collectableArray.length; i++) {
			 for(int j = 0; j < 2; j++) {
				 if(j == 0) {
					 x = collectableArray[i][j];
				 }
				 else {
					 y = collectableArray[i][j];
					 if(x == posX && y == posY) {
						 collectableArray[i][j] = 999;
						 collectableArray[i][j-1] = 999;
						 CollectPts(posX, posY);
					 }
				 }
			 }
		}
	}

	private static void CollectPts(int posX, int posY) {
		// TODO:	Collectables zählen und nächste Level starten, wenn alle eingesammelt.
		// TODO:	Punkte hochzählen und im Panel ausgeben
		//			Eventuell lieber mit ArrayList statt Array arbeiten?
		StartGame.board.receiveMessage("image " + posX + " " + posY + " -\n");
		System.out.println("Match!");
		
		collectableCounter--;
		if(collectableCounter == 0) {
			StartGame.nextLevel();
		}
	}
}
