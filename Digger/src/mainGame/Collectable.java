package mainGame;

public class Collectable {
	static int level = 1;
	
	public static int[][] CreateCollectables(int boardSize) {
		int posX, posY;
		
		int[][] collectables = new int[level+4][2];
		 for(int i = 0; i < collectables.length; i++ ) {
				 posX = (int) (Math.random()*20);
				 posY = (int) (Math.random()*20);
				 while(posX == boardSize-1 && posX == posY) {
					 posY = (int) (Math.random()*20);
					 System.out.println("Tomate auf Startplatz aussortiert");
				 }
				 collectables[i][0] = posX;
				 collectables[i][1] = posY;
				 System.out.print("Tomate auf: (\tx="+collectables[i][0]+"\t|\ty="+collectables[i][1]+"\t)\n");
		 }
		 return collectables;
	}
}

