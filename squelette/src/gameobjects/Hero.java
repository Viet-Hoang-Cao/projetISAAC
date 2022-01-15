package gameobjects;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CycleInfos;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import gameobjects.Inventory;



public class Hero
{	
	private int LP;
	private int damage;
	private int maxHP;
	private boolean invicible;
	private boolean tempInvunerability;
	int armor;
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private Inventory Inventaire;
	private int dateCycleInfo;
	private int dateCycleInvulnerabilityStart;
	private double speedTear;
	private List<Tear>Tears;
	private double portee;
	private boolean superSpeed;

	


	public Hero(Vector2 position, Vector2 size, double speed, String imagePath, int LP, int damage)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.speedTear=0.02;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.LP= LP;
		this.maxHP=LP;
		this.damage=damage;
		this.Inventaire=new Inventory();
		this.invicible=false;
		this.tempInvunerability=false;
		this.Tears= new LinkedList<>();
		this.portee=2*RoomInfos.TILE_SIZE.getX()+RoomInfos.HALF_TILE_SIZE.getX();
		this.superSpeed = false;
	}
	public Hero(Vector2 position, Vector2 size, double speed, String imagePath)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.speedTear=0.02;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.LP= 6;
		this.maxHP=LP;
		this.damage=1;
		this.Inventaire=new Inventory();
		this.invicible=false;
		this.tempInvunerability=false;
		this.portee=2*RoomInfos.TILE_SIZE.getX()+RoomInfos.TILE_SIZE.getX();
		this.superSpeed = false;
	}
	/**
	 * cette fonction entre le degat que le Hero prend.
	 */
	public void takeDamage(int dmgMonstre) {
		this.LP=LP-dmgMonstre;
	}

	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void invunerable() {
		if(this.invicible==false) {
			this.invicible=true;
		}
		else this.invicible=false;
	}
	
	public Inventory getInventaire() {
		return Inventaire;
	}
	
	
	public void updateGameObject()
	{
		move();
		updateProjectile();
		deleteTear(this.portee);
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	

	public void drawLifePoint(double x, double y) {
		for(int i=0; i<this.maxHP; i++) {
			 if(i%2==0) {
				 StdDraw.picture(x+(i*RoomInfos.HALF_TILE_SIZE.getX()/2),y, ImagePaths.EMPTY_HEART_HUD, 
							RoomInfos.HALF_TILE_SIZE.getX()/2, RoomInfos.HALF_TILE_SIZE.getY()/2);
				 if(i<this.LP) {
					 StdDraw.picture(x+(i*RoomInfos.HALF_TILE_SIZE.getX()/2),y, ImagePaths.HEART_HUD, 
								RoomInfos.HALF_TILE_SIZE.getX()/2, RoomInfos.HALF_TILE_SIZE.getY()/2);
				 }
			 }
			 if(i==this.LP && i%2==1) {
				 StdDraw.picture(x+((i-1)*RoomInfos.HALF_TILE_SIZE.getX()/2),y, ImagePaths.HALF_HEART_HUD, 
							RoomInfos.HALF_TILE_SIZE.getX()/2, RoomInfos.HALF_TILE_SIZE.getY()/2);
			 }
		}
	}
	
	
	
	  public void drawProjectile() { 
		  for(Tear t: Tears) {
			  t.drawLarme();
		  }
	  }
	 
	  public void updateProjectile() { 
		  for(Tear t: Tears) {
			  t.updateTirLarme();
		  }
	 }

	public void drawGameObject()
	{
		if(!tempInvunerability) {
			StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),0);
		}
		else if(CycleInfos.Cycle%2==1) {//Il y a probablement mieux mais pas ltime
			StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),0);
		}
		if(invicible) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(position.getX()-size.getX()/2, position.getY()-size.getY(), position.getX()+size.getX()/2, position.getY()-size.getY());
		}
		if(superSpeed) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(position.getX()-size.getX()/2, position.getY()-size.getY(), position.getX()-size.getX()/2, position.getY()+size.getY()/2);
		}
		drawProjectile();
	}
	
	public void moveToPosition(Vector2 position) {
		if(position.getX()-this.getPosition().getX()>0) {
			this.goRightNext();
		}
		else if(position.getX()-this.getPosition().getX()<0){
			this.goLeftNext();
		}
		if(position.getY()-this.getPosition().getY()>0) {
			this.goUpNext();
		}
		else if(position.getY()-this.getPosition().getY()<0) {
			this.goDownNext();
		}
	}
	
	public void moveby1() {
		double b= getrandomdouble(3)-1;
		double a= getrandomdouble(3)-1;
		getDirection().addX(a);
		getDirection().addY(b);
	}
	
	
	public double getrandomdouble(int max) {
		return Math.floor(Math.random()*max);
	}
	
	



	/**
	 * Moving from key inputs. Direction vector is later normalised.
	 **/
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
	public void projectileUpNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dirNorm= new Vector2(0,1);
		dirNorm.euclidianNormalize(speedTear);
		Tears.add(new Tear(position, dirNorm));
		
	}
	public void projectileDownNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dirNorm= new Vector2(0,-1);
		dirNorm.euclidianNormalize(speedTear);
		Tears.add(new Tear(position, dirNorm));
		
		
	}
	public void projectileLeftNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dirNorm= new Vector2(-1,0);
		dirNorm.euclidianNormalize(speedTear);
		Tears.add(new Tear(position, dirNorm));
		
	}
	public void projectileRightNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dirNorm= new Vector2(1,0);
		dirNorm.euclidianNormalize(speedTear);
		Tears.add(new Tear(position, dirNorm));
		
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	public void deleteTear(double portee) {
		for(Tear t:Tears) {
		if(t.getDirection().getX()>0) {
			Vector2 a = new Vector2(t.getPositionInitial());
			a.addX(portee);
			if(Physics.rectangleCollision(a, RoomInfos.HALF_TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) {
				Tears.remove(t);
			}
		}
		if(t.getDirection().getX()<0) {
			Vector2 a = new Vector2(t.getPositionInitial());
			a.addX(-portee);
			if(Physics.rectangleCollision(a, RoomInfos.HALF_TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) {
				Tears.remove(t);
			}
		}
		if(t.getDirection().getY()>0) {
			Vector2 a = new Vector2(t.getPositionInitial());
			a.addY(portee);
			if(Physics.rectangleCollision(a, RoomInfos.HALF_TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) {
				Tears.remove(t);
			}
		}
		if(t.getDirection().getY()<0) {
			Vector2 a = new Vector2(t.getPositionInitial());
			a.addY(-portee);
			if(Physics.rectangleCollision(a, RoomInfos.HALF_TILE_SIZE, t.getPosition(), HeroInfos.TEAR_SIZE)) {
				Tears.remove(t);
			}
		}
		}
	}
	
	public void SPPEEEEEEEEEEEEEEED_ON_OFF (){
		if(superSpeed) {
			speed-=0.03;
			superSpeed=false;
		}
		else {
			speed+=0.03;
			superSpeed=true;
		}
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
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public int getLP() {
		return LP;
	}
	public boolean isInvicible() {
		return invicible;
	}
	public void setInvicible(boolean invicible) {
		this.invicible = invicible;
	}
	public boolean isTempInvunerability() {
		return tempInvunerability;
	}
	public void setTempInvunerability(boolean tempInvunerability) {
		this.tempInvunerability = tempInvunerability;
		dateCycleInvulnerabilityStart = CycleInfos.Cycle;
	}
	public int getDateCycleInfo() {
		return dateCycleInfo;
	}
	public void setDateCycleInfo(int dateCycleInfo) {
		this.dateCycleInfo = dateCycleInfo;
	}
	public int getDateCycleInvulnerabilityStart() {
		return dateCycleInvulnerabilityStart;
	}
	public List<Tear> getTears() {
		return Tears;
	}
	public void setTears(List<Tear> tears) {
		Tears = tears;
	}
	
}

