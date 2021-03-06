package com.envy3d.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpriteHolder {
	private TextureAtlas atlas;
	
	public SpriteHolder() {
		atlas = new TextureAtlas(Gdx.files.internal("sprites/LD26spritesheet.atlas"));
	}
	
	public Sprite grabSprite(String name) {
		return atlas.createSprite(name);
	}
	
	public void dispose() {
		atlas.dispose();
	}
}
