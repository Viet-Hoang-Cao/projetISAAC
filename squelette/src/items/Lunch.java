package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Lunch extends Item {

	public Lunch() {
		setPrice(10);
	}

	public Lunch(Vector2 pos) {
		super(pos);
		setPrice(10);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.LUNCH, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

	@Override
	public void effect(Hero h) {
		h.setMaxHP(h.getMaxHP()+2);
		h.setLP(h.getLP()+2);
	}

}
