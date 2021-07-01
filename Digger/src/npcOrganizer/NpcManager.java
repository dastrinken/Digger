package npcOrganizer;

import mainGame.GameManager;

public class NpcManager {
	private Pepper pepper, pepper2, pepper3, pepper4, pepper5, pepper6, pepper7, pepper8, pepper9;
	
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
		case 7:
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
		//use case 1 as a template
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
			pepper = new Pepper(12, 19);
			pepper.pepperBehaviour();
			pepper.pepperPaint(level);
			pepper.startPepper();
			break;
		case 10:
			pepper = new Pepper(5, 14);
			pepper.pepperBehaviour();
			pepper.pepperPaint(level);
			pepper.startPepper();
			
			pepper2 = new Pepper(5, 14);
			pepper2.pepperBehaviour();
			pepper2.pepperPaint(level);
			pepper2.startPepper();
			
			pepper3 = new Pepper(5, 14);
			pepper3.pepperBehaviour();
			pepper3.pepperPaint(level);
			pepper3.startPepper();
			
			pepper4 = new Pepper(5, 14);
			pepper4.pepperBehaviour();
			pepper4.pepperPaint(level);
			pepper4.startPepper();
			
			pepper5 = new Pepper(5, 14);
			pepper5.pepperBehaviour();
			pepper5.pepperPaint(level);
			pepper5.startPepper();
			
			pepper6 = new Pepper(5, 14);
			pepper6.pepperBehaviour();
			pepper6.pepperPaint(level);
			pepper6.startPepper();
			
			pepper7 = new Pepper(5, 14);
			pepper7.pepperBehaviour();
			pepper7.pepperPaint(level);
			pepper7.startPepper();
			
			pepper8 = new Pepper(5, 14);
			pepper8.pepperBehaviour();
			pepper8.pepperPaint(level);
			pepper8.startPepper();
			
			pepper9 = new Pepper(5, 14);
			pepper9.pepperBehaviour();
			pepper9.pepperPaint(level);
			pepper9.startPepper();
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
			pepper.stopPepper();
			break;
		case 10:
			pepper.stopPepper();
			pepper2.stopPepper();
			pepper3.stopPepper();
			pepper4.stopPepper();
			pepper5.stopPepper();
			pepper6.stopPepper();
			pepper7.stopPepper();
			pepper8.stopPepper();
			pepper9.stopPepper();
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
		case 7:
			if(playerX == pepper.getEnPosX() && playerY == pepper.getEnPosY()){
				GameManager.loseLife();
			}
			break;
		case 10:
			if(playerX == pepper.getEnPosX() && playerY == pepper.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper2.getEnPosX() && playerY == pepper2.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper3.getEnPosX() && playerY == pepper3.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper4.getEnPosX() && playerY == pepper4.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper5.getEnPosX() && playerY == pepper5.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper6.getEnPosX() && playerY == pepper6.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper7.getEnPosX() && playerY == pepper7.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper8.getEnPosX() && playerY == pepper8.getEnPosY()){
				GameManager.loseLife();
			}
			if(playerX == pepper9.getEnPosX() && playerY == pepper9.getEnPosY()){
				GameManager.loseLife();
			}
			break;
		default:
			break;
		}
		
	}
}
