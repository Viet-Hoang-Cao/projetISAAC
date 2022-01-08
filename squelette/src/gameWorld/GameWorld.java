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
		for(int i =0; i<generationDJ.length;i++) {
			for(int j=0; j<generationDJ[i].length;j++) {
				if(generationDJ[j][i]==true) {
					switch(numberinstance(generationDJ, i, j)) {
					case 0 : 
						break;
					case 1 :
						break;
					case 2 :
						break;
					case 3 :
						break;
					case 4 :
						break;
					case 5 :
						break;
					case 6 :
						break;
					case 7 :
						break;
					case 8 :
						break;
					case 9 :
						break;
					case 10 :
						break;
					case 11 : 
						break;
					case 12 :
						break;
					default :
					}
				}
			}
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
	 */
	public int numberinstance(boolean [][]tab, int y, int x) {
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
			else if(x==tab[y].length) {
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
		else if(y==tab.length) {
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
			else if(x==tab[y].length) {
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
			else if (x==tab[y].length) {
				if(tab[y][x-1]==true && tab[y-1][x]==true && tab[y+1][x]==true) {
					return 9;
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
		if(hero.getLP()==0) {
			return true;
		}
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
