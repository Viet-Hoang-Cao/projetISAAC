package gameWorld;

import gameobjects.Hero;

public class MonstersRoomRightDoor extends MonstersRoom {
	
	

	public MonstersRoomRightDoor(Hero hero) {
		super(hero);
	}
	
	
	
	public MonstersRoomRightDoor(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
	
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
