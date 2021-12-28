package gameWorld;

import java.util.LinkedList;

import gameobjects.Hero;
import gameobjects.Spider;
import resources.ImagePaths;
import resources.RoomInfos;
import resources.SpiderInfos;

//classe de test pour tes appels de monstres ;p

public class TESTROOM extends SpawnRoom {
	
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
	
	@Override
	public void drawRoom() {
		super.drawRoom();
		Spider spider1=new Spider(RoomInfos.POSITION_CENTER_OF_ROOM, SpiderInfos.SPIDER_SIZE, SpiderInfos.SPIDER_SPEED, ImagePaths.SPIDER);
		addmonster(spider1);
		drawmonsters();
	}

}
