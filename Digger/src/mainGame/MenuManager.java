package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import npcOrganizer.NpcManager;

import javax.swing.JTextField;

public class MenuManager extends GameManager {
	public static ImageIcon icon = new ImageIcon("./images/solid.png");
	private static File saveFile = new File("savegame.bin");
	private static File highScore = new File("highScore.txt");
	private static JCheckBox musicCheckbox;
	private static JSlider musicVolumeSlider;
	private static JSlider soundsVolumeSlider;
	private static JCheckBox soundsCheckbox;
	private static JTextField nameField;

	public static JMenuBar getGameMenu() {
		JMenuBar menu = new JMenuBar();

		JMenu file = getFileMenu();
		JMenu help = getHelpMenu();

		menu.add(file);
		menu.add(help);
		return menu;
	}

	public static JMenu getFileMenu() {
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
				if (saveFile.exists() && !saveFile.isDirectory()) {
					System.out.println(saveFile + " exists");
					confirmSave();
				} else {
					if (save()) {
						System.out.println("Successfully saved game");
					} else {
						JDialog errorDialog = new JDialog();
						errorDialog.setSize(200, 150);
						errorDialog.setAlwaysOnTop(true);

						JLabel errorMsg = new JLabel("Error while saving game.");
						errorMsg.setPreferredSize(new Dimension(180, 45));
						errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
						errorDialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

						JButton confirmBtn = new JButton("O.K.");
						confirmBtn.setMaximumSize(new Dimension(50, 23));
						confirmBtn.setMinimumSize(new Dimension(50, 50));
						confirmBtn.setPreferredSize(new Dimension(150, 50));
						confirmBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								errorDialog.dispose();
							}
						});

						errorDialog.getContentPane().add(errorMsg);
						errorDialog.getContentPane().add(confirmBtn);
						errorDialog.setLocationRelativeTo(null);
						errorDialog.setVisible(true);
					}
				}
			}
		});
		JMenuItem optionsBtn = new JMenuItem("Settings");
		optionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOptionsMenu();
			}
		});
		JMenuItem returnBtn = new JMenuItem("Main menu");
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
		file.add(optionsBtn);
		file.add(returnBtn);
		file.add(close);
		
		return file;
	}

	public static JMenu getHelpMenu() {
		JMenu help = new JMenu("Help");

		JMenuItem restart = new JMenuItem("Restart Level");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUpBoard();
			}
		});

		JMenuItem controls = new JMenuItem("Controls");
		controls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame helpControls = new JFrame();
				helpControls.setIconImage(icon.getImage());
				helpControls.setContentPane(new JLabel(new ImageIcon("./images/helpMenuMovement.png")));
				helpControls.setSize(510, 210);
				helpControls.setResizable(false);
				helpControls.setLocationRelativeTo(null);
				helpControls.setVisible(true);
			}
		});
		
		JMenuItem blocks = new JMenuItem("Blocks");
		blocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame helpBlockFrame = new JFrame();
				JLabel blockInfo = new JLabel(new ImageIcon("./images/helpBlocks.jpg"));
				JScrollPane scrollPane = new JScrollPane(blockInfo);
				helpBlockFrame.getContentPane().add(scrollPane);
				helpBlockFrame.setIconImage(icon.getImage());
				helpBlockFrame.setSize(537, 543);
				helpBlockFrame.setResizable(false);
				helpBlockFrame.setLocationRelativeTo(null);
				helpBlockFrame.setVisible(true);
			}
		});

		JMenuItem highScoreBtn = new JMenuItem("Show Highscore");
		highScoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighScore.showHighScore();
			}
		});

		help.add(restart);
		help.add(controls);
		help.add(blocks);
		help.add(highScoreBtn);
		
		return help;
	}
	
	public static void confirmSave() {
		JFrame saveConfirmFrame = new JFrame();
		saveConfirmFrame.setSize(375, 60);
		saveConfirmFrame.setUndecorated(true);
		saveConfirmFrame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel saveConfirmText = new JLabel("Overwrite old savegame file?");
		saveConfirmText.setPreferredSize(new Dimension(139, 50));
		saveConfirmText.setMaximumSize(new Dimension(139, 50));
		saveConfirmText.setHorizontalAlignment(SwingConstants.CENTER);
		saveConfirmFrame.getContentPane().add(saveConfirmText);

		JButton yesBtn = new JButton("Yes");
		yesBtn.setPreferredSize(new Dimension(100, 50));
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (save()) {
					saveConfirmFrame.dispose();
				} else {
					JDialog errorDialog = new JDialog();
					errorDialog.setSize(200, 150);
					errorDialog.setAlwaysOnTop(true);

					JLabel errorMsg = new JLabel("Error while saving game.");
					errorMsg.setPreferredSize(new Dimension(180, 45));
					errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
					errorDialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

					JButton confirmBtn = new JButton("O.K.");
					confirmBtn.setMaximumSize(new Dimension(50, 23));
					confirmBtn.setMinimumSize(new Dimension(50, 50));
					confirmBtn.setPreferredSize(new Dimension(150, 50));
					confirmBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							errorDialog.dispose();
						}
					});

					errorDialog.getContentPane().add(errorMsg);
					errorDialog.getContentPane().add(confirmBtn);
					errorDialog.setLocationRelativeTo(null);
					errorDialog.setVisible(true);
				}
			}
		});
		saveConfirmFrame.getContentPane().add(yesBtn);

		JButton noBtn = new JButton("No");
		noBtn.setPreferredSize(new Dimension(100, 50));
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveConfirmFrame.dispose();
			}
		});
		saveConfirmFrame.getContentPane().add(noBtn);

		saveConfirmFrame.setVisible(true);
		saveConfirmFrame.setLocationRelativeTo(null);
		saveConfirmFrame.setAlwaysOnTop(true);
	}

	public static void showOptionsMenu() {
		JFrame optionFrame = new JFrame();
		optionFrame.setSize(450, 295);
		optionFrame.setTitle("Digger - Options");
		optionFrame.getContentPane().setLayout(null);

		musicCheckbox = new JCheckBox("Enabled");
		musicCheckbox.setSelected(musicOn);
		musicCheckbox.setBounds(106, 7, 97, 26);
		optionFrame.getContentPane().add(musicCheckbox);

		musicVolumeSlider = new JSlider(0, 100, musicSliderValue);
		musicVolumeSlider.setBounds(224, 7, 200, 26);
		optionFrame.getContentPane().add(musicVolumeSlider);

		JLabel musicLabel = new JLabel("Music");
		musicLabel.setBounds(23, 7, 46, 26);
		optionFrame.getContentPane().add(musicLabel);

		JLabel soundLabel = new JLabel("Sounds");
		soundLabel.setBounds(23, 44, 46, 26);
		optionFrame.getContentPane().add(soundLabel);

		soundsCheckbox = new JCheckBox("Enabled");
		soundsCheckbox.setSelected(soundsOn);
		soundsCheckbox.setBounds(106, 43, 97, 26);
		optionFrame.getContentPane().add(soundsCheckbox);

		soundsVolumeSlider = new JSlider(0, 100, soundsSliderValue);
		soundsVolumeSlider.setBounds(224, 44, 200, 26);
		optionFrame.getContentPane().add(soundsVolumeSlider);

		JButton saveBtn = new JButton("Apply");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// saving variables
				musicOn = musicCheckbox.isSelected();
				soundsOn = soundsCheckbox.isSelected();
				musicSliderValue = musicVolumeSlider.getValue();
				soundsSliderValue = soundsVolumeSlider.getValue();

				// applying user settings
				if (musicCheckbox.isSelected()) {
					float volume = (float) musicVolumeSlider.getValue();
					SoundManager.setMusicVolume(volume / 1000);
				} else {
					SoundManager.setMusicVolume(0);
				}
				if (soundsCheckbox.isSelected()) {
					float volume = (float) soundsVolumeSlider.getValue();
					soundsVolume = volume / 1000;
				} else {
					soundsVolume = 0;
				}
			}
		});
		saveBtn.setBounds(10, 216, 414, 34);
		optionFrame.getContentPane().add(saveBtn);

		optionFrame.setAlwaysOnTop(true);
		optionFrame.setLocationRelativeTo(null);
		optionFrame.setVisible(true);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void getGameOverMenu(int level) {
		NpcManager.stopPepper();
		String hintString;
		gameOver.setSize(500, 200);
		gameOver.getContentPane().setLayout(null);
		gameOver.setUndecorated(true);

		JLabel gameOverLbl = new JLabel("Game Over :(");
		gameOverLbl.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLbl.setBounds(114, 11, 282, 23);
		gameOverLbl.setFont(createCustomFont(13f));

		JButton gameOverBtn = new JButton("Save Highscore");
		gameOverBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.setName(nameField.getText());
				if (highScore.exists() && !highScore.isDirectory()) {
					saveHighScore();
				} else {
					File highScore = new File("highScore.txt");
					try {
						if (highScore.createNewFile()) {
							saveHighScore();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		gameOverBtn.setBounds(114, 159, 181, 30);

		gameOver.getContentPane().add(gameOverLbl);
		gameOver.getContentPane().add(gameOverBtn);

		switch (level) {
		case 1:
			hintString = "How is it possible to lose in Lvl 1?";
			break;
		case 3:
			hintString = "Hint: The floor is lava!";
			break;
		default:
			hintString = "<html><body style=\"text-align:center; margin: auto;\"><p><u>Hint:</u><br/><br/>Some blocks afflict damage.<br/>Try to avoid those, okay?</p></body></html>";
			break;
		}

		JLabel hintLbl = new JLabel(hintString);
		hintLbl.setHorizontalAlignment(SwingConstants.CENTER);
		hintLbl.setBounds(10, 36, 480, 96);
		hintLbl.setFont(createCustomFont(9f));
		gameOver.getContentPane().add(hintLbl);

		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setText("Enter your name");
		nameField.setBounds(114, 135, 282, 25);
		gameOver.getContentPane().add(nameField);
		nameField.setColumns(10);

		JButton quitBtn = new JButton("Quit");
		quitBtn.setBounds(305, 159, 89, 30);
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphic.dispose();
				gameOver.dispose();
				MainMenu.setFrame();
			}
		});
		gameOver.getContentPane().add(quitBtn);

		gameOver.setResizable(false);
		gameOver.setAlwaysOnTop(true);
		gameOver.setLocationRelativeTo(null);
		gameOver.setVisible(true);
	}
}
