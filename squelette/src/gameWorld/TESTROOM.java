package gameWorld;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Spider;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Timer;
import libraries.Vector2;
import resources.CycleInfos;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.RoomInfos;
import resources.SpiderInfos;
import resources.FlyInfos;
import resources.HeroInfos;
import gameobjects.Tear;

//classe de test pour tes appels de monstres ;p

public class TESTROOM extends SpawnRoom {

	private LinkedList<Hero> monsters;
	LinkedList<Wall>Walls;
	
	public static void main(String[] args)
	{
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, ImagePaths.ISAAC, 6, 2);
		GameWorld world = new GameWorld(isaac);
		
		//Test de changement de room || Le constructeur initialisera automatiquement le DJ et cette ligne deviendra useless.
		world.setCurrentRoom(new TESTROOM(isaac));
		
		initializeDisplay();
		// Main loop of the game
		while (!world.gameOver())
		{
			CycleInfos.Cycle++;
			if(CycleInfos.Cycle==Integer.MAX_VALUE) {
				CycleInfos.Cycle=0;
			}
			processNextStep(world);
		}
		StdDraw.picture(0.5, 0.5, ImagePaths.LOSE_SCREEN, 1, 1);
		StdDraw.show();
	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}

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
	
	/**
	 * Fonction pour verifier les autres fonctions
	 */
	public void updateRoom() {
		moveSpider();
		moveFly();
		pushBack();
		collisionWallsMonstre();
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

	/**
	 * Ajouter une mouche
	 * @param fly1
	 */
	private void addmonster(Fly fly1) {
		this.monsters.add(fly1);
	}
	
	/**
	 * Ajouter une araignee
	 * @param monster
	 */
	public void addmonster(Spider monster) {
		this.monsters.add(monster);
	}
	
	/**
	 * Dessiner les monstres
	 */
	public void drawmonsters() {
		for (Hero m : this.monsters) {
			m.drawGameObject();
		}
	}
	
	
	/**
	 * Fonction qui fait avancer tout les monstres
	 */
	public void moveby1allMonsters() {

		for (Hero m : this.monsters) {
			m.moveby1();
		}

		/*
		 * for (Hero n: this.monsters) { n.moveby1();
		 */
		// }
	}
	
	/**
	 * Fonction qui permet de faire bouger la mouche vers la position d'Isaac
	 */
	public void moveFly() {
		for (Hero f : this.monsters) {
			if (f instanceof Fly) {
				f.moveToPosition(getHero().getPosition());
			}
		}
	}
	
	/**
	 * Fonction qui permet de faire bouger l'araignee aleatoirement
	 */
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
	
	/**
	 * 
	 * @param borneInf
	 * @param borneSup
	 * @return a qui est une double 
	 */
	//public static double genererInt(double borneInf, double borneSup) {
		//Random generateur = new Random();
		//double a = 0;
		//a = borneInf + generateur.nextDouble();
		//return a;
	//}
	
	/**
	 * Les degats que Isaac va recevoir lorsqu'il est touche par les monstres
	 */
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
	
	/**
	 * Fonction creee pour mettre une placement aleatoire des monstres dans le room des monstres
	 * @return une position aleatoire sur le room
	 */
	public Vector2 positionAlea() {
		Random rand = new Random();
		Vector2 pos = positionFromTileIndex(rand.nextInt(7) + 1, rand.nextInt(7) + 1);
		return pos;
	}

	/**
	 * Fonction qui fait reculer les enemies lorsqu'ils sont touches par les larmes
	 */
	public void pushBack() { 
		for (Hero m : this.monsters) {
			for (Tear t: getHero().getTears()) {
				if(t.getDirection().getY()>0) {
					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());
						m.getPosition().addY(RoomInfos.HALF_TILE_SIZE.getY());
						getHero().getTears().remove(t);
					}
				}
				if(t.getDirection().getY()<0) {
					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());
						m.getPosition().addY(-RoomInfos.HALF_TILE_SIZE.getY());
						getHero().getTears().remove(t);
						
					  	}
					}
				if(t.getDirection().getX()>0) {
					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());
						m.getPosition().addX(RoomInfos.HALF_TILE_SIZE.getX());
						getHero().getTears().remove(t);
					}
				 }
				if(t.getDirection().getX()<0) {
					if(Physics.rectangleCollision(t.getPosition(), HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize())) {
						m.takeDamage(getHero().getDamage());						
						m.getPosition().addX(-RoomInfos.HALF_TILE_SIZE.getX());
						getHero().getTears().remove(t);
					}
				}
			}
		 }
	 }
	
	public void collisionWallsMonstre() {
		for(Hero m:this.monsters) {
			for(Wall w: walls) {
				w.collisionWalls(m);
			}
		}
	}
	
	/**
	 * Fonction collision entre larmes et le mur qui fait disparaitre les larmes lors du contact
	 */
	public void collisionTearsWalls() {
		for (Tear t:getHero().getTears()) {
			for(Wall w: walls) {
				if(t.getDirection().getY()>0) {
					if(Physics.rectangleCollision(w.getPos(), RoomInfos.TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) { 
						getHero().getTears().remove(t);
					}
				}
				if(t.getDirection().getY()<0) {
					if(Physics.rectangleCollision(w.getPos(), RoomInfos.TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) { 
						getHero().getTears().remove(t);
					}
				}
				if(t.getDirection().getX()>0) {
					if(Physics.rectangleCollision(w.getPos(), RoomInfos.TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) { 
						getHero().getTears().remove(t);
					}
				}
				if(t.getDirection().getX()<0) {
					if(Physics.rectangleCollision(w.getPos(), RoomInfos.TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) { 
						getHero().getTears().remove(t);
					}
				}
			}
		}
	}
		

	@Override
	public void drawRoom() {
		super.drawRoom();
		drawmonsters();
		getHero().drawLifePoint(positionFromTileIndex(0, 8).getX(), positionFromTileIndex(0, 8).getY());
	}

}
