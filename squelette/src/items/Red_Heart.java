package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Red_Heart extends Item {

	public Red_Heart() {
		// TODO Auto-generated constructor stub
	}

	public Red_Heart(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.HEART_PICKABLE, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

}
