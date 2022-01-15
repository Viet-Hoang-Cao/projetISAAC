package gameWorld;

import java.util.LinkedList;
import java.util.Random;

import gameobjects.Boss1;
import gameobjects.Hero;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CycleInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class MonstersRoom extends SpawnRoom {

	private LinkedList<Hero> monsters;
	private LinkedList<Vector2> spikesphysics;
	private LinkedList<Vector2> rocksphysics;
	private boolean closed_door;
	private boolean spawnRoom;
	private boolean bossRoom;
	private boolean merchantRoom;
	
	public MonstersRoom(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
		this.monsters      = new LinkedList<Hero>();
		this.spikesphysics = new LinkedList<Vector2>();
		this.rocksphysics  = new LinkedList<Vector2>();
		this.closed_door   = true;
		this.spawnRoom     = false;
		this.bossRoom      = false;
		this.merchantRoom  = false;
	}

	public MonstersRoom(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		this.spikesphysics = new LinkedList<Vector2>();
		this.rocksphysics = new LinkedList<Vector2>();
		this.closed_door=true;
		this.spawnRoom=false;
		this.bossRoom=false;
		this.merchantRoom=false;
	}
	
	@Override
	 //Make every entity that compose a room process one step
	public void updateRoom()
	{
		//TODO insérer methode pour IA monstres ici
		
		for (Hero m: this.monsters) { 
			collisionWalls(m);
		}
		for(Hero m: this.monsters) {
			m.updateGameObject();
		}
		collision_rocks();
		updateDamage();
		if(CycleInfos.Cycle > getHero().getDateCycleInvulnerabilityStart()+15 && getHero().isTempInvunerability()) {
			getHero().setTempInvunerability(false);
		}
		super.updateRoom();
	}
	
	@Override
	public void drawRoom() {
		super.drawRoom();
		drawRocks();
		drawSpikes();
		if(isSpawnRoom())StdDraw.text(0.5, 0.5, "SPAWN");
		if(isBossRoom())StdDraw.text(0.5, 0.5, "BOSS");
		if(isMerchantRoom())StdDraw.text(0.5, 0.5, "MERCHANTROOM");
		drawmonsters();
	}
	
	/**
	 * draw the monsters
	 */
	public void drawmonsters() {
		for (Hero m: this.monsters) {
			m.drawGameObject();
		}
	}
	/**
	 * @param Boss un monstre de type boss1.
	 */
	public void addMonster(Boss1 Boss) {
		monsters.add(Boss);
	}
	
	
	public void generateRock() {
		Random rand = new Random();
		int nb = rand.nextInt(6);//ne pas trop mettre de rocher. On ne check pas si Isaac a un path ni si l'on excede le nbre de case!!
		//nb = 45; -> permet de faire des test rapide; 49cases -4 = 45; 45 = maxvalue
		int x = rand.nextInt(7)+1;
		int y = rand.nextInt(7)+1;
		for(int i = 0; i< nb && i<45; i++) {
			while((x == 4 && y == 1) || (x == 4 && y == 7) || (x == 1 && y == 4) || (x == 7 && y == 4) || inRockList(x, y)){
					x = rand.nextInt(7)+1;
					y = rand.nextInt(7)+1;
			}
			addRockPhysics(x, y);
			x = rand.nextInt(7)+1;
			y = rand.nextInt(7)+1;
		}
	}
	
	public boolean inRockList(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		for(Vector2 rock : rocksphysics) {
			if(pos.getX() == rock.getX() && pos.getY() == rock.getY())return true;
		}
		return false;
	}
	
	/**
	 * draw a rock on a tile
	 */
	public void drawRocks(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.ROCK, 
				RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
	}
	/**
	 * draw all rocks
	 */
	public void drawRocks() {
		for(Vector2 pos: rocksphysics) {
			StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.ROCK, 
					RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
		}
	}
	/**
	 * gere la collision avec les rocher de la meme maniere que les murs. 
	 */
	public void collision_rocks() {
		for(Vector2 v : rocksphysics ) {
			//addvector.getnormalizeddirection est la pour determiner le FUTUR lieu de la collision afin de ne pas y aller.
			//cad le deplacement n'est pas de 0, 1 mais la version norme par exemple
			if(Physics.rectangleCollision(getHero().getPosition().addVector(getHero().getNormalizedDirection()),
					getHero().getSize(), v, RoomInfos.HALF_TILE_SIZE)) {//avec half tile, l'on peut passer en diagonale des rochers
				if(getHero().getDirection().getX()==-1 && getHero().getPosition().getX() - v.getX() >0) {
					getHero().getDirection().addX(1);
				}
				else if(getHero().getDirection().getX()==1 && getHero().getPosition().getX() - v.getX() <0) {
					getHero().getDirection().addX(-1);
				}
				if(getHero().getDirection().getY()==-1 && getHero().getPosition().getY() - v.getY() >0) {
					getHero().getDirection().addY(1);
				}
				else if(getHero().getDirection().getY()==1 && getHero().getPosition().getY() - v.getY() <0) {
					getHero().getDirection().addY(-1);
				}
				break;
			}
		}
	}
	/**
	 * add rock physic X,Y (tile position)
	 */
	public void addRockPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		rocksphysics.add(pos);
	}
	/**
	 * delete rock X,Y(tile position)
	 */
	public void deleteRockPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		rocksphysics.remove(pos);
	}
	
	
	public void generateSpikes() {
		Random rand = new Random();
		int nb = rand.nextInt(4); // attention, 45 - rockList = nb d'emplacement restant !
		int x = rand.nextInt(7)+1;
		int y = rand.nextInt(7)+1;
		for(int i = 0; i< nb && i<45; i++) {
			while((x == 4 && y == 1) || (x == 4 && y == 7) || (x == 1 && y == 4) || (x == 7 && y == 4) || inRockList(x, y) || 
					inSpikesList(x, y)){
					x = rand.nextInt(7)+1;
					y = rand.nextInt(7)+1;
			}
			addSpikesPhysics(x, y);
			x = rand.nextInt(7)+1;
			y = rand.nextInt(7)+1;
		}
	}
	
	public boolean inSpikesList(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		for(Vector2 rock : spikesphysics) {
			if(pos.getX() == rock.getX() && pos.getY() == rock.getY())return true;
		}
		return false;
	}
	/**
	 * draw spikes on a tile
	 */
	public void drawSpikes(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.SPIKES, 
				RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
	}
	/**
	 * draw all spikes
	 */
	public void drawSpikes() {
		for(Vector2 pos: spikesphysics) {
			StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.SPIKES, 
					RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
		}
	}
	/**
	 * add spikes physics X,Y (tile position)
	 */
	public void addSpikesPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		spikesphysics.add(pos);
	}
	
	/**
	 * Traite de la collision avec les piques et renvoi vrai s'il y a eu collision avec le hero
	 */
	public boolean spikesCollisions() {
		for(Vector2 spikes : this.spikesphysics) {
			if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), spikes, RoomInfos.HALF_TILE_SIZE)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * draw a closed door in top position
	 */
	public void drawCloseDoorUp() {
		Vector2 pos = positionFromTileIndex(4, 8);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 0);
	}
	
	/**
	 * draw a closed door in down position
	 */	
	public void drawCloseDoorDown() {
		Vector2 pos = positionFromTileIndex(4, 0);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 180);
	}
	
	/**
	 * draw a closed door in left position
	 */
	public void drawCloseDoorLeft() {
		Vector2 pos = positionFromTileIndex(0, 4);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 90);
	}
	
	/**
	 * draw a closed door in right position
	 */
	public void drawCloseDoorRight() {
		Vector2 pos = positionFromTileIndex(8, 4);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 270);
	}
	
	
	/**
	 * Cette methode fait en sorte que le hero ne prennent pas continuellement des degats et traites de toutes les forme de damage.
	 * Il faut appeler ici toutes formes de damage potentiel, monstres, spikes, projectile envers le Hero.
	 */
	public void updateDamage() {
		if(!getHero().isInvicible() && !getHero().isTempInvunerability()) {
			for(Hero m:this.monsters) {
				if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
						m.getPosition(), m.getSize())) {
					getHero().takeDamage(m.getDamage());
					getHero().setTempInvunerability(true);
				}		
			}
			if(spikesCollisions()) {
				getHero().takeDamage(1);
				getHero().setTempInvunerability(true);
			}
		}
	}

	public LinkedList<Hero> getMonsters() {
		return monsters;
	}

	public boolean isClosed_door() {
		return closed_door;
	}

	public void setClosed_door(boolean closed_door) {
		this.closed_door = closed_door;
	}
	
	public boolean isBossRoom() {
		return bossRoom;
	}

	public void setBossRoom(boolean bossRoom) {
		this.bossRoom = bossRoom;
	}

	public boolean isSpawnRoom() {
		return spawnRoom;
	}

	public void setSpawnRoom(boolean spawnRoom) {
		this.spawnRoom = spawnRoom;
	}

	public boolean isMerchantRoom() {
		return merchantRoom;
	}

	public void setMerchantRoom(boolean merchantRoom) {
		this.merchantRoom = merchantRoom;
	}
	

}
