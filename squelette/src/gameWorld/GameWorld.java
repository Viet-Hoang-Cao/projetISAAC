package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import resources.Controls;
import java.util.Random;
import libraries.Vector2;

import java.util.LinkedList;
import java.util.List;

public class GameWorld
{
	private Room currentRoom;
	private Hero hero;
	private Room[][] Donjon;
	private int LV;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		currentRoom = new Room(hero);
		this.Donjon = new Room[4][4];
		this.LV = 1;
	}
	
	public void createDungeon() {
		int numberOfRoom = giveNumberOfRoom(this.LV);
		Random rand = new Random();
		boolean[][] generationDJ = new boolean[4][4];
		int x = rand.nextInt(4);
		int y = rand.nextInt(4);
		generationDJ[y][x] = true;
		checkAroundandAdd(generationDJ, x, y, 1, numberOfRoom);
		List<Vector2> emplacementsSalles = new LinkedList<Vector2>();
		for(int i =0; i<generationDJ.length;i++) {
			for(int j=0; j<generationDJ[i].length;j++) {
				if(generationDJ[j][i]==true)emplacementsSalles.add(new Vector2(j, i));
			}
		}
		//TODO finir fonction
	}
	
	public void placeSpawnRoom(List<Vector2> emplacement, boolean [][]grille) {
		for (Vector2 v : emplacement) {
			if (v.getY()==0) {
				if(grille[(int)v.getY()+1][(int)v.getX()]==true) {
					this.Donjon[(int)v.getY()][(int)v.getX()] = new SpawnRoomDownDoor(hero);
					emplacement.remove(v);
					break;
				}
				if(v.getX()!=grille.length && grille[(int)v.getY()][(int)v.getX()+1]) {
					this.Donjon[(int)v.getY()][(int)v.getX()] = new SpawnRoomRightDoor(hero);
					emplacement.remove(v);
					break;
				}
				this.Donjon[(int)v.getY()][(int)v.getX()] = new SpawnRoomLeftDoor(hero);
				emplacement.remove(v);
				break;
			}
		}
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
	
	/**
	 * Renvoi l'emplacement du vector le plus loin par rapport a lui meme
	 * @param vList
	 * @param spawn
	 * @return le vector le plus loin
	 */
	public Vector2 mostfaraway(List<Vector2> vList, Vector2 spawn) {
		Vector2 vtmp =spawn;
		int dtmp =0;
		int d=0;
		for (Vector2 v : vList) {
			dtmp=Math.abs((int)(v.getX()-vtmp.getX())) + Math.abs((int)(v.getY()-v.getY())); 
			if(dtmp>d) {
				d=dtmp;
				vtmp =v;
			}
		}
		return vtmp;
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
		return false;
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
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
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
}
