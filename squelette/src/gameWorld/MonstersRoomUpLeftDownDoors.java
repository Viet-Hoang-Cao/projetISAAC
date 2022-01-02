package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class MonstersRoomUpLeftDownDoors extends MonstersRoom {

	public MonstersRoomUpLeftDownDoors(Hero hero) {
		super(hero);
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomUpLeftDownDoors(Hero hero, Vector2 tileNumber) {
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
			drawCloseDoorUp();
			drawCloseDoorLeft();
		}
		else {
			drawOpenDoorDown();
			drawOpenDoorUp();
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
			addOpenDoorUpPhysics();
			addOpenDoorLeftPhysics();
			setClosed_door(false);
		}
	}

}
