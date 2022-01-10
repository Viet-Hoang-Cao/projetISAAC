package gameWorld;

import gameobjects.Hero;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room
{
	
	private Hero hero;
	int tileNumberX;
	int tileNumberY;

	public Room(Hero hero)
	{
		this.hero = hero;
	}
	
	public Room(Hero hero, int tileNumberY, int tileNumberX) {
		this.hero = hero;
		this.tileNumberY = tileNumberY;
		this.tileNumberX = tileNumberX;
		
	}



	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
	}


	private void makeHeroPlay()
	{
		hero.updateGameObject();
	}

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
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		hero.drawGameObject();
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	protected Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}


	public Hero getHero() {
		return hero;
	}
	
	public void draw_dungeon(Room[][]tab) {
		Vector2 position = positionFromTileIndex(8, 6);
		StdDraw.setPenColor(StdDraw.BLUE);
		for(int i =0; i<tab.length;i++) {
			for(int j=0; j<tab[i].length;j++) {
				if(tab[i][j]!=null) {
					StdDraw.rectangle(position.getX()-RoomInfos.HALF_TILE_SIZE.getX()/1.2+j*RoomInfos.HALF_TILE_SIZE.getX()/2,
							position.getY()-i*RoomInfos.HALF_TILE_SIZE.getY()/2, 
							RoomInfos.HALF_TILE_SIZE.getX()/8,
							RoomInfos.HALF_TILE_SIZE.getY()/8);
					if(i==tileNumberY && j == tileNumberX) {
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.filledCircle(position.getX()-RoomInfos.HALF_TILE_SIZE.getX()/1.2+j*RoomInfos.HALF_TILE_SIZE.getX()/2,
								position.getY()-i*RoomInfos.HALF_TILE_SIZE.getY()/2, 
								RoomInfos.HALF_TILE_SIZE.getX()/16);
						StdDraw.setPenColor(StdDraw.BLUE);
					}
				}
			}
		}
		
	}
	
	public int getTileNumberX() {
		return tileNumberX;
	}

	public void setTileNumberX(int tileNumberX) {
		this.tileNumberX = tileNumberX;
	}

	public int getTileNumberY() {
		return tileNumberY;
	}

	public void setTileNumberY(int tileNumberY) {
		this.tileNumberY = tileNumberY;
	}
	
	public int changecurrentRoomX() {
		Vector2 v=positionFromTileIndex(0, 4);
		if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), v, RoomInfos.HALF_TILE_SIZE)){
			return -1;
		}
		v=positionFromTileIndex(8, 4);
		if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), v, RoomInfos.HALF_TILE_SIZE)) {
			return 1;
		}
		return 0;
	}
	
	public int changecurrentRoomY() {
		Vector2 v=positionFromTileIndex(4, 8);
		if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), v, RoomInfos.HALF_TILE_SIZE)){
			return -1;
		}
		v=positionFromTileIndex(4, 0);
		if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), v, RoomInfos.HALF_TILE_SIZE)) {
			return 1;
		}
		return 0;
	}
	
}
