package com.pearl.main.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pearl.main.game.Assets;

public class Health extends AbstractGameObject{
	
	
	private Sprite sprite;
	
	
	public Health(Vector2 position)
	{
		super();
		this.position.set(position); 
		init();
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		this.speed.set(0,1f);
		this.acceleration.set(0,0.01f);
		sprite = new Sprite(Assets.instance.health.health);
		this.scale = 0.5f;
		dimension.set(sprite.getWidth()*scale, sprite.getHeight()*scale);
		
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		speed.add(acceleration);
		this.position.sub(speed);
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(sprite, position.x - dimension.x/2, position.y - dimension.y/2, dimension.x, dimension.y );
	}

}
