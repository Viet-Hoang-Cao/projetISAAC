package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
//import java.util.Random;
import resources.CycleInfos;

public class Fly extends Hero{
	
	public Fly(Vector2 position, Vector2 size, double speed, String imagepath, int LP, int damage) {
		super(position, size, speed, imagepath, LP, damage);
	}
	public Fly(Vector2 position, Vector2 size, double speed, String imagepath) {
		super(position, size, speed, imagepath, 3, 1);
	}
	
	@Override
	public void updateGameObject() {
		if(getTears().size()< 1) {
			if(getDirection().getX()>0) projectileRightNext();
			else if(getDirection().getX()<0) projectileLeftNext();
			else if(getDirection().getY()>0) projectileUpNext();
			else if(getDirection().getY()<0) projectileDownNext();
		}
		
		super.updateGameObject();
	}
	
	public double getrandomdouble(int max) {
		return Math.floor(Math.random()*max);
	}
	
	
	/*
	 * Projectile
	 */
	@Override
	public void projectileUpNext() {
		Vector2 dirNorm= new Vector2(0,1);
		dirNorm.euclidianNormalize(getSpeedTear()/2);
		getTears().add(new Tear(getPosition(), dirNorm));
		
	}
	@Override
	public void projectileDownNext() {
		Vector2 dirNorm= new Vector2(0,-1);
		dirNorm.euclidianNormalize(getSpeedTear()/2);
		getTears().add(new Tear(getPosition(), dirNorm));
		
		
	}
	@Override
	public void projectileLeftNext() {
		Vector2 dirNorm= new Vector2(-1,0);
		dirNorm.euclidianNormalize(getSpeedTear()/2);
		getTears().add(new Tear(getPosition(), dirNorm));
		
	}
	@Override
	public void projectileRightNext() {
		Vector2 dirNorm= new Vector2(1,0);
		dirNorm.euclidianNormalize(getSpeedTear()/2);
		getTears().add(new Tear(getPosition(), dirNorm));
		
	}

}

