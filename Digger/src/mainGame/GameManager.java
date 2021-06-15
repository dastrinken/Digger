package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import jserver.Board;
import jserver.Symbol;
import jserver.XSendAdapter;
import plotter.Graphic;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class GameManager extends StartGame implements KeyListener {
	// General variables
	private static GameManager manager = new GameManager();
	private static boolean move = true;
	private static boolean moveLeft = true;

	// Menu and display variables
	public static Font customFont;
	public static JLabel ptDisplay;
	public JLabel lvlDisplay;
	public JPanel livesDisplay;
	public final ImageIcon heartIcon = new ImageIcon("./images/heart.png");

	// Board & position variables
	protected static Board board;
	protected static Graphic graphic;
	private static XSendAdapter xsend;
	protected static Symbol symbol;

	public static final int boardSize = 20;
	private int posX;
	private int posY;

	// In-game Item variables
	public int collectableCounter;
	public int dmgCounter;
	protected static int[][] collectableArray;
	protected static int[][] solidsArray;
	protected static int[][] onionsArray;
	protected static int[][] lavaArray;

	// Music variables
	protected static Clip bgMusic;
	protected static Clip soundEffect;
	protected static boolean musicOn = true;
	protected static boolean soundsOn = true;
	protected static int musicSliderValue = 100;
	protected static int soundsSliderValue = 100;
	protected static float soundsVolume = .1f;

	protected GameManager() {
		System.out.println("Objektinstanz gebildet.");
	}

	public static synchronized GameManager getInstance() {
		if (GameManager.manager == null) {
			GameManager.manager = new GameManager();
		}
		return manager;
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

	public static void updatePoints(int points) {
		ptDisplay.setText(String.valueOf(points) + " Points");
	}

	/**
	 * @wbp.parser.entryPoint
	 */
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

	public void setUpBoard() {
		level = player.getLevel();
		collectableCounter = player.getPtCounter();
		dmgCounter = player.getDmgCounter();
		
		collectableArray = LevelManager.getTomatoPos(level);
		solidsArray = LevelManager.getSolidsPos(level);
		onionsArray = LevelManager.getOnionsPos(level);
		lavaArray = LevelManager.getLavaPos(level);

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
		board.receiveMessage("image " + posX + " " + posY + " ./images/chef.png \n");
		symbol = board.getSymbol(posX, posY);
		symbol.getImageObject().setWorldWidth(0);

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (i != posX || j != posY) {
					board.receiveMessage("image " + i + " " + j + " ./images/earth.png \n");
					symbol = board.getSymbol(i, j);
					symbol.getImageObject().setWorldWidth(0);
				}
			}
		}
		ItemManager.setUpItems(level);
		graphic.setVisible(true);
	}

	public void checkPosition(int posX, int posY) {
		int x = 999, y = 999;
		for (int i = 0; i < collectableArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = collectableArray[i][0];
				y = collectableArray[i][1];
				if (x == posX && y == posY) {
					collectableArray[i][0] = 999;
					collectableArray[i][1] = 999;
					CollectPts(posX, posY, "tomato");
				}
			}
		}
		for (int i = 0; i < onionsArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = onionsArray[i][0];
				y = onionsArray[i][1];
				if (x == posX && y == posY) {
					onionsArray[i][0] = 999;
					onionsArray[i][1] = 999;
					CollectPts(posX, posY, "onion");
				}
			}
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
					SoundManager.stoneHit();
				}
			}
		}
		return fieldAvailable;
	}

	public void checkLava(int posX, int posY) {
		int x, y;
		boolean loseLife = false;
		for (int i = 0; i < lavaArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = lavaArray[i][0];
				y = lavaArray[i][1];
				if(loseLife == false) {
					if (x == posX && y == posY) {
						loseLife = true;
						loseLife();
						System.out.println("Game Over!");
						// Game Over!
					} else {
						for (int k = x - 1; k <= x + 1; k++) {
							for (int l = y - 1; l <= y + 1; l++) {
								if (k == posX && l == posY) {
									loseLife = true;
									afflictDmg();
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void loseLife() {
		int lives = player.getLives();
		if(lives > 0) {
			player.setLives(--lives);
			//Repaint display
			livesDisplay.removeAll();
			for (int i = 0; i < player.getLives(); i++) {
				livesDisplay.add(new JLabel(heartIcon));
			}
			setUpBoard();
		} else {
			move = false;
			SoundManager.gameOver();
			MenuManager.getGameOverMenu(level);
		}
	}
	
	private void afflictDmg() {
		--dmgCounter;
		SoundManager.dmg();
		if(dmgCounter == 0) {
			loseLife();
		}
	}

	private void CollectPts(int posX, int posY, String type) {
		level = player.getLevel();
		board.receiveMessage("image " + posX + " " + posY + " -\n");
		switch (type) {
		case "tomato":
			collectableCounter--;
			player.incPoints(1);
			SoundManager.crunchyBite();
			break;
		case "onion":
			player.incPoints(5);
			SoundManager.sparkleCollect();
			break;
		}
		if (collectableCounter == 0) {
			player.setLevel(++level);
			player.resetCounter(player.getLevel());
			collectableCounter = player.getPtCounter();
			move = false;
			setUpBoard();
			SoundManager.lvlUp();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (move) {
			int keyCode = e.getKeyCode();
			// UP
			if (keyCode == 87 || keyCode == 38) {
				if (posY < boardSize - 1 && checkSolids(posX, posY + 1)) {
					++posY;
					board.receiveMessage("image " + posX + " " + (posY - 1) + " -\n");
				}
				// LEFT
			} else if (keyCode == 65 || keyCode == 37) {
				moveLeft = true;
				if (posX > 0 && checkSolids(posX - 1, posY)) {
					--posX;
					board.receiveMessage("image " + (posX + 1) + " " + posY + " -\n");
				}
				// DOWN
			} else if (keyCode == 83 || keyCode == 40) {
				if (posY > 0 && checkSolids(posX, posY - 1)) {
					--posY;
					board.receiveMessage("image " + posX + " " + (posY + 1) + " -\n");
				}
				// RIGHT
			} else if (keyCode == 68 || keyCode == 39) {
				moveLeft = false;
				if (posX < boardSize - 1 && checkSolids(posX + 1, posY)) {
					++posX;
					board.receiveMessage("image " + (posX - 1) + " " + posY + " -\n");
				}
			}
			checkPosition(posX, posY);
			checkLava(posX, posY);
			if (moveLeft == true) {
				board.receiveMessage("image " + posX + " " + posY + " ./images/chef.png \n");
			} else {
				board.receiveMessage("image " + posX + " " + posY + " ./images/chef_r.png \n");
			}

			symbol = board.getSymbol(posX, posY);
			symbol.getImageObject().setWorldWidth(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static boolean save(Player player) {
		boolean saved;
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.bin"))) {
			out.writeObject(player);
			saved = true;
			System.out.println("Successfully saved game");
			System.out.println();
		} catch (Exception e) {
			saved = false;
			System.out.println("Failed to save game");
			System.out.println();
		}
		return saved;
	}
}