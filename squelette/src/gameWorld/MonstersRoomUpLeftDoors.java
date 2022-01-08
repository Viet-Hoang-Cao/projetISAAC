package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpLeftDoors extends MonstersRoom {

	public MonstersRoomUpLeftDoors(Hero hero) {
		super(hero);
	}
	

	
	public MonstersRoomUpLeftDoors(Hero hero, int tileNumberY, int tileNumberX) {
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
			drawCloseDoorUp();
		}
		else {
			drawOpenDoorLeft();
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
			addOpenDoorUpPhysics();
			setClosed_door(false);
		}
	}

}
