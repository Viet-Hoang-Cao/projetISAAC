package gameloop;

import gameWorld.*;
import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.CycleInfos;
import resources.DisplaySettings;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Main
{
	public static void main(String[] args)
	{
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, ImagePaths.ISAAC, 6, 2);
		GameWorld world = new GameWorld(isaac);
		//TODO trouver le pb dans la generation du DJ qui empeche de le jeu de parfois se lancer
		
		//Test de changement de room || Le constructeur initialisera automatiquement le DJ et cette ligne deviendra useless.
		//world.setCurrentRoom(new TESTROOM(isaac));
		
		initializeDisplay();
		// Main loop of the game
		while (!world.gameOver())
		{
			CycleInfos.Cycle++;
			if(CycleInfos.Cycle==Integer.MAX_VALUE) {
				CycleInfos.Cycle=0;
			}
			processNextStep(world);
		}
		StdDraw.picture(0.5, 0.5, ImagePaths.LOSE_SCREEN, 1, 1);
		StdDraw.show();
	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}
