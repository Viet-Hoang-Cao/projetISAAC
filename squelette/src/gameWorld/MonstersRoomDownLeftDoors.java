package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class MonstersRoomDownLeftDoors extends MonstersRoom {

	public MonstersRoomDownLeftDoors(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomDownLeftDoors(Hero hero, Vector2 tileNumber) {
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
			drawCloseDoorLeft();
		}
		else {
			drawOpenDoorDown();
			drawOpenDoorLeft();
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
			addOpenDoorLeftPhysics();
			setClosed_door(false);
		}
	}

}
