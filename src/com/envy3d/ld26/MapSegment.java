package com.envy3d.ld26;

import com.badlogic.gdx.utils.Array;

public class MapSegment {
	public Array<MapPoint> mapPoints;
	public Junction junctionFrom;
	public Junction junctionToward;
	
	public MapSegment() {
		mapPoints = new Array<MapPoint>(true, 10, MapPoint.class);
	}
}
