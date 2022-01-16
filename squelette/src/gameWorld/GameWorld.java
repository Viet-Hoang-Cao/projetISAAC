package gameWorld;

import gameobjects.Bidulf;
import gameobjects.Hero;
import libraries.StdDraw;
import resources.Controls;
import resources.CycleInfos;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

import java.util.Random;

import libraries.Vector2;

import java.util.LinkedList;
import java.util.List;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;
	private MonstersRoom[][] Donjon;
	private boolean[][] generationDJ;
	private int LV;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		//currentRoom = new Room(hero);
		this.Donjon = new MonstersRoom[4][4];
		generationDJ = new boolean[this.Donjon.length][this.Donjon.length];
		this.LV = 1;
		createDungeon();
		this.currentRoom = getDJSpawnRoom(); 
		
		//Par manque de temps je vais dire que la fonction create dungeon ne risque pas d'avoir de pb
		//Note à soi-même : Arrêter d'avoir confiance en soi.
		
	}
	
	/**
	 * Creation des rooms dans le donjons de facon aleatoire
	 */
	public void createDungeon() {
		int numberOfRoom = giveNumberOfRoom(this.LV);
		Random rand = new Random();
		int x = rand.nextInt(this.Donjon.length);
		int y = rand.nextInt(this.Donjon.length);
		generationDJ[y][x] = true;
		checkAroundandAdd(generationDJ, x, y, 1, numberOfRoom);
		for(int i =0; i<generationDJ.length;i++) {
			for(int j=0; j<generationDJ[i].length;j++) {
				if(generationDJ[i][j]==true) {
					createInstance(number_typeofinstance(generationDJ, i, j), i , j);
				}
			}
		}
		List<MonstersRoom> roomListDJ = createRoomList();
		//Le spawn va etre defini aleatoirement
		MonstersRoom spawn = roomListDJ.get(rand.nextInt(roomListDJ.size()));
		spawn.setSpawnRoom(true);
		mostfarawayfrom(roomListDJ, spawn).setBossRoom(true); // on choisit la room la plus eloigne
		//cailloux ? caillou. 
		for(MonstersRoom r : roomListDJ) {
			if(!r.isBossRoom() && !r.isMerchantRoom() && !r.isSpawnRoom()) {
				r.generateRock();
				r.generateSpikes();
				r.generate_monsters();
			}
			if(r.isBossRoom()) {
				r.addmonster(new Bidulf(r.positionFromTileIndex(4, 4)));
			}
		}
	}
	
	
	
	public void createInstance(int type, int y, int x) {
		switch(type) {
		case 0 :
			this.Donjon[y][x] = new MonstersRoomUpRightLeftDownDoors(this.hero, y, x);
			break;
		case 1 :
			this.Donjon[y][x] = new MonstersRoomDownRightDoors(this.hero,  y, x);
			break;
		case 2 :
			this.Donjon[y][x] = new MonstersRoomDownLeftDoors(this.hero,  y, x);
			break;
		case 3 :
			this.Donjon[y][x] = new MonstersRoomDownLeftRightDoors(this.hero,  y, x);
			break;
		case 4 :
			this.Donjon[y][x] = new MonstersRoomRightDoor(this.hero, y, x);
			break;
		case 5 :
			this.Donjon[y][x] = new MonstersRoomDownDoor(this.hero, y, x);
			break;
		case 6 :
			this.Donjon[y][x] = new MonstersRoomUpDoor(this.hero, y, x);
			break;
		case 7 :
			this.Donjon[y][x] = new MonstersRoomLeftDoor(this.hero, y, x);
			break;
		case 8 :
			this.Donjon[y][x] = new MonstersRoomUpLeftDoors(this.hero, y, x);
			break;
		case 9 :
			this.Donjon[y][x] = new MonstersRoomUpLeftDownDoors(this.hero, y, x);
			break;
		case 10 :
			this.Donjon[y][x] = new MonstersRoomUpRightDoors(this.hero, y, x);
			break;
		case 11 : 
			this.Donjon[y][x] = new MonstersRoomUpRightDownDoors(this.hero, y, x);
			break;
		case 12 :
			this.Donjon[y][x] = new MonstersRoomUpRightLeftDoors(this.hero, y, x);
			break;
		case 13 :
			this.Donjon[y][x] = new MonstersRoomDownUpDoors(this.hero, y, x); 
			break;
		case 14 :
			this.Donjon[y][x] = new MonstersRoomLeftRightDoors(this.hero, y, x);
			break;
		default :
			this.Donjon[y][x]=null;
		}
	}
	
	/**
	 * Cette fonction n'est pas optimisé //TODO à refaire si possible
	 * Cette fonction renvoi un nombre correspondant à l'instance qui doit etre ajoute
	 * par exemple : 
	 * -1 -> room is bugged, please delete it
	 * 0  -> UpRightLeftDownDoors
	 * 1  -> DownRight
	 * 2  -> DownLeft
	 * 3  -> DownLeftRight
	 * 4  -> Right
	 * 5  -> Down
	 * 6  -> Up
	 * 7  -> Left
	 * 8  -> UpLeft
	 * 9  -> UpLeftDown
	 * 10 -> UpRight
	 * 11 -> UpRightDown
	 * 12 -> UpRightLeft
	 * 13 -> DownUp
	 * 14 -> LeftRight
	 */
	public int number_typeofinstance(boolean [][]tab, int y, int x) {
		if(y==0) {
			if (x==0) {
				if(tab[y][x+1]==true && tab[y+1][x]==true) {
					return 1;
				}
				else if (tab[y][x+1]==true) {
					return 4;
				}
				else if(tab[y+1][x]==true) {
					return 5;
				}
				else return -1;
			}
			else if(x==tab[y].length-1) {
				if(tab[y][x-1]==true && tab[y+1][x]==true) {
					return 2;
				}
				else if (tab[y][x-1]==true) {
					return 7;
				}
				else if(tab[y+1][x]==true) {
					return 5;
				}
				else return -1;
			}
			else {
				if(tab[y][x+1]==true && tab[y+1][x]==true && tab[y][x-1]==true ) {
					return 3;
				}
				else if(tab[y][x+1]==true && tab[y][x-1]==true) {
					return 14;
				}
				else if(tab[y][x-1]==true && tab[y+1][x]==true) {
					return 2;
				}
				else if(tab[y][x+1]==true && tab[y+1][x]==true) {
					return 1;
				}
				else if (tab[y][x-1]==true) {
					return 7;
				}
				else if(tab[y+1][x]==true) {
					return 5;
				}
				else if(tab[y][x+1]==true) {
					return 4;
				}
				else return -1;
			}
		}
		else if(y==tab.length-1) {
			if (x==0) {
				if(tab[y][x+1]==true && tab[y-1][x]==true) {
					return 10;
				}
				else if (tab[y][x+1]==true) {
					return 4;
				}
				else if(tab[y-1][x]==true) {
					return 6;
				}
				else return -1;
			}
			else if(x==tab[y].length-1) {
				if(tab[y][x-1]==true && tab[y-1][x]==true) {
					return 8;
				}
				else if (tab[y][x-1]==true) {
					return 7;
				}
				else if(tab[y-1][x]==true) {
					return 6;
				}
				else return -1;
			}
			else {
				if(tab[y][x+1]==true && tab[y-1][x]==true && tab[y][x-1]==true ) {
					return 12;
				}
				else if(tab[y][x+1]==true && tab[y][x-1]==true) {
					return 14;
				}
				else if(tab[y][x-1]==true && tab[y-1][x]==true) {
					return 8;
				}
				else if(tab[y][x+1]==true && tab[y-1][x]==true) {
					return 10;
				}
				else if (tab[y][x-1]==true) {
					return 7;
				}
				else if(tab[y-1][x]==true) {
					return 6;
				}
				else if(tab[y][x+1]==true) {
					return 4;
				}
				else return -1;
			}
		}
		else {
			if(x==0) {
				if(tab[y][x+1]==true && tab[y-1][x]==true && tab[y+1][x]==true) {
					return 11;
				}
				else if(tab[y-1][x]==true && tab[y+1][x]==true) {
					return 13;
				}
				else if(tab[y][x+1]==true && tab[y-1][x]==true) {
					return 10;
				}
				else if(tab[y][x+1]==true && tab[y+1][x]==true) {
					return 1;
				}
				else if (tab[y][x+1]==true) {
					return 4;
				}
				else if(tab[y+1][x]==true) {
					return 5;
				}
				else if(tab[y-1][x]==true) {
					return 6;
				}
				else return -1;
			}
			else if (x==tab[y].length-1) {
				if(tab[y][x-1]==true && tab[y-1][x]==true && tab[y+1][x]==true) {
					return 9;
				}
				else if(tab[y-1][x]==true && tab[y+1][x]==true) {
					return 13;
				}
				else if(tab[y][x-1]==true && tab[y-1][x]==true) {
					return 8;
				}
				else if(tab[y][x-1]==true && tab[y+1][x]==true) {
					return 2;
				}
				else if (tab[y][x-1]==true) {
					return 7;
				}
				else if(tab[y+1][x]==true) {
					return 5;
				}
				else if(tab[y-1][x]==true) {
					return 6;
				}
				else return -1;
			}
			else {
				if(tab[y][x+1]==true && tab[y-1][x]==true && tab[y+1][x]==true && tab[y][x-1]==true) {
					return 1;
				}
				else if (tab[y][x+1]==true && tab[y-1][x]==true && tab[y+1][x]==true) {
					return 11;
				}
				else if(tab[y-1][x]==true && tab[y+1][x]==true && tab[y][x-1]==true) {
					return 9;
				}
				else if (tab[y][x+1]==true && tab[y-1][x]==true && tab[y][x-1]==true ) {
					return 12;
				}
				else if(tab[y][x+1]==true && tab[y+1][x]==true && tab[y][x-1]==true ) {
					return 3;
				}
				else if(tab[y-1][x]==true && tab[y+1][x]==true) {
					return 13;
				}
				else if(tab[y][x+1]==true && tab[y][x-1]==true) {
					return 14;
				}
				else if(tab[y][x-1]==true && tab[y-1][x]==true) {
					return 8;
				}
				else if(tab[y][x-1]==true && tab[y+1][x]==true) {
					return 2;
				}
				else if(tab[y][x+1]==true && tab[y-1][x]==true) {
					return 10;
				}
				else if(tab[y][x+1]==true && tab[y+1][x]==true) {
					return 1;
				}
				else if (tab[y][x-1]==true) {
					return 7;
				}
				else if(tab[y+1][x]==true) {
					return 5;
				}
				else if(tab[y-1][x]==true) {
					return 6;
				}
				else if (tab[y][x+1]==true) {
					return 4;
				}
			}
		}
		return -1;
	}
	
	/**
	 * cree les 1 autour du premier 1 du tableau | Une premiere valeur a 1 doit  etre instanciee !
	 * @param genTab tableau de boolean, les x, y sont l'emplacement du premier 1
	 * @param count doit etre a 1
	 * @param nbR
	 */
	public void checkAroundandAdd(boolean[][] genTab, int x, int y, int count, int nbR ) {
		Random rand = new Random();
		boolean jobdone = false;
		if(!(count==nbR)) {
			while(!jobdone) {
				int ran = rand.nextInt(4);
				switch (ran) {
				case 1 : 
					if(x!=0 && !genTab[y][x-1]) {
						genTab[y][x-1] = true;
						jobdone = true;
						checkAroundandAdd(genTab, x-1, y, count+1, nbR);
					}
					break;
				case 2 :
					if(x!=genTab[y].length-1 && !genTab[y][x+1]) {
						genTab[y][x+1] = true;
						jobdone = true;
						checkAroundandAdd(genTab, x+1, y, count+1, nbR);
					}
					break;
				case 3 :
					if(y!=0 && !genTab[y-1][x]) {
						genTab[y-1][x] = true;
						jobdone = true;
						checkAroundandAdd(genTab, x, y-1, count+1, nbR);
					}
					break;
				default :
					if(y!=genTab.length-1 && !genTab[y+1][x]) {
						genTab[y+1][x] = true;
						jobdone = true;
						checkAroundandAdd(genTab, x, y+1, count+1, nbR);
					}
				}
			}
		}
	}
	
	public Room getDJSpawnRoom() {
		for(int i =0; i<this.Donjon.length;i++) {
			for(int j=0; j<this.Donjon[i].length;j++) {
				if(this.Donjon[i][j]!=null && this.Donjon[i][j].isSpawnRoom())return this.Donjon[i][j];
			}
		}
		return null;
	}
	
	/**
	 * Renvoi l'emplacement du vector le plus loin par rapport a lui meme
	 * @param vList
	 * @param spawn
	 * @return le vector le plus loin
	 */
	public MonstersRoom mostfarawayfrom(List<MonstersRoom> rList, MonstersRoom Room) {
		MonstersRoom rtmp = Room;
		Vector2 spawn =new Vector2(rtmp.getTileNumberX(), rtmp.getTileNumberY());
		Vector2 vtmp = new Vector2();
		double dtmp=0;
		for (MonstersRoom r : rList) {
			vtmp = new Vector2(r.getTileNumberX(), r.getTileNumberY());
			if(spawn.distance(vtmp)>dtmp) {
				dtmp = spawn.distance(vtmp);
				rtmp =r;
			}
		}
		return rtmp;
	}
	
	/**
	 * @param x la piece d'origine
	 * @param roomList La liste des piece a tester
	 * @return le x le plus grand par rapport au x d'origine
	 */
	public int mostfarawayX(int x, List<SpawnRoom>roomList) {
		int retour =x;
		for (SpawnRoom r : roomList) {
			if(r.getTileNumberX()>retour) {
				retour = r.getTileNumberX();
			}
		}
		return retour;
	}
	/**
	 * @param y la piece d'origine
	 * @param roomList La liste des pieces a tester
	 * @return le y le plus grand par rapport au x d'origine
	 */
	public int mostfarawayY(int y, List<SpawnRoom>roomList) {
		int retour =y;
		for (SpawnRoom r : roomList) {
			if(r.getTileNumberY()>retour) {
				retour = r.getTileNumberX();
			}
		}
		return retour;
	}
	
	public List<MonstersRoom> createRoomList(){
		List<MonstersRoom>rList = new LinkedList<>();
		for(int i =0; i<this.Donjon.length;i++) {
			for(int j=0; j<this.Donjon[i].length;j++) {
				if(this.Donjon[i][j]!= null)rList.add((MonstersRoom)this.Donjon[i][j]);
			}
		}
		
		return rList;
	}
	
	/**
	 * donne le nombre de room par rapport au LV
	 * @param LV le niveau du DJ
	 * @return le nombre de room correspondant
	 */
	public int giveNumberOfRoom(int LV) {
		switch(LV) {
		case 1: return 5;
		case 2: return 6;
		case 3: return 7;
		case 4: return 8;
		case 5: return 9;
		default : return -1;
		}
	}

	public void processUserInput()
	{
		processKeysForMovement();
	}

	public boolean gameOver()
	{
		if(hero.getLP()<=0) {
			return true;
		}
		return false;
	}

	public void updateGameObjects()
	{	
		int x = currentRoom.changecurrentRoomX();
		int y = currentRoom.changecurrentRoomY();
		if( x != 0 || y !=0){
			this.setCurrentRoom(Donjon[currentRoom.tileNumberY+y]
					[currentRoom.tileNumberX+x]);
			if(x==1) {
				hero.setPosition(currentRoom.positionFromTileIndex(1, 4));
			}
			else if(x==-1) {
				hero.setPosition(currentRoom.positionFromTileIndex(7, 4));
			}
			else if(y==1) { //note, la maniere dont a ete construit le dj correspond a un affichage console
				hero.setPosition(currentRoom.positionFromTileIndex(4, 7));
			}
			else {
				hero.setPosition(currentRoom.positionFromTileIndex(4, 1));
			}
			
		}
		currentRoom.updateRoom();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
		currentRoom.draw_dungeon(this.Donjon);
	}
	

	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
		if(StdDraw.isKeyPressed(Controls.goInvicible)) {
			hero.invunerable();
		}
		if (StdDraw.isKeyPressed(Controls.goLight))
		{
			hero.SPPEEEEEEEEEEEEEEED_ON_OFF();
		}
		if (StdDraw.isKeyPressed(Controls.goKillAll))
		{
			currentRoom.killAllMonster();
		}
		if (StdDraw.isKeyPressed(Controls.goOfferGold))
		{
			hero.getInventaire().addDime();
		}
		if (StdDraw.isKeyPressed(Controls.goPowerfull))
		{
			hero.POWWWW_ON_OFF();
		}
		if (StdDraw.isKeyPressed(Controls.directionalKeyUp))
		{
			if(hero.getDateCycleInfo() + 10 < CycleInfos.Cycle) { // on passe de 20 a 10 cycle parce qu'on veut tirer les larmes plus rapides
				hero.projectileUpNext();
			}
		}
		if (StdDraw.isKeyPressed(Controls.directionalKeyDown))
		{
			if(hero.getDateCycleInfo() + 10 < CycleInfos.Cycle) {
				hero.projectileDownNext();
			}
		}
		if (StdDraw.isKeyPressed(Controls.directionalKeyLeft))
		{
			if(hero.getDateCycleInfo() + 10 < CycleInfos.Cycle) {
				hero.projectileLeftNext();
			}
		}
		if (StdDraw.isKeyPressed(Controls.directionalKeyRight))
		{
			if(hero.getDateCycleInfo() + 10 < CycleInfos.Cycle) {
				hero.projectileRightNext();
			}
		}
	}
	

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
}
