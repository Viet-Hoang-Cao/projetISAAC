package gameWorld;

import gameobjects.Hero;

public class MonstersRoomDownDoor extends MonstersRoom {
	

	public MonstersRoomDownDoor(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
	}

	public MonstersRoomDownDoor(Hero hero) {
		super(hero);
	}
	
	@Override
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		super.drawRoom();
		if(isClosed_door())drawCloseDoorDown();
		else drawOpenDoorDown();
		getHero().drawGameObject();
	}
	
	@Override
	//Make every entity that compose a room process one step
	public void updateRoom()
	{
		super.updateRoom();
		if(getMonsters().isEmpty() && isClosed_door()) {
			addOpenDoorDownPhysics();
			setClosed_door(false);
		}
	}
	

}
