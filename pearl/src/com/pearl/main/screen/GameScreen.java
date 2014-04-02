package com.pearl.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.pearl.main.game.Assets;
import com.pearl.main.game.WorldController;
import com.pearl.main.game.WorldRenderer;

public class GameScreen extends AbstractGameScreen{

	private WorldController controller;
	private WorldRenderer renderer;
	private Music backgroundMusic;
	public GameScreen(Director game) {
		super(game);
		init();
		
	}
	
	private void init()
	{
		controller = new WorldController(game);
		renderer = new WorldRenderer(controller);
		backgroundMusic= Assets.instance.music.background;
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	@Override
	public void render(float deltaTime) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		controller.update(deltaTime);
		renderer.render(deltaTime);
		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		backgroundMusic.stop();
		controller.dispose();
		renderer.dispose();
		Gdx.input.setCatchBackKey(false);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		controller = new WorldController(game);
		renderer = new WorldRenderer(controller);
		Gdx.input.setCatchBackKey(true);
		backgroundMusic.play();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		//backgroundMusic.pause();
	}

	@Override
	public InputProcessor getInputProcessor() {
		// TODO Auto-generated method stub
		return controller;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
