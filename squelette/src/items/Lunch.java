package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Lunch extends Item {

	public Lunch() {
		// TODO Auto-generated constructor stub
	}

	public Lunch(Vector2 pos) {
		super(pos);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.LUNCH, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

}
