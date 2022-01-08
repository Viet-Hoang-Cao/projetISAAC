package gameWorld;

import gameobjects.Hero;

public class MonstersRoomDownLeftDoors extends MonstersRoom {

	public MonstersRoomDownLeftDoors(Hero hero, int tileNumberY, int tileNumberX) {
		super(hero, tileNumberY, tileNumberX);
	}


	public MonstersRoomDownLeftDoors(Hero hero) {
		super(hero);
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
