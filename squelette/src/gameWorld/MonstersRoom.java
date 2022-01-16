package gameWorld;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import gameobjects.Bidulf;
import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Spider;
import gameobjects.Tear;
import items.Item;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.CycleInfos;
import resources.FlyInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import resources.SpiderInfos;

public class MonstersRoom extends SpawnRoom {

	private LinkedList<Hero> monsters;
	private LinkedList<Spikes> spikes;
	private LinkedList<Cailloux> rocks;
	private boolean closed_door;
	private boolean spawnRoom;
	private boolean bossRoom;
	private boolean merchantRoom;
	private MerchantRoom magasin;
	private boolean Itemdropped;
	
	public MonstersRoom(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
		this.monsters      = new LinkedList<Hero>();
		this.spikes        = new LinkedList<Spikes>();
		this.rocks         = new LinkedList<Cailloux>();
		this.closed_door   = true;
		this.spawnRoom     = false;
		this.bossRoom      = false;
		this.merchantRoom  = false;
		this.Itemdropped   = false; 
	}

	public MonstersRoom(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		this.spikes = new LinkedList<Spikes>();
		this.rocks = new LinkedList<Cailloux>();
		this.closed_door=true;
		this.spawnRoom=false;
		this.bossRoom=false;
		this.merchantRoom=false;
		this.Itemdropped=false;
	}
	
	@Override
	 //Make every entity that compose a room process one step
	public void updateRoom()
	{	
		/*
		 *TODO A refaire, optimisation des differentes boucles for en une -> permetrait de moins ralentir le jeu
		 *-> pas apercu du probleme, mais sur des pc plus faible que le mien(), ca fera la difference
		 */
		
		//deplacement des montres
		moveMonsters();
		
		//check collision avec caillou monstre et hero
		for(Cailloux rock: rocks) {
			rock.collision_rocks(getHero());
			for(Hero m : monsters) {
				rock.collision_rocks(m);
			}
		}
		//check collision monstres
		for(Wall w : walls) {
			for(Hero m : monsters) {
				w.collisionWalls(m);
			}
		}
		//update des degats des monstres + frame invu
		updateDamage();
		if(CycleInfos.Cycle > getHero().getDateCycleInvulnerabilityStart()+15 && getHero().isTempInvunerability()) {
			getHero().setTempInvunerability(false);
		}
		
		//degats des larmes du hero
		for(Hero m : monsters) {
			for(Tear t : getHero().getTears()) {
				if(t.PhysicTear(m)) {
					m.takeDamage(getHero().getDamage());
					/*m.setTempInvunerability(true);*/
					getHero().getTears().remove(t);
					break;
				}
			}
		}
		
		//enlever invu tempo des monstres /TODO ne fonctionne pas
		/*for(Hero m : monsters) {
			if(CycleInfos.Cycle > m.getDateCycleInvulnerabilityStart()+30 && m.isTempInvunerability()) {
				m.setTempInvunerability(false);
			}
		}*/
		
		//update des monstres
		for (Hero m : monsters) {
			if(m.getLP()<0) {
				monsters.remove(m);
				break;
			}
			m.updateGameObject();
		}
		
		//update magasin
		if(isMerchantRoom()) {
			magasin.updateRoom(getHero());
		}
		
		super.updateRoom();
	}
	
	@Override
	public void drawRoom() {
		super.drawRoom();
		drawRocks();
		drawSpikes();
		if(isSpawnRoom()) {
			StdDraw.text(0.5, 0.5, "SPAWN" );
			StdDraw.text(positionFromTileIndex(4, 3).getX(), positionFromTileIndex(4, 3).getY(), 
					"Appuyer sur E pour utiliser les bombes");
		}
		if(isBossRoom()) {
			if(monsters.isEmpty()) {
				StdDraw.text(0.5, 0.5, "WIN");
			}
			else StdDraw.text(0.5, 0.5, "BOSS");
		}
		if(isMerchantRoom()) {
			magasin.drawRoom();
			StdDraw.text(0.5, 0.5, "MERCHANTROOM");
		}
		drawmonsters();
		getHero().getInventaire().drawInventory(positionFromTileIndex(1, 7).getX(), positionFromTileIndex(1, 7).getY());
	}
	
	@Override
	/**
	 * tue tout les monstres.
	 */
	public void killAllMonster() {
		monsters.clear();
	}
	
	/**
	 * draw the monsters
	 */
	public void drawmonsters() {
		for (Hero m: this.monsters) {
			m.drawGameObject();
		}
	}
	/**
	 * @param Boss un monstre de type boss1.
	 */
	public void addmonster(Hero monster) {
		monsters.add(monster);
	}

	public boolean posinMonstersList(Vector2 pos) {
		for(Hero m : monsters) {
			if(m.getPosition().getX() == pos.getX() && m.getPosition().getY() == pos.getY()) {
				return true;
			}
		}
		return false;
	}
	public void generate_monsters() {
		Random rand = new Random();
		int nbMonsterSpider = rand.nextInt(4);
		int nbMonsterFly = rand.nextInt(3);
		Vector2 pos = positionAlea();
		for(int i=0; i<nbMonsterSpider; i++) {
			while(posinMonstersList(pos) || inRockList(pos)) {
				pos = positionAlea();
			}
			addmonster(new Spider(pos, SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER));
		}
		for(int i=0; i<nbMonsterFly; i++) {
			while(posinMonstersList(pos) || inRockList(pos)) {
				pos = positionAlea();
			}
			addmonster(new Fly(pos, FlyInfos.FLY_SIZE, FlyInfos.FLY_SPEED, ImagePaths.FLY));
		}
	}
	
	public void moveMonsters() {
		Random rand = new Random();
		for (Hero m : this.monsters) {
			if(m instanceof Bidulf || m instanceof Fly) {
				m.moveToPosition(getHero().getPosition(), getHero().getSize());
			}
			if(m instanceof Spider) {
				if (CycleInfos.Cycle % 75 == 0) {
					Vector2 pos = positionFromTileIndex(rand.nextInt(7) + 1, rand.nextInt(7) + 1);
					((Spider) m).setPOSITIONTOGO(pos);
				}
				m.moveToPosition(((Spider) m).getPOSITIONTOGO(), RoomInfos.HALF_TILE_SIZE);
			}
		}
	}
	
	
	public void generateRock() {
		Random rand = new Random();
		int nb = rand.nextInt(6);//ne pas trop mettre de rocher. On ne check pas si Isaac a un path ni si l'on excede le nbre de case!!
		//nb = 45; -> permet de faire des test rapide; 49cases -4 = 45; 45 = maxvalue
		int x = rand.nextInt(7)+1;
		int y = rand.nextInt(7)+1;
		for(int i = 0; i< nb && i<45; i++) {
			while((x == 4 && y == 1) || (x == 4 && y == 7) || (x == 1 && y == 4) || (x == 7 && y == 4) || 
					inRockList(positionFromTileIndex(x, y))){
					x = rand.nextInt(7)+1;
					y = rand.nextInt(7)+1;
			}
			rocks.add(new Cailloux(positionFromTileIndex(x, y)));
			x = rand.nextInt(7)+1;
			y = rand.nextInt(7)+1;
		}
	}
	
	public boolean inRockList(Vector2 pos) {
		for(Cailloux rock : rocks) {
			if(pos.getX() == rock.getPos().getX() && pos.getY() == rock.getPos().getY()) return true;
		}
		return false;
	}
	
	/**
	 * draw all rocks
	 */
	public void drawRocks() {
		for(Cailloux rock: rocks) {
			StdDraw.picture(rock.getPos().getX(), rock.getPos().getY(), ImagePaths.ROCK, 
					RoomInfos.TILE_SIZE.getX(), RoomInfos.TILE_SIZE.getY());
		}
	}

	/**
	 * delete rock X,Y(tile position)
	 */
	public void deleteRockPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		for(Cailloux rock : rocks) {
			if(pos.getX() == rock.getPos().getX() && pos.getY() == rock.getPos().getY()) 
				rocks.remove(rock);
		}
	}
	
	
	public void generateSpikes() {
		Random rand = new Random();
		int nb = rand.nextInt(4); // attention, 45 - rockList = nb d'emplacement restant !
		int x = rand.nextInt(7)+1;
		int y = rand.nextInt(7)+1;
		for(int i = 0; i< nb && i<45; i++) {
			while((x == 4 && y == 1) || (x == 4 && y == 7) || (x == 1 && y == 4) || (x == 7 && y == 4) || 
					inRockList(positionFromTileIndex(x, y)) || inSpikesList(x, y)){
					x = rand.nextInt(7)+1;
					y = rand.nextInt(7)+1;
			}
			addSpikesPhysics(x, y);
			x = rand.nextInt(7)+1;
			y = rand.nextInt(7)+1;
		}
	}
	
	public boolean inSpikesList(Vector2 pos) {
		for(Spikes spike : spikes) {
			if(pos.getX() == spike.getPos().getX() && pos.getY() == spike.getPos().getY())return true;
		}
		return false;
	}
	
	public boolean inSpikesList(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		for(Spikes spike : spikes) {
			if(pos.getX() == spike.getPos().getX() && pos.getY() == spike.getPos().getY())return true;
		}
		return false;
	}

	/**
	 * draw all spikes
	 */
	public void drawSpikes() {
		for(Spikes spike : spikes) {
			spike.drawSpikes();
		}
	}
	/**
	 * add spikes physics X,Y (tile position)
	 */
	public void addSpikesPhysics(int x, int y) {
		Vector2 pos = positionFromTileIndex(x, y);
		spikes.add(new Spikes(pos));
	}
	
	
	public boolean inpositionItemList(Vector2 pos) {
		for(Item I : getItemRoomList()) {
			if(pos.getX() == I.getPos().getX() && pos.getY() == I.getPos().getY())return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return un emplacement innocupe
	 */
	public Vector2 emplacement_libre() {
		Random rand = new Random();
		Vector2 pos = positionFromTileIndex(rand.nextInt(7)+1, rand.nextInt(7)+1);
		while(inRockList(pos)|| inSpikesList(pos)|| inpositionItemList(pos)) {
			pos = positionFromTileIndex(rand.nextInt(7)+1, rand.nextInt(7)+1);
		}
		return pos;
	}
	
	/**
	 * draw a closed door in top position
	 */
	public void drawCloseDoorUp() {
		Vector2 pos = positionFromTileIndex(4, 8);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 0);
	}
	
	/**
	 * draw a closed door in down position
	 */	
	public void drawCloseDoorDown() {
		Vector2 pos = positionFromTileIndex(4, 0);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 180);
	}
	
	/**
	 * draw a closed door in left position
	 */
	public void drawCloseDoorLeft() {
		Vector2 pos = positionFromTileIndex(0, 4);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 90);
	}
	
	/**
	 * draw a closed door in right position
	 */
	public void drawCloseDoorRight() {
		Vector2 pos = positionFromTileIndex(8, 4);
		StdDraw.picture(pos.getX(), pos.getY(), ImagePaths.CLOSED_DOOR,
				RoomInfos.TILE_SIZE.getX()*1.5,RoomInfos.TILE_SIZE.getY()*1.1, 270);
	}
	
	
	/**
	 * Cette methode fait en sorte que le hero ne prennent pas continuellement des degats et traites de toutes les forme de damage.
	 * Il faut appeler ici toutes formes de damage potentiel, monstres, spikes, projectile envers le Hero.
	 */
	public void updateDamage() {
		if(!getHero().isInvicible() && !getHero().isTempInvunerability()) {
			for(Hero m:this.monsters) {
				if(Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
						m.getPosition(), m.getSize())) {
					getHero().takeDamage(m.getDamage());
					getHero().setTempInvunerability(true);
				}
				for(Tear t : m.getTears()) {
					if(t.PhysicTear(getHero())) {
						getHero().takeDamage(m.getDamage());
						getHero().setTempInvunerability(true);
					}
				}
			}
			for(Spikes spike: spikes) {
				if(spike.spikesCollisions(getHero())) {
					getHero().takeDamage(1);
					getHero().setTempInvunerability(true);
				}
			}
		}
	}
	
	
	/**
	 *  genere une position aleatoire mais qui n'est pas le numero de la tuile
	 * @return une position aleatoire
	 */
	public Vector2 positionAlea() {
		Random rand = new Random();
		Vector2 pos = positionFromTileIndex(rand.nextInt(7) + 1, rand.nextInt(7) + 1);
		return pos;
	}

	public LinkedList<Hero> getMonsters() {
		return monsters;
	}

	public boolean isClosed_door() {
		return closed_door;
	}

	public void setClosed_door(boolean closed_door) {
		this.closed_door = closed_door;
	}
	
	public boolean isBossRoom() {
		return bossRoom;
	}

	public void setBossRoom(boolean bossRoom) {
		this.bossRoom = bossRoom;
	}

	public boolean isSpawnRoom() {
		return spawnRoom;
	}

	public void setSpawnRoom(boolean spawnRoom) {
		this.spawnRoom = spawnRoom;
	}
	

	public boolean isItemdropped() {
		return Itemdropped;
	}

	public void setItemdropped(boolean itemdropped) {
		Itemdropped = itemdropped;
	}

	public boolean isMerchantRoom() {
		return merchantRoom;
	}

	public void setMerchantRoom(boolean merchantRoom) {
		this.merchantRoom = merchantRoom;
	}
	
	
	/**
	 * setup du magasin
	 */
	public void setupmagasin(List<Item> ItemAVendre) {
		int i =0;
		for(Item I : ItemAVendre) {
			I.setPos(positionFromTileIndex(2+i, 3)); //La liste devrait faire 3 items
			i+=2;
		}
		this.magasin= new MerchantRoom(ItemAVendre);
	}
	

}
