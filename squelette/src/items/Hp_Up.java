package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Hp_Up extends Item {

	public Hp_Up() {
		super();
		setPrice(15);
	}

	public Hp_Up(Vector2 pos) {
		super(pos);
		setPrice(15);
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.HP_UP, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

	@Override
	public void effect(Hero h) {
		h.setMaxHP(h.getMaxHP()+2);
		h.setLP(h.getMaxHP());
		
	}

}
