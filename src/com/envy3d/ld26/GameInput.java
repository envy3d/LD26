package com.envy3d.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameInput implements InputProcessor {
	
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
	
	public GameInput(GameScreen game, String highlightSpriteName) {
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
		Junction northJ;
		Junction eastJ;
		Junction southJ;
		Junction westJ;
		MapSegment northS;
		MapSegment eastS;
		MapSegment southS;
		MapSegment westS;
		PlaceJunction northP;
		PlaceJunction eastP;
		PlaceJunction southP;
		PlaceJunction westP;
		int numOfJ = 0;
		int numOfS = 0;
		int numOfP = 0;
		
		for (int i = 0; i < game.map.placeJunctions.size; i++) {
			if (gridX == game.map.placeJunctions.items[i].x && gridY + 1 == game.map.placeJunctions.items[i].y) {
				northP = game.map.placeJunctions.items[i];
				numOfP++;
			}
			if (gridX + 1 == game.map.placeJunctions.items[i].x && gridY == game.map.placeJunctions.items[i].y) {
				eastP = game.map.placeJunctions.items[i];
				numOfP++;
			}
			if (gridX == game.map.placeJunctions.items[i].x && gridY - 1 == game.map.placeJunctions.items[i].y) {
				southP = game.map.placeJunctions.items[i];
				numOfP++;
			}
			if (gridX - 1 == game.map.placeJunctions.items[i].x && gridY == game.map.placeJunctions.items[i].y) {
				westP = game.map.placeJunctions.items[i];
				numOfP++;
			}
		}
		for (int i = 0; i < game.map.junctions.size; i++) {
			if (gridX == game.map.junctions.items[i].x && gridY + 1 == game.map.junctions.items[i].y) {
				northJ = game.map.junctions.items[i];
				numOfJ++;
			}
			if (gridX + 1 == game.map.junctions.items[i].x && gridY == game.map.junctions.items[i].y) {
				eastJ = game.map.junctions.items[i];
				numOfJ++;
			}
			if (gridX == game.map.junctions.items[i].x && gridY - 1 == game.map.junctions.items[i].y) {
				southJ = game.map.junctions.items[i];
				numOfJ++;
			}
			if (gridX - 1 == game.map.junctions.items[i].x && gridY == game.map.junctions.items[i].y) {
				westJ = game.map.junctions.items[i];
				numOfJ++;
			}		
			if (gridY + 1 == game.map.junctions.items[i].y) {
				for (int j = 0; j < game.map.junctions.items[i].numOfSegments; j++) {
					if (gridY + 1 == game.map.junctions.items[i].mapSegOut[j].junctionToward.y) {
						if ((gridX > game.map.junctions.items[i].x  && gridX < game.map.junctions.items[i].mapSegOut[j].junctionToward.x) ||
							(gridX < game.map.junctions.items[i].x  && gridX > game.map.junctions.items[i].mapSegOut[j].junctionToward.x)) {
							
							northS = game.map.junctions.items[i].mapSegOut[j];
							numOfS++;
						}
					}
				}
			}
			if (gridX + 1 == game.map.junctions.items[i].x) {
				for (int j = 0; j < game.map.junctions.items[i].numOfSegments; j++) {
					if (gridX + 1 == game.map.junctions.items[i].mapSegOut[j].junctionToward.x) {
						if ((gridY > game.map.junctions.items[i].y  && gridY < game.map.junctions.items[i].mapSegOut[j].junctionToward.y) ||
							(gridY < game.map.junctions.items[i].y  && gridY > game.map.junctions.items[i].mapSegOut[j].junctionToward.y)) {
							
							eastS = game.map.junctions.items[i].mapSegOut[j];
							numOfS++;
						}
					}
				}
			}
			if (gridY - 1 == game.map.junctions.items[i].y) {
				for (int j = 0; j < game.map.junctions.items[i].numOfSegments; j++) {
					if (gridY - 1 == game.map.junctions.items[i].mapSegOut[j].junctionToward.y) {
						if ((gridX > game.map.junctions.items[i].x  && gridX < game.map.junctions.items[i].mapSegOut[j].junctionToward.x) ||
							(gridX < game.map.junctions.items[i].x  && gridX > game.map.junctions.items[i].mapSegOut[j].junctionToward.x)) {
							
							southS = game.map.junctions.items[i].mapSegOut[j];
							numOfS++;
						}
					}
				}
			}
			if (gridX - 1 == game.map.junctions.items[i].x) {
				for (int j = 0; j < game.map.junctions.items[i].numOfSegments; j++) {
					if (gridX - 1 == game.map.junctions.items[i].mapSegOut[j].junctionToward.x) {
						if ((gridY > game.map.junctions.items[i].y  && gridY < game.map.junctions.items[i].mapSegOut[j].junctionToward.y) ||
							(gridY < game.map.junctions.items[i].y  && gridY > game.map.junctions.items[i].mapSegOut[j].junctionToward.y)) {
							
							westS = game.map.junctions.items[i].mapSegOut[j];
							numOfS++;
						}
					}
				}
			}
		}
		
		if (numOfJ == 0 && numOfP == 0 && numOfS == 0) {
			insertNeighborlessJunction();
		}
		
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
	
	public void insertJunctionInSegment(MapSegment segmentA, MapSegment segmentB, int x, int y) {
		int newJuncIdx = 0;
		for (int i = 0; i < segmentA.mapPoints.size; i++) {
			if (segmentA.mapPoints.items[i].x == x && segmentA.mapPoints.items[i].y == y) {
				newJuncIdx = i;
			}
		}
		Junction newJunction = game.map.addJunction(x, y);
		MapSegment segA1 = new MapSegment(segmentA.junctionFrom, newJunction, newJuncIdx - 1);
		MapSegment segA2 = new MapSegment(newJunction, segmentA.junctionToward, segmentA.mapPoints.size - newJuncIdx - 1);
		MapSegment segB1 = new MapSegment(segmentB.junctionFrom, newJunction, segmentA.mapPoints.size - newJuncIdx - 1);
		MapSegment segB2 = new MapSegment(newJunction, segmentB.junctionToward, newJuncIdx - 1);
		int aIdx = 0;
		int bIdx = 0;
		for (int i = 0; i < segmentA.junctionFrom.numOfSegments; i++) {
			if (segmentA.junctionFrom.mapSegOut[i].junctionToward == segmentA.junctionToward) {
				aIdx = i;
			}
		}
		for (int i = 0; i < segmentB.junctionFrom.numOfSegments; i++) {
			if (segmentB.junctionFrom.mapSegOut[i].junctionToward == segmentB.junctionToward) {
				bIdx = i;
			}
		}
		segmentA.junctionFrom.mapSegOut[aIdx] = segA1;
		segmentA.junctionFrom.mapSegInc[aIdx] = segB2;
		segmentB.junctionFrom.mapSegOut[bIdx] = segB1;
		segmentB.junctionFrom.mapSegInc[bIdx] = segA2;
		newJunction.addBranch(segA1, segB2);
		newJunction.addBranch(segA2, segB1);
	}
	
	public boolean checkLocValidity() {
		for (int i = 0; i < game.map.places.size; i++) {
			if (gridX >= game.map.places.items[i].left && gridX <= game.map.places.items[i].right && gridY >= game.map.places.items[i].bottom && gridY <= game.map.places.items[i].top) {
				return false;
			}
		}
		for (int i = 0; i < game.map.placeJunctions.size; i++) {
			if (gridX == game.map.placeJunctions.items[i].x && gridY == game.map.placeJunctions.items[i].y) {
				return false;
			}
		}
		for (int i = 0; i < game.map.junctions.size; i++) {
			if (gridX == game.map.junctions.items[i].x && gridY == game.map.junctions.items[i].y) {
				return false;
			}
			else if (gridX == game.map.junctions.items[i].x) {
				for (int j = 0; j < game.map.junctions.items[i].numOfSegments; j++) {
					if (gridX == game.map.junctions.items[i].mapSegOut[j].junctionToward.x) {
						for (int k = 0; k < game.map.junctions.items[i].mapSegOut[j].mapPoints.size; k++) {
							if (gridX == game.map.junctions.items[i].mapSegOut[j].mapPoints.items[k].x && gridY == game.map.junctions.items[i].mapSegOut[j].mapPoints.items[k].y)
								return false;
						}
					}
				}
			}
		}
		return true;
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
