package gameWorld;

import gameobjects.Hero;

public class MonstersRoomDownRightDoors extends MonstersRoom {

	public MonstersRoomDownRightDoors(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomDownRightDoors(Hero hero, int tileNumber) {
		super(hero, tileNumber);
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
			drawCloseDoorDown();
			drawCloseDoorRight();
		}
		else {
			drawOpenDoorDown();
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
			addOpenDoorDownPhysics();
			addOpenDoorRightPhysics();
			setClosed_door(false);
		}
	}

}
