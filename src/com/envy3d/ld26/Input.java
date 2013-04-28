package com.envy3d.ld26;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Input implements InputProcessor {
	
	public GameScreen game;
	public int mouseX, mouseY;
	public int gridX, gridY;
	public boolean validLocation;
	private Sprite highlightSprite;
	
	public Input(GameScreen game, String highlightSpriteName) {
		this.game = game;
		highlightSprite = game.spriteHolder.grabSprite(highlightSpriteName);
		validLocation = false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub	
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if (character == ' ') {
			game.gameState = GameScreen.END_STATE;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = screenY;
		gridX = mouseX / 16;
		gridY = mouseY / 16;
		
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void render() {
		if (validLocation) {
			highlightSprite.setPosition(gridX * 16, gridY * 16);
			highlightSprite.draw(game.batch);
		}
	}
}
