package gameWorld;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

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
		Vector2 a = new Vector2(genererInt(0, 0.8), genererInt(0, 0.8));
		Spider spider1=new Spider(a, SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		addmonster(spider1);
		Fly fly1= new Fly(RoomInfos.POSITION_ALEATOIRE, FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY);
		addmonster(fly1);
		//Vector2 a = new Vector2(genererInt(0, 0.8), genererInt(0, 0.8));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 //Make every entity that compose a room process one step
	public void updateRoom()
	{
		if (CycleInfos.Cycle%5==0)
			moveby1allMonsters();
		for (Hero m: this.monsters) { 
			collisionWalls(m);
		}
		for(Hero m: this.monsters) {
			m.updateGameObject();
		}
		super.updateRoom();
	}
	
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
		
		/*
		 * for (Hero n: this.monsters) { n.moveby1();
		 */
		//}
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
	
	public static double genererInt(double borneInf, double borneSup) {
		Random generateur = new Random();
		double a=0;
		a = borneInf+generateur.nextDouble();
		return a;
	}
	
	//public static final Vector2 POSITION_ALEATOIRE = new Vector2(TESTROOM.genererInt(0, 0.8), TESTROOM.genererInt(0, 0.8));

	
	@Override
	public void drawRoom() {
		super.drawRoom();
		drawmonsters();
	}

}
