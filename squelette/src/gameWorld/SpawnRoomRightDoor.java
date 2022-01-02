package gameWorld;

import gameobjects.Hero;

public class SpawnRoomRightDoor extends SpawnRoom {

	public SpawnRoomRightDoor(Hero hero) {
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
		drawOpenDoorRight();
		getHero().drawGameObject();
	}

}
