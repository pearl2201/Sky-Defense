package com.pearl.main.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.pearl.main.game.Assets;
import com.pearl.main.utils.Constants;

public class MeteorDestroy extends AbstractGameObject {
	private Animation destroy;
	private float stateTime;
	private boolean finish;
	private boolean running;
	private TextureRegion currentFrame;

	public MeteorDestroy(Vector2 position) {
		super();
		this.position.set(position);
		init();

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		destroy = Assets.instance.meteorDestroy.explosion;
		
		Gdx.app.log("Explosion",Float.toString(destroy.animationDuration));
		stateTime = 0.0f;
		running = false;
		finish = false;
		
		stateTime =0;
		running = true;
		finish = false;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if ((destroy.isAnimationFinished(stateTime)) && !finish)
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
			currentFrame = destroy.getKeyFrame(stateTime);
			batch.draw(currentFrame, position.x - currentFrame.getRegionWidth()/2, Constants.DEFAULT_VIEWPORT_HEIGHT - currentFrame.getRegionHeight());
		}
	}

	public boolean isRunning()
	{
		return running;
	}
	
	public boolean isFinish()
	{
		finish = destroy.isAnimationFinished(stateTime);
		return finish;
	}
}
