package com.envy3d.ld26;

public class MapFactory {
	
	public Map map;
	public GameScreen game;
	
	public MapFactory(Map map, GameScreen game) {
		this.map = map;
		this.game = game;
		
	}
	
	// Mound(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, boolean isEnterable, boolean isExitable, boolean enemy, GameScreen game)
	//  Food(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, GameScreen game, boolean isPotato)
	
	public static void build(Map map, GameScreen game) {
		map.places.add(new Mound(3, 10, 5, 8, 800, 4, true, true, false, game));
		map.places.add(new Food(20, 2, 3, 5, 50, 2, game, false));
	}
	
}
