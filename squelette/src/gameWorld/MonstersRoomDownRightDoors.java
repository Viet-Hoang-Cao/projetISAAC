package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class MonstersRoomDownRightDoors extends MonstersRoom {

	public MonstersRoomDownRightDoors(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomDownRightDoors(Hero hero, Vector2 tileNumber) {
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
