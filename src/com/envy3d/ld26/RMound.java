package com.envy3d.ld26;

public class RMound extends RPlace {
	
	public final float restEnergy = 30.0f;

	public RMound(int x, int y, int width, int height, int maxAntsInside, int numOfJunctions, boolean isEnterable, boolean isExitable, boolean enemy, GameScreen game) {
		super(x, y, width, height, maxAntsInside, numOfJunctions, game, isEnterable, isExitable, (enemy) ? 4 : 1);

	}
	
	@Override
	public void update(float deltaTime) {
		if (isEnterable) {
			super.update(deltaTime);
			game.food -= (deltaTime * 0.25f) * antsInsideTailIndex;
			if (game.food < 0)
				game.food = 0;
		}
	}
	
	public void render() {
		
	}

	@Override
	protected void sendMod(Ant ant) {
		
		
	}

	@Override
	protected void receiveMod(Ant ant) {
		if (game.food > 0) {
			ant.energy += restEnergy;
			game.food -= 30;
			if (game.food < 0)
				game.food = 0;
		}
		game.food += ant.carriedFood;
		ant.carriedFood = 0;
	}

	@Override
	public void onConnect() {
		isEnterable = true;
		isExitable = true;
		
	}
}