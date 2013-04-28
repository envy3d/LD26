package com.envy3d.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	
	public LD26game LD26;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public static final int MENU_STATE = 0;
	public static final int GAME_STATE = 1;
	public static final int END_STATE = 2;
	public int gameState;
	
	public SpriteHolder spriteHolder;
	public Map map;
	public AntManager antManager;
	public GameInput input;
	public int food = 0;
	
	public float deltaTime;
	
	public GameScreen(LD26game LD26) {
		this.LD26 = LD26;
	}

	@Override
	public void render(float delta) {
		deltaTime = Gdx.graphics.getRawDeltaTime();
		if (gameState == 1) {
			//antManager.update(deltaTime);
			//map.update(deltaTime);
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		//if (gameState == 1 || gameState == 2)
			map.render();
		//if (gameState == 1) {
			antManager.render();
			input.render();
		//}
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		gameState = 1;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		camera.update();
		batch = new SpriteBatch();
		spriteHolder = new SpriteHolder();
		
		deltaTime = 0.0f;
		
		input = new GameInput(this, "grey16");
		Gdx.input.setInputProcessor(input);
		antManager = new AntManager(this, "blue8");
		map = new Map(this, "black16", "blue16", "yellow16", "brown16", "red16");
		MapFactory.build(map, this);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		spriteHolder.dispose();
		batch.dispose();
	}

}
