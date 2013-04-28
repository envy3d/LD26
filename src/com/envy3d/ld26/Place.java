package com.envy3d.ld26;

import com.badlogic.gdx.math.MathUtils;

public abstract class Place {
	public Map map;
	public int top, left, bottom, right;
	protected boolean topFilled, leftFilled, bottomFilled, rightFilled;
	protected Ant[] antsInside;
	protected int antsInsideTailIndex;
	public Junction[] accesses;
	protected float[] junctionTimers;
	protected final float junctionWaitTime = 1.5f;
	
	public Place(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, Map map) {
		left = x;
		top = y;
		right = x + width;
		bottom = y + height;
		topFilled = false;
		leftFilled = false;
		bottomFilled = false;
		rightFilled = false;
		antsInside = new Ant[maxAntsInside];
		antsInsideTailIndex = 0;
		accesses = new Junction[numOfJunctions];
		initializeJunctions(numOfJunctions);
		junctionTimers = new float[numOfJunctions];
		
		this.map = map;
	}
	
	protected boolean sendAnt(int junctionNum) {
		if (antsInsideTailIndex != 0) {
			if (accesses[junctionNum].ant1 == null){
				accesses[junctionNum].ant1 = antsInside[antsInsideTailIndex];
				antsInsideTailIndex--;
				return true;
			}
			else if (accesses[junctionNum].ant2 == null) {
				accesses[junctionNum].ant2 = antsInside[antsInsideTailIndex];
				antsInsideTailIndex--;
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
					accesses[currentJunctionIdx] = map.addJunction(rand, top - 1);
				}
				else if (filledSides[i] == 2) {
					rand = MathUtils.random(top, bottom);
					accesses[currentJunctionIdx] = map.addJunction(left - 1, rand);
				}
				else if (filledSides[i] == 3) {
					rand = MathUtils.random(left, right);
					accesses[currentJunctionIdx] = map.addJunction(rand, bottom + 1);
				}
				else if (filledSides[i] == 4) {
					rand = MathUtils.random(top, bottom);
					accesses[currentJunctionIdx] = map.addJunction(right + 1, rand);
				}
				currentJunctionIdx++;
			}
		}
	}
}
