package com.envy3d.ld26;

public class MapPoint {
	public int x, y;
	public boolean occupied;
	
	public MapPoint(int x, int y) {
		this.x = x;
		this.y = y;
		occupied = false;
	}
}
