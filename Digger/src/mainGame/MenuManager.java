package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class MenuManager extends GameManager {
	public static ImageIcon icon = new ImageIcon("./images/solid.png");
	private static File saveFile = new File("savegame.bin");
	private static JCheckBox musicCheckbox;
	private static JSlider musicVolumeSlider;
	private static JSlider soundsVolumeSlider;
	private static JCheckBox soundsCheckbox;

	public static JMenuBar getGameMenu() {
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
				if (saveFile.exists() && !saveFile.isDirectory()) {
					System.out.println(saveFile + " exists");
					confirmSave();
				} else {
					if (save(player)) {
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

		JMenu help = new JMenu("Help");
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
		help.add(controls);
		help.add(blocks);

		menu.add(file);
		menu.add(help);
		return menu;
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
				if (save(player)) {
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
				//saving variables for better usability
				musicOn = musicCheckbox.isSelected();
				soundsOn = soundsCheckbox.isSelected();
				musicSliderValue = musicVolumeSlider.getValue();
				soundsSliderValue = soundsVolumeSlider.getValue();
				
				//applying user settings
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
}
