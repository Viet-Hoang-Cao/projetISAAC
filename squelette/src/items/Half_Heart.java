package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Half_Heart extends Item {

	public Half_Heart() {
		// TODO Auto-generated constructor stub
	}

	public Half_Heart(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.HALF_HEART_PICKABLE, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

}
