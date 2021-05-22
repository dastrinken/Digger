package mainGame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jserver.Board;
import jserver.Symbol;
import jserver.XSendAdapter;
import plotter.Graphic;
import java.awt.FlowLayout;

import javax.swing.SwingConstants;
import java.awt.Dimension;

/**
 * This program is a Remake of the original game "Digger" released in 1983
 * as part of a study project by Armin Prinz & Sebastian Ziegler
 * 
 * @author Armin Prinz
 * @author Sebastian Ziegler
 * @version 1.0
 * 
 */

public class StartGame extends Thread implements KeyListener {
	
	protected static Board board;
	protected static Graphic graphic;
	protected static XSendAdapter xsend;

	private int boardSize = 20;
	private int posX = boardSize - 1;
	private int posY = boardSize - 1;

	public static void main(String[] args) {
		//Neues Spiel starten. 
		StartGame digger = new StartGame();
		
		Player player = new Player();
		player.setLives(3);
		player.setPoints(0);
		
		digger.setUpBoard();
		digger.setUpCollectables();
		//TODO: Spiel fortsetzen und Highscore
	}

	public void setUpBoard() {
		// TODO: Level als Parameter übergeben und Board nach Abschluss eines Levels neu aufsetzen!
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
		
		JLabel livesDisplay = new JLabel("Leben");
		livesDisplay.setPreferredSize(new Dimension(190, 14));
		livesDisplay.setHorizontalAlignment(SwingConstants.LEFT);
		southPanel.add(livesDisplay);
		
		JLabel lvlDisplay = new JLabel("Level");
		lvlDisplay.setPreferredSize(new Dimension(99, 14));
		lvlDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		southPanel.add(lvlDisplay);
		
		JLabel ptDisplay = new JLabel("Punkte");
		ptDisplay.setPreferredSize(new Dimension(190, 14));
		ptDisplay.setVerticalAlignment(SwingConstants.BOTTOM);
		ptDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		southPanel.add(ptDisplay);
		
		graphic.addSouthComponent(southPanel);
	}
	
	public void setUpCollectables() {
		int xCollectable = 1000;
		int yCollectable = 1000;
		
		int[][] collectableArray = Collectable.CreateCollectables(boardSize);
		LevelManager.setCollectableArray(collectableArray);
		
		for (int i = 0; i < collectableArray.length; i++) {
			 for(int j = 0; j < 2; j++) {
				 if(j == 0) {
					 xCollectable = collectableArray[i][j];
				 }
				 else {
					 yCollectable = collectableArray[i][j];
					 board.receiveMessage("image " + xCollectable + " " + yCollectable + " ./images/erde_tomato.png \n");
				 }
			 }
		}
	}
	
	public static void nextLevel() {
		System.out.println("Gewonnen!");
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		//UP
		if (keyCode == 87 || keyCode == 38) {
			if (posY < boardSize - 1) {
				++posY;
				board.receiveMessage("image " + posX + " " + (posY - 1) + " -\n");
			}
		//LEFT
		} else if (keyCode == 65 || keyCode == 37) {
			if (posX > 0) {
				--posX;
				board.receiveMessage("image " + (posX + 1) + " " + posY + " -\n");
			}
		//DOWN
		} else if (keyCode == 83 || keyCode == 40) {
			if (posY > 0) {
				--posY;
				board.receiveMessage("image " + posX + " " + (posY + 1) + " -\n");
			}
		//RIGHT
		} else if (keyCode == 68 || keyCode == 39) {
			if (posX < boardSize - 1) {
				++posX;
				board.receiveMessage("image " + (posX - 1) + " " + posY + " -\n");
			}
		}
		
		LevelManager.checkPosition(posX, posY);
		board.receiveMessage("image " + posX + " " + posY + " ./images/digger_test.jpg \n");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
