package items;

import java.util.List;
import java.util.Random;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Bomb extends Item {

	public Bomb() {
		super();
		setPrice(5);
	}

	public Bomb(Vector2 pos) {
		super(pos);
		setPrice(5);
	}

	@Override
	public void effect() {
		
		// TODO Auto-generated method stub
	}
	
	public void effect(List<Hero> m) {
		//TODO A faire
	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.BOMB);
	}

	@Override
	public void effect(Hero h) {
		h.getInventaire().addBomb();
		
	}

}
