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
import resources.HeroInfos;
import gameobjects.Tear;

//classe de test pour tes appels de monstres ;p

public class TESTROOM extends SpawnRoom {

	private LinkedList<Tear> Tears;
	private LinkedList<Hero> monsters;

	public TESTROOM(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		Spider spider1 = new Spider(positionAlea(), SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED,
				ImagePaths.SPIDER, 5, 1);
		addmonster(spider1);
		Fly fly1 = new Fly(positionAlea(), FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY, 3, 1);
		addmonster(fly1);
	}

	@Override
	// Make every entity that compose a room process one step
	public void updateRoom() {
		/*
		 * if (CycleInfos.Cycle%5==0) moveby1allMonsters();
		 */
		moveSpider();
		moveFly();
		for (Hero m : this.monsters) {
			for(Wall w : walls) {
				w.collisionWalls(m);
			}
		}
		for (Hero m : this.monsters) {
			m.updateGameObject();
		}
		updateDamage();
		if (CycleInfos.Cycle % 50 == 0 && getHero().isTempInvunerability()) {
			getHero().setTempInvunerability(false);
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
		for (Hero m : this.monsters) {
			m.drawGameObject();
		}
	}

	public void moveby1allMonsters() {

		for (Hero m : this.monsters) {
			m.moveby1();
		}

		/*
		 * for (Hero n: this.monsters) { n.moveby1();
		 */
		// }
	}

	public void moveFly() {
		for (Hero f : this.monsters) {
			if (f instanceof Fly) {
				f.moveToPosition(getHero().getPosition());
			}
		}
	}

	public void moveSpider() {
		Random rand = new Random();
		Vector2 pos = SpiderInfos.POSITIONTOGO;
		if (CycleInfos.Cycle % 50 == 0) {
			pos = positionFromTileIndex(rand.nextInt(7) + 1, rand.nextInt(7) + 1);
			SpiderInfos.POSITIONTOGO = pos;
		}
		for (Hero s : this.monsters) {
			if (s instanceof Spider) {
				((Spider) s).moveToPosition(pos);
			}
		}
	}

	public static double genererInt(double borneInf, double borneSup) {
		Random generateur = new Random();
		double a = 0;
		a = borneInf + generateur.nextDouble();
		return a;
	}

	public void updateDamage() {
		if (!getHero().isInvicible() && !getHero().isTempInvunerability()) {

			for (Hero m : this.monsters) {
				if (Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), m.getPosition(),
						m.getSize())) {
					getHero().takeDamage(m.getDamage());
					getHero().setTempInvunerability(true);
				}

			}
		}

	}

	public Vector2 positionAlea() {
		Random rand = new Random();
		Vector2 pos = positionFromTileIndex(rand.nextInt(7) + 1, rand.nextInt(7) + 1);
		return pos;
	}

	
	public void pushBack(double portee) { 
		for (Hero m : this.monsters) {
			for (Tear t: Tears) {
				if(t.getDirection().getY()>0) {

					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());
						m.getPosition().addY(RoomInfos.HALF_TILE_SIZE.getY());
						Tears.remove(t);
					}
				}
				if(t.getDirection().getY()<0) {

					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());
						m.getPosition().addY(-RoomInfos.HALF_TILE_SIZE.getY());
						Tears.remove(t);
					  	}
					}
				if(t.getDirection().getX()>0) {

					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());
						m.getPosition().addY(RoomInfos.HALF_TILE_SIZE.getX());
						Tears.remove(t);
					}
				 }
				if(t.getDirection().getX()<0) {

					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());
						m.getPosition().addY(-RoomInfos.HALF_TILE_SIZE.getX());
						Tears.remove(t);
					}
				}
			}
		 }
	 } 
	 

	// public static final Vector2 POSITION_ALEATOIRE = new
	// Vector2(TESTROOM.genererInt(0, 0.8), TESTROOM.genererInt(0, 0.8));

	@Override
	public void drawRoom() {
		super.drawRoom();
		drawmonsters();
		getHero().drawLifePoint(positionFromTileIndex(0, 8).getX(), positionFromTileIndex(0, 8).getY());
	}

}
