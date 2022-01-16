package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Blood_of_the_Martyr extends Item {

	public Blood_of_the_Martyr() {
		super();
		setPrice(15);
	}

	public Blood_of_the_Martyr(Vector2 pos) {
		super(pos);
		setPrice(15);
	}

	@Override
	public void effect() {

	}

	@Override
	public void effect(Hero h) {
		h.setDamage(h.getDamage()+1);
	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.BLOOD_OF_THE_MARTYR);

	}

	@Override
	public void removeeffect(Hero h) {
		h.setDamage(h.getDamage()-1);
	}

}
