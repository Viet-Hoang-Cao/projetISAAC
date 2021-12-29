package gameWorld;

import java.util.Collection;
import java.util.LinkedList;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Spider;
import libraries.Vector2;
import resources.CycleInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import resources.SpiderInfos;
import resources.FlyInfos;


//classe de test pour tes appels de monstres ;p

public class TESTROOM extends SpawnRoom {
	
	private LinkedList<Hero> monsters;

	public TESTROOM(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		Spider spider1=new Spider(positionFromTileIndex(1, 1), SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		addmonster(spider1);
		Fly fly1= new Fly(positionFromTileIndex(1, 1), FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY);
		addmonster(fly1);
		// TODO Auto-generated constructor stub
	}
	
	/*@Override
	
	 * Make every entity that compose a room process one step
	 
	public void updateRoom()
	{
		for (Hero m: this.monsters) { 
			collisionWalls(m);
		}
		super.updateRoom();
	}
	*/
	private void addmonster(Fly fly1) {
		this.monsters.add(fly1);
	}

	public void addmonster(Spider monster) {
		this.monsters.add(monster);
	}
	
	public void drawmonsters() {
		for (Hero m: this.monsters) {
			m.drawGameObject();
		}
	}
	public void moveby1allMonsters() {
		
		for (Hero m: this.monsters) { m.moveby1(); }
		
		for (Hero n: this.monsters) {
			n.moveby1();
		}
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

	
	@Override
	public void drawRoom() {
		super.drawRoom();
		if (CycleInfos.Cycle%5==0)
		moveby1allMonsters();
		drawmonsters();
	}

}
