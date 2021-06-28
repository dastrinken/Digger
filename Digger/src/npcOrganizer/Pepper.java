package npcOrganizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import jserver.Symbol;
import mainGame.GameManager;

public class Pepper {
	private int lastMove;
	public Timer pepperMove;
	private int dirCounter;
	public int enPosX;
	public int enPosY;
	
	ActionListener pepperRun;
	int delay = 300; // peppers speed

	public Pepper(int enPosX, int enPosY) {
		this.enPosX = enPosX;
		this.enPosY = enPosY;
	}
	
	protected int getEnPosX() {
		return enPosX;
	}

	protected int getEnPosY() {
		return enPosY;
	}

	public void pepperPaint(int level) {
		switch (level) {
		case 1:
			GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " ./images/pepper.png \n");
			break;
		case 2:
			GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " ./images/pepper.png \n");
			break;
		case 7:
			GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " ./images/pepper.png \n");
			break;
		default:
			break;
		}
	}

	public void startPepper() {
		pepperMove = new Timer(delay, pepperRun);
		pepperMove.start();
	}
	
	public void stopPepper() {
		if (pepperMove != null && pepperMove.isRunning()) {
			pepperMove.stop();
		}
	}
	
	public void pepperBehaviour() {
		Random rnd = new Random();
		pepperRun = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int moveTo;
				int rollDice;
				// 0 = UP ; 1 = DOWN ; 2 = LEFT ; 3 = RIGHT
				if (fieldAvailable(lastMove)) {
					if (dirCounter > 10) {
						moveTo = rnd.nextInt(4);
					} else {
						moveTo = lastMove;
					}
				} else {
					moveTo = rnd.nextInt(4);
					if (lastMove == 0) {
						if (fieldAvailable(2)) {
							if (fieldAvailable(3)) {
								rollDice = rnd.nextInt(2) + 1;
								if (rollDice % 2 == 0) {
									moveTo = 2;
								} else {
									moveTo = 3;
								}
							} else {
								moveTo = 2;
							}
						} else if (fieldAvailable(3)) {
							moveTo = 3;
						} else if (fieldAvailable(0)) {
							moveTo = 0;
						} else {
							moveTo = 1;
						}
					} else if (lastMove == 1) {
						if (fieldAvailable(3)) {
							moveTo = 3;
						} else if (fieldAvailable(2)) {
							moveTo = 2;
						} else if (fieldAvailable(1)) {
							moveTo = 1;
						} else {
							moveTo = 0;
						}
					} else if (lastMove == 2) {
						if (fieldAvailable(1)) {
							moveTo = 1;
						} else if (fieldAvailable(0)) {
							moveTo = 0;
						} else if (fieldAvailable(2)) {
							moveTo = 2;
						} else {
							moveTo = 3;
						}
					} else if (lastMove == 3) {
						if (fieldAvailable(1)) {
							moveTo = 1;
						} else if (fieldAvailable(0)) {
							moveTo = 0;
						} else if (fieldAvailable(3)) {
							moveTo = 3;
						} else {
							moveTo = 2;
						}
					} else {
						moveTo = rnd.nextInt(4);
					}
				}

				if (lastMove == moveTo) {
					dirCounter++;
				} else {
					dirCounter = 0;
				}
//				System.out.println("LastMove: " + lastMove + " moveTo: " + moveTo + "  -  dirCounter: " + dirCounter);
				// UP
				if (moveTo == 0 && fieldAvailable(0)) {
					GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					GameManager.board.receiveMessage("image " + enPosX + " " + (enPosY + 1) + " ./images/pepper.png \n");

					enPosY += 1;
					lastMove = moveTo;
				}
				// DOWN
				else if (moveTo == 1 && fieldAvailable(1)) {
					GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					GameManager.board.receiveMessage("image " + enPosX + " " + (enPosY - 1) + " ./images/pepper.png \n");

					enPosY -= 1;
					lastMove = moveTo;
				}
				// LEFT
				else if (moveTo == 2 && fieldAvailable(2)) {
					GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					GameManager.board.receiveMessage("image " + (enPosX - 1) + " " + enPosY + " ./images/pepper.png \n");

					enPosX -= 1;
					lastMove = moveTo;
				}
				// RIGHT
				else if (moveTo == 3 && fieldAvailable(3)) {
					GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					GameManager.board.receiveMessage("image " + (enPosX + 1) + " " + enPosY + " ./images/pepper.png \n");

					enPosX += 1;
					lastMove = moveTo;
				}
				checkPlayerCollision();
			}
		};
	}
	public boolean fieldAvailable(int direction) {
		boolean isAvailable = false;
		String str;
		String[] split;
		Symbol symbol;
		
		switch (direction) {
		case 0:
			if (enPosY + 1 < GameManager.boardSize) {
				symbol = GameManager.board.getSymbol(enPosX, enPosY + 1);
				str = symbol.toString();
				split = str.split("=|,");
				if (Integer.valueOf(split[7]) == 149) {
					isAvailable = true;
				}
			}
			break;

		case 1:
			if (enPosY - 1 >= 0) {
				symbol = GameManager.board.getSymbol(enPosX, enPosY - 1);
				str = symbol.toString();
				split = str.split("=|,");
				if (Integer.valueOf(split[7]) == 149) {
					isAvailable = true;
				}
			}
			break;

		case 2:
			if (enPosX - 1 >= 0) {
				symbol = GameManager.board.getSymbol(enPosX - 1, enPosY);
				str = symbol.toString();
				split = str.split("=|,");
				if (Integer.valueOf(split[7]) == 149) {
					isAvailable = true;
				}
			}
			break;

		case 3:
			if (enPosX + 1 < GameManager.boardSize) {
				symbol = GameManager.board.getSymbol(enPosX + 1, enPosY);
				str = symbol.toString();
				split = str.split("=|,");
				if (Integer.valueOf(split[7]) == 149) {
					isAvailable = true;
				}
			}
			break;
		}
		return isAvailable;
	}
	
	public void checkPlayerCollision() {
		if (GameManager.getPosX() == enPosX && GameManager.getPosY() == enPosY) {
			GameManager.loseLife();
		}
	}
}
