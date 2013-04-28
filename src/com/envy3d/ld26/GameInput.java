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
		MapPoint tempPoint1 = new MapPoint(junction.x, junction.y);
		MapPoint tempPoint2 = new MapPoint(junction.x, junction.y);
		junction.x = gridX;
		junction.y = gridY;
		junction.mapSegInc[0].mapPoints.add(tempPoint1);
		shiftSegmentArray(junction.mapSegOut[0], tempPoint2);
	}
	
	public void fuseChains(Junction junctionA, Junction junctionB) {
		junctionA.mapSegInc[0].mapPoints.addAll(junctionB.mapSegOut[0].mapPoints);
		junctionB.mapSegInc[0].mapPoints.addAll(junctionA.mapSegOut[0].mapPoints);
		Junction tempJuncADest = junctionA.mapSegOut[0].junctionToward;
		Junction tempJuncBDest = junctionB.mapSegOut[0].junctionToward;
		int juncADestSegIdx = 0;
		int juncBDestSegIdx = 0;
		for (int i = 0; i < tempJuncADest.numOfSegments; i++) {
			if (tempJuncADest.mapSegInc[i].junctionFrom == junctionA)
				juncADestSegIdx = i;
		}
		for (int i = 0; i < tempJuncBDest.numOfSegments; i++) {
			if (tempJuncBDest.mapSegInc[i].junctionFrom == junctionB)
				juncBDestSegIdx = i;
		}
		tempJuncADest.mapSegInc[juncADestSegIdx] = junctionB.mapSegInc[0];
		tempJuncADest.mapSegInc[juncADestSegIdx].junctionToward = tempJuncADest;
		tempJuncBDest.mapSegInc[juncBDestSegIdx] = junctionA.mapSegInc[0];
		tempJuncBDest.mapSegInc[juncBDestSegIdx].junctionToward = tempJuncBDest;
		game.map.removeJunction(junctionA);
		game.map.removeJunction(junctionB);
	}
	
	public void attachToJunction(Junction fixedJunction, Junction attachingJunction) {
		MapPoint tempPoint1 = new MapPoint(attachingJunction.x, attachingJunction.y);
		MapPoint tempPoint2 = new MapPoint(attachingJunction.x, attachingJunction.y);
		attachingJunction.mapSegInc[0].mapPoints.add(tempPoint1);
		shiftSegmentArray(attachingJunction.mapSegOut[0], tempPoint2);
		attachingJunction.mapSegInc[0].junctionToward = fixedJunction;
		attachingJunction.mapSegOut[0].junctionFrom = fixedJunction;
		fixedJunction.addBranch(attachingJunction.mapSegInc[0], attachingJunction.mapSegOut[0]);
		game.map.removeJunction(attachingJunction);
	}
	
	public void createJunctionChain(Junction baseJunction) {
		Junction newJunction = game.map.addJunction(gridX, gridY);
		baseJunction.addBranch(new MapSegment(baseJunction, newJunction, 0), new MapSegment(baseJunction, newJunction, 0));
	}
	
	public void insertJunctionInSegment() {
		
	}
	
	public boolean checkLocValidity() {
		
	}
	
	public void shiftSegmentArray(MapSegment segment, MapPoint newPoint) {
		segment.mapPoints.add(segment.mapPoints.items[segment.mapPoints.size -1]);
		if (segment.mapPoints.size > 2) {
			for (int i = segment.mapPoints.size - 2; i > 0; i--) {
				segment.mapPoints.items[i] = segment.mapPoints.items[i - 1];
			}
		}
		segment.mapPoints.items[0] = newPoint;
	}
	
	public void render() {
		if (validLocation) {
			highlightSprite.setPosition(gridX * 16, gridY * 16);
			highlightSprite.draw(game.batch);
		}
	}
}
