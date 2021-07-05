package npcOrganizer;

import mainGame.GameManager;

public class NpcManager {
	private Pepper pepper;
	private Pepper pepperArray[];
	
	private static NpcManager manager;

	protected NpcManager() {
		System.out.println("Objektinstanz NpcManager gebildet.");
	}

	public static synchronized NpcManager getInstance() {
		if (NpcManager.manager == null) {
			NpcManager.manager = new NpcManager();
		}
		return manager;
	}
	
	//if we decide to add more than one enemy, this should be helpful
	//currently there's only pepper
	public void setUpEnemies(int level) {
		//insert all enemies to create for each level
		switch(level) {
		case 1:
			setUpPepper(level);
			break;
		case 2:
			setUpPepper(level);
			break;
		case 4:
			setUpPepper(level);
			break;
		case 7:
			setUpPepper(level);
			break;
		case 9:
			setUpPepper(level);
			break;
		case 10:
			setUpPepper(level);
			break;
		default:
			break;
		}
	}
	
	public void setUpPepper(int level) {
		//use case 1 as a template for one enemy
		//case 2 as a template for more than one
		switch(level) {
		case 1:
			//instantiating a new Pepper object, attributes are x / y coordinates (start position).
			pepper = new Pepper(0, 16);
			//loading enemy behaviour
			pepper.pepperBehaviour();
			//set up start position
			pepper.pepperPaint(level);
			//start timer
			pepper.startPepper();
			break;
		case 2:
			//for more than one enemy, use pepperArray.
			pepperArray = new Pepper[2];
			pepperArray[0] = new Pepper(6, 18);
			pepperArray[1] = new Pepper(6, 16);
			for(int i = 0; i < pepperArray.length; i++) {
				pepperArray[i].pepperBehaviour();
				pepperArray[i].pepperPaint(level);
				pepperArray[i].startPepper();
			}
			break;
		case 4:
			pepper = new Pepper(2, 13);
			pepper.pepperBehaviour();
			pepper.pepperPaint(level);
			pepper.startPepper();
			break;
		case 7:
			pepperArray = new Pepper[3];
			for(int i = 0; i < pepperArray.length; i++) {
				pepperArray[i] = new Pepper(12, 19);
				pepperArray[i].pepperBehaviour();
				pepperArray[i].pepperPaint(level);
				pepperArray[i].startPepper();
			}
			break;
		case 9:
			pepperArray = new Pepper[2];
			for(int i = 0; i < pepperArray.length; i++) {
				pepperArray[i] = new Pepper(16, 16);
				pepperArray[i].pepperBehaviour();
				pepperArray[i].pepperPaint(level);
				pepperArray[i].startPepper();
			}
			break;
		case 10:
			pepperArray = new Pepper[12];
			for(int i = 0; i < pepperArray.length; i++) {
				pepperArray[i] = new Pepper(5, 14);
				pepperArray[i].pepperBehaviour();
				pepperArray[i].pepperPaint(level);
				pepperArray[i].startPepper();
			}
			break;
		default:
			break;
		}
	}
	
	public void stopEnemies(int level) {
		//insert all created enemies for each level
		switch(level) {
		case 1:
			pepper.stopPepper();
			break;
		case 4:
			pepper.stopPepper();
			break;
		default:
			if(pepperArray.length > 0) {
				for(int i = 0; i < pepperArray.length; i++) {
					pepperArray[i].stopPepper();
				}
			}
			break;
		}
		
	}

	public void checkCollision(int level, int playerX, int playerY) {
		switch(level) {
		case 1:
			if(playerX == pepper.getEnPosX() && playerY == pepper.getEnPosY()){
				GameManager.loseLife();
			}
			break;
		case 4:
			if(playerX == pepper.getEnPosX() && playerY == pepper.getEnPosY()){
				GameManager.loseLife();
			}
			break;
		default:
			if(pepperArray.length > 0) {
				for(int i = 0; i < pepperArray.length; i++) {
					if(playerX == pepperArray[i].getEnPosX() && playerY == pepperArray[i].getEnPosY()){
						GameManager.loseLife();
					}
				}
			}
			break;
		}
		
	}
}
