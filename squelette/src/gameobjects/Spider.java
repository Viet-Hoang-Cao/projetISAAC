package gameobjects;
import libraries.StdDraw;
import libraries.Vector2;
import java.util.Random;

public class Spider extends Hero {
	public Spider(Vector2 position, Vector2 size, double speed, String imagepath) {
		super(position, size, speed, imagepath);
		}
	public void updateObjects() {
		move();
	}
	
	private void move() {
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		getrandomInt(2);
		}
	
	
	public int moveby1(int a) {
		int PlusOrMinus = (int)Math.round(Math.random());
		getrandomInt(PlusOrMinus);
		if(a>1) {
			Vector2 direction;
		}
		return a;
	}
	public double getrandomInt(int max) {
		return Math.floor(Math.random()*max);
	}
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);}

}
