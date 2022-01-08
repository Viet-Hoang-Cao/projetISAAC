package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpDoor extends MonstersRoom {
	

	public MonstersRoomUpDoor(Hero hero, int tileNumber) {
		super(hero, tileNumber);
	}

	public MonstersRoomUpDoor(Hero hero) {
		super(hero);
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
