package com.envy3d.ld26;

public class Junction {
	public int x, y;
	public MapSegment[] mapSegments;
	public int numOfSegments = 0;
	
	public Junction(int x, int y) {
		this.x = x;
		this.y = y;
		mapSegments = new MapSegment[4];
	}
	
	public void AddSegment(MapSegment segment) {
		mapSegments[numOfSegments] = segment;
		numOfSegments++;
	}
}
