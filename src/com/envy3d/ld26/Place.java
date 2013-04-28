package com.envy3d.ld26;

import com.badlogic.gdx.math.MathUtils;

public abstract class Place {
	public GameScreen game;
	public int top, left, bottom, right;
	protected boolean topFilled, leftFilled, bottomFilled, rightFilled;
	protected Ant[] antsInside;
	protected int antsInsideTailIndex;
	public Junction[] accesses;
	protected float[] junctionTimers;
	protected final float junctionWaitTime = 1.5f;
	protected boolean updateOrder = false;
	protected boolean isEnterable, isExitable;
	public int type; // 1 = mound, 2 = food, 3 = potato, 4 = enemy
	
	public Place(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, GameScreen gameScreen, boolean isEnterable, boolean isExitable, int type) {
		left = x;
		top = y + height;
		right = x + width;
		bottom = y;
		topFilled = false;
		leftFilled = false;
		bottomFilled = false;
		rightFilled = false;
		antsInside = new Ant[maxAntsInside];
		antsInsideTailIndex = 0;
		accesses = new Junction[numOfJunctions];
		initializeJunctions(numOfJunctions);
		junctionTimers = new float[numOfJunctions];
		
		this.isEnterable = isEnterable;
		this.isExitable = isExitable;
		this.type = type;
		
		this.game = gameScreen;
	}
	
	public void update(float deltaTime) {
		for (int i = 0; i < accesses.length; i++) {
			if (junctionTimers[i] <= 0) {
				if (updateOrder) {
					if (!sendAnt(i)) {
						receiveAnt(i);
					}
				}
				else {
					if (!receiveAnt(i)) {
						sendAnt(i);
					}
				}
			}
			junctionTimers[i] -= deltaTime;
		}
		updateOrder = !updateOrder;
		
	}
	
	protected abstract void sendMod(Ant ant);
	
	protected boolean sendAnt(int junctionNum) {
		if (isExitable && antsInsideTailIndex != 0) {
			if (accesses[junctionNum].ant1 == null) {
				accesses[junctionNum].ant1 = antsInside[antsInsideTailIndex];
				game.antManager.addAnt(antsInside[antsInsideTailIndex]);
				sendMod(antsInside[antsInsideTailIndex]);
				antsInsideTailIndex--;
				if (antsInsideTailIndex < 0)
					antsInsideTailIndex = 0;
				junctionTimers[junctionNum] = junctionWaitTime;
				return true;
			}
			else if (accesses[junctionNum].ant2 == null) {
				accesses[junctionNum].ant2 = antsInside[antsInsideTailIndex];
				game.antManager.addAnt(antsInside[antsInsideTailIndex]);
				sendMod(antsInside[antsInsideTailIndex]);
				antsInsideTailIndex--;
				if (antsInsideTailIndex < 0)
					antsInsideTailIndex = 0;
				junctionTimers[junctionNum] = junctionWaitTime;
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	protected abstract void receiveMod(Ant ant);
	
	protected boolean receiveAnt(int junctionNum) {
		if (isEnterable && antsInsideTailIndex + 1 < antsInside.length) { 
			if ((accesses[junctionNum].ant1 != null && accesses[junctionNum].ant1.carriedFood <= 0) && accesses[junctionNum].ant1.destination == accesses[junctionNum]) {
				antsInsideTailIndex++;
				antsInside[antsInsideTailIndex] = accesses[junctionNum].ant1;
				receiveMod(accesses[junctionNum].ant1);
				game.antManager.removeAnt(accesses[junctionNum].ant1);
				accesses[junctionNum].ant1 = null;
				junctionTimers[junctionNum] = junctionWaitTime;
				return true;
			}
			else if ((accesses[junctionNum].ant2 != null && accesses[junctionNum].ant2.carriedFood <= 0) && accesses[junctionNum].ant2.destination == accesses[junctionNum]) {
				antsInsideTailIndex++;
				antsInside[antsInsideTailIndex] = accesses[junctionNum].ant2;
				receiveMod(accesses[junctionNum].ant2);
				game.antManager.removeAnt(accesses[junctionNum].ant2);
				accesses[junctionNum].ant2 = null;
				junctionTimers[junctionNum] = junctionWaitTime;
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public void initializeJunctions(int numOfJunctions) {
		int currentJunctionIdx = 0;
		if (numOfJunctions < 4) {
			int[] filledSides = new int[numOfJunctions];
			for (int i = 0; i < numOfJunctions; i++) {
				filledSides[i] = 0;
			}
			int rand = 0;
			while (numOfJunctions > 0) {
				boolean free = true;
				rand = MathUtils.random(1,4);
				for (int i = 0; i < filledSides.length; i++) {
					if (rand == filledSides[i])
						free = false;
				}
				if (free) {
					numOfJunctions = numOfJunctions - 1;
					filledSides[numOfJunctions] = rand;
				}
			}
			for (int i = 0; i < filledSides.length; i++) {
				if (filledSides[i] == 1) {
					rand = MathUtils.random(left, right);
					accesses[currentJunctionIdx] = game.map.addJunction(rand, top + 1);
				}
				else if (filledSides[i] == 2) {
					rand = MathUtils.random(top, bottom);
					accesses[currentJunctionIdx] = game.map.addJunction(left - 1, rand);
				}
				else if (filledSides[i] == 3) {
					rand = MathUtils.random(left, right);
					accesses[currentJunctionIdx] = game.map.addJunction(rand, bottom - 1);
				}
				else if (filledSides[i] == 4) {
					rand = MathUtils.random(top, bottom);
					accesses[currentJunctionIdx] = game.map.addJunction(right + 1, rand);
				}
				currentJunctionIdx++;
			}
		}
	}
	
	public abstract void onConnect();
	
	public abstract void render();
}
