package gameWorld;

import java.util.LinkedList;

import gameobjects.Hero;
import gameobjects.Spider;
import resources.CycleInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import resources.SpiderInfos;

//classe de test pour tes appels de monstres ;p

public class TESTROOM extends SpawnRoom {
	
	private LinkedList<Spider> monsters;

	public TESTROOM(Hero hero) {
		super(hero);
		this.monsters = new LinkedList<Spider>();
		Spider spider1=new Spider(RoomInfos.POSITION_CENTER_OF_ROOM, SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		addmonster(spider1);
		// TODO Auto-generated constructor stub
	}
	
	public void addmonster(Spider monster) {
		this.monsters.add(monster);
	}
	
	public void drawmonsters() {
		for (Hero m: this.monsters) {
			m.drawGameObject();
		}
	}
	public void moveby1allMonsters() {
		for (Spider m: this.monsters) {
			m.moveby1();
		}
	}
	
	@Override
	public void drawRoom() {
		super.drawRoom();
		if (CycleInfos.Cycle%2==0)
		moveby1allMonsters();
		drawmonsters();
	}

}
