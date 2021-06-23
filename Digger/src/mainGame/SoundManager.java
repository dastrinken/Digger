package mainGame;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager extends GameManager {

	public static void setMusicVolume(float volume) {
		if (volume < 0f || volume > 1f)
			throw new IllegalArgumentException("Music volume not valid: " + volume);
		FloatControl gainControl = (FloatControl) bgMusic.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(10f * (float) Math.log10(volume));
	}

	public static void setSoundsVolume(float volume) {
		if (volume < 0f || volume > 1f)
			throw new IllegalArgumentException("Sounds volume not valid: " + volume);
		FloatControl gainControl = (FloatControl) soundEffect.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(10f * (float) Math.log10(volume));
	}
	
	public static void menuBtnSound() {
		URL url;
		try {
			url = new File("sounds/menuBtn.wav").toURI().toURL();
			soundEffect = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			soundEffect.open(ais);
			soundEffect.loop(0);
			setSoundsVolume(soundsVolume);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playMusic() {
		URL url;
		try {
			url = new File("sounds/tomatensalat.wav").toURI().toURL();
			bgMusic = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			bgMusic.open(ais);
			bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
			// TODO: save/load settings to/from .ini file?
			setMusicVolume(.05f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void playSound(String soundSource) {
		URL url;
		String location = null;
		switch(soundSource) {
		case "tomato":
			location = "./sounds/crunchy_bite.wav";
			break;
		case "onion":
			location = "./sounds/sparkle.wav";
			break;
		case "frost":
			location = "./sounds/water_splash.wav";
			break;
		case "stone":
			location = "./sounds/stone_hit.wav";
			break;
		case "dmg":
			location = "./sounds/dmg.wav";
			break;
		case "life":
			location = "./sounds/extra_life.wav";
			break;
		case "lvlUp":
			location = "./sounds/lvl_up.wav";
			break;
		case "gameOver":
			location = "./sounds/gameOver.wav";
			break;
		}
		try {
			url = new File(location).toURI().toURL();
			soundEffect = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			soundEffect.open(ais);
			soundEffect.loop(0);
			setSoundsVolume(soundsVolume);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
