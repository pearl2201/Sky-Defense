package com.pearl.main.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pearl.main.game.Assets;
import com.pearl.main.utils.Constants;

public class Clouds {
	
	private Cloud[] clouds;
	private Sprite cloudS;
	private static final Vector2 speed = new Vector2(1,0);
	private int count;
	public class Cloud extends AbstractGameObject{
		
		private Sprite sprite;
		
		
		
		public Cloud( Vector2 speed, Vector2 position)
		{
			super();
			setPosition(position);
			setSpeed(speed);
			sprite = new Sprite(Assets.instance.background.cloud);
			
		}
		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update(float deltaTime) {
			// TODO Auto-generated method stub
			
			position = position.sub(speed); 
			if (position.x + sprite.getWidth()< (-20) )
			{
				position.x = Constants.DEFAULT_VIEWPORT_WIDTH; 
				
			}
			
			
		}

		@Override
		public void render(SpriteBatch batch) {
			// TODO Auto-generated method stub
			batch.draw(sprite, position.x, position.y);
		}
		
	}
	
	public Clouds()
	{
		clouds = new Cloud[6];
		cloudS = new Sprite(Assets.instance.background.cloud);
		count =0;
		int d = -1;
		for (int i =0; i< 6; i++)
		{
			
			d = d*(-1);
			clouds[i] = new Cloud(speed,new Vector2((cloudS.getWidth()+20)*i,400 + 60*d ));
			count++;
		}
	}
	
	public void update(float deltaTime)
	{
		for (int i=0;i<count; i++)
		{
			clouds[i].update(deltaTime);
		}
	}
	
	public void render(SpriteBatch batch)
	{
		for (int i=0;i<count; i++)
		{
			clouds[i].render(batch);
		}
	}

}
