package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class GameManager extends StartGame implements KeyListener {
	private static GameManager manager = new GameManager();
	// TODO: load player stats
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

	public int collectableCounter;
	protected static int[][] collectableArray;
	protected static int[][] solidsArray;
	protected static int[][] onionsArray;

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
		collectableCounter = player.getPtCounter();
		board = new Board();
		board.setSize(748, 780);

		graphic = board.getGraphic();
		graphic.setJMenuBar(getGameMenu());
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
		livesDisplay.setPreferredSize(new Dimension(190, 16));
		for (int i = 0; i < player.getLives(); i++) {
			livesDisplay.add(new JLabel(heartIcon));
		}
		southPanel.add(livesDisplay);

		lvlDisplay = new JLabel("Level " + player.getLevel());
		lvlDisplay.setFont(createCustomFont(13f));
		lvlDisplay.setPreferredSize(new Dimension(99, 18));
		lvlDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		southPanel.add(lvlDisplay);

		ptDisplay = new JLabel(player.getPoints() + " Points");
		ptDisplay.setFont(createCustomFont(9f));
		ptDisplay.setPreferredSize(new Dimension(190, 16));
		ptDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(ptDisplay);

		graphic.addSouthComponent(southPanel);

	}

	public JMenuBar getGameMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem load = new JMenuItem("Load");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphic.dispose();
				StartGame.loadSaveGame();
			}
		});
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(player);
			}
		});
		JMenuItem returnBtn = new JMenuItem("Return to menu");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphic.dispose();
				MainMenu.setFrame();
			}
		});
		JMenuItem close = new JMenuItem("Quit");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(load);
		file.add(save);
		file.add(returnBtn);
		file.add(close);
		JMenu help = new JMenu("Help");
		JMenuItem controls = new JMenuItem("Controls");
		JMenuItem blocks = new JMenuItem("Blocks");
		help.add(controls);
		help.add(blocks);
		menu.add(file);
		menu.add(help);
		return menu;
	}

	public void setUpBoard() {
		level = player.getLevel();
		collectableArray = LevelManager.getTomatoPos(level);
		solidsArray = LevelManager.getSolidsPos(level);
		onionsArray = LevelManager.getOnionsPos(level);

		player.resetCounter(level);
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

	/*
	 * public static void levelCompletedGui() { // graphic.setVisible(false);
	 * 
	 * JDialog chooseFrame = new JDialog(); chooseFrame.setUndecorated(true);
	 * chooseFrame.setAlwaysOnTop(true); chooseFrame.setResizable(false);
	 * chooseFrame.setSize(600, 64); chooseFrame.setLocationRelativeTo(null);
	 * chooseFrame.getContentPane().setLayout(new
	 * BoxLayout(chooseFrame.getContentPane(), BoxLayout.X_AXIS));
	 * 
	 * JButton quitSaveBtn = new JButton("Quit & Save");
	 * quitSaveBtn.setFont(createCustomFont(18f)); quitSaveBtn.setPreferredSize(new
	 * Dimension(300, 64)); quitSaveBtn.setMaximumSize(new Dimension(300, 64));
	 * quitSaveBtn.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { chooseFrame.dispose();
	 * MainMenu.mainFrame.setVisible(true); graphic.dispose(); } });
	 * chooseFrame.getContentPane().add(quitSaveBtn);
	 * 
	 * JButton nextLevelBtn = new JButton("Next Level");
	 * nextLevelBtn.setFont(createCustomFont(18f));
	 * nextLevelBtn.setPreferredSize(new Dimension(300, 64));
	 * nextLevelBtn.setMaximumSize(new Dimension(300, 64));
	 * nextLevelBtn.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { chooseFrame.dispose();
	 * StartGame.nextLevel(); } }); chooseFrame.getContentPane().add(nextLevelBtn);
	 * 
	 * chooseFrame.setVisible(true); }
	 */

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
				}
			}
		}
		return fieldAvailable;
	}

	private void CollectPts(int posX, int posY, String type) {
		level = player.getLevel();
		board.receiveMessage("image " + posX + " " + posY + " -\n");
		switch (type) {
		case "tomato":
			collectableCounter--;
			player.incPoints(1);
			break;
		case "onion":
			player.incPoints(5);
			break;
		}
		if (collectableCounter == 0) {
			player.setLevel(++level);
			player.resetCounter(player.getLevel());
			collectableCounter = player.getPtCounter();
			move = false;
			setUpBoard();
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

	public static void save(Player player) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.bin"))) {
			out.writeObject(player);
			System.out.println("Successfully saved game");
			System.out.println();
		} catch (Exception e) {
			System.out.println("Failed to save game");
			System.out.println();
		}
	}
}