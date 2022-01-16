package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Hp_Up extends Item {

	public Hp_Up() {
		// TODO Auto-generated constructor stub
	}

	public Hp_Up(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.HP_UP);

	}

}
