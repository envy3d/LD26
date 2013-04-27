package com.envy3d.ld26;

import com.badlogic.gdx.utils.Array;

public class MapSegment {
	public Array<MapPoint> mapPoints;
	public Junction junctionA;
	public Junction junctionB;
	
	public MapSegment() {
		mapPoints = new Array<MapPoint>(true, 10, MapPoint.class);
	}
}
