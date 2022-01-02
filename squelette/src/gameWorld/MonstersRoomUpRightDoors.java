package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class MonstersRoomUpRightDoors extends MonstersRoom {

	public MonstersRoomUpRightDoors(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomUpRightDoors(Hero hero, Vector2 tileNumber) {
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
