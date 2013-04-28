package com.envy3d.ld26;

import com.badlogic.gdx.utils.Array;

public class Map {
	
	public Array<MapSegment> mapSegments;
	public Array<Junction> junctions;
	
	public Map() {
		mapSegments = new Array<MapSegment>(true, 16, MapSegment.class);
		junctions = new Array<Junction>(true, 16, Junction.class);
		
	}
	
	public Junction addJunction(int x, int y) {
		Junction junction = new Junction(x, y);
		junctions.add(junction);
		return junction;
	}
	
	
}
