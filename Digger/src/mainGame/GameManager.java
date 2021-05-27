package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

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

// TODO: Gegner generieren?

public class GameManager implements KeyListener {
	// TODO: Player dynamisch generieren lassen wenn "Spiel fortsetzen"!
	private static int lives = 3;
	private static int points = 0;
	private static Player player = new Player(lives, points);
	private static int level = player.level;

	public static JLabel ptDisplay;
	public JLabel lvlDisplay;
	public JLabel livesDisplay;

	static int collectableCounter = player.getPtCounter();

	private static Board board;
	protected static Graphic graphic;
	private static XSendAdapter xsend;
	private static Symbol symbol;

	private int boardSize = 20;
	private int posX;
	private int posY;

	LevelManager levelManager = new LevelManager(boardSize);
	static int[][] collectableArray;
	static int[][] solidsArray;

	// Custom font
	public static Font customFont = createCustomFont(13);

	public GameManager() {
		super();
	}

	public static Font createCustomFont(int size) {
		try {
			// create the font to use. Specify the size!
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./other/PressStart2P-Regular.ttf"))
					.deriveFont((float) size);
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
	public void setUpBoard() {
		
		posX = boardSize - 1;
		posY = boardSize - 1;
		board = new Board();
		board.setSize(770, 800);

		graphic = board.getGraphic();
		graphic.setVisible(false);
		graphic.addKeyListener(this);
		graphic.setTitle("Digger V0.1 SoSe2021");
		graphic.setLocationRelativeTo(null);
		graphic.setResizable(false);

		xsend = new XSendAdapter(board);
		xsend.groesse(boardSize, boardSize);
		xsend.flaeche(0x9E5C2D);
		xsend.formen("none");
		xsend.rahmen(XSendAdapter.BLACK);
		board.receiveMessage("image " + posX + " " + posY + " ./images/digger.png \n");
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

		graphic.setVisible(true);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(500, 20));
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		livesDisplay = new JLabel(player.lives + " Lives");
		livesDisplay.setFont(customFont);
		livesDisplay.setPreferredSize(new Dimension(190, 16));
		livesDisplay.setHorizontalAlignment(SwingConstants.LEFT);
		southPanel.add(livesDisplay);

		lvlDisplay = new JLabel("Level " + player.level);
		lvlDisplay.setFont(customFont);
		lvlDisplay.setPreferredSize(new Dimension(99, 18));
		lvlDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		southPanel.add(lvlDisplay);

		ptDisplay = new JLabel(player.points + " Points");
		ptDisplay.setFont(customFont);
		ptDisplay.setPreferredSize(new Dimension(190, 16));
		ptDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(ptDisplay);

		graphic.addSouthComponent(southPanel);
	}

	public void setUpItems() {
		int xCollectable;
		int yCollectable;

		collectableArray = levelManager.getTomatoPos(level);
		solidsArray = levelManager.getSolidsPos(level);

		for (int i = 0; i < collectableArray.length; i++) {
			xCollectable = collectableArray[i][0];
			yCollectable = collectableArray[i][1];
			if (i % 2 == 0) {
				board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/earth_tomato.png \n");
			} else {
				board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/earth_onion.png \n");
			}
			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}
		for (int i = 0; i < solidsArray.length; i++) {
			xCollectable = solidsArray[i][0];
			yCollectable = solidsArray[i][1];
			if (i % 2 == 0) {
				board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/solid.png \n");
			} else {
				board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/solid.png \n");
			}
			symbol = board.getSymbol(xCollectable, yCollectable);
			symbol.getImageObject().setWorldWidth(0);
		}

	}

	public static void checkPosition(int posX, int posY) {
		int x = 999, y = 999;
		for (int i = 0; i < collectableArray.length; i++) {
			for (int j = 0; j < 2; j++) {
				x = collectableArray[i][0];
				y = collectableArray[i][1];
				if (x == posX && y == posY) {
					collectableArray[i][0] = 999;
					collectableArray[i][1] = 999;
					CollectPts(posX, posY);
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
					// blockMovement(posX, posY);
				}
			}
		}
		return fieldAvailable;
	}

	private static void CollectPts(int posX, int posY) {
		board.receiveMessage("image " + posX + " " + posY + " -\n");
		System.out.println("Match!");
		player.incPoints();
		collectableCounter--;
		System.out.println(collectableCounter);
		if (collectableCounter == 0) {
			player.setLevel(++level);
			player.resetCounter();
			collectableCounter = player.getPtCounter();
			StartGame.nextLevel();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// UP
		if (keyCode == 87 || keyCode == 38) {
			if (posY < boardSize - 1 && checkSolids(posX, posY + 1)) {
				++posY;
				board.receiveMessage("image " + posX + " " + (posY - 1) + " -\n");
			}
			// LEFT
		} else if (keyCode == 65 || keyCode == 37) {
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
			if (posX < boardSize - 1 && checkSolids(posX + 1, posY)) {
				++posX;
				board.receiveMessage("image " + (posX - 1) + " " + posY + " -\n");
			}
		}

		checkPosition(posX, posY);
		board.receiveMessage("image " + posX + " " + posY + " ./images/digger.png \n");
		symbol = board.getSymbol(posX, posY);
		symbol.getImageObject().setWorldWidth(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
