package gameWorld;

import java.util.ArrayList;
import java.util.List;
import gameobjects.Hero;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;

import resources.ImagePaths;
import resources.RoomInfos;

public class SpawnRoom extends Room {
	
	//Contient tout les emplacements ou le joueur ne peut pas aller
	List<Vector2> wallphysics;
	
	public SpawnRoom(Hero hero) {
		super(hero);
		this.wallphysics = new ArrayList<>();//to be fair : Je n'arrive pas à me decider 
		//sur une ArrayList, une Linkedlist ou autre. J'ai besoin d'acces rapide autant que de rajouter
		//et enlever des elements
		wallphysics();
		addOpenDoorRightPhysics();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		//WALLS
		DrawWalls();
		//DOORS
		addOpenDoorRight();
		//Isaac position
		Vector2 position = positionFromTileIndex(4, 8);
		StdDraw.text(position.getX(),position.getY(), getHero().getPosition().toString());


	}
	
	/**
	 * DrawWalls of room (must be drawn BEFORE THE OTHER STUFF)
	 */
	public void DrawWalls() {
		StdDraw.setPenColor(150,75,0);
		for(int i = 0; i<RoomInfos.NB_TILES;i++) {
			Vector2 position = positionFromTileIndex(0, i);
			StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
					RoomInfos.HALF_TILE_SIZE.getY());
			
			position = positionFromTileIndex(i, 0);
			StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
					RoomInfos.HALF_TILE_SIZE.getY());
			
			position = positionFromTileIndex(8, i);
			StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
					RoomInfos.HALF_TILE_SIZE.getY());
			
			position = positionFromTileIndex(i, 8);
			StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
					RoomInfos.HALF_TILE_SIZE.getY());
		}
	}
	
	/**
	 * ajoute la physique des murs
	 */
	public void wallphysics() {
		for(int i = 0; i<RoomInfos.NB_TILES;i++) {
			wallphysics.add(positionFromTileIndex(0, i));
			wallphysics.add(positionFromTileIndex(i, 0));
			wallphysics.add(positionFromTileIndex(8, i));
			wallphysics.add(positionFromTileIndex(i, 8));
		}
	}
	
	/**
	 * prend effet si le hero cogne un mur
	 *  
	 */
	public void collisionWalls() {
		for(Vector2 v : wallphysics ) {
			if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), v, RoomInfos.TILE_SIZE)) {
				if(getHero().getDirection().getX()==-1) {
					getHero().getDirection().addX(3);
				}
				if(getHero().getDirection().getX()==1) {
					getHero().getDirection().addX(-3);
				}
				if(getHero().getDirection().getY()==-1) {
					getHero().getDirection().addY(3);
				}
				if(getHero().getDirection().getY()==1) {
					getHero().getDirection().addY(-3);
				}
			}
		}
	}
	
		
	/**
	 * prend effet si le hero specifié cogne un mur
	 *//*
	public void collisionWalls(Hero monster) {
		for(Vector2 v : wallphysics ) {
			if(Physics.rectangleCollision(monster.getPosition(), monster.getSize(), v, RoomInfos.TILE_SIZE)) {
				if(monster.getDirection().getX()==-1) {
					monster.goRightNext();
				}
				if(monster.getDirection().getX()==1) {
					monster.goLeftNext();
				}
				if(monster.getDirection().getY()==-1) {
					monster.goUpNext();
				}
				if(monster.getDirection().getY()==1) {
					monster.goDownNext();
				}
			}
		}
	}*/
	
	@Override
	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		collisionWalls();
		super.updateRoom();
	}
	
	public void deleteVectorOfWall(Vector2 pos) {
		for(Vector2 v : wallphysics) {
			if(v.getX() == pos.getX() && v.getY() == pos.getY()) {
				wallphysics.remove(v);
				break;
			}
		}
	}
	
	/**
	 * dessine une door en haut
	 */
	public void addOpenDoorUp() {
		Vector2 pos = positionFromTileIndex(4, 8);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 0);
		
	}
	/**
	 * add physics for open door en haut
	 */
	public void addOpenDoorUpPhysics() {
		deleteVectorOfWall(positionFromTileIndex(4, 8));
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
	 * add physics for open door en bas
	 */
	public void addOpenDoorUpDown() {
		deleteVectorOfWall(positionFromTileIndex(4, 0));
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
	 * add physics for open door à gauche
	 */
	public void addOpenDoorLeftPhysics() {
		deleteVectorOfWall(positionFromTileIndex(0, 4));
	}
	
	/**
	 * dessine une door à droite
	 */
	public void addOpenDoorRight() {
		Vector2 pos = positionFromTileIndex(8, 4);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 270);
	}
	/**
	 * add physics for open door à droite
	 */
	public void addOpenDoorRightPhysics() {
		deleteVectorOfWall(positionFromTileIndex(8, 4));
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
