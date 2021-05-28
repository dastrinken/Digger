package mainGame;

public class Player {
	int points;
	int lives;
	String name;
	int level = 1;
	static int ptCounter = 5;

	public Player(int lives, int points) {
		super();
		this.lives = lives;
		this.points = points;
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

	public static int getPtCounter() {
		return ptCounter;
	}

	public void incPoints() {
		++this.points;
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
			ptCounter = 5;
			break;
		}
		
	}
}
