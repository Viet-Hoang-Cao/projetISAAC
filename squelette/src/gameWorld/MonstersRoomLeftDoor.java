package gameWorld;

import gameobjects.Hero;

public class MonstersRoomLeftDoor extends MonstersRoom {
	


	public MonstersRoomLeftDoor(Hero hero) {
		super(hero);
	}
	
	
	public MonstersRoomLeftDoor(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
	
	}


	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		if(isClosed_door())drawCloseDoorLeft();
		else drawOpenDoorLeft();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty() && isClosed_door()) {
			addOpenDoorLeftPhysics();
			setClosed_door(false);
		}
	}

}
