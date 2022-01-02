package gameWorld;

import gameobjects.Hero;

public class MonstersRoomDownDoor extends MonstersRoom {

	public MonstersRoomDownDoor(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		drawCloseDoorDown();
		getHero().drawGameObject();
	}
}
