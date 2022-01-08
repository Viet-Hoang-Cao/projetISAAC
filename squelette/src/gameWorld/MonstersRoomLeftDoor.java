package gameWorld;

import gameobjects.Hero;

public class MonstersRoomLeftDoor extends MonstersRoom {
	
	private boolean bossRoom;

	public MonstersRoomLeftDoor(Hero hero, int tileNumber) {
		super(hero, tileNumber);
		this.bossRoom = false;
		// TODO Auto-generated constructor stub
	}

	public MonstersRoomLeftDoor(Hero hero) {
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

	public boolean isBossRoom() {
		return bossRoom;
	}

	public void setBossRoom(boolean bossRoom) {
		this.bossRoom = bossRoom;
	}

}
