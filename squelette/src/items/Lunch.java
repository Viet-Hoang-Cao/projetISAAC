package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

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
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.LUNCH);

	}

}
