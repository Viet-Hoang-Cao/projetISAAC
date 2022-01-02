package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class MonstersRoomUpDoor extends MonstersRoom {

	public MonstersRoomUpDoor(Hero hero, Vector2 tileNumber) {
		super(hero, tileNumber);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomUpDoor(Hero hero) {
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
		if(isClosed_door())drawCloseDoorUp();
		else drawOpenDoorUp();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty() && isClosed_door()) {
			addOpenDoorUpPhysics();
			setClosed_door(false);
		}
	}

}