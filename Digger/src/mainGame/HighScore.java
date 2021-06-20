package mainGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class HighScore {
	static File highScoreFile = new File("./highScore.txt");
	private static JTable scoreTable;
	private static String[] readInData;
	private static String[][] displayDataArray;

	public HighScore() {
	}

	@SuppressWarnings("serial")
	public static void showHighScore() {
		getHighScoreData();
		String[] sortedData = bSortData();
		
		displayDataArray = new String[sortedData.length][2];
		for (int i = 0; i < sortedData.length; i++) {
			String[] splitLine = sortedData[i].split(";");
			displayDataArray[i][0] = splitLine[0];
			displayDataArray[i][1] = splitLine[1];
		}

		String[] title = new String[] { "Name", "Punkte" };

		JFrame highScoreFrame = new JFrame();
		highScoreFrame.setTitle("Tomatensalat - Highscore");
		highScoreFrame.setIconImage(MainMenu.icon.getImage());
		scoreTable = new JTable(displayDataArray, title) {
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		JScrollPane scrollPane = new JScrollPane(scoreTable);
		highScoreFrame.getContentPane().add(scrollPane);
		highScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		highScoreFrame.setFont(MainMenu.customFontSmall);
		scoreTable.getTableHeader().setFont(MainMenu.customFontSmall);
		scoreTable.setFont(MainMenu.customFontSmall);

		highScoreFrame.setSize(300, 400);
		highScoreFrame.setLocationRelativeTo(null);
		highScoreFrame.setAlwaysOnTop(true);
		highScoreFrame.setResizable(false);
		highScoreFrame.setVisible(true);

	}

	// read-in file
	public static void getHighScoreData() {
		ArrayList<String> dataList = new ArrayList<String>();
		if (!highScoreFile.canRead() || !highScoreFile.isFile()) {
			JDialog errorHscore = new JDialog();
			errorHscore.setIconImage(MainMenu.icon.getImage());
			JLabel errorHscLbl = new JLabel("There is no Highscore yet!", SwingConstants.CENTER);
			errorHscLbl.setFont(MainMenu.customFontSmall);
			errorHscore.add(errorHscLbl);
			errorHscore.setSize(300, 100);
			errorHscore.setLocationRelativeTo(null);
			errorHscore.setAlwaysOnTop(true);
			errorHscore.setVisible(true);
		} else {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(highScoreFile));
				String zeile = null;
				while ((zeile = in.readLine()) != null) {
					dataList.add(zeile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

			readInData = new String[dataList.size()];
			dataList.toArray(readInData);
		}
	}

	// Bubblesort
	public static String[] bSortData() {
		String k;
		for (int i = 0; i < readInData.length - 1; i++) {
			if (splitInt(readInData[i]) > splitInt(readInData[i + 1])
					|| splitInt(readInData[i]) == splitInt(readInData[i + 1])) {
				continue;
			}
			k = readInData[i];
			readInData[i] = readInData[i + 1];
			readInData[i + 1] = k;
			bSortData();
		}
		return readInData;
	}

	// Split int values for bubblesort
	public static int splitInt(String data) {
		String[] splitContent = data.split(";");
		int splitInteger = Integer.valueOf(splitContent[1]);
		return splitInteger;
	}
}
