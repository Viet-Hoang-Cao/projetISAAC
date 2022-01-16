package gameobjects;

import libraries.StdDraw;
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
		this.spider = new Spider(position, SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		this.fly = new Fly(position, FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY);
		fly.setPortee(RoomInfos.TILE_SIZE.getX()*4);
		
		//fixe l'objet de Bidulf sur les tears de fly
		setTears(fly.getTears());
	}
	public Bidulf(Vector2 position) {
		super(position, RoomInfos.TILE_SIZE.scalarMultiplication(1.2), 0.005, ImagePaths.BIDULF, 12, 2);
		this.spider = new Spider(position, SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		this.fly = new Fly(position, FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY);
		fly.setPortee(RoomInfos.TILE_SIZE.getX()*4);
		
		setTears(fly.getTears());
	}
	
	@Override
	public void updateGameObject() {
		fly.setPosition(getPosition());
		spider.setPosition(getPosition());
		fly.updateGameObject();
		
		super.updateGameObject();
	}
	
	
	
	//TODO ADD PATERNS
}
