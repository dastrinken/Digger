package mainGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class MenuManager extends GameManager {
	private static File saveFile = new File("savegame.bin");

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
				if(saveFile.exists() && !saveFile.isDirectory()){
			        System.out.println(saveFile + " exists");
					confirmSave();
			       }
				else {
					if(save(player)) {
						System.out.println("Successfully saved game");
					}
					else {
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
				if(save(player)) {
					saveConfirmFrame.dispose();
				}
				else {
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

}
