package com.envy3d.ld26;

import com.badlogic.gdx.utils.Array;

public class MapSegment implements ILoc{
	public Array<MapPoint> mapPoints;
	public Junction junctionFrom;
	public Junction junctionToward;
	
	public MapSegment() {
		mapPoints = new Array<MapPoint>(true, 10, MapPoint.class);
	}
	
	public MapSegment(Junction junctionFrom, Junction junctionToward, int segmentLength) {
		this.junctionFrom = junctionFrom;
		this.junctionToward = junctionToward;
		mapPoints = new Array<MapPoint>(true, 10, MapPoint.class);
		int xDir = 0;
		int yDir = 0;
		if (junctionFrom.x == junctionToward.x) {
			yDir = (junctionFrom.y < junctionToward.y) ? 1 : -1;
		}
		else if (junctionFrom.y == junctionToward.y) {
			xDir = (junctionFrom.x < junctionToward.x) ? 1 : -1;
		}
		for (int i = 0; i < segmentLength; i++) {
			mapPoints.add(new MapPoint(junctionFrom.x + (i * xDir), junctionFrom.y + (i * yDir)));
		}
	}

	@Override
	public int type() {
		return 2;
	}
}
