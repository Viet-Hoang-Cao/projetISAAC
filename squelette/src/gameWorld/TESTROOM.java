package gameWorld;

import java.util.LinkedList;

import gameobjects.Hero;

//classe de test pour tes appels de monstres ;p

public class TESTROOM extends RoomBase {
	
	private LinkedList<Hero> monsters;

	public TESTROOM(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Hero>();
		// TODO Auto-generated constructor stub
	}
	
	public void addmonster(Hero monster) {
		this.monsters.add(monster);
	}
	
	public void drawmonsters() {
		for (Hero m: this.monsters) {
			m.drawGameObject();
		}
	}

}
