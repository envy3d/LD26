package com.envy3d.ld26;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class AntManager {
	
	public GameScreen game;
	private Array<Ant> ants;
	public int gapFillerIndex;
	public Sprite antSprite;
	public Sprite enemySprite;
	
	public AntManager(GameScreen game, String antSpriteName) {
		this.game = game;
		
		ants = new Array<Ant>(true, 128, Ant.class);
		antSprite = game.spriteHolder.grabSprite(antSpriteName);
	}
	
	public void update(float deltaTime) {
		
	}
	
	public void addAnt(Ant ant) {
		int prevGapFillerIndex = gapFillerIndex;
		boolean gapFound = false;
		if (ants.items[gapFillerIndex] == null) {
			ants.items[gapFillerIndex] = ant;
		}
		else if (gapFillerIndex == ants.size) {
			ants.add(ant);
			gapFillerIndex++;
		}	
		else {
			for (; gapFillerIndex < ants.size; gapFillerIndex++){
				if (ants.items[gapFillerIndex] == null) {
					ants.items[gapFillerIndex] = ant;
					gapFound = true;
					break;
				}
			}
			if (!gapFound) {
				for (gapFillerIndex = 0 ; gapFillerIndex < prevGapFillerIndex; gapFillerIndex++) {
					if (ants.items[gapFillerIndex] == null) {
						ants.items[gapFillerIndex] = ant;
						gapFound = true;
						break;
					}
				}
				if (!gapFound) {
					ants.add(ant);
					gapFillerIndex = ants.size;
				}
			}
		}
	}
	
	public void removeAnt(Ant ant) {
		gapFillerIndex = ants.indexOf(ant, true);
		ants.items[gapFillerIndex] = null;
	}
	
	public void render() {
		for (int i = 0; i < ants.size; i++) {
			if (ants.items[i] != null) {
				antSprite.setPosition(ants.items[i].pxlX, ants.items[i].pxlY);
				antSprite.draw(game.batch);
			}
		}
	}
}
