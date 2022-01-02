package gameWorld;

import gameobjects.Hero;

public class MonstersRoomLeftDoor extends MonstersRoom {

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
		if(!getMonsters().isEmpty())drawCloseDoorLeft();
		else drawOpenDoorLeft();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty()) {
			addOpenDoorLeftPhysics();
		}
	}

}
