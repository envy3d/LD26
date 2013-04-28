package com.envy3d.ld26;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class Map {
	
	public GameScreen game;
	public Array<MapSegment> mapSegments;
	public Array<Junction> junctions;
	public Array<Place> places;
	
	private Sprite pathSprite;
	private Sprite moundSprite;
	private Sprite foodSprite;
	private Sprite potatoSprite;
	private Sprite enemySprite;
	
	public Map(String pathSpriteName, String moundSpriteName, String foodSpriteName, String potatoSpriteName, String enemySpriteName, GameScreen game) {
		this.game = game;
		mapSegments = new Array<MapSegment>(true, 16, MapSegment.class);
		junctions = new Array<Junction>(true, 16, Junction.class);
		places = new Array<Place>(true, 16, Place.class);
		
		pathSprite = game.spriteHolder.grabSprite(pathSpriteName);
		moundSprite = game.spriteHolder.grabSprite(moundSpriteName);
		foodSprite = game.spriteHolder.grabSprite(foodSpriteName);
		potatoSprite = game.spriteHolder.grabSprite(potatoSpriteName);
		enemySprite = game.spriteHolder.grabSprite(enemySpriteName);
	}
	
	public Junction addJunction(int x, int y) {
		Junction junction = new Junction(x, y);
		junctions.add(junction);
		return junction;
	}
	
	public void render() {
		/*for (int i = 0; i < mapSegments.size; i++) {
			for (int j = 0; j < mapSegments.items[i].mapPoints.size ; j++) {
				pathSprite.setPosition(mapSegments.items[i].mapPoints.items[j].x, mapSegments.items[i].mapPoints.items[j].y);
				pathSprite.draw(game.batch);
			}
		}*/
		for (int i = 0; i < junctions.size; i++) {
			pathSprite.setPosition(junctions.items[i].x, junctions.items[i].y);
			pathSprite.draw(game.batch);
			for (int j = 0; j < junctions.items[i].numOfSegments; j++) {
				for (int k = 0; k < junctions.items[i].mapSegOut[j].mapPoints.size ; k++) {
					pathSprite.setPosition(junctions.items[i].mapSegOut[j].mapPoints.items[k].x, junctions.items[i].mapSegOut[j].mapPoints.items[k].y);
					pathSprite.draw(game.batch);
				}
			}
		}
		for (int i = 0; i < places.size; i++) {
			
		}
	}
}
