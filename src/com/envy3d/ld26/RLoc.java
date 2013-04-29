package com.envy3d.ld26;

public class RLoc {
	
	public int x, y;
	public RLoc[] neighbors;
	public int numOfNeighbors;
	public Ant ant1;
	public Ant ant2;
	public int type; // 0 = not connected, 1 = normal, 2 = turned, 3 = intersection
	
	public RLoc(int x, int y) {
		this.x = x;
		this.y = y;
		neighbors = new RLoc[4];
		numOfNeighbors = 0;
		type = 0;
	}
	
	public void addNeighbor(RLoc neighbor) {
		neighbors[numOfNeighbors] = neighbor;
		numOfNeighbors++;
		
		type = 1;
		if (numOfNeighbors > 2)
			type = 3;
		else if (numOfNeighbors == 2) {
			if (neighbors[0].x == neighbors[1].x || neighbors[0].y == neighbors[1].y) {
				type = 1;
			}
			else 
				type = 2;
		}
	}	
}
