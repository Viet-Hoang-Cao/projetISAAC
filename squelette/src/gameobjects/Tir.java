package gameobjects;

import java.util.HashMap;

import libraries.StdDraw;
import libraries.Vector2;
import resources.CycleInfos;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.CycleInfos;


public class Tir {
	private Vector2 direction;
	private Vector2 position;
	private Vector2 positionInitial;
	private Vector2 distance;
	private double speed;
	private int dateCycleInfo;
	private HashMap<Vector2, Vector2>positionProjectileUp; //<direction, <position, position initial>>
	private HashMap<Vector2, Vector2> positionProjectileDown;
	private HashMap <Vector2, Vector2> positionProjectileRight;
	private HashMap <Vector2, Vector2> positionProjectileLeft; 
	
	public Tir (Vector2 distance, double speed)
	{
		this.position= new Vector2();
		this.positionInitial= new Vector2();
		this.direction= new Vector2();
		this.distance=distance;
		this.speed=HeroInfos.TEAR_SPEED;
		this.dateCycleInfo = 20;
		this.positionProjectileUp= new HashMap<Vector2, Vector2>();
		this.positionProjectileDown= new HashMap<Vector2, Vector2>();
		this.positionProjectileRight= new HashMap<Vector2, Vector2>();
		this.positionProjectileLeft= new HashMap<Vector2, Vector2>();
	}
	
	public void updateTirLarme() {
		
	}
	
	public void drawLarme()
	{
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
	
	
	public Vector2 getNormalizedDirectionTear()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	public void projectileUpNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dir = new Vector2();
		dir.addY(1);
		Vector2 futurpos = getNormalizedDirectionTear();
		positionProjectileUp.put(dir, position.addVector(futurpos));
	}
	public void projectileDownNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dir = new Vector2();
		dir.addY(-1);
		Vector2 futurpos = getNormalizedDirectionTear();
		positionProjectileDown.put(dir, position.addVector(futurpos));
	}
	public void projectileLeftNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dir = new Vector2();
		dir.addX(-1);
		Vector2 futurpos = getNormalizedDirectionTear();
		positionProjectileLeft.put(dir, position.addVector(futurpos));
	}
	public void projectileRightNext() {
		dateCycleInfo=CycleInfos.Cycle;
		Vector2 dir = new Vector2();
		dir.addX(1);
		Vector2 futurpos = getNormalizedDirectionTear();
		positionProjectileRight.put(dir, position.addVector(futurpos));
	}
	
}
