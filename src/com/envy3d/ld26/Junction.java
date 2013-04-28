package com.envy3d.ld26;

public class Junction {
	public int x, y;
	public MapSegment[] mapSegments;
	public int numOfSegments;
	
	public Junction(int x, int y) {
		this.x = x;
		this.y = y;
		mapSegments = new MapSegment[8];
		numOfSegments = 0;
	}
	
	public void addBranch(MapSegment segment1, MapSegment segment2) {
		mapSegments[numOfSegments] = segment1;
		numOfSegments++;
		mapSegments[numOfSegments] = segment2;
		numOfSegments++;
	}
}
