package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jserver.Board;
import jserver.Symbol;
import jserver.XSendAdapter;
import plotter.Graphic;

// TODO: Unbewegliche Objekte (solids) einfügen,
// TODO: Gegner generieren?

public class GameManager implements KeyListener {
	// TODO: Player dynamisch generieren lassen wenn "Spiel fortsetzen"!
	private int level;
	private static int lives = 3;
	private static int points = 0;
	private static Player player = new Player(lives, points);
	public static JLabel ptDisplay;
	public JLabel lvlDisplay;
	public JLabel livesDisplay;
	
	private static int[][] collectableArray;
	private static int collectableCounter = 5;
	
	private static Board board;
	private static Graphic graphic;
	private static XSendAdapter xsend;

	private int boardSize = 20;
	private int posX = boardSize - 1;
	private int posY = boardSize - 1;
	
	public GameManager(int level) {
		super();
		this.level = level;
	}

	public static void updatePoints(int points) {
		ptDisplay.setText(String.valueOf(points)+" Points");
	}

	public static int[][] getCollectableArray() {
		return collectableArray;
	}

	public static void setCollectableArray(int[][] getCollectableArray) {
		GameManager.collectableArray = getCollectableArray;
	}

	public void setUpBoard() {
		// TODO: Level als Parameter übergeben und Board nach Abschluss eines Levels neu
		// aufsetzen!
		/*
		 * Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();
		 * int screenWidth = (int) screensize.getWidth() / 2; int screenHeight = (int)
		 * screensize.getHeight() / 2;
		 */
		board = new Board();
		board.setSize(725, 590);

		Symbol symbol;

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
		board.receiveMessage("image " + posX + " " + posY + " ./images/digger_test.jpg \n");

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (i != posX || j != posY) {
					board.receiveMessage("image " + i + " " + j + " ./images/erde_test.png \n");
					symbol = board.getSymbol(i, j);
					symbol.getImageObject().setWorldWidth(0);
				}
			}
		}

		graphic.setVisible(true);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(500, 17));
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		livesDisplay = new JLabel(player.lives+" Lives");
		livesDisplay.setPreferredSize(new Dimension(190, 14));
		livesDisplay.setHorizontalAlignment(SwingConstants.LEFT);
		southPanel.add(livesDisplay);

		lvlDisplay = new JLabel("Level "+this.level);
		lvlDisplay.setPreferredSize(new Dimension(99, 14));
		lvlDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		southPanel.add(lvlDisplay);

		ptDisplay = new JLabel(player.points+" Points");
		ptDisplay.setPreferredSize(new Dimension(190, 14));
		ptDisplay.setVerticalAlignment(SwingConstants.BOTTOM);
		ptDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(ptDisplay);

		graphic.addSouthComponent(southPanel);
	}
	
	public void setUpItems() {
		int xCollectable;
		int yCollectable;

		int[][] collectableArray = Items.CreateCollectables(boardSize);
		GameManager.setCollectableArray(collectableArray);
		
		for (int i = 0; i < collectableArray.length; i++) {
			xCollectable = collectableArray[i][0];
			yCollectable = collectableArray[i][1];
			board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/erde_tomato.png \n");
		}
		
		int[][] solidArray = Items.CreateSolids(boardSize, collectableArray);
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

	private static void CollectPts(int posX, int posY) {
		// TODO: Collectables zählen und nächste Level starten, wenn alle eingesammelt.
		// TODO: Punkte hochzählen und im Panel ausgeben
		// Eventuell lieber mit ArrayList statt Array arbeiten?
		board.receiveMessage("image " + posX + " " + posY + " -\n");
		System.out.println("Match!");
		player.incPoints();

		collectableCounter--;
		if (collectableCounter == 0) {
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
			if (posY < boardSize - 1) {
				++posY;
				board.receiveMessage("image " + posX + " " + (posY - 1) + " -\n");
			}
			// LEFT
		} else if (keyCode == 65 || keyCode == 37) {
			if (posX > 0) {
				--posX;
				board.receiveMessage("image " + (posX + 1) + " " + posY + " -\n");
			}
			// DOWN
		} else if (keyCode == 83 || keyCode == 40) {
			if (posY > 0) {
				--posY;
				board.receiveMessage("image " + posX + " " + (posY + 1) + " -\n");
			}
			// RIGHT
		} else if (keyCode == 68 || keyCode == 39) {
			if (posX < boardSize - 1) {
				++posX;
				board.receiveMessage("image " + (posX - 1) + " " + posY + " -\n");
			}
		}

		GameManager.checkPosition(posX, posY);
		board.receiveMessage("image " + posX + " " + posY + " ./images/digger_test.jpg \n");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
