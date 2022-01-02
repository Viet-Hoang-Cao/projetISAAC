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

	public MonstersRoom(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		this.spikes = new LinkedList<Vector2>();
		// TODO Auto-generated constructor stub
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
	 * dessine les monstres
	 */
	public void drawmonsters() {
		for (Hero m: this.monsters) {
			m.drawGameObject();
		}
	}
	
	/**
	 * dessine un rocher sur une tuile
	 */
	public void drawRocks(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.ROCK, 
				RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
	}
	/**
	 * add rock physic X,Y
	 */
	public void addRockPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		wallphysics.add(pos);
	}
	
	/**
	 * dessine une pique sur une tuile
	 */
	public void drawSpikes(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.SPIKES, 
				RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
	}
	/**
	 * add spikes physics X,Y
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

}
