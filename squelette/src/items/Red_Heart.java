package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Red_Heart extends Item {

	public Red_Heart() {
		setPrice(3);
	}

	public Red_Heart(Vector2 pos) {
		super(pos);
		setPrice(3);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.HEART_PICKABLE, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}
	
	@Override
	public void effect(Hero h) {
		h.setLP(h.getLP()+2);
		if(h.getLP() >h.getMaxHP()) h.setLP(h.getMaxHP());
	}

}
