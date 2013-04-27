package com.envy3d.ld26;

public class Junction {
	public int x, y;
	public MapSegment[] mapSegments;
	public int numOfSegments = 0;
	
	public Junction(int x, int y) {
		this.x = x;
		this.y = y;
		mapSegments = new MapSegment[8];
	}
	
	public void AddBranch(MapSegment segment1, MapSegment segment2) {
		mapSegments[numOfSegments] = segment1;
		numOfSegments++;
		mapSegments[numOfSegments] = segment2;
		numOfSegments++;
	}
}
