package gameWorld;

import gameobjects.Hero;

public class MonstersRoomUpLeftDownDoors extends MonstersRoom {

	public MonstersRoomUpLeftDownDoors(Hero hero) {
		super(hero);
		
	}
	

	
	public MonstersRoomUpLeftDownDoors(Hero hero, int tileNumberY, int tileNumberX) {
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
