package gameobjects;

import items.Bomb;
import items.Item;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Inventory {
	private int nbKeys;
	private int nbBombs;
	private int gold;
	private Item Equipment;
	
	public Inventory() {
		this.nbKeys=0;
		this.nbBombs=1;
		this.gold=0;
		this.Equipment=null;
	}
	public Inventory(int nbKeys, int nbBombs, int gold) {
		this.nbKeys=nbKeys;
		this.nbBombs=nbBombs;
		this.gold=gold;
		this.Equipment=null;
	}
	
	public void drawInventory(double x, double y) {
		StdDraw.setPenColor();
		StdDraw.picture(x-RoomInfos.HALF_TILE_SIZE.getX(), y, ImagePaths.KEY, 
				RoomInfos.TILE_SIZE.getX()/3, RoomInfos.TILE_SIZE.getY()/3);
		StdDraw.text(x-RoomInfos.HALF_TILE_SIZE.getX()+RoomInfos.TILE_SIZE.getX()/3, y, ""+nbKeys);
		
		StdDraw.picture(x-RoomInfos.HALF_TILE_SIZE.getX(), y-RoomInfos.TILE_SIZE.getX()/3, ImagePaths.BOMB, 
				RoomInfos.TILE_SIZE.getX()/3, RoomInfos.TILE_SIZE.getY()/3);
		StdDraw.text(x-RoomInfos.HALF_TILE_SIZE.getX()+RoomInfos.TILE_SIZE.getX()/3, y-RoomInfos.TILE_SIZE.getX()/3, ""+nbBombs);
		
		StdDraw.picture(x-RoomInfos.HALF_TILE_SIZE.getX(), y-2*RoomInfos.TILE_SIZE.getX()/3, ImagePaths.COIN, 
				RoomInfos.TILE_SIZE.getX()/3, RoomInfos.TILE_SIZE.getY()/3);
		StdDraw.text(x-RoomInfos.HALF_TILE_SIZE.getX()+RoomInfos.TILE_SIZE.getX()/3, y-2*RoomInfos.TILE_SIZE.getX()/3, ""+gold);
		
		if(Equipment!= null && Equipment.getPos()!= null) {
			Equipment.drawitem();
		}
		
	}
	
	public void UseBomb(Vector2 pos) {
		Bomb bomb = new Bomb(pos);
		if(nbBombs>=0) {
			nbBombs--;
			bomb.effect();
		}
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
	public Item getEquipment() {
		return Equipment;
	}
	public void setEquipment(Item equipment) {
		Equipment = equipment;
	}
	
	

}
