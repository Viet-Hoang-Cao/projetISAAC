package items;

import java.util.List;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

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
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.BOMB, 
				RoomInfos.HALF_TILE_SIZE.getX(),RoomInfos.HALF_TILE_SIZE.getY());
	}

	@Override
	public void effect(Hero h) {
		h.getInventaire().addBomb();
		
	}

	@Override
	public void removeeffect(Hero h) {
		// TODO Auto-generated method stub
		
	}

}
