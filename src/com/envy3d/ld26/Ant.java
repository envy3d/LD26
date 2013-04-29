package com.envy3d.ld26;

public class Ant {
	
	public RLoc destination;
	public int pxlX, pxlY;
	public int gridX, gridY;
	public float energy;
	public static final float normalMaxEnergy = 30.0f;
	public float carriedFood;
	public float moveTimer;
	public boolean firstStep;
	public static final float MOVE_TIME = 0.75f;
	
	public Ant(int gridX, int gridY, float energy, RLoc dest) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.energy = energy;
		carriedFood = 0;
		destination = dest;
		moveTimer = Ant.MOVE_TIME;
		firstStep = true;
	}
}
