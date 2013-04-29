package com.envy3d.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RGameInput implements InputProcessor {
	
	public GameScreen game;
	public int mouseX, mouseY;
	public int prevScreenX, prevScreenY;
	public int gridX, gridY;
	public boolean validLocation;
	private Sprite highlightSprite;
	private boolean lmb;
	private boolean rmb;
	private float leftScreenBound;
	private float rightScreenBound;
	private float topScreenBound;
	private float bottomScreenBound;
	
	public RGameInput(GameScreen game, String highlightSpriteName) {
		this.game = game;
		highlightSprite = game.spriteHolder.grabSprite(highlightSpriteName);
		validLocation = false;
		lmb = false;
		rmb = false;
		leftScreenBound = game.camera.position.x;
		rightScreenBound = game.camera.position.x;
		topScreenBound = game.camera.position.y;
		bottomScreenBound = game.camera.position.y;
	}

	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub	
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if (character == 'q') {
			game.gameState = GameScreen.END_STATE;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			lmb = true;
			if (validLocation) {
				actOnSquare();
			}
		}
		else if (button == Input.Buttons.RIGHT){
			rmb = true;
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			lmb = false;
		}
		else if (button == Input.Buttons.RIGHT){
			rmb = false;
			prevScreenX = screenX;
			prevScreenY = screenY;
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseX = screenX + (int)(game.camera.position.x - (game.camera.viewportWidth / 2));
		mouseY = Math.abs(Gdx.graphics.getHeight() - screenY) + (int)(game.camera.position.y - (game.camera.viewportHeight / 2));
		gridX = mouseX / 16;
		gridY = mouseY / 16;
		validLocation = checkLocValidity();
		
		if (lmb && validLocation) {
			actOnSquare();
		}
		else if (rmb) {
			game.camera.position.x += prevScreenX - screenX;
			game.camera.position.y += screenY - prevScreenY;
			prevScreenX = screenX;
			prevScreenY = screenY;
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX + (int)(game.camera.position.x - (game.camera.viewportWidth / 2));
		mouseY = Math.abs(Gdx.graphics.getHeight() - screenY) + (int)(game.camera.position.y - (game.camera.viewportHeight / 2));
		gridX = mouseX / 16;
		gridY = mouseY / 16;
		
		validLocation = checkLocValidity();
		prevScreenX = screenX;
		prevScreenY = screenY;
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void actOnSquare() {
		
	}
	
	public boolean checkLocValidity() {
		for (int i = 0; i < game.map.places.size; i++) {
			if (gridX >= game.map.places.items[i].left && gridX <= game.map.places.items[i].right && gridY >= game.map.places.items[i].bottom && gridY <= game.map.places.items[i].top) {
				return false;
			}
		}
		for (int i = 0; i < game.map.rLocs.size; i++) {
			if (gridX == game.map.rLocs.items[i].x && gridY == game.map.rLocs.items[i].y) {
				return false;
			}
		}
		return true;
	}
	
	public void shiftSegmentArray(MapSegment segment, MapPoint newPoint) {
		if (segment.mapPoints.size > 0)
		segment.mapPoints.add(segment.mapPoints.items[segment.mapPoints.size]);
		if (segment.mapPoints.size > 1) {
			for (int i = segment.mapPoints.size - 1; i > 0; i--) {
				segment.mapPoints.items[i] = segment.mapPoints.items[i - 1];
			}
		}
		segment.mapPoints.items[0] = newPoint;
	}
	
	public void updateCameraBounds(int x, int y) {
		if (x < leftScreenBound) {
			leftScreenBound = x;
			if (leftScreenBound < Gdx.graphics.getWidth() / 2) {
				leftScreenBound = Gdx.graphics.getWidth() / 2;
			}
		}
		else if (x > rightScreenBound) {
			rightScreenBound = x;
			if (rightScreenBound < (game.mapSizeX * 16) - (Gdx.graphics.getWidth() / 2)) {
				rightScreenBound = (game.mapSizeX * 16) - (Gdx.graphics.getWidth() / 2);
			}
		}
		if (y < bottomScreenBound) {
			bottomScreenBound = y;
			if (bottomScreenBound < Gdx.graphics.getHeight() / 2) {
				bottomScreenBound = Gdx.graphics.getHeight() / 2;
			}
		}
		else if (y > topScreenBound) {
			topScreenBound = y;
			if (topScreenBound < (game.mapSizeY * 16) - (Gdx.graphics.getHeight() / 2)) {
				topScreenBound = (game.mapSizeY * 16) - (Gdx.graphics.getHeight() / 2);
			}
		}
	}
	
	public void correctCamera() {
		if (game.camera.position.x < leftScreenBound) {
			game.camera.position.x = leftScreenBound;
		}
		if (game.camera.position.x > rightScreenBound) {
			game.camera.position.x = rightScreenBound;
		}
		if (game.camera.position.y < bottomScreenBound) {
			game.camera.position.y = bottomScreenBound;
		}
		if (game.camera.position.y > topScreenBound) {
			game.camera.position.y = topScreenBound;
		}
	}
	
	public void render() {
		if (validLocation) {
			highlightSprite.setPosition(gridX * 16, gridY * 16);
			highlightSprite.draw(game.batch);
		}
	}
}
