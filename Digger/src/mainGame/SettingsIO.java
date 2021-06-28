package mainGame;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SettingsIO {
	private static File ini = new File("./digger.properties");
	private static Properties props = new Properties();

	public static void collectProperties() {
		if(ini.exists() && !ini.isDirectory()) {
			try {
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(ini));
				props.load(in);
				in.close();
				
				String music = props.getProperty("music");
				String musicValue = props.getProperty("musicValue");
				String sound = props.getProperty("sound");
				String soundValue = props.getProperty("soundValue");
				
				SoundManager.musicOn = Boolean.valueOf(music);
				SoundManager.soundsOn = Boolean.valueOf(sound);
				SoundManager.musicSliderValue = Integer.valueOf(musicValue);
				SoundManager.soundsSliderValue = Integer.valueOf(soundValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//creates new .properties file with standard settings
			String settingsData = "# General Settings\nmusic = true\nmusicValue = 30\nsound = true\nsoundValue = 80";
			try {
				FileWriter write = new FileWriter(ini);
				write.write(settingsData);
				write.close();
				collectProperties();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void saveProperties(String key, String value) {
		props.setProperty(key, value);
		try {
			props.store(new FileWriter(ini), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
