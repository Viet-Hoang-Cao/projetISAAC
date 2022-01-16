package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Half_Heart extends Item {

	public Half_Heart() {
		setPrice(2);
	}

	public Half_Heart(Vector2 pos) {
		super(pos);
		setPrice(2);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.HALF_HEART_PICKABLE, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

	@Override
	public void effect(Hero h) {
		h.setLP(h.getLP()+1);
		if(h.getLP() >h.getMaxHP()) h.setLP(h.getMaxHP());
	}

}
