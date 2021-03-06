package mainGame;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * This program is a Remake of the original game "Digger" released in 1983 as
 * part of a study project by Armin Prinz & Sebastian Ziegler.
 * 
 * @author Armin Prinz
 * @author Sebastian Ziegler
 * @version 1.0
 * 
 */

public class MainMenu {
	/**
	 * Entry point, main method starts the game menu.
	 */
	public static JFrame mainFrame;
	
	public static ImageIcon icon = new ImageIcon("./images/icon.png");
	static ImageIcon background = new ImageIcon("./images/background.jpg");
	static Font customFontRegular = GameManager.createCustomFont(12f);
	static Font customFontSmall = GameManager.createCustomFont(7.5f);
	
	private static int cowLvlEnabler;

	public static void main(String[] args) {
		SoundManager sounds = new SoundManager();
		SettingsIO.collectProperties();
		sounds.startUp();
		setFrame();
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void setFrame() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		mainFrame = new JFrame();
		mainFrame.setIconImage(icon.getImage());
		mainFrame.setTitle("Digger - Tomatensalat");
		
		mainFrame.setSize(510, 312);
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setLayout(null);

		@SuppressWarnings("serial")
		JDesktopPane desktopPane = new JDesktopPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, this);
			}
		};
		desktopPane.setBounds(0, 0, 510, 312);
		mainFrame.getContentPane().add(desktopPane);
		
		JButton btnStart = new JButton("New Game");
		btnStart.setBackground(UIManager.getColor("Button.background"));
		btnStart.setToolTipText("Start a new Game at Lvl.1");
		btnStart.setFont(customFontRegular);
		btnStart.setBounds(new Rectangle(10, 0, 400, 75));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGame.newGame();
				mainFrame.setVisible(false);
			}
		});
		btnStart.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				SoundManager.menuBtnSound();
			}
		});
		btnStart.setBounds(50, 103, 400, 35);
		desktopPane.add(btnStart);
		
		JButton btnContinueGame = new JButton("Continue");
		btnContinueGame.setFont(customFontRegular);
		btnContinueGame.setToolTipText("Load your latest savegame");
		btnContinueGame.setBounds(50, 149, 400, 35);
		btnContinueGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				StartGame.loadSaveGame();
			}
		});
		btnContinueGame.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				SoundManager.menuBtnSound();
			}
		});
		desktopPane.add(btnContinueGame);
		
		JButton btnHighscore = new JButton("Highscore");
		btnHighscore.setFont(customFontRegular);
		btnHighscore.setToolTipText("Show Highscore");
		btnHighscore.setBounds(50, 195, 194, 35);
		btnHighscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighScore.showHighScore();
			}
		});
		btnHighscore.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				SoundManager.menuBtnSound();
			}
		});
		desktopPane.add(btnHighscore);

		JButton optionsBtn = new JButton("Settings");
		optionsBtn.setBounds(256, 195, 194, 35);
		optionsBtn.setFont(customFontRegular);
		optionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuManager.showOptionsMenu();
			}
		});
		optionsBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				SoundManager.menuBtnSound();
			}
		});
		desktopPane.add(optionsBtn);
		
		JLabel projectLabel = new JLabel("Digger V1.0 - A project by Sebastian Z. & Armin P.");
		projectLabel.setFont(customFontSmall);
		projectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		projectLabel.setBounds(10, 238, 490, 48);
		desktopPane.add(projectLabel);
		
		JButton cheatBtn = new JButton("?");
		cheatBtn.setBounds(463, 251, 37, 23);
		cheatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cowLvlEnabler++;
				if(cowLvlEnabler == 1) {
					StartGame.cheat();
				}
			}
		});
		desktopPane.add(cheatBtn);
		
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}