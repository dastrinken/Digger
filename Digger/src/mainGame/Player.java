package mainGame;

public class Player {
	int points;
	int lives;
	String name;
	
	
	public Player() {
		super();
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
		System.out.println(points);
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
}
