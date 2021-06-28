package npcOrganizer;

import mainGame.GameManager;

public class NpcManager {
	private Pepper pepper;
	private Pepper pepper2;
	
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
		default:
			break;
		}
	}
	
	public void setUpPepper(int level) {
		//use case 1 as a template
		switch(level) {
		case 1:
			//instantiating a new Pepper object, attributes are x / y coordinates (start position).
			pepper = new Pepper(19, 16);
			//loading enemy behaviour
			pepper.pepperBehaviour();
			//set up start position
			pepper.pepperPaint(level);
			//start timer
			pepper.startPepper();
			break;
		case 2:
			pepper = new Pepper(6, 18);
			pepper.pepperBehaviour();
			pepper.pepperPaint(level);
			pepper.startPepper();
			
			pepper2 = new Pepper(6, 16);
			pepper2.pepperBehaviour();
			pepper2.pepperPaint(level);
			pepper2.startPepper();
			break;
		case 4:
			pepper = new Pepper(2, 13);
			pepper.pepperBehaviour();
			pepper.pepperPaint(level);
			pepper.startPepper();
			break;
		case 7:
			pepper = new Pepper(19, 16);
			pepper.pepperPaint(level);
			pepper.startPepper();
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
		case 2:
			pepper.stopPepper();
			pepper2.stopPepper();
			break;
		case 4:
			pepper.stopPepper();
			break;
		case 7:
			break;
		default:
			break;
		}
		
	}

	public void checkCollision(int level, int playerX, int playerY) {
		//insert all created enemies for each level
		switch(level) {
		case 1:
			if(playerX == pepper.getEnPosX() && playerY == pepper.getEnPosY()){
				GameManager.loseLife();
			}
			break;
		case 2:
			if(playerX == pepper.getEnPosX() && playerY == pepper.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper2.getEnPosX() && playerY == pepper2.getEnPosY()){
				GameManager.loseLife();
			}
			break;
		case 4:
			if(playerX == pepper.getEnPosX() && playerY == pepper.getEnPosY()){
				GameManager.loseLife();
			}
			break;
		default:
			break;
		}
		
	}
}
