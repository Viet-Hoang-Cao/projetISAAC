package resources;

import libraries.Vector2;
import java.util.Random;

public class RoomInfos
{
	public static final int NB_TILES = 9;
	public static final double TILE_WIDTH = 1.0 / NB_TILES;
	public static final double TILE_HEIGHT = 1.0 / NB_TILES;
	public static final Vector2 TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT);
	public static final Vector2 HALF_TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT).scalarMultiplication(0.5);
	
	public static final Vector2 POSITION_CENTER_OF_ROOM = new Vector2(0.5, 0.5);
	
	public static double genererInt(double borneInf, double borneSup) {
		Random generateur = new Random();
		double a=0;
		a = borneInf+generateur.nextDouble(borneSup-borneInf);
		return a;
	}
	public static final Vector2 POSITION_ALEATOIRE = new Vector2(genererInt(0, 0.8), genererInt(0, 0.8));
}
