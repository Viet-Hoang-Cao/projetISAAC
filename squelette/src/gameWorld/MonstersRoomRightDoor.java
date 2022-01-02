package gameWorld;

import gameobjects.Hero;

public class MonstersRoomRightDoor extends MonstersRoom {

	public MonstersRoomRightDoor(Hero hero) {
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
		if(!getMonsters().isEmpty())drawCloseDoorRight();
		else drawOpenDoorRight();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty()) {
			addOpenDoorRightPhysics();
		}
	}
}
