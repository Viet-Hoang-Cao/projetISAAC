package gameobjects;


import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;




public class Tear {
	private Vector2 direction;
	private Vector2 position;
	private Vector2 positionInitial;
	
	public Tear (Vector2 positionInitial, Vector2 direction)
	{
		this.direction= direction;
		this.position= positionInitial.addVector(this.direction);		
		this.positionInitial= positionInitial;		
	}
	
	public void updateTirLarme() {
		Vector2 positionAfterMoving = getPosition().addVector(direction);
		setPosition(positionAfterMoving);
	}
	
	public void drawLarme()
	{
		  StdDraw.picture(position.getX(),
		  position.getY(), ImagePaths.TEAR,
		  HeroInfos.TEAR_SIZE.getX(), HeroInfos.TEAR_SIZE.getY()); } 
	
	/**
	 * 
	 * @param m un monstre
	 * @return vrai s'il y a collision avec un monstre
	 */
	public boolean PhysicTear(Hero m) {
		if(Physics.rectangleCollision(position, HeroInfos.TEAR_SIZE, m.getPosition(), m.getSize()))return true;
		return false;
	}
	
		 	
			
	
	public Vector2 getPositionInitial() {
		return positionInitial;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	

}
