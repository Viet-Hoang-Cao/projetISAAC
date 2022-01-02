package gameWorld;

import gameobjects.Hero;

public class SpawnRoomLeftDoor extends SpawnRoom {

	public SpawnRoomLeftDoor(Hero hero) {
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
		drawOpenDoorLeft();
		getHero().drawGameObject();
	}

}
