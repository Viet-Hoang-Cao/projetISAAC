package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Penny extends Item {

	public Penny() {
		// TODO Auto-generated constructor stub
	}

	public Penny(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.COIN, 
				RoomInfos.HALF_TILE_SIZE.getX(),RoomInfos.HALF_TILE_SIZE.getY());

	}

	@Override
	public void effect(Hero h) {
		h.getInventaire().addPenny();
		
	}

	@Override
	public void removeeffect(Hero h) {
		// TODO Auto-generated method stub
		
	}

}
