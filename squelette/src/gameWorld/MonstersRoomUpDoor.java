package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpDoor extends MonstersRoom {

	public MonstersRoomUpDoor(Hero hero) {
		super(hero);
		addOpenDoorUpPhysics();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		drawCloseDoorUp();
		getHero().drawGameObject();
	}

}
