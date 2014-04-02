package com.pearl.main.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pearl.main.game.Assets;

public class Bomb extends AbstractGameObject {

	private Sprite bombSprite;
	private Sprite sparkleSprite;
	private Sprite haloSprite;
	private float maxScale;
	private float minScale;
	private boolean visible;
	private float duration = 100f;
	private float distance;
	private float rotationSparkle;
	
	public Bomb()
	{
		super();
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		bombSprite = new Sprite(Assets.instance.bomb.bomb);
		sparkleSprite = new Sprite(Assets.instance.bomb.sparkle);
		haloSprite = new Sprite(Assets.instance.bomb.halo);
		visible = false;
		maxScale = 0.3f;
		minScale = 0.05f;
		distance = (maxScale - minScale)/duration; 
		rotationSparkle =90;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		if (visible) {
			
			scale += distance;
			rotationSparkle = (rotationSparkle + deltaTime*30)%360; 
			
			
			if (scale >= maxScale) {
				scale = maxScale;
			}
			
			
		}
		
	}

	public void hide() {
		visible = false;
	}

	public void show() {
		visible = true;
	}

	public void reset(float f, float g) {
		position.set(f, g);
		scale = minScale;
		visible = true;
		 
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	public boolean isMaxSize()
	{
		return (scale>= 0.25f) ? true:false;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if (visible)
		{
			batch.draw(bombSprite, position.x - bombSprite.getWidth()*scale/2, position.y - bombSprite.getHeight()*scale/2, bombSprite.getWidth()*scale, bombSprite.getHeight()*scale);
			
			batch.draw(sparkleSprite, position.x - sparkleSprite.getWidth()/2 + bombSprite.getWidth()*scale/6, position.y - sparkleSprite.getHeight()/2 + bombSprite.getHeight()*scale/4,sparkleSprite.getWidth()/2, sparkleSprite.getHeight()/2, sparkleSprite.getWidth(), sparkleSprite.getHeight(), scale, scale, rotationSparkle);
			batch.draw(haloSprite, position.x - haloSprite.getWidth()/2 - bombSprite.getWidth()*scale/10, position.y - haloSprite.getHeight()/2 - bombSprite.getWidth()*scale/10,haloSprite.getWidth()/2, haloSprite.getHeight()/2, haloSprite.getWidth(), haloSprite.getHeight(), scale, scale, rotationSparkle);
			
			
		}
		}

}
