package mainGame;

import java.io.File;
import javax.swing.JFrame;

public class HighScore {
	File highScoreFile = new File("highScore.txt");

	public HighScore() {
	}
	public void showHighScore() {
		JFrame highScoreFrame = new JFrame();
		
		highScoreFrame.setVisible(true);
	}
	public void getHighScore() {
		
	}
}
