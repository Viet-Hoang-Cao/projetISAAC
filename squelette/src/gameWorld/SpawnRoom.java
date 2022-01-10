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
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Ce constructeur a besoin du numero de tuile
	 * @param hero
	 * @param TileNumber un vector le numero est : [X, Y] par rapport au 2D ARRAY du DJ !
	 */
	public SpawnRoom(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
		this.wallphysics = new ArrayList<>();//to be fair : Je n'arrive pas à me decider 
		//sur une ArrayList, une Linkedlist ou autre. J'ai besoin d'acces rapide autant que de rajouter
		//et enlever des elements
		wallphysics();
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

		//Isaac position
		Vector2 position = positionFromTileIndex(6, 8);
		StdDraw.text(position.getX(),position.getY(), getHero().getPosition().toString());
		//ROOM COORD
		position = positionFromTileIndex(8, 8);
		StdDraw.text(position.getX(),position.getY(), " Y: " +tileNumberY + " X:" + tileNumberX );
		//Redraw of Isaac
		getHero().drawGameObject();
		getHero().drawLifePoint(positionFromTileIndex(0, 8).getX(), positionFromTileIndex(0, 8).getY());

	}
	
	/**
	 * Draw the walls of the room
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
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	
	/**
	 *  Add the physics of walls(must be call in constructor)
	 */
	public void wallphysics() {
		for(int i = 0; i<RoomInfos.NB_TILES;i++) {
			wallphysics.add(positionFromTileIndex(0, i));
			if(i!=0) {
				wallphysics.add(positionFromTileIndex(i, 0));
				wallphysics.add(positionFromTileIndex(8, i));
				if(i!=8)wallphysics.add(positionFromTileIndex(i, 8));
			}
		}
	}
	
	/**
	 * Check if the Hero bump a wall 
	 */
	public void collisionWalls() {
		for(Vector2 v : wallphysics ) {
			//addvector.getnormalizeddirection est la pour determiner le FUTUR lieu de la collision afin de ne pas y aller.
			if(Physics.rectangleCollision(getHero().getPosition().addVector(getHero().getNormalizedDirection()),
					getHero().getSize(), v, RoomInfos.TILE_SIZE)) {
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
	 * check if a specified hero bump a wall
	 */
	public void collisionWalls(Hero monster) {
		for(Vector2 v : wallphysics ) {
			if(Physics.rectangleCollision(monster.getPosition().addVector(monster.getNormalizedDirection())
					, monster.getSize(), v, RoomInfos.TILE_SIZE)) {
				if(monster.getDirection().getX()==-1 && monster.getPosition().getX() - v.getX() >0) {
					monster.getDirection().addX(1);
				}
				else if(monster.getDirection().getX()==1 && monster.getPosition().getX() - v.getX() <0) {
					monster.getDirection().addX(-1);
				}
				if(monster.getDirection().getY()==-1 && monster.getPosition().getY() - v.getY() >0) {
					monster.getDirection().addY(1);
				}
				else if(monster.getDirection().getY()==1 && monster.getPosition().getY() - v.getY() <0) {
					monster.getDirection().addY(-1);
				}
				break;
			}
		}
	}
	/**
	 * Delete some physics of wall
	 * @param pos the position of the wall (NOT A TILE!)
	 */
	public void deleteVectorOfWall(Vector2 pos) {
		for(Vector2 v : wallphysics) {
			if(v.getX() == pos.getX() && v.getY() == pos.getY()) {
				wallphysics.remove(v);
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
		collisionWalls();
		super.updateRoom();
	}
	
	/**
	 * draw an open door in top position
	 */
	public void drawOpenDoorUp() {
		Vector2 pos = positionFromTileIndex(4, 8);
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
