package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import jserver.Board;
import jserver.Symbol;
import jserver.XSendAdapter;
import levelOrganizer.ItemArrays;
import levelOrganizer.ItemPainter;
import plotter.Graphic;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class GameManager extends StartGame implements KeyListener {
	/**
	 * Main class to organize most of the game.
	 * 
	 */
	// General variables
	private static GameManager manager = new GameManager();
	protected static boolean move = true;
	private static boolean moveLeft = true;
	protected static int maxLevel = 10;

	// Menu and display variables
	public static Font customFont;
	public static JLabel ptDisplay;
	public static JLabel lvlDisplay;
	public static JPanel livesDisplay;
	public final static ImageIcon heartIcon = new ImageIcon("images/heart.png");
	public static JDialog gameOver = new JDialog();

	// Board & position variables
	public static Board board;
	protected static Graphic graphic;
	protected static XSendAdapter xsend;
	public static Symbol symbol;

	public static final int boardSize = 20;
	protected static int posX;
	protected static int posY;

	boolean fieldAvailable;

	// In-game Item variables
	public static int collectedPoints;
	public static int collectableCounter;
	public static int dmgCounter;
	public static int frostedCounter;
	protected static int[][] collectableArray;
	protected static int[][] solidsArray;
	protected static int[][] onionsArray;
	protected static int[][] lavaArray;
	protected static int[][] healthArray;
	protected static int[][] frostArray;
	protected static int[][] diamondsArray;
	protected static int[][] emptyCauldronArray;
	public static boolean emptyCauldronsActive;

	//constructor and getInstance (singleton)
	protected GameManager() {
		System.out.println("Objektinstanz GameManager gebildet.");
	}

	public static synchronized GameManager getInstance() {
		if (GameManager.manager == null) {
			GameManager.manager = new GameManager();
		}
		return manager;
	}

	//players x and y, useful getters for some methods
	public static int getPosX() {
		return posX;
	}

	public static int getPosY() {
		return posY;
	}

	public static Font createCustomFont(float size) {
		try {
			// create the font to use. Specify the size!
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./other/PressStart2P-Regular.ttf"))
					.deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		return customFont;
	}

	public static void updatePoints() {
		ptDisplay.setText(String.valueOf(collectedPoints) + " Points");
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	
	//first board setup
	public void createBoard(Player player) {
		board = new Board();
		board.setSize(748, 780);

		graphic = board.getGraphic();
		graphic.setJMenuBar(MenuManager.getGameMenu());
		graphic.setTitle("Digger 1.0 - SoSe2021 - Powered by BoS");
		graphic.addKeyListener(this);
		graphic.setResizable(false);

		xsend = new XSendAdapter(board);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(500, 25));
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		livesDisplay = new JPanel();
		livesDisplay.setLayout(new BoxLayout(livesDisplay, BoxLayout.X_AXIS));
		livesDisplay.setMaximumSize(new Dimension(190, 16));
		livesDisplay.setPreferredSize(new Dimension(180, 16));
		for (int i = 0; i < player.getLives(); i++) {
			livesDisplay.add(new JLabel(heartIcon));
		}
		southPanel.add(livesDisplay);

		lvlDisplay = new JLabel("Level " + player.getLevel());
		lvlDisplay.setFont(createCustomFont(13f));
		lvlDisplay.setPreferredSize(new Dimension(119, 18));
		lvlDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		southPanel.add(lvlDisplay);

		ptDisplay = new JLabel(player.getPoints() + " Points");
		ptDisplay.setFont(createCustomFont(9f));
		ptDisplay.setPreferredSize(new Dimension(180, 16));
		ptDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(ptDisplay);

		graphic.addSouthComponent(southPanel);
	}

	//method that builds the board each level anew
	public static void setUpBoard() {
		emptyCauldronsActive = false;
		frostedCounter = 0;
		level = player.getLevel();
		if(level > maxLevel) {
			MenuManager.getGameOverMenu(level);
		} else {
			collectedPoints = player.getPoints();
			collectableCounter = player.getPtCounter();
			dmgCounter = player.getDmgCounter();

			collectableArray = ItemArrays.getTomatoPos(level);
			solidsArray = ItemArrays.getSolidsPos(level);
			onionsArray = ItemArrays.getOnionsPos(level);
			lavaArray = ItemArrays.getLavaPos(level);
			healthArray = ItemArrays.getHealthPos(level);
			frostArray = ItemArrays.getFrostPos(level);
			diamondsArray = ItemArrays.getDiamondPos(level);
			emptyCauldronArray = ItemArrays.getEmptyCauldronPos(level);

			player.resetCounter(level);
			lvlDisplay.setText("Level " + String.valueOf(level));
			move = true;
			posX = boardSize - 1;
			posY = boardSize - 1;

			graphic.setLocationRelativeTo(null);
			graphic.setVisible(false);

			xsend.groesse(boardSize, boardSize);
			xsend.flaeche(0x95612D);
			xsend.formen("none");
			xsend.rahmen(XSendAdapter.BLACK);
			board.receiveMessage("image " + posX + " " + posY + " images/chef.png \n");
			symbol = board.getSymbol(posX, posY);
			symbol.getImageObject().setWorldWidth(0);

			ItemPainter.setUpItems(level);
			npc.setUpEnemies(level);
			updatePoints();
			graphic.setVisible(true);
		}
	}

	//Following methods manage what to do when something happens in the game
	//for example: checkPosition handles iterations through item arrays and decides what to do when it finds a match
	public void checkPosition(int posX, int posY) {
		npc.checkCollision(level, posX, posY);
		int x = 999, y = 999;
		// Check for Tomatoes
		for (int i = 0; i < collectableArray.length; i++) {
			x = collectableArray[i][0];
			y = collectableArray[i][1];
			if (x == posX && y == posY) {
				collectableArray[i][0] = 999;
				collectableArray[i][1] = 999;
				CollectPts(posX, posY, "tomato");
			}
		}
		// Check for Onions
		for (int i = 0; i < onionsArray.length; i++) {
			x = onionsArray[i][0];
			y = onionsArray[i][1];
			if (x == posX && y == posY) {
				onionsArray[i][0] = 999;
				onionsArray[i][1] = 999;
				CollectPts(posX, posY, "onion");
			}
		}
		// Check for Diamonds
		for (int i = 0; i < diamondsArray.length; i++) {
			x = diamondsArray[i][0];
			y = diamondsArray[i][1];
			if (x == posX && y == posY) {
				diamondsArray[i][0] = 999;
				diamondsArray[i][1] = 999;
				CollectPts(posX, posY, "diamond");
				emptyCauldronsActive = true;
				ItemPainter.activateCauldrons(level);
			}
		}
		// Check for Health plus
		for (int i = 0; i < healthArray.length; i++) {
			int lives = player.getLives();
			x = healthArray[i][0];
			y = healthArray[i][1];
			if (x == posX && y == posY) {
				healthArray[i][0] = 999;
				healthArray[i][1] = 999;
				player.setLives(++lives);
				// Repaint display
				livesDisplay.removeAll();
				for (int k = 0; k < player.getLives(); k++) {
					livesDisplay.add(new JLabel(heartIcon));
				}
				SoundManager.playSound("life");
			}
		}
		// Check for Frost protection
		for (int i = 0; i < frostArray.length; i++) {
			x = frostArray[i][0];
			y = frostArray[i][1];
			if (x == posX && y == posY) {
				frostArray[i][0] = 999;
				frostArray[i][1] = 999;
				frostedCounter += 9;
				System.out.println("Frost protection achieved! Available for " + frostedCounter + " fields");
				SoundManager.playSound("frost");

				if (moveLeft == true) {
					board.receiveMessage("image " + posX + " " + posY + " images/frosted_chef.png \n");
				} else {
					board.receiveMessage("image " + posX + " " + posY + " images/frosted_chef_r.png \n");
				}
			}
		}
	}

	public void checkFrostCounter() {
		if (frostedCounter > 0 && fieldAvailable) {
			--frostedCounter;
			System.out.println("Frostprotection reduced! Available for: " + frostedCounter + " fields");
		}
	}
	
	public boolean checkSolids(int posX, int posY) {
		int x, y;
		boolean fieldAvailable = true;
		for (int i = 0; i < solidsArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = solidsArray[i][0];
				y = solidsArray[i][1];
				if (x == posX && y == posY) {
					fieldAvailable = false;
					SoundManager.playSound("stone");
				}
			}
		}
		// empty Cauldrons
		if (emptyCauldronsActive == false) {
			for (int i = 0; i < emptyCauldronArray.length; i++) {
				for (int j = 0; j < 2; j++) {
					x = emptyCauldronArray[i][0];
					y = emptyCauldronArray[i][1];
					if (x == posX && y == posY) {
						fieldAvailable = false;
						SoundManager.playSound("stone");
					}
				}
			}
		}
		return fieldAvailable;
	}

	public void checkLava(int posX, int posY) {
		boolean lavaField = false;
		int x, y;
		for (int i = 0; i < lavaArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = lavaArray[i][0];
				y = lavaArray[i][1];
				if (lavaField == false) {
					if (x == posX && y == posY) {
						lavaField = true;
						loseLife();
						System.out.println("Game Over!");
						// Game Over!
					} else {
						for (int k = x - 1; k <= x + 1; k++) {
							for (int l = y - 1; l <= y + 1; l++) {
								if (k == posX && l == posY) {
									if (frostedCounter == 0) {
										lavaField = true;
										afflictDmg();
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(emptyCauldronsActive == true) {
			for (int i = 0; i < emptyCauldronArray.length; i++) {
				for (int j = 0; j < 2; j++) {
					x = emptyCauldronArray[i][0];
					y = emptyCauldronArray[i][1];
					if (lavaField == false) {
						if (x == posX && y == posY) {
							lavaField = true;
							loseLife();
							System.out.println("Game Over!");
							// Game Over!
						} else {
							for (int k = x - 1; k <= x + 1; k++) {
								for (int l = y - 1; l <= y + 1; l++) {
									if (k == posX && l == posY) {
										if (frostedCounter == 0) {
											lavaField = true;
											afflictDmg();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// method lavaPainter, used for repainting lava on floor after player digged
	// through
	public static boolean lavaPainter(int posX, int posY) {
		boolean repaint = false;
		int x, y;
		for (int i = 0; i < lavaArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = lavaArray[i][0];
				y = lavaArray[i][1];
				for (int k = x - 1; k <= x + 1; k++) {
					for (int l = y - 1; l <= y + 1; l++) {
						if (k == posX && l == posY) {
							repaint = true;
						}
					}
				}
			}
		}
		return repaint;
	}
	
	//Next methods are to call, when collecting points, losing life or get damage
	private void afflictDmg() {
		--dmgCounter;
		SoundManager.playSound("dmg");
		System.out.println("Lava hits the player! dmgCounter: " + dmgCounter);
		if (dmgCounter == 0) {
			loseLife();
		}
	}

	public static void loseLife() {
		npc.stopEnemies(level);
		SoundManager.playSound("dmg");
		int lives = player.getLives();
		if (lives > 0) {
			player.setLives(--lives);
			// Repaint display
			livesDisplay.removeAll();
			for (int i = 0; i < player.getLives(); i++) {
				livesDisplay.add(new JLabel(heartIcon));
			}
			setUpBoard();
		} else {
			move = false;
			SoundManager.playSound("gameOver");
			MenuManager.getGameOverMenu(level);
		}
	}

	private void CollectPts(int posX, int posY, String type) {
		level = player.getLevel();
		switch (type) {
		case "tomato":
			collectableCounter--;
			++collectedPoints;
			SoundManager.playSound("tomato");
			break;
		case "onion":
			collectedPoints += 5;
			SoundManager.playSound("onion");
			break;
		case "diamond":
			collectedPoints += 50;
			SoundManager.playSound("diamond");
			break;
		}
		if (collectableCounter == 0) {
			npc.stopEnemies(level);
			player.setLevel(++level);
			player.setPoints(collectedPoints);
			player.resetCounter(player.getLevel());
			collectableCounter = player.getPtCounter();
			move = false;
			setUpBoard();
			SoundManager.playSound("lvlUp");
		} else {
			updatePoints();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// methods to call when user presses a key
	// TODO: use game-loop instead of keyPressed
	@Override
	public void keyPressed(KeyEvent e) {
		// variable "move" to prevent player movement in some situations (transition,
		// game over, etc.)
		if (move) {
			int keyCode = e.getKeyCode();
			// UP
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				moveUp();
			} else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
				moveLeft();
			} else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				moveDown();
			} else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
				moveRight();
			}
			paintPlayer();

			checkFrostCounter();
			checkPosition(posX, posY);
			checkLava(posX, posY);

			// resizing image
			symbol = board.getSymbol(posX, posY);
			symbol.getImageObject().setWorldWidth(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void moveUp() {
		if (posY < boardSize - 1 && checkSolids(posX, posY + 1)) {
			fieldAvailable = true;
			++posY;

			if (lavaPainter(posX, posY - 1)) {
				board.receiveMessage("image " + posX + " " + (posY - 1) + " ./images/earth_fire.jpg \n");
			} else {
				board.receiveMessage("image " + posX + " " + (posY - 1) + " -\n");
				xsend.farbe2(posX, posY - 1, 0x95612D);
			}
		} else {
			fieldAvailable = false;
		}
	}

	public void moveDown() {
		if (posY > 0 && checkSolids(posX, posY - 1)) {
			fieldAvailable = true;
			--posY;

			if (lavaPainter(posX, posY + 1)) {
				board.receiveMessage("image " + posX + " " + (posY + 1) + " ./images/earth_fire.jpg \n");
			} else {
				board.receiveMessage("image " + posX + " " + (posY + 1) + " -\n");
				xsend.farbe2(posX, posY + 1, 0x95612D);
			}
		} else {
			fieldAvailable = false;
		}
	}

	public void moveLeft() {
		moveLeft = true;
		if (posX > 0 && checkSolids(posX - 1, posY)) {
			fieldAvailable = true;
			--posX;

			if (lavaPainter(posX + 1, posY)) {
				board.receiveMessage("image " + (posX + 1) + " " + posY + " ./images/earth_fire.jpg \n");
			} else {
				board.receiveMessage("image " + (posX + 1) + " " + posY + " -\n");
				xsend.farbe2(posX + 1, posY, 0x95612D);
			}
		} else {
			fieldAvailable = false;
		}
	}

	public void moveRight() {
		moveLeft = false;
		if (posX < boardSize - 1 && checkSolids(posX + 1, posY)) {
			fieldAvailable = true;
			++posX;

			if (lavaPainter(posX - 1, posY)) {
				board.receiveMessage("image " + (posX - 1) + " " + posY + " ./images/earth_fire.jpg \n");
			} else {
				board.receiveMessage("image " + (posX - 1) + " " + posY + " -\n");
				xsend.farbe2(posX - 1, posY, 0x95612D);
			}
		} else {
			fieldAvailable = false;
		}
	}

	//paints the players character onto the board
	public void paintPlayer() {
		xsend.farbe2(posX, posY, 0x95612D);
		if (moveLeft == true) {
			if (frostedCounter > 1) {
				board.receiveMessage("image " + posX + " " + posY + " images/frosted_chef.png \n");
			} else {
				board.receiveMessage("image " + posX + " " + posY + " images/chef.png \n");
			}
		} else {
			if (frostedCounter > 1) {
				board.receiveMessage("image " + posX + " " + posY + " images/frosted_chef_r.png \n");
			} else {
				board.receiveMessage("image " + posX + " " + posY + " images/chef_r.png \n");
			}
		}
	}

	//saving player object
	public static boolean save() {
		boolean saved;
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.bin"))) {
			out.writeObject(player);
			saved = true;
			System.out.println("Successfully saved game");
			System.out.println();
		} catch (Exception e) {
			saved = false;
			System.out.println("Failed to save game");
			System.out.print(e);
			System.out.println();
		}
		return saved;
	}

	public static void saveHighScore() {
		String name = player.getName();
		String score = String.valueOf(player.getPoints());
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("highScore.txt", true));
			if (name.length() > 0) {
				writer.write(name + ";" + score);
				writer.newLine();
				writer.close();
				graphic.dispose();
				gameOver.dispose();
				MainMenu.setFrame();
			} else {
				JDialog emptyName = new JDialog();
				emptyName.setSize(300, 100);
				JLabel errorMsg = new JLabel("Please enter your name.");
				errorMsg.setFont(createCustomFont(9f));
				errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
				emptyName.add(errorMsg);

				emptyName.setAlwaysOnTop(true);
				emptyName.setLocationRelativeTo(null);
				emptyName.setVisible(true);
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}

	}
}