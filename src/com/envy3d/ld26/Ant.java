package com.envy3d.ld26;

public class Ant {
	
	public Junction destination;
	public int pxlX, pxlY;
	public int gridX, gridY;
	public float energy;
	public static final float normalMaxEnergy = 30.0f;
	public int carriedFood;
	
	public Ant(int pxlX, int pxlY, float energy, Junction dest) {
		this.pxlX = pxlX;
		this.pxlY = pxlY;
		this.energy = energy;
		carriedFood = 0;
		destination = dest;
	}
}
