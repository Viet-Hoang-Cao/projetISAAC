package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
//import java.util.Random;

public class Fly extends Hero{
	public Fly(Vector2 position, Vector2 size, double speed, String imagepath, int LP, int damage) {
		super(position, size, speed, imagepath, LP, damage);
		
	}
	public Fly(Vector2 position, Vector2 size, double speed, String imagepath) {
		super(position, size, speed, imagepath, 3, 1);
		
	}
	
	public double getrandomdouble(int max) {
		return Math.floor(Math.random()*max);
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);}

}

