package gameobjects;

import java.util.List;
import java.util.Random;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class ItemsTable {
	
	double bombsDroprate;
	double keyDroprate;
	double pennyDroprate;
	double nickelDroprate;
	double dimeDroprate;
	double red_heartDroprate;
	double red_half_heartDroprate;
	double magic_MushroomDroprate;
	double lunchDroprate;
	double jesuis_JuiceDroprate;
	double hp_upDroprate;
	

	public ItemsTable() {
		this.bombsDroprate=0.5;
		this.keyDroprate=0.35;
		this.pennyDroprate=0.6;
		this.nickelDroprate=0.3;
		this.dimeDroprate=0.05;
		this.red_heartDroprate=0.4;
		this.red_half_heartDroprate=0.6;
		this.magic_MushroomDroprate=0.2;
		this.lunchDroprate=0.1;
		this.jesuis_JuiceDroprate=0.1;
		this.hp_upDroprate=0.1;
	}
	
	public void RandomDropRoon(Hero H) {
		Random rand = new Random();
		double drop = rand.nextDouble();
		//if(drop<)
	}
	
	/**
	 * renvoie true si l'objet est en collision avec les murs
	 */
	public boolean collisionsmuretObjet(Vector2 pos, List<Vector2> wallsPhysics ) {
		for(Vector2 v: wallsPhysics) {
			if(Physics.rectangleCollision(pos, RoomInfos.TILE_SIZE, v, RoomInfos.TILE_SIZE)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

}
