package com.pearl.main.game.objects;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pearl.main.game.Assets;

public class Shockwave extends AbstractGameObject{

	private Sprite shockwaveSprite;
	private float maxScale = 0.7f;
	private float minScale = 0f;
	private float duration = 20f;
	private float distance = (maxScale - minScale)/duration;
	private boolean growing;
	private boolean finish;
	
	
	public Shockwave()
	{
		super();
		init();
		
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		shockwaveSprite = new Sprite(Assets.instance.shockwave.shockwave);
		shockwaveSprite.setSize(0.8f*shockwaveSprite.getWidth(), 0.8f*shockwaveSprite.getHeight());
		growing= false;
		finish = false;
		
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if (scale >= maxScale) {
			finish = true;
			growing = false;
		}
		
		if (growing)
		{
		scale += distance;
		this.dimension.set(shockwaveSprite.getWidth()*scale,shockwaveSprite.getHeight()* scale);
		}
		
		
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(growing)
		{
			
			batch.draw(shockwaveSprite, position.x - dimension.x/2, position.y - dimension.y/2 , shockwaveSprite.getWidth()*scale, shockwaveSprite.getHeight()*scale);
		}
				
	}
	
	public boolean isGrowing()
	{
		return growing;
	}
	
	public boolean isFinish()
	{
		
		return finish;
	}
	
	public void reset(Vector2 position)
	{
		
		scale = minScale;
		growing = true;
		finish = false;
		dimension.set(shockwaveSprite.getWidth()*scale,shockwaveSprite.getHeight()* scale);
		this.position.set(position);
		
		
	}

	public void hide() {
		// TODO Auto-generated method stub
		growing = false;
		
	}
}
