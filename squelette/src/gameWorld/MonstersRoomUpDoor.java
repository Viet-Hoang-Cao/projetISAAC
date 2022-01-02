package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpDoor extends MonstersRoom {

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
		if(!getMonsters().isEmpty())drawCloseDoorUp();
		else drawOpenDoorUp();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty()) {
			addOpenDoorUpPhysics();
		}
	}

}
