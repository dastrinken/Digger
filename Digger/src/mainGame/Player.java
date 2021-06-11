package mainGame;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable {
	private int points;
	private int lives;
	private String name;
	private int level = 1;
	private int ptCounter;

	public Player(int lives, int points, int level) {
		super();
		this.lives = lives;
		this.points = points;
		this.level = level;
		resetCounter(this.level);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPtCounter() {
		return ptCounter;
	}

	public void incPoints(int amount) {
		this.points += amount;
		GameManager.updatePoints(this.points);
	}

	public void resetCounter(int level) {
		switch (level) {
		case 1:
			ptCounter = 5;
			break;
		case 2:
			ptCounter = 6;
			break;
		default:
			ptCounter = level+4;
			break;
		}
		
	}
}