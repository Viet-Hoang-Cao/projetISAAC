package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpRightDownDoors extends MonstersRoom {

	public MonstersRoomUpRightDownDoors(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomUpRightDownDoors(Hero hero, int tileNumber) {
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
			drawCloseDoorUp();
			drawCloseDoorRight();
		}
		else {
			drawOpenDoorDown();
			drawOpenDoorUp();
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
			addOpenDoorUpPhysics();
			addOpenDoorRightPhysics();
			setClosed_door(false);
		}
	}

}
