package items;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Jesuis_Juice extends Item {

	public Jesuis_Juice() {
		// TODO Auto-generated constructor stub
		setPrice(15);
	}

	public Jesuis_Juice(Vector2 pos) {
		super(pos);
		setPrice(15);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawitem() {
		StdDraw.picture(getPos().getX(), getPos().getY(), ImagePaths.JESUS_JUICE, RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());

	}

	@Override
	public void effect(Hero h) {
		h.setDamage(h.getDamage()+1);
		h.setPortee(h.getPortee()+RoomInfos.HALF_TILE_SIZE.getX()/2);
		//tearheight in orginal game
		
	}

}
