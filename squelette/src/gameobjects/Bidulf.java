package gameobjects;

import libraries.Vector2;
import resources.FlyInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import resources.SpiderInfos;

public class Bidulf extends Hero {
	private Spider spider;
	private Fly fly;
	
	public Bidulf(Vector2 position, Vector2 size, double speed, String imagePath, int LP, int damage) {
		super(position, size, speed, imagePath, LP, damage);
		this.spider = new Spider(getPosition(), SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		this.fly = new Fly(getPosition(), FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY);
		fly.setPortee(RoomInfos.TILE_SIZE.getX()*4);
		
		//fixe l'objet de Bidulf sur les tears de fly
		setTears(fly.getTears());
	}
	public Bidulf(Vector2 position) {
		super(position, RoomInfos.TILE_SIZE.scalarMultiplication(1.2), 0.005, ImagePaths.BIDULF, 12, 2);
		this.spider = new Spider(getPosition(), SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		this.fly = new Fly(getPosition(), FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY);
		fly.setPortee(RoomInfos.TILE_SIZE.getX()*4);
		
		setTears(fly.getTears());
	}
	
	@Override
	public void updateGameObject() {
		fly.updateGameObject();
		spider.updateGameObject();
		
		
		//problem de ref donc manual test
		if(getTears().size()< 1) {
			if(getDirection().getX()>0) projectileRightNext();
			else if(getDirection().getX()<0) projectileLeftNext();
			else if(getDirection().getY()>0) projectileUpNext();
			else if(getDirection().getY()<0) projectileDownNext();
		}
		
		super.updateGameObject();
	}
	
	
	
	//TODO ADD PATERNS
	
	
	//BIDULF NE VEUT PAS FONCTIONNER PAR REFERENCE :I
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
