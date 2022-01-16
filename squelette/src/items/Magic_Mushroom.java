package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Magic_Mushroom extends Item {

	public Magic_Mushroom() {
		// TODO Auto-generated constructor stub
	}

	public Magic_Mushroom(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.MAGIC_MUSHROOM, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());
	}

}
