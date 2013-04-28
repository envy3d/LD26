package com.envy3d.ld26;

public class Junction {
	public int x, y;
	public MapSegment[] mapSegOut;
	public MapSegment[] mapSegInc;
	public Ant ant1;
	public Ant ant2;
	public int numOfSegments;
	
	public Junction(int x, int y) {
		this.x = x;
		this.y = y;
		mapSegOut = new MapSegment[4];
		mapSegInc = new MapSegment[4];
		numOfSegments = 0;
		ant1 = null;
		ant2 = null;
	}
	
	public void onConnect() {
		
	}
	
	public void addBranch(MapSegment segment1, MapSegment segment2) {
		if (segment1.junctionFrom == this) {
			mapSegInc[numOfSegments] = segment2;
			mapSegOut[numOfSegments] = segment1;
			numOfSegments++;
		}
		else {
			mapSegInc[numOfSegments] = segment1;
			mapSegOut[numOfSegments] = segment2;
			numOfSegments++;
		}
	}
}
