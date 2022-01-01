package resources;

import java.awt.event.KeyEvent;

import libraries.Keybinding;

public class Controls
{
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');
	
	public static int goInvicible = Keybinding.keycodeOf('i');
	public static int goLight = Keybinding.keycodeOf('l');
	public static int goKillAll = Keybinding.keycodeOf('k');
	public static int goPowerfull = Keybinding.keycodeOf('p');
	public static int goOfferGold = Keybinding.keycodeOf('o');
	
	public static int directionalKeyUp = KeyEvent.VK_UP;
	public static int directionalKeyDown = KeyEvent.VK_DOWN;
	public static int directionalKeyLeft = KeyEvent.VK_LEFT;
	public static int directionalKeyRight = KeyEvent.VK_RIGHT;
	
}
