package com.envy3d.ld26;

public class Food extends Place {
	
	private static final float foodValue = 45.0f;
	private static final float potatoValue = 90.0f;
	
	public float totalFood;
	public boolean isPotato;

	public Food(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, GameScreen game, boolean isPotato) {
		super(x, y, width, height, maxAntsInside, numOfJunctions, game, false, false);
		this.isPotato = isPotato;
		totalFood = width * height * 1000;
		
	}
	
	@Override
	public void update(float deltaTime) {
		if (isEnterable || isExitable)
		super.update(deltaTime);
		if (totalFood <= 0)
			isEnterable = false;
		if (totalFood <= 0 && antsInsideTailIndex == 0 && antsInside[0] == null) {
			isExitable = false;
		}
		
	}
	
	public void render() {
		
	}

	@Override
	protected void sendMod(Ant ant) {
		ant.energy = Ant.normalMaxEnergy;
		ant.carriedFood = (isPotato) ? potatoValue : foodValue;
		
	}

	@Override
	protected void receiveMod(Ant ant) {
		
		
	}

	@Override
	public void onConnect() {
		isEnterable = true;
		isExitable = true;
	}
}
