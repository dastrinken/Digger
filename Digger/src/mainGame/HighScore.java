package mainGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class HighScore {
	static File highScoreFile = new File("highScore.txt");
	private static JTable scoreTable;
	private static String[] readInData;
	private static String[][] displayDataArray;
	private static ArrayList<String> dataList = new ArrayList<String>();
	private static boolean sorted = false;

	public HighScore() {
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("serial")
	public static void showHighScore() {
		if(sorted == false) {
			getHighScoreData();
			String[] sortedData = bSortData();
			displayDataArray = new String[sortedData.length][2];
			for(int i = 0; i < sortedData.length; i++) {
				String[] splitLine = sortedData[i].split("-x-");
				displayDataArray[i][0] = splitLine[0];
				displayDataArray[i][1] = splitLine[1];
			}
			sorted = true;
		}

		String[] title = new String[] { "Name", "Punkte" };

		JFrame highScoreFrame = new JFrame();
		highScoreFrame.setTitle("Tomatensalat - Highscore");
		highScoreFrame.setSize(400, 300);
		highScoreFrame.setIconImage(MainMenu.icon.getImage());
		scoreTable = new JTable(displayDataArray, title) {
            public boolean isCellEditable(int x, int y) {
                return false;
            }
        };
		JScrollPane scrollPane = new JScrollPane(scoreTable);
		highScoreFrame.getContentPane().add(scrollPane);
		highScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		highScoreFrame.setLocationRelativeTo(null);
		highScoreFrame.setAlwaysOnTop(true);
		
		highScoreFrame.setFont(MainMenu.customFontSmall);
		scoreTable.getTableHeader().setFont(MainMenu.customFontSmall);
		scoreTable.setFont(MainMenu.customFontSmall);
		
		highScoreFrame.setResizable(false);
		highScoreFrame.setVisible(true);

	}
	//read-in file
	public static void getHighScoreData() {
		if (!highScoreFile.canRead() || !highScoreFile.isFile())
			System.exit(0);
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
	//Bubblesort
	public static String[] bSortData() {
		String k;
		for (int i = 0; i < readInData.length - 1; i++) {
			if (splitInt(readInData[i]) > splitInt(readInData[i + 1])) {
				continue;
			}
			k = readInData[i];
			readInData[i] = readInData[i + 1];
			readInData[i + 1] = k;
			bSortData();
		}
		return readInData;
	}
	//Split int values for bubblesort
	public static int splitInt(String data) {
		String[] splitContent = data.split("-x-");
		int splitInteger = Integer.valueOf(splitContent[1]);
		return splitInteger;
	}
}
