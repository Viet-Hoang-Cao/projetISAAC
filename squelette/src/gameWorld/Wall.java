package gameWorld;

import gameobjects.Hero;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Wall {
	
	Vector2 pos;
	public Wall(Vector2 pos) {
		this.pos=pos;
	}
	
	/**
	 * check if a specified hero bump a wall
	 */
	public void collisionWalls(Hero m) {
		if(Physics.rectangleCollision(m.getPosition().addVector(m.getNormalizedDirection()),
				m.getSize(), pos, RoomInfos.TILE_SIZE)) {
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
	
	public void drawWall() {
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.WALL);
	}
	
	public void drawBrownWall() {
		StdDraw.setPenColor(150,75,0);
		StdDraw.filledRectangle(pos.getX(), pos.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
				RoomInfos.HALF_TILE_SIZE.getY());
	}
	
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	
	

}
