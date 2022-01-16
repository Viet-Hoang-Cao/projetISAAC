package items;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Jesuis_Juice extends Item {

	public Jesuis_Juice() {
		// TODO Auto-generated constructor stub
	}

	public Jesuis_Juice(Vector2 pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.JESUS_JUICE);

	}

}
