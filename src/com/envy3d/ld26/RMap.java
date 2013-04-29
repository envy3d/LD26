package com.envy3d.ld26;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class RMap {
	
	public GameScreen game;
	public Array<RLoc> rLocs;
	public Array<RPlace> places;
	
	private Sprite pathSprite;
	private Sprite moundSprite;
	private Sprite foodSprite;
	private Sprite potatoSprite;
	private Sprite enemySprite;
	
	public RMap(GameScreen game, String pathSpriteName, String moundSpriteName, String foodSpriteName, String potatoSpriteName, String enemySpriteName) {
		this.game = game;
		rLocs = new Array<RLoc>(false, 16, RLoc.class);
		places = new Array<RPlace>(true, 16, RPlace.class);
		
		pathSprite = game.spriteHolder.grabSprite(pathSpriteName);
		moundSprite = game.spriteHolder.grabSprite(moundSpriteName);
		foodSprite = game.spriteHolder.grabSprite(foodSpriteName);
		potatoSprite = game.spriteHolder.grabSprite(potatoSpriteName);
		enemySprite = game.spriteHolder.grabSprite(enemySpriteName);
	}
	
	public RLoc addRLoc(int x, int y) {
		RLoc rLoc = new RLoc(x, y);
		rLocs.add(rLoc);
		return rLoc;
	}
	
	public void update(float deltaTime) {
		for (int i = 0; i < places.size; i++) {
			places.items[i].update(deltaTime);
		}
	}
	
	public void render() {

		for (int i = 0; i < rLocs.size; i++) {
			pathSprite.setPosition(rLocs.items[i].x * 16, rLocs.items[i].y * 16);
			pathSprite.draw(game.batch);
		}
		for (int i = 0; i < places.size; i++) {
			if (places.items[i].type == 1) {
				for (int j = places.items[i].bottom; j <= places.items[i].top; j++) {
					for (int k = places.items[i].left; k <= places.items[i].right; k++) {
						moundSprite.setPosition(k * 16, j * 16);
						moundSprite.draw(game.batch);
					}
				}
			}
			if (places.items[i].type == 2) {
				for (int j = places.items[i].bottom; j <= places.items[i].top; j++) {
					for (int k = places.items[i].left; k <= places.items[i].right; k++) {
						foodSprite.setPosition(k * 16, j * 16);
						foodSprite.draw(game.batch);
					}
				}
			}
			if (places.items[i].type == 3) {
				for (int j = places.items[i].bottom; j <= places.items[i].top; j++) {
					for (int k = places.items[i].left; k <= places.items[i].right; k++) {
						potatoSprite.setPosition(k * 16, j * 16);
						potatoSprite.draw(game.batch);
					}
				}
			}
			if (places.items[i].type == 4) {
				for (int j = places.items[i].bottom; j <= places.items[i].top; j++) {
					for (int k = places.items[i].left; k <= places.items[i].right; k++) {
						enemySprite.setPosition(k * 16, j * 16);
						enemySprite.draw(game.batch);
					}
				}
			}
		}
	}
}

