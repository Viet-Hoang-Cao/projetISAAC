package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import java.util.Random;

public class Fly extends Hero{
	public Fly(Vector2 position, Vector2 size, double speed, String imagepath, int LP, int damage) {
		super(position, size, speed, imagepath, LP, damage);
		
	}
	
	public void updateObjects() {
		move();
	}
	
	private void move() {
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		getDirection();
		}
	
	
		/*
		 * public void moveby1() { double b= getrandomdouble(3)-1; double a=
		 * getrandomdouble(3)-1; getDirection().addX(a); getDirection().addY(b);
		 * move();
		 }
*/	
	
	public double getrandomdouble(int max) {
		return Math.floor(Math.random()*max);
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);}

}

