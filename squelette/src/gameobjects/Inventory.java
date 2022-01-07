package gameobjects;

public class Inventory {
	private int nbKeys;
	private int nbBombs;
	private int gold;
	
	public Inventory() {
		this.nbKeys=0;
		this.nbBombs=1;
		this.gold=0;
	}
	public Inventory(int nbKeys, int nbBombs, int gold) {
		this.nbKeys=nbKeys;
		this.nbBombs=nbBombs;
		this.gold=gold;
	}
	
	public int getNbKeys() {
		return nbKeys;
	}
	public void setNbKeys(int nbKeys) {
		this.nbKeys = nbKeys;
	}
	public int getNbBombs() {
		return nbBombs;
	}
	public void setNbBombs(int nbBombs) {
		this.nbBombs = nbBombs;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void addPenny() {
		this.gold++;
		if(this.gold>99) {
			this.gold=99;
		}
	}
	public void addNickel() {
		this.gold+=5;
		if(this.gold>99) {
			this.gold=99;
		}
	}
	public void addDime() {
		this.gold+=10;
		if(this.gold>99) {
			this.gold=99;
		}
	}
	
	public void addBomb() {
		this.nbBombs++;
	}
	
	public void addKeys() {
		this.nbKeys++;
	}
	

}
