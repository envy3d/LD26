package com.envy3d.ld26;

public class RMapFactory {
	
	public RMap map;
	public GameScreen game;
	
	public RMapFactory(RMap map, GameScreen game) {
		this.map = map;
		this.game = game;
		
	}
	
	// Mound(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, boolean isEnterable, boolean isExitable, boolean enemy, GameScreen game)
	//  Food(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, GameScreen game, boolean isPotato)
	
	public static void build(RMap map, GameScreen game) {
		map.places.add(new RMound(3, 10, 5, 8, 800, 4, true, true, false, game));
		map.places.add(new RFood(20, 2, 3, 5, 50, 2, game, false));
	}
	
}
