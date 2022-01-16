package gameWorld;

import gameobjects.Hero;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Cailloux {
	
	Vector2 pos;
	public Cailloux(Vector2 pos) {
		this.pos=pos;
	}
	
	/**
	 * gere la collision avec les rocher de la meme maniere que les murs
	 */
	public void collision_rocks(Hero m) {
		if(Physics.rectangleCollision(m.getPosition().addVector(m.getNormalizedDirection()),
			m.getSize(), pos, RoomInfos.HALF_TILE_SIZE)) {
			if(m.getDirection().getX()==-1 && m.getPosition().getX() - pos.getX() >0) {
				m.goRightNext();
			}
			else if(m.getDirection().getX()==1 && m.getPosition().getX() - pos.getX() <0) {
				m.goLeftNext();
			}
			if(m.getDirection().getY()==-1 && m.getPosition().getY() - pos.getY() >0) {
				m.goUpNext();
			}
			else if(m.getDirection().getY()==1 && m.getPosition().getY() - pos.getY() <0) {
				m.goDownNext();
			}
		}
	}
	
	/**
	 * draw all rocks
	 */
	public void drawRocks() {
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.ROCK, 
				RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
	}

	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	
}
