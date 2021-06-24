package mainGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class NpcManager extends GameManager {
	static private int lastMove;
	public static Timer pepperMove;

	public NpcManager() {
	}

	public static void pepperBehaviour() {
		Random rnd = new Random();
		int delay = 500; // peppers speed
		ActionListener pepperRun = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int moveTo;
				int rollDice;
				// 0 = UP ; 1 = DOWN ; 2 = LEFT ; 3 = RIGHT
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

				System.out.println("LastMove: " + lastMove + " moveTo: " + moveTo);
				lastMove = moveTo;
				// UP
				if (moveTo == 0 && fieldAvailable(0)) {
					board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					board.receiveMessage("image " + enPosX + " " + (enPosY + 1) + " ./images/pepper.png \n");

					enPosY += 1;
					lastMove = moveTo;
				}
				// DOWN
				else if (moveTo == 1 && fieldAvailable(1)) {
					board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					board.receiveMessage("image " + enPosX + " " + (enPosY - 1) + " ./images/pepper.png \n");

					enPosY -= 1;
					lastMove = moveTo;
				}
				// LEFT
				else if (moveTo == 2 && fieldAvailable(2)) {
					board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					board.receiveMessage("image " + (enPosX - 1) + " " + enPosY + " ./images/pepper.png \n");

					enPosX -= 1;
					lastMove = moveTo;
				}
				// RIGHT
				else if (moveTo == 3 && fieldAvailable(3)) {
					board.receiveMessage("image " + enPosX + " " + enPosY + " - \n");
					board.receiveMessage("image " + (enPosX + 1) + " " + enPosY + " ./images/pepper.png \n");

					enPosX += 1;
					lastMove = moveTo;
				}
				checkPlayerCollision();
			}
		};
		pepperMove = new Timer(delay, pepperRun);

	}

	public static boolean fieldAvailable(int direction) {
		boolean isAvailable = false;
		String str;
		String[] split;

		switch (direction) {
		case 0:
			if (enPosY + 1 < boardSize) {
				symbol = board.getSymbol(enPosX, enPosY + 1);
				str = symbol.toString();
				split = str.split("=|,");
				if (Integer.valueOf(split[7]) == 149) {
					isAvailable = true;
				}
			}
			break;

		case 1:
			if (enPosY - 1 >= 0) {
				symbol = board.getSymbol(enPosX, enPosY - 1);
				str = symbol.toString();
				split = str.split("=|,");
				if (Integer.valueOf(split[7]) == 149) {
					isAvailable = true;
				}
			}
			break;

		case 2:
			if (enPosX - 1 >= 0) {
				symbol = board.getSymbol(enPosX - 1, enPosY);
				str = symbol.toString();
				split = str.split("=|,");
				if (Integer.valueOf(split[7]) == 149) {
					isAvailable = true;
				}
			}
			break;

		case 3:
			if (enPosX + 1 < boardSize) {
				symbol = board.getSymbol(enPosX + 1, enPosY);
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

	public static void pepperStartPos(int level) {
		switch (level) {
		case 10:
			enPosX = 0;
			enPosY = 19;
			board.receiveMessage("image " + enPosX + " " + enPosY + " ./images/pepper.png \n");
			break;
		default:
			break;
		}
	}

	public static void startPepper() {
		pepperMove.start();
	}

	public static void stopPepper() {
		if (pepperMove != null && pepperMove.isRunning()) {
			pepperMove.stop();
		}
	}

	private static void checkPlayerCollision() {
		if (posX == enPosX && posY == enPosY) {
			loseLife();
		}
	}
}
