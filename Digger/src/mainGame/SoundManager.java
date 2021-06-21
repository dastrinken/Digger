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
			setMusicVolume(.1f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void crunchyBite() {
		URL url;
		try {
			url = new File("sounds/crunchy_bite.wav").toURI().toURL();
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

	public static void sparkleCollect() {
		URL url;
		try {
			url = new File("sounds/sparkle.wav").toURI().toURL();
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

	public static void stoneHit() {
		URL url;
		try {
			url = new File("sounds/stone_hit.wav").toURI().toURL();
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

	public static void lvlUp() {
		URL url;
		try {
			url = new File("sounds/lvl_up.wav").toURI().toURL();
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

	public static void dmg() {
		URL url;
		try {
			url = new File("sounds/dmg.wav").toURI().toURL();
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

	public static void gameOver() {
		URL url;
		try {
			url = new File("sounds/gameOver.wav").toURI().toURL();
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
}
