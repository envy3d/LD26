package com.envy3d.ld26;

public class PlaceJunction extends Junction {
	
	private Place place;
	
	public PlaceJunction(int x, int y, Place place) {
		super(x, y);
		this.place = place;
	}
	
	@Override
	public void onConnect() {
		place.onConnect();
	}
	
	@Override
	public int type() {
		return 3;
	}
}
