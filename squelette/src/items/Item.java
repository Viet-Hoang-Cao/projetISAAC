package items;

import gameobjects.Hero;
import libraries.Physics;
import libraries.Vector2;
import resources.RoomInfos;

public abstract class Item {
	
	private Vector2 pos;
	private boolean used;
	private int price;

	public Item() {
		this.pos=null;
		this.used=false;
		this.price = 0;
	}
	
	public Item(Vector2 pos) {
		this.pos = pos;
		this.used=false;
		this.price = 0;
	}
	
	public boolean physics(Hero h) {
		if(Physics.rectangleCollision(h.getPosition().addVector(h.getNormalizedDirection()),
				h.getSize(), pos, RoomInfos.HALF_TILE_SIZE)) {
			return true;
		}
		return false;
	}
	
	abstract public void effect();
	abstract public void effect(Hero h);
	
	abstract public void drawitem();

	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	

}
