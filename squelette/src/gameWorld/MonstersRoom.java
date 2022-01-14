package gameWorld;

import java.util.LinkedList;
import java.util.Random;

import gameobjects.Hero;
import libraries.Physics;
//import resources.CycleInfos;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class MonstersRoom extends SpawnRoom {

	private LinkedList<Hero> monsters;
	private LinkedList<Vector2> spikesphysics;
	private LinkedList<Vector2> rocks;
	private boolean closed_door;
	private boolean spawnRoom;
	private boolean bossRoom;
	private boolean merchantRoom;
	
	public MonstersRoom(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
		this.monsters      = new LinkedList<Hero>();
		this.spikesphysics = new LinkedList<Vector2>();
		this.rocks         = new LinkedList<Vector2>();
		this.closed_door   = true;
		this.spawnRoom     = false;
		this.bossRoom      = false;
		this.merchantRoom  = false;
	}

	public MonstersRoom(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		this.spikesphysics = new LinkedList<Vector2>();
		this.rocks = new LinkedList<Vector2>();
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
		//collision_rocks();
		super.updateRoom();
	}
	
	@Override
	public void drawRoom() {
		super.drawRoom();
		if(isSpawnRoom())StdDraw.text(0.5, 0.5, "SPAWN");
		if(isBossRoom())StdDraw.text(0.5, 0.5, "BOSS");
		if(isMerchantRoom())StdDraw.text(0.5, 0.5, "MERCHANTROOM");
		drawRocks();
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
	
	
	public void generateRock() {
		Random rand = new Random();
		int nb = rand.nextInt(4);//ne pas trop mettre de rocher. On ne check pas si Isaac a un path ni si l'on excede le nbre!!
		//de rocher
		nb = 45;
		int x = rand.nextInt(7)+1;
		int y = rand.nextInt(7)+1;
		for(int i = 0; i< nb; i++) {
			for(Vector2 rock : rocks) {
				while((x == 4 && y == 1) || (x == 4 && y == 7) || (x == 1 && y == 4) || (x == 7 && y == 4) || 
						(positionFromTileIndex(x, y).getX() == rock.getX() && positionFromTileIndex(x, y).getY() == rock.getY()))
					//On check devant les portes et si l'on ne fait pas deja parti de la liste
					//oblige de passer par position from tile puisque les pos des rocks ont ete enregistre comme ca pour le moment
					{
					x = rand.nextInt(7)+1;
					y = rand.nextInt(7)+1;
					}
			}	
			addRockPhysics(x, y);
		}
		
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
	 * draw alls rock
	 */
	public void drawRocks() {
		for(Vector2 pos: rocks) {
			StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.ROCK, 
					RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
			StdDraw.text(pos.getX(), pos.getY(), pos.toString());
		}
	}
	/**
	 * gere la collision avec les rocher de la meme maniere que les murs. 
	 */
	public void collision_rocks() {
		for(Vector2 v : rocks ) {
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
		rocks.add(pos);
	}
	/**
	 * delete rock X,Y(tile position)
	 */
	public void deleteRockPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		rocks.remove(pos);
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
	 * add spikes physics X,Y (tile position)
	 */
	public void addSpikesPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		spikesphysics.add(pos);
	}
	
	/**
	 * Traite de la collision avec les piques
	 */
	public void spikesCollisions() {
		//TODO à faire lorsque le hero sera fini (Traite les damages ainsi que le recul)
		for(Vector2 spikes : this.spikesphysics) {
			spikes.getX();
			spikes.getY();
		}
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
