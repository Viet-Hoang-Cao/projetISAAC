package gameWorld;

import gameobjects.Hero;

public class MonstersRoomRightDoor extends MonstersRoom {

	public MonstersRoomRightDoor(Hero hero) {
		super(hero);
		addOpenDoorRightPhysics();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		drawCloseDoorRight();
		getHero().drawGameObject();
	}
}
