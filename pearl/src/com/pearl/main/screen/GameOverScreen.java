package com.pearl.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.pearl.main.game.Assets;
import com.pearl.main.game.objects.Background;
import com.pearl.main.screen.transition.ScreenTransition;
import com.pearl.main.screen.transition.ScreenTransitionSlide;
import com.pearl.main.utils.Constants;

public class GameOverScreen extends AbstractGameScreen implements InputProcessor{

	private Background background;
	SpriteBatch batch;
	OrthographicCamera camera;
	private Music backgroundMusic;
	
	public GameOverScreen(Director game) {
		super(game);
		init();
	}
	
	private void init()
	{
		camera = new OrthographicCamera(Constants.DEFAULT_VIEWPORT_WIDTH,Constants.DEFAULT_VIEWPORT_HEIGHT);
		camera.position.set(Constants.DEFAULT_VIEWPORT_WIDTH/2, Constants.DEFAULT_VIEWPORT_HEIGHT/2, 0);
		camera.update();
		background = new Background(); 
		batch = new SpriteBatch();
		backgroundMusic= Assets.instance.music.background;
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}
	
	public void render(float deltaTime)
	{
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);	
		batch.begin();
		background.render(batch);
		batch.draw(Assets.instance.menu.try_again, Constants.DEFAULT_VIEWPORT_WIDTH/2 - Assets.instance.menu.try_again.getRegionWidth()/2 , Constants.DEFAULT_VIEWPORT_HEIGHT/2-Assets.instance.menu.try_again.getRegionHeight()/2);
		batch.draw(Assets.instance.menu.logo, Constants.DEFAULT_VIEWPORT_WIDTH/2 - Assets.instance.menu.game_over.getRegionWidth()/2, Constants.DEFAULT_VIEWPORT_HEIGHT/2 - Assets.instance.menu.game_over.getRegionHeight()+200);
		batch.end();
		
	}

	public void resize(float width, float height)
	{
		
	}
	
	public void show()
	{
		
	}
	
	public void hide()
	{
		backgroundMusic.stop();
	}
	
	public void pause()
	{
		
	}
	
	
	public InputProcessor getInputProcessor()
	{
		return this;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		
			float duration = 1f;
			ScreenTransition transition = ScreenTransitionSlide.init(duration, ScreenTransitionSlide.UP, true, Interpolation.linear);
			game.setScreen(new GameScreen(game), transition);
			background.dispose();
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	
	
}
