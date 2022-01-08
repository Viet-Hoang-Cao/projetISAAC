package gameWorld;

import gameobjects.Hero;

public class MonstersRoomLeftRightDoors extends MonstersRoom {


	public MonstersRoomLeftRightDoors(Hero hero) {
		super(hero);
	}
	
	
	public MonstersRoomLeftRightDoors(Hero hero, int tileNumberY, int tileNumberX) {
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
