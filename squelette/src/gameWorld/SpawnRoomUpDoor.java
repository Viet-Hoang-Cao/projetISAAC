package gameWorld;

import gameobjects.Hero;

public class SpawnRoomUpDoor extends SpawnRoom {

	public SpawnRoomUpDoor(Hero hero) {
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
		drawOpenDoorUp();
		getHero().drawGameObject();
	}

}
