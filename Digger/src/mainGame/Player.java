package mainGame;

public class Player {
	int points;
	int lives;
	String name;
	int level = 1;
	int ptCounter;

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