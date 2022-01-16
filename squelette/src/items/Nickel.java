package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Nickel extends Item {

	public Nickel() {
		// TODO Auto-generated constructor stub
	}

	public Nickel(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.NICKEL, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

}
