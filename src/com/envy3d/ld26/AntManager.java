package com.envy3d.ld26;

import com.badlogic.gdx.utils.Array;

public class AntManager {
	private Array<Ant> ants;
	public int gapFillerIndex;
	
	public AntManager() {
		ants = new Array<Ant>(true, 128, Ant.class);
	}
	
	public void addAnt(Ant ant) {
		int prevGapFillerIndex = gapFillerIndex;
		boolean gapFound = false;
		if (ants.items[gapFillerIndex] == null) {
			ants.items[gapFillerIndex] = ant;
		}
		else {
			for (; gapFillerIndex < ants.size; gapFillerIndex++){
				if (ants.items[gapFillerIndex] == null) {
					ants.items[gapFillerIndex] = ant;
					gapFound = true;
				}
			}
			for ( ; gapFillerIndex < prevGapFillerIndex; gapFillerIndex++) {
				
			}
		}
	}
}
