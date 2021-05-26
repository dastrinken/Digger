package mainGame;

public class Player {
	int points;
	int lives;
	String name;
	
	public Player(int lives, int points) {
		super();
		this.lives = lives;
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
		System.out.println(points);
	}
	
	public void incPoints() {
		++this.points;
		GameManager.updatePoints(this.points);
	}
}
