package gameWorld;

import gameobjects.Hero;

public class MonstersRoomDownLeftRightDoors extends MonstersRoom {

	public MonstersRoomDownLeftRightDoors(Hero hero) {
		super(hero);
	}

	
	public MonstersRoomDownLeftRightDoors(Hero hero, int tileNumberY, int tileNumberX) {
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
