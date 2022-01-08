package gameWorld;

import gameobjects.Hero;

public class MonstersRoomRightDoor extends MonstersRoom {
	
	
	public MonstersRoomRightDoor(Hero hero, int tileNumber) {
		super(hero, tileNumber);
	}

	public MonstersRoomRightDoor(Hero hero) {
		super(hero);
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		if(isClosed_door())drawCloseDoorRight();
		else drawOpenDoorRight();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty() && isClosed_door()) {
			addOpenDoorRightPhysics();
			setClosed_door(false);
		}
	}

}
