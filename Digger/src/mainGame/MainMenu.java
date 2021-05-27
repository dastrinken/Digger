package mainGame;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class MainMenu {
	final static ImageIcon background = new ImageIcon("./images/background.png");
	static Font customFontRegular = GameManager.createCustomFont(12);
	static Font customFontSmall = GameManager.createCustomFont(7);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setFrame();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void setFrame() {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(510, 310);
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setLayout(null);

		@SuppressWarnings("serial")
		JDesktopPane desktopPane = new JDesktopPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, this);
			}
		};
		desktopPane.setBounds(0, 0, 504, 281);
		mainFrame.getContentPane().add(desktopPane);
		
		JButton btnStart = new JButton("New Game");
		btnStart.setBackground(UIManager.getColor("Button.background"));
		btnStart.setToolTipText("Start a new Game at Lvl.1");
		btnStart.setFont(customFontRegular);
		btnStart.setBounds(new Rectangle(10, 0, 400, 75));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGame.main(null);
			}
		});
		btnStart.setBounds(50, 100, 400, 35);
		desktopPane.add(btnStart);
		
		JButton btnContinueGame = new JButton("Continue");
		btnContinueGame.setFont(customFontRegular);
		btnContinueGame.setToolTipText("Load your latest savegame");
		btnContinueGame.setBounds(50, 146, 400, 35);
		desktopPane.add(btnContinueGame);
		
		JButton btnHighscore = new JButton("Highscore");
		btnHighscore.setFont(customFontRegular);
		btnHighscore.setToolTipText("Show Highscore");
		btnHighscore.setBounds(50, 192, 400, 35);
		desktopPane.add(btnHighscore);
		
		JLabel lblNewLabel = new JLabel("Digger V0.1 - A project by Sebastian Z. & Armin P.");
		lblNewLabel.setFont(customFontSmall);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 246, 490, 14);
		desktopPane.add(lblNewLabel);
		
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
