package com.envy3d.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameInput implements InputProcessor {
	
	public GameScreen game;
	public int mouseX, mouseY;
	public int gridX, gridY;
	public boolean validLocation;
	private Sprite highlightSprite;
	
	public GameInput(GameScreen game, String highlightSpriteName) {
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
		if (Input.Buttons.LEFT == button && validLocation == true) {
			
		}
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
		mouseX = screenX + (int)(game.camera.position.x - (game.camera.viewportWidth / 2));
		mouseY = Math.abs(Gdx.graphics.getHeight() - screenY) + (int)(game.camera.position.y - (game.camera.viewportHeight / 2));
		gridX = mouseX / 16;
		gridY = mouseY / 16;
		
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Junction insertNeighborlessJunction() {
		return game.map.addJunction(gridX, gridY);
	}
	
	public void extendJunctionChain(Junction junction) {
		MapPoint tempPoint = new MapPoint(junction.x, junction.y, )
	}
	
	public void fuseChains() {
		
	}
	
	public void createJunctionChain() {
		
	}
	
	public void insertJunctionInSegment() {
		
	}
	
	public boolean checkLocValidity() {
		
	}
	
	public void shiftSegmentArray(MapSegment segment, MapPoint newPoint) {
		segment.mapPoints.add(segment.mapPoints.items[segment.mapPoints.size -1]);
		
	}
	
	public void render() {
		if (validLocation) {
			highlightSprite.setPosition(gridX * 16, gridY * 16);
			highlightSprite.draw(game.batch);
		}
	}
}
