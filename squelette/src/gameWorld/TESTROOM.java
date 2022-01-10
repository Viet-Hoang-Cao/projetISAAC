package gameWorld;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Spider;
import libraries.Physics;
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
		Random rand2 = new Random();
		this.monsters = new LinkedList<Hero>();
		//Vector2 a = new Vector2(genererInt(0.15, 0.8), genererInt(0.15, 0.8));
		Vector2 b = positionFromTileIndex(rand2.nextInt(7)+1, rand2.nextInt(7)+1);
		Spider spider1=new Spider(b, SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER, 5, 1);
		addmonster(spider1);
		Fly fly1= new Fly(RoomInfos.POSITION_ALEATOIRE, FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY, 3, 1);
		addmonster(fly1);
		//Vector2 a = new Vector2(genererInt(0, 0.8), genererInt(0, 0.8));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	 //Make every entity that compose a room process one step
	public void updateRoom()
	{
		/*
		 * if (CycleInfos.Cycle%5==0) moveby1allMonsters();
		 */
		moveSpider();
		moveFly();
		for (Hero m: this.monsters) { 
			collisionWalls(m);
		}
		for(Hero m: this.monsters) {
			m.updateGameObject();
		}
		updateDamage();
		if(CycleInfos.Cycle%50==0 && getHero().isTempInvunerability()) {
			getHero().setTempInvunerability(false);
		}
		//if(CycleInfos.Cycle%20==0)getHero().takeDamage(1);
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
	
	public void moveFly() {
		for (Hero f:this.monsters) {
			if (f instanceof Fly) {
				f.moveToPositionby1(getHero().getPosition());
			}
		}
	}
	
	public void moveSpider() {
		Random rand = new Random();
		Vector2 pos= SpiderInfos.POSITIONTOGO;
		if (CycleInfos.Cycle%50==0) {
			pos= positionFromTileIndex(rand.nextInt(7)+1, rand.nextInt(7)+1);
			SpiderInfos.POSITIONTOGO=pos;
		}
		for (Hero s:this.monsters) {
			if(s instanceof Spider) {
				((Spider) s).moveToPositionby1(pos);
			}
		}
	}
	public static double genererInt(double borneInf, double borneSup) {
		Random generateur = new Random();
		double a=0;
		a = borneInf+generateur.nextDouble();
		return a;
	}
	
	public void updateDamage() {
		if(!getHero().isInvicible() && !getHero().isTempInvunerability()) {
			
			for(Hero m:this.monsters) {
				if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
						m.getPosition(), m.getSize())) {
					getHero().takeDamage(m.getDamage());
					getHero().setTempInvunerability(true);
				}
				
			}
		}
		
	}
	
	//public static final Vector2 POSITION_ALEATOIRE = new Vector2(TESTROOM.genererInt(0, 0.8), TESTROOM.genererInt(0, 0.8));

	
	@Override
	public void drawRoom() {
		super.drawRoom();
		drawmonsters();
		getHero().drawLifePoint(positionFromTileIndex(0, 8).getX(), positionFromTileIndex(0, 8).getY());
	}

}
