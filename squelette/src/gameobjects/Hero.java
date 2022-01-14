package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.CycleInfos;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import java.util.HashMap;
import gameobjects.Inventory;



public class Hero
{	
	private int LP;
	private int damage;
	private int maxHP;
	private boolean invicible;
	private boolean tempInvunerability;
	private HashMap<Vector2, Vector2>positionProjectileUp; //<direction, <position, position initial>>
	private HashMap<Vector2, Vector2> positionProjectileDown;
	private HashMap <Vector2, Vector2> positionProjectileRight;
	private HashMap <Vector2, Vector2> positionProjectileLeft; 
	int armor;
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private Inventory Inventaire;
	private int dateCycleInfo;
	private double speedTear;

	


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
		this.positionProjectileUp= new HashMap<Vector2, Vector2>();
		this.positionProjectileDown= new HashMap<Vector2, Vector2>();
		this.positionProjectileRight= new HashMap<Vector2, Vector2>();
		this.positionProjectileLeft= new HashMap<Vector2, Vector2>();
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
		this.positionProjectileUp= new HashMap<Vector2, Vector2>();
		this.positionProjectileDown= new HashMap<Vector2, Vector2>();
		this.positionProjectileRight= new HashMap<Vector2, Vector2>();
		this.positionProjectileLeft= new HashMap<Vector2, Vector2>();
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
		  for (Vector2 v: positionProjectileUp.keySet())
	  { 
			  StdDraw.picture(positionProjectileUp.get(v).getX(),
	  positionProjectileUp.get(v).getY(), ImagePaths.TEAR,
	  HeroInfos.TEAR_SIZE.getX(), HeroInfos.TEAR_SIZE.getY()); } 
		  for (Vector2 v:
	  positionProjectileDown.keySet()) {
	  StdDraw.picture(positionProjectileDown.get(v).getX(),
	  positionProjectileDown.get(v).getY(), ImagePaths.TEAR,
	  HeroInfos.TEAR_SIZE.getX(), HeroInfos.TEAR_SIZE.getY()); } 
		  for (Vector2 v:
	  positionProjectileRight.keySet()) {
	  StdDraw.picture(positionProjectileRight.get(v).getX(),
	  positionProjectileRight.get(v).getY(), ImagePaths.TEAR,
	  HeroInfos.TEAR_SIZE.getX(), HeroInfos.TEAR_SIZE.getY()); } 
		  for (Vector2 v:
	  positionProjectileLeft.keySet()) {
	  StdDraw.picture(positionProjectileLeft.get(v).getX(),
	  positionProjectileLeft.get(v).getY(), ImagePaths.TEAR,
	  HeroInfos.TEAR_SIZE.getX(), HeroInfos.TEAR_SIZE.getY()); } }
	  
	  public void updateProjectile() {
		  StdDraw.text(0.5, 0.6, "Y");
		  for (Vector2 v:
	  positionProjectileUp.keySet()) { 
			  positionProjectileUp.get(v).addVector(v);
	  StdDraw.text(0.5, 0.6, "Y"); } 
		  for (Vector2 v:
	  positionProjectileDown.keySet()) {
			  positionProjectileDown.get(v).addVector(v); StdDraw.text(0.5, 0.4, "O"); }
	  for (Vector2 v: positionProjectileRight.keySet()) {
		  positionProjectileRight.get(v).addVector(v); StdDraw.text(0.6, 0.5, "L"); }
	  for (Vector2 v: positionProjectileLeft.keySet()) {
		  positionProjectileLeft.get(v).addVector(v); StdDraw.text(0.4, 0.5, "O"); }
	 



	}

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		drawProjectile();
	}
	
	public void moveToPositionby1(Vector2 position) {
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
		Vector2 dir = new Vector2();
		dir.addY(1);
		Vector2 futurpos = getNormalizedDirectionTear(dir);
		positionProjectileUp.put(dir, position.addVector(futurpos));
	}
	public void projectileDownNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dir = new Vector2();
		dir.addY(-1);
		Vector2 futurpos = getNormalizedDirectionTear(dir);
		positionProjectileDown.put(dir, position.addVector(futurpos));
	}
	public void projectileLeftNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dir = new Vector2();
		dir.addX(-1);
		Vector2 futurpos = getNormalizedDirectionTear(dir);
		positionProjectileLeft.put(dir, position.addVector(futurpos));
	}
	public void projectileRightNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dir = new Vector2();
		dir.addX(1);
		Vector2 futurpos = getNormalizedDirectionTear(dir);
		positionProjectileRight.put(dir, position.addVector(futurpos));
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	public Vector2 getNormalizedDirectionTear(Vector2 normalizedVector)
	{
		//Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speedTear);
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
	}
	public int getDateCycleInfo() {
		return dateCycleInfo;
	}
	public void setDateCycleInfo(int dateCycleInfo) {
		this.dateCycleInfo = dateCycleInfo;
	}
	
	
}
