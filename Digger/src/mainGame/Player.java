package mainGame;

public class Player {
	int points;
	int lives;
	String name;
	int level = 1;
	int ptCounter = 5;

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
		System.out.println(points);
	}

	public int getPtCounter() {
		return ptCounter;
	}

	public void setPtCounter(int ptCounter) {
		this.ptCounter = ptCounter;
	}

	public void incPoints() {
		++this.points;
		GameManager.updatePoints(this.points);
	}

	public void resetCounter() {
		ptCounter = 5;
		/* TODO: Level und Counter fest definieren! zb: switch-case?
		 * Eventuell Level mit als Parameter übergeben?
		switch (this.level) {
		case 1:
			counter = 5;
			break;
		case 2:
			counter = 10;
			break;
		}
		*/
	}
}
