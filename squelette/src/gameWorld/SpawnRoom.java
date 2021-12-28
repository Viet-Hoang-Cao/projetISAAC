package gameWorld;

import java.util.Set;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class SpawnRoom extends Room {
	
	//Contient tout les emplacements ou le joueur ne peut pas aller
	//Set<Vector2> physics;
	Vector2 heropreviousposition;
	
	public SpawnRoom(Hero hero) {
		super(hero);
		this.heropreviousposition=super.getHero().getPosition();
		//this.physics = new Set<>();
		wallphysics();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{	
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{	
				if(j==0) StdDraw.setPenColor(150,75,0);
				if(j==RoomInfos.NB_TILES-1)StdDraw.setPenColor(150,75,0);
				
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
				
				if(i!=0 && i!=RoomInfos.NB_TILES-1)StdDraw.setPenColor(StdDraw.GRAY);
			}
			StdDraw.setPenColor(StdDraw.GRAY);
		}
		
		//DOORS
		addOpenDoorRight();
		
		//hero physics
		//if(physics.contains(super.getHero().getPosition()))
			super.getHero().setPosition(heropreviousposition);
		super.getHero().drawGameObject();
		heropreviousposition = super.getHero().getPosition();
	}

	/**
	 * ajoute la physique des murs
	 *//*
	public void wallphysics() {
		for(int i = 0; i<RoomInfos.NB_TILES;i++) {
			physics.add(positionFromTileIndex(0, i));
			physics.add(positionFromTileIndex(i, 0));
			physics.add(positionFromTileIndex(8, i));
			physics.add(positionFromTileIndex(i, 8));
		}
	}*/
	
	/**
	 * dessine une door en haut
	 */
	public void addOpenDoorUp() {
		Vector2 pos = positionFromTileIndex(4, 8);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 0);
	}
	/**
	 * dessine une door en bas
	 */	
	public void addOpenDoorDown() {
		Vector2 pos = positionFromTileIndex(4, 0);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 180);
	}
	/**
	 * dessine une door à gauche
	 */
	public void addOpenDoorLeft() {
		Vector2 pos = positionFromTileIndex(0, 4);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 90);
	}
	/**
	 * dessine une door à droite
	 */
	public void addOpenDoorRight() {
		Vector2 pos = positionFromTileIndex(8, 4);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 270);
	}
	
	public void addPhysics(Vector2 pos) {
		this.physics.add(pos);
	}
	
	public void removePhysics(Vector2 pos) {
		this.physics.remove(pos);
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

}