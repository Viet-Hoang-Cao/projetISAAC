package items;

import java.util.List;
import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Bomb extends Item {

	public Bomb() {
		super();
	}

	public Bomb(Vector2 pos) {
		super(pos);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.BOMB);
	}

}
