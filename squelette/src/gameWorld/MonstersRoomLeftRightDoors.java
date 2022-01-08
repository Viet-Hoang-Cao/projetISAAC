package gameWorld;

import gameobjects.Hero;

public class MonstersRoomLeftRightDoors extends MonstersRoom {

	public MonstersRoomLeftRightDoors(Hero hero, int tileNumber) {
		super(hero, tileNumber);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomLeftRightDoors(Hero hero) {
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
		if(isClosed_door()) {
			drawCloseDoorLeft();
			drawCloseDoorRight();
		}
		else {
			drawOpenDoorLeft();
			drawOpenDoorRight();
		}
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty() && isClosed_door()) {
			addOpenDoorLeftPhysics();
			addOpenDoorRightPhysics();
			setClosed_door(false);
		}
	}
}
