package gameWorld;

import gameobjects.Hero;

public class SpawnRoomDownDoor extends SpawnRoom {

	public SpawnRoomDownDoor(Hero hero) {
		super(hero);
		addOpenDoorDownPhysics();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		drawOpenDoorDown();
		getHero().drawGameObject();
	}

}
