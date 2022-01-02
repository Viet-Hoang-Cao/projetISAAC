package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class MonstersRoomLeftDoor extends MonstersRoom {

	public MonstersRoomLeftDoor(Hero hero, Vector2 tileNumber) {
		super(hero, tileNumber);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomLeftDoor(Hero hero) {
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
		if(isClosed_door())drawCloseDoorLeft();
		else drawOpenDoorLeft();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty() && isClosed_door()) {
			addOpenDoorLeftPhysics();
			setClosed_door(false);
		}
	}

}
