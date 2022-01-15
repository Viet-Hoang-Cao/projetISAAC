package gameWorld;

import gameobjects.Hero;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Spikes {

	Vector2 pos;
	public Spikes(Vector2 pos) {
		this.pos=pos;
	}
	
	public void drawSpikes() {
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.SPIKES, 
				RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
	}

	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	/**
	 * Traite de la collision avec les piques et renvoi vrai s'il y a eu collision avec le hero
	 */
	public boolean spikesCollisions(Hero m) {
		if(Physics.rectangleCollision(m.getPosition(), m.getSize(), pos, RoomInfos.HALF_TILE_SIZE)) {
			return true;
		}
		return false;
	}

}
