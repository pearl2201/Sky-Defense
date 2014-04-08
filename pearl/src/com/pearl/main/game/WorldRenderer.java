package com.pearl.main.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.pearl.main.game.objects.Background;
import com.pearl.main.utils.Constants;
public class WorldRenderer implements Disposable{

	private WorldController controller;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Background background;
	
	
	public WorldRenderer(WorldController controller)
	{
		this.controller = controller;
		init();
	}
	
	private void init()
	{
		camera = new OrthographicCamera(Constants.DEFAULT_VIEWPORT_WIDTH, Constants.DEFAULT_VIEWPORT_HEIGHT);
		camera.position.set(Constants.DEFAULT_VIEWPORT_WIDTH/2, Constants.DEFAULT_VIEWPORT_HEIGHT/2, 0);
		
		camera.update();
		batch = new SpriteBatch();
		background = new Background();
	
	}
	
	
	public void render(float deltaTime)
	{
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.render(batch);
		controller.clouds.render(batch);
		controller.bomb.render(batch);
		controller.shockwave.render(batch);
		controller.player.render(batch);
		for (int i=0; i<controller.explosions.size; i++)
		{
			controller.explosions.get(i).render(batch);
		}
		for (int i=0; i<controller.destroyes.size; i++)
		{
			controller.destroyes.get(i).render(batch);
		}
		for (int i=0; i<controller.meteors.size; i++)
		{
			controller.meteors.get(i).render(batch);
		}
		for (int i=0; i<controller.healths.size; i++)
		{
			controller.healths.get(i).render(batch);
		}
		batch.end();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		
	}

}
