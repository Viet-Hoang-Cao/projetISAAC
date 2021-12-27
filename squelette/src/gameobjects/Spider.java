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
	}
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);}

}
