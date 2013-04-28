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
	public Input input;
	public int food = 0;
	
	public float deltaTime;
	
	public GameScreen(LD26game LD26) {
		this.LD26 = LD26;
	}

	@Override
	public void render(float delta) {
		deltaTime = Gdx.graphics.getRawDeltaTime();
		
		antManager.update(deltaTime);
		map.update(deltaTime);
		
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		map.render();
		antManager.render();
		input.render();
		
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
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		spriteHolder = new SpriteHolder();
		
		deltaTime = 0.0f;
		
		input = new Input(this, "grey16");
		antManager = new AntManager(this, "blue8");
		map = new Map(this, "black16", "blue16", "yellow16", "brown16", "red16");
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
