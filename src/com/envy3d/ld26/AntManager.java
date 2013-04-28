package com.envy3d.ld26;

import com.badlogic.gdx.utils.Array;

public class AntManager {
	public Array<Ant> ants;
	public int gapFillerIndex;
	
	public AntManager() {
		ants = new Array<Ant>(true, 128, Ant.class);
	}
	
	
}
