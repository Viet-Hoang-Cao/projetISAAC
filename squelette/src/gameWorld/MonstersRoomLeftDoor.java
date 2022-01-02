package gameWorld;

import gameobjects.Hero;

public class MonstersRoomLeftDoor extends MonstersRoom {

	public MonstersRoomLeftDoor(Hero hero) {
		super(hero);
		addOpenDoorLeftPhysics();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		drawCloseDoorLeft();
		getHero().drawGameObject();
	}

}
