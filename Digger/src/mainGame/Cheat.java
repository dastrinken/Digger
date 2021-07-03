package mainGame;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import npcOrganizer.NpcManager;

public class Cheat {
	private JTextField lvlField;
	private JTextField livesField;
	private JTextField ptsField;

	private int level, pts, lives;

	public void cheat() {
		chooseAll();
	}

	public void startLevel() {
		GameManager cheatManager;
		StartGame.player = new Player(lives, pts, level);
		cheatManager = GameManager.getInstance();
		StartGame.npc = NpcManager.getInstance();
		cheatManager.createBoard(StartGame.player);
		GameManager.setUpBoard();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void chooseAll() {
		JFrame chooseFrame = new JFrame();
		chooseFrame.setTitle("Levelchooser (Cheat)");
		chooseFrame.setSize(200, 250);
		chooseFrame.getContentPane().setLayout(null);

		lvlField = new JTextField();
		lvlField.setHorizontalAlignment(SwingConstants.CENTER);
		lvlField.setBounds(107, 11, 58, 26);
		chooseFrame.getContentPane().add(lvlField);
		lvlField.setColumns(10);

		JLabel lvlLbl = new JLabel("Level (int):");
		lvlLbl.setLabelFor(lvlField);
		lvlLbl.setBounds(10, 11, 87, 26);
		chooseFrame.getContentPane().add(lvlLbl);

		JLabel lifesLbl = new JLabel("Lives (int):");
		lifesLbl.setBounds(10, 42, 87, 26);
		chooseFrame.getContentPane().add(lifesLbl);

		livesField = new JTextField();
		livesField.setHorizontalAlignment(SwingConstants.CENTER);
		livesField.setText("2");
		lifesLbl.setLabelFor(livesField);
		livesField.setBounds(107, 42, 58, 26);
		chooseFrame.getContentPane().add(livesField);
		livesField.setColumns(10);

		JLabel ptsLbl = new JLabel("Points (int):");
		ptsLbl.setBounds(10, 73, 87, 26);
		chooseFrame.getContentPane().add(ptsLbl);

		ptsField = new JTextField();
		ptsLbl.setLabelFor(ptsField);
		ptsField.setHorizontalAlignment(SwingConstants.CENTER);
		ptsField.setText("0");
		ptsField.setBounds(107, 73, 58, 26);
		chooseFrame.getContentPane().add(ptsField);
		ptsField.setColumns(10);

		JButton startBtn = new JButton("Start!");
		startBtn.setBounds(10, 176, 155, 33);
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level = Integer.valueOf(lvlField.getText());
				pts = Integer.valueOf(ptsField.getText());
				lives = Integer.valueOf(livesField.getText());
				startLevel();
				chooseFrame.setVisible(false);
			}
		});
		chooseFrame.getContentPane().add(startBtn);
		
		JLabel cautionLbl = new JLabel("<html>This is for testing purposes.<br/>Don't expect anything to work properly.</html>");
		cautionLbl.setVerticalAlignment(SwingConstants.TOP);
		cautionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cautionLbl.setBounds(10, 110, 155, 55);
		chooseFrame.getContentPane().add(cautionLbl);

		chooseFrame.setLocationRelativeTo(null);
		chooseFrame.setAlwaysOnTop(true);
		chooseFrame.setVisible(true);
	}
}
