package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Key extends Item {

	public Key() {
		super();
		setPrice(5);
	}

	public Key(Vector2 pos) {
		super(pos);
		setPrice(5);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.KEY, 
				RoomInfos.HALF_TILE_SIZE.getX(),RoomInfos.HALF_TILE_SIZE.getY());

	}

	@Override
	public void effect(Hero h) {
		h.getInventaire().addKeys();
	}

	@Override
	public void removeeffect(Hero h) {
		// TODO Auto-generated method stub
		
	}

}
