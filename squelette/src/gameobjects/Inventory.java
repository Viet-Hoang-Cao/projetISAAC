package gameobjects;

import libraries.StdDraw;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Inventory {
	private int nbKeys;
	private int nbBombs;
	private int gold;
	
	public Inventory() {
		this.nbKeys=0;
		this.nbBombs=1;
		this.gold=0;
	}
	public Inventory(int nbKeys, int nbBombs, int gold) {
		this.nbKeys=nbKeys;
		this.nbBombs=nbBombs;
		this.gold=gold;
	}
	
	public void drawInventory(double x, double y) {
		StdDraw.picture(x-RoomInfos.HALF_TILE_SIZE.getX(), y, ImagePaths.KEY, 
				RoomInfos.TILE_SIZE.getX()/3, RoomInfos.TILE_SIZE.getY()/3);
		StdDraw.text(x-RoomInfos.HALF_TILE_SIZE.getX()+RoomInfos.TILE_SIZE.getX()/3, y, ""+nbKeys);
		
		StdDraw.picture(x-RoomInfos.HALF_TILE_SIZE.getX(), y-RoomInfos.TILE_SIZE.getX()/3, ImagePaths.BOMB, 
				RoomInfos.TILE_SIZE.getX()/3, RoomInfos.TILE_SIZE.getY()/3);
		StdDraw.text(x-RoomInfos.HALF_TILE_SIZE.getX()+RoomInfos.TILE_SIZE.getX()/3, y-RoomInfos.TILE_SIZE.getX()/3, ""+nbBombs);
		
		StdDraw.picture(x-RoomInfos.HALF_TILE_SIZE.getX(), y-2*RoomInfos.TILE_SIZE.getX()/3, ImagePaths.COIN, 
				RoomInfos.TILE_SIZE.getX()/3, RoomInfos.TILE_SIZE.getY()/3);
		StdDraw.text(x-RoomInfos.HALF_TILE_SIZE.getX()+RoomInfos.TILE_SIZE.getX()/3, y-2*RoomInfos.TILE_SIZE.getX()/3, ""+gold);
		
	}
	
	public int getNbKeys() {
		return nbKeys;
	}
	public void setNbKeys(int nbKeys) {
		this.nbKeys = nbKeys;
	}
	public int getNbBombs() {
		return nbBombs;
	}
	public void setNbBombs(int nbBombs) {
		this.nbBombs = nbBombs;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void addPenny() {
		this.gold++;
		if(this.gold>99) {
			this.gold=99;
		}
	}
	public void addNickel() {
		this.gold+=5;
		if(this.gold>99) {
			this.gold=99;
		}
	}
	public void addDime() {
		this.gold+=10;
		if(this.gold>99) {
			this.gold=99;
		}
	}
	
	public void addBomb() {
		this.nbBombs++;
	}
	
	public void addKeys() {
		this.nbKeys++;
	}
	

}
