package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jserver.Board;
import jserver.Symbol;
import jserver.XSendAdapter;
import plotter.Graphic;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Gegner generieren?

public class GameManager extends StartGame implements KeyListener {
	// TODO: Player dynamisch generieren lassen wenn "Spiel fortsetzen"!
	private static boolean move = true;

	public static Font customFont;
	public static JLabel ptDisplay;
	public JLabel lvlDisplay;
	public JPanel livesDisplay;
	public final ImageIcon heartIcon = new ImageIcon("./images/heart.png");

	protected static Board board;
	protected static Graphic graphic;
	private static XSendAdapter xsend;
	protected static Symbol symbol;

	public static final int boardSize = 20;
	private int posX;
	private int posY;

	public static int collectableCounter = Player.getPtCounter();
	protected static int[][] collectableArray;
	protected static int[][] solidsArray;

	public GameManager() {
		super();
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
	public void createBoard() {
		JMenuBar menu = new JMenuBar();
		// TODO: Neues Menü erstellen.
		board = new Board();
		board.setSize(770, 780);

		graphic = board.getGraphic();
		graphic.setJMenuBar(menu);
		graphic.setTitle("Digger V0.1 SoSe2021");
		graphic.addKeyListener(this);
		graphic.setResizable(false);

		xsend = new XSendAdapter(board);

		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(500, 25));
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		livesDisplay = new JPanel();
		livesDisplay.setLayout(new BoxLayout(livesDisplay, BoxLayout.X_AXIS));
		livesDisplay.setMaximumSize(new Dimension(190, 16));
		livesDisplay.setPreferredSize(new Dimension(190, 16));
		for (int i = 0; i < player.lives; i++) {
			livesDisplay.add(new JLabel(heartIcon));
		}
		southPanel.add(livesDisplay);
		
		lvlDisplay = new JLabel("Level " + player.level);
		lvlDisplay.setFont(createCustomFont(13f));
		lvlDisplay.setPreferredSize(new Dimension(99, 18));
		lvlDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		southPanel.add(lvlDisplay);

		ptDisplay = new JLabel(player.points + " Points");
		ptDisplay.setFont(createCustomFont(9f));
		ptDisplay.setPreferredSize(new Dimension(190, 16));
		ptDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(ptDisplay);

		graphic.addSouthComponent(southPanel);

	}

	public void setUpBoard(int level) {
		lvlDisplay.setText("Level " + String.valueOf(level));
		move = true;
		posX = boardSize - 1;
		posY = boardSize - 1;

		graphic.setLocationRelativeTo(null);
		graphic.setVisible(false);

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
		ItemManager.setUpItems(level);
		graphic.setVisible(true);
	}

	public static void cleanBoard() {
		board.reset();
		xsend.formen("none");
		xsend.groesse(0, 0);

		JFrame chooseFrame = new JFrame();
		chooseFrame.setUndecorated(true);
		chooseFrame.setAlwaysOnTop(true);
		chooseFrame.setResizable(false);
		chooseFrame.setSize(600, 64);
		chooseFrame.setLocationRelativeTo(null);
		chooseFrame.getContentPane().setLayout(new BoxLayout(chooseFrame.getContentPane(), BoxLayout.X_AXIS));

		JButton quitSaveBtn = new JButton("Quit & Save");
		quitSaveBtn.setFont(createCustomFont(18f));
		quitSaveBtn.setPreferredSize(new Dimension(300, 64));
		quitSaveBtn.setMaximumSize(new Dimension(300, 64));
		quitSaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFrame.dispose();
				MainMenu.main(null);
				graphic.dispose();
			}
		});
		chooseFrame.getContentPane().add(quitSaveBtn);

		JButton nextLevelBtn = new JButton("Next Level");
		nextLevelBtn.setFont(createCustomFont(18f));
		nextLevelBtn.setPreferredSize(new Dimension(300, 64));
		nextLevelBtn.setMaximumSize(new Dimension(300, 64));
		nextLevelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFrame.dispose();
				StartGame.nextLevel();
			}
		});
		chooseFrame.getContentPane().add(nextLevelBtn);

		chooseFrame.setVisible(true);
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
				}
			}
		}
		return fieldAvailable;
	}

	private static void CollectPts(int posX, int posY) {
		board.receiveMessage("image " + posX + " " + posY + " -\n");
		player.incPoints();
		collectableCounter--;
		// All items collected, disabling movement, resetting counter, increasing level
		// etc.:
		if (collectableCounter == 0) {
			player.setLevel(++player.level);
			player.resetCounter(player.level);
			collectableCounter = Player.getPtCounter();
			move = false;
			cleanBoard();
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
