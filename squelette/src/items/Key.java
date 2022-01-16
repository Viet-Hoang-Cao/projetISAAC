package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Key extends Item {

	public Key() {
		// TODO Auto-generated constructor stub
	}

	public Key(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.KEY, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

}
