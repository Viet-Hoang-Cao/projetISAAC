package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpRightLeftDownDoors extends MonstersRoom {

	public MonstersRoomUpRightLeftDownDoors(Hero hero) {
		super(hero);
	}

	
	public MonstersRoomUpRightLeftDownDoors(Hero hero, int tileNumberY, int tileNumberX) {
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
			drawCloseDoorUp();
		}
		else {
			drawOpenDoorLeft();
			drawOpenDoorRight();
			drawOpenDoorDown();
			drawOpenDoorUp();
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
			addOpenDoorUpPhysics();
			setClosed_door(false);
		}
	}

}
