package gameWorld;

import java.util.LinkedList;
import java.util.List;
import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;

import resources.ImagePaths;
import resources.RoomInfos;

public class SpawnRoom extends Room {
	
	List<Wall> walls;
	
	public SpawnRoom(Hero hero) {
		super(hero);
		this.walls = new LinkedList<Wall>();
		wallphysics();
	}
	
	/**
	 * Ce constructeur a besoin du numero de tuile
	 * @param hero
	 * @param TileNumber un vector le numero est : [X, Y] par rapport au 2D ARRAY du DJ !
	 */
	public SpawnRoom(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
		this.walls = new LinkedList<Wall>();
		wallphysics();
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

		//Isaac position
		StdDraw.setPenColor();
		Vector2 position = positionFromTileIndex(6, 8);
		StdDraw.text(position.getX(),position.getY(), getHero().getPosition().toString());
		//ROOM COORD
		position = positionFromTileIndex(8, 8);
		StdDraw.text(position.getX(),position.getY(), " Y: " +tileNumberY + " X:" + tileNumberX );
		//Redraw of Isaac
		getHero().drawGameObject();
		getHero().drawLifePoint(positionFromTileIndex(0, 8).getX(), positionFromTileIndex(0, 8).getY());
		getHero().getInventaire().drawInventory(positionFromTileIndex(1, 7).getX(), positionFromTileIndex(1, 7).getY());

	}
	
	/**
	 * Draw the walls of the room
	 */
	public void DrawWalls() {
		for(Wall w : walls) {
			w.drawBrownWall();
		}
	}
	
	/**
	 *  Add the physics of walls(must be call in constructor)
	 */
	public void wallphysics() {
		for(int i = 0; i<RoomInfos.NB_TILES;i++) {
			walls.add(new Wall(positionFromTileIndex(0, i)));
			if(i!=0) {
				walls.add(new Wall(positionFromTileIndex(i, 0)));
				walls.add(new Wall(positionFromTileIndex(8, i)));
				if(i!=8)walls.add(new Wall(positionFromTileIndex(i, 8)));
			}
		}
	}
	
	/**
	 * Delete some physics of wall
	 * @param pos the position of the wall (NOT A TILE!)
	 */
	public void deleteVectorOfWall(Vector2 pos) {
		for(Wall w : walls) {
			if(w.getPos().getX() == pos.getX() && w.getPos().getY() == pos.getY()) {
				walls.remove(w);
				break;
			}
		}
	}
	
	@Override
	/**
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		for(Wall w : walls) {
			w.collisionWalls(getHero());
		}
		super.updateRoom();
	}
	
	
	/*
	 * doors
	 */
	
	/**
	 * draw an open door in top position
	 */
	public void drawOpenDoorUp() {
		Vector2 pos = positionFromTileIndex(4, 8);
		StdDraw.setPenColor(150,75,0);
		StdDraw.filledRectangle(pos.getX(), pos.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
				RoomInfos.HALF_TILE_SIZE.getY());
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 0);
		
	}
	/**
	 * add physics for an open door in top position
	 */
	public void addOpenDoorUpPhysics() {
		deleteVectorOfWall(positionFromTileIndex(4, 8));
	}
	
	/**
	 * draw an open door in down position
	 */	
	public void drawOpenDoorDown() {
		Vector2 pos = positionFromTileIndex(4, 0);
		StdDraw.setPenColor(150,75,0);
		StdDraw.filledRectangle(pos.getX(), pos.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
				RoomInfos.HALF_TILE_SIZE.getY());
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 180);
	}
	/**
	 * add physics for an open door in down position
	 */
	public void addOpenDoorDownPhysics() {
		deleteVectorOfWall(positionFromTileIndex(4, 0));
	}
	
	/**
	 * draw an open door to the left
	 */
	public void drawOpenDoorLeft() {
		Vector2 pos = positionFromTileIndex(0, 4);
		StdDraw.setPenColor(150,75,0);
		StdDraw.filledRectangle(pos.getX(), pos.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
				RoomInfos.HALF_TILE_SIZE.getY());
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 90);
	}
	/**
	 * add physics for an open door in left position
	 */
	public void addOpenDoorLeftPhysics() {
		deleteVectorOfWall(positionFromTileIndex(0, 4));
	}
	
	/**
	 * draw an open door in right position
	 */
	public void drawOpenDoorRight() {
		Vector2 pos = positionFromTileIndex(8, 4);
		StdDraw.setPenColor(150,75,0);
		StdDraw.filledRectangle(pos.getX(), pos.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
				RoomInfos.HALF_TILE_SIZE.getY());
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.OPENED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 270);
	}
	/**
	 * add physics for an open door in right position
	 */
	public void addOpenDoorRightPhysics() {
		deleteVectorOfWall(positionFromTileIndex(8, 4));
	}

}
