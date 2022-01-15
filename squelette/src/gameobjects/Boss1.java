package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Boss1 extends Hero {
	
	public Boss1(Vector2 position, Vector2 size, double speed, String imagePath, int LP, int damage) {
		super(position, size, speed, imagePath, LP, damage);
		// TODO Auto-generated constructor stub
	}
	public Boss1(Vector2 position) {
		super(position, RoomInfos.HALF_TILE_SIZE, 0.02, ImagePaths.BIDULF, 12, 2);
	}
	
	@Override
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),0);
	}
	
	public void moveToPosition(Vector2 position) {
		if(position.getX()-this.getPosition().getX()>0) {
			this.goRightNext();
		}
		else if(position.getX()-this.getPosition().getX()<0){
			this.goLeftNext();
		}
		if(position.getY()-this.getPosition().getY()>0) {
			this.goUpNext();
		}
		else if(position.getY()-this.getPosition().getY()<0) {
			this.goDownNext();
		}
	}
	
	//@Override
	public void updateObjects() {
		move();
	}
	
	private void move() {
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		getDirection();
		}
}
