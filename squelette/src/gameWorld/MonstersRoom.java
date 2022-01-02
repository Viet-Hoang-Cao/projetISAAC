package gameWorld;

import java.util.LinkedList;

import gameobjects.Hero;
//import resources.CycleInfos;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class MonstersRoom extends SpawnRoom {
	
	private LinkedList<Hero> monsters;
	private LinkedList<Vector2> spikes;
	private boolean closed_door;

	public MonstersRoom(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		this.spikes = new LinkedList<Vector2>();
		this.closed_door=true;
		// TODO Auto-generated constructor stub
	}
	
	public MonstersRoom(Hero hero, Vector2 tileNumber) {
		super(hero, tileNumber);
		this.monsters = new LinkedList<Hero>();
		this.spikes = new LinkedList<Vector2>();
		this.closed_door=true;
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
		super.updateRoom();
	}
	
	@Override
	public void drawRoom() {
		super.drawRoom();
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
	 * draw a rock on a tile
	 */
	public void drawRocks(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.ROCK, 
				RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
	}
	/**
	 * add rock physic X,Y (tile position)
	 */
	public void addRockPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		wallphysics.add(pos);
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
		spikes.add(pos);
	}
	
	/**
	 * Traite de la collision avec les piques
	 */
	public void spikesCollisions() {
		//TODO à faire lorsque le hero sera fini (Traite les damages ainsi que le recul)
		for(Vector2 spikes : this.spikes) {
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
	

}