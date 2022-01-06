package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import gameWorld.Room;

public class Hero
{	
	private int LP;
	private int damage;
	int armor;
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;


	public Hero(Vector2 position, Vector2 size, double speed, String imagePath, int LP, int damage)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.LP= LP;
		this.damage=damage;
	}

	public void updateGameObject()
	{
		move();
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public void moveby1() {
		double b= getrandomdouble(3)-1;
		double a= getrandomdouble(3)-1;
		getDirection().addX(a);
		getDirection().addY(b);
		//move();
	}
	
	
	public double getrandomdouble(int max) {
		return Math.floor(Math.random()*max);
	}
	
	public int LPhero() {
		int a=6;
		boolean gameover=false;
		while(!gameover) {
			if(a<=0) {
				gameover=true;
				return a;
			}
			else if(a >= 12){
				return a;
			}
		}
		return a;
	}
	
	public int damageHero(int a) { //false code
		int Vlarme=20;
		int degatlarme=1;
		a=Vlarme*degatlarme;
		return a;
	}
	
	public int damageInflicted() {
		int a;
		int HP=6;
		int damage=1;
		boolean hit=false;
		if (hit==true) {
			HP= HP-damage;
		}
		return HP;
	}

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}


	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}
}
