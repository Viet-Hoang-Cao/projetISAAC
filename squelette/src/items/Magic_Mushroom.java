package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Magic_Mushroom extends Item {

	public Magic_Mushroom() {
		// TODO Auto-generated constructor stub
		setPrice(15);
	}

	public Magic_Mushroom(Vector2 pos) {
		super(pos);
		setPrice(15);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.MAGIC_MUSHROOM, 
				RoomInfos.HALF_TILE_SIZE.getX(),RoomInfos.HALF_TILE_SIZE.getY());
	}

	@Override
	public void effect(Hero h) {
		h.setDamage(h.getDamage()+1);
		h.setMaxHP(h.getMaxHP()+2);
		h.setLP(h.getMaxHP());
		if(h.getSpeed() < 0.03)h.setSpeed(h.getSpeed()+h.getSpeed()/3);
		if(h.getSize().getY() <RoomInfos.TILE_SIZE.getY()-RoomInfos.TILE_SIZE.getY()/9)h.setSize(h.getSize().scalarMultiplication(1.1));
		
	}

	@Override
	public void removeeffect(Hero h) {
		// TODO Auto-generated method stub
		
	}

}
