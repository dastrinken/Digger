package npcOrganizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import jserver.Symbol;
import mainGame.GameManager;

public class Pepper {
	private int lastMove;
	int moveTo;
	public Timer pepperMove;
	private int dirCounter;
	public int enPosX;
	public int enPosY;
	Random rnd = new Random();
	
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
		pepperRun = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int rollDice;
				// 0 = UP ; 1 = DOWN ; 2 = LEFT ; 3 = RIGHT
				if (fieldAvailable(lastMove)) {
					if(lastMove == 0 || lastMove == 1) {
						moveTo = checkLeftRight(lastMove);
					}
					else if(lastMove == 2 || lastMove == 3) {
						moveTo = checkUpDown(lastMove);
					}
					if(moveTo == lastMove) {
						moveTo = checkDirCounter(lastMove, dirCounter);
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
				movePepper(moveTo);
				checkPlayerCollision();
			}
		};
	}
	
	protected void movePepper(int moveTo) {
		//checking for lavaField and repainting
		if (GameManager.lavaPainter(enPosX, enPosY)) {
			GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " ./images/earth_fire.jpg \n");
		} else {
			GameManager.board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
		}
		// UP
		if (moveTo == 0 && fieldAvailable(0)) {
			GameManager.board.receiveMessage("image " + enPosX + " " + (enPosY + 1) + " ./images/pepper.png \n");

			enPosY += 1;
			lastMove = moveTo;
		}
		// DOWN
		else if (moveTo == 1 && fieldAvailable(1)) {
			GameManager.board.receiveMessage("image " + enPosX + " " + (enPosY - 1) + " ./images/pepper.png \n");

			enPosY -= 1;
			lastMove = moveTo;
		}
		// LEFT
		else if (moveTo == 2 && fieldAvailable(2)) {
			GameManager.board.receiveMessage("image " + (enPosX - 1) + " " + enPosY + " ./images/pepper.png \n");

			enPosX -= 1;
			lastMove = moveTo;
		}
		// RIGHT
		else if (moveTo == 3 && fieldAvailable(3)) {
			GameManager.board.receiveMessage("image " + (enPosX + 1) + " " + enPosY + " ./images/pepper.png \n");

			enPosX += 1;
			lastMove = moveTo;
		}
	}

	protected int checkDirCounter(int lastMove, int dirCounter) {
		Random rnd = new Random();
		int moveTo;
		if (dirCounter > 10) {
			moveTo = rnd.nextInt(4);
		} else {
			moveTo = lastMove;
		}
		return moveTo;
	}

	protected int checkUpDown(int lastMove) {
		int moveTo, roll;
		Random rnd = new Random();
		if(fieldAvailable(0)) {
			roll = rnd.nextInt(2) + 1;
			if(fieldAvailable(1)) {
				if(roll % 2 != 0) {
					moveTo = 1;
				} else {
					moveTo = 0;
				}
			} else {
				if(roll % 2 != 0) {
					moveTo = 0;
				} else {
					moveTo = lastMove;
				}
			}
		} else if(fieldAvailable(1)) {
			roll = rnd.nextInt(2) + 1;
			if(roll % 2 != 0) {
				moveTo = 1;
			} else {
				moveTo = lastMove;
			}
		} else {
			moveTo = lastMove;
		}
		return moveTo;
	}

	protected int checkLeftRight(int lastMove) {
		int moveTo, roll;
		Random rnd = new Random();
		if(fieldAvailable(2)) {
			roll = rnd.nextInt(2) + 1;
			if(fieldAvailable(3)) {
				if(roll % 2 != 0) {
					moveTo = 2;
				} else {
					moveTo = 3;
				}
			} else {
				if(roll % 2 != 0) {
					moveTo = 2;
				} else {
					moveTo = lastMove;
				}
			}
		} else if(fieldAvailable(3)) {
			roll = rnd.nextInt(2) + 1;
			if(roll % 2 != 0) {
				moveTo = 3;
			} else {
				moveTo = lastMove;
			}
		} else {
			moveTo = lastMove;
		}
		return moveTo;
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
