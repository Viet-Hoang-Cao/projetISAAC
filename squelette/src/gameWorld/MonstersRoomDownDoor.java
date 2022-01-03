package gameWorld;

import gameobjects.Hero;
import libraries.Vector2;

public class MonstersRoomDownDoor extends MonstersRoom {
	
	private boolean bossRoom;

	public MonstersRoomDownDoor(Hero hero, Vector2 tileNumber) {
		super(hero, tileNumber);
		this.bossRoom = false;
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomDownDoor(Hero hero) {
		super(hero);
		this.bossRoom = false;
		// TODO Auto-generated constructor stub
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
	
	public boolean isBossRoom() {
		return bossRoom;
	}

	public void setBossRoom(boolean bossRoom) {
		this.bossRoom = bossRoom;
	}
}
