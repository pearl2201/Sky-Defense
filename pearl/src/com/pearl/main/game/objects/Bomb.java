package com.pearl.main.game.objects;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pearl.main.game.Assets;

public class Bomb extends AbstractGameObject {

	private Sprite bombS;
	private Sprite sparkleS;
	private Sprite haloS;
	private float maxScale;
	private float minScale;
	private boolean visible;
	private float duration = 100f; // time bomb from minSize to maxSize
	private float distance;// the step size raise in 1f; 
	private float rotationSparkle; // sparkle, halo rotatiton at orgin of it
	
	public Bomb()
	{
		super();
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		bombS = new Sprite(Assets.instance.bomb.bomb);
		sparkleS = new Sprite(Assets.instance.bomb.sparkle);
		haloS = new Sprite(Assets.instance.bomb.halo);
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
			batch.draw(bombS, position.x - bombS.getWidth()*scale/2, position.y - bombS.getHeight()*scale/2, bombS.getWidth()*scale, bombS.getHeight()*scale);
			
			batch.draw(sparkleS, position.x - sparkleS.getWidth()/2 + bombS.getWidth()*scale/6, position.y - sparkleS.getHeight()/2 + bombS.getHeight()*scale/4,sparkleS.getWidth()/2, sparkleS.getHeight()/2, sparkleS.getWidth(), sparkleS.getHeight(), scale, scale, rotationSparkle);
			batch.draw(haloS, position.x - haloS.getWidth()/2 - bombS.getWidth()*scale/10, position.y - haloS.getHeight()/2 - bombS.getWidth()*scale/10,haloS.getWidth()/2, haloS.getHeight()/2, haloS.getWidth(), haloS.getHeight(), scale, scale, rotationSparkle);
			
			
		}
		}

}
