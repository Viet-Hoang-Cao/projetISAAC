package gameWorld;

import gameobjects.Hero;

public class MonstersRoomDownLeftRightDoors extends MonstersRoom {

	public MonstersRoomDownLeftRightDoors(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomDownLeftRightDoors(Hero hero, int tileNumber) {
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
			drawCloseDoorLeft();
			drawCloseDoorRight();
			drawCloseDoorDown();
		}
		else {
			drawOpenDoorLeft();
			drawOpenDoorRight();
			drawOpenDoorDown();
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
			addOpenDoorDownPhysics();
			setClosed_door(false);
		}
	}

}
