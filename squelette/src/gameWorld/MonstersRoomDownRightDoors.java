package gameWorld;

import gameobjects.Hero;

public class MonstersRoomDownRightDoors extends MonstersRoom {

	public MonstersRoomDownRightDoors(Hero hero) {
		super(hero);
	}
	
	
	public MonstersRoomDownRightDoors(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
	
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
