package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpRightDoors extends MonstersRoom {

	public MonstersRoomUpRightDoors(Hero hero) {
		super(hero);
	}

	
	
	public MonstersRoomUpRightDoors(Hero hero, int tileNumberY, int tileNumberX) {
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
			drawCloseDoorRight();
			drawCloseDoorUp();
		}
		else {
			drawOpenDoorRight();
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
			addOpenDoorRightPhysics();
			addOpenDoorUpPhysics();
			setClosed_door(false);
		}
	}

}
