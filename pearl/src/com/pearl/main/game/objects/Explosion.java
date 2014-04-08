package com.pearl.main.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.pearl.main.game.Assets;

public class Explosion extends AbstractGameObject {

	private Animation explosion;
	private float stateTime;
	private boolean finish;
	private boolean running;
	private TextureRegion currentFrame;

	public Explosion(Vector2 position) {
		super();
		this.position.set(position);
		init();

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		explosion = Assets.instance.explosion.explosion;
		
		stateTime = 0.0f;
		running = false;
		finish = false;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if ((explosion.isAnimationFinished(stateTime)) && !finish)
		{
			finish = true;
			running = false;
			Gdx.app.log("Explosion", Float.toString(stateTime));
		}
		
		if (running) {
			stateTime += deltaTime;
			Gdx.app.log("Explosion", Float.toString(stateTime));
			
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if (running)
		{
			currentFrame = explosion.getKeyFrame(stateTime);
			batch.draw(currentFrame, position.x - currentFrame.getRegionWidth()/2, position.y - currentFrame.getRegionHeight()/2);
		}
	}

	public boolean isRunning()
	{
		return running;
	}
	
	public boolean isFinish()
	{
		finish = explosion.isAnimationFinished(stateTime);
		return finish;
	}

}
