package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Bidulf extends Hero {
	
	public Bidulf(Vector2 position, Vector2 size, double speed, String imagePath, int LP, int damage) {
		super(position, size, speed, imagePath, LP, damage);
	}
	public Bidulf(Vector2 position) {
		super(position, RoomInfos.TILE_SIZE.scalarMultiplication(1.5), 0.005, ImagePaths.BIDULF, 12, 2);
	}
	
	@Override
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),0);
	}
	
	//TODO ADD PATERNS
}
