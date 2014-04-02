package com.pearl.main.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pearl.main.game.Assets;

public class Meteor extends AbstractGameObject{

	private Sprite meteor;
	private int score; 
	 
	
	public Meteor(Vector2 position)
	{
		super();
		this.position = position;
		init();
		
	}
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		meteor = new Sprite( Assets.instance.meteor.meteor); 
		scale = 0.5f;
		speed = new Vector2(0,1f);
		dimension = new Vector2(meteor.getWidth()*scale, meteor.getHeight()*scale);
		score = 10;
		
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		position.add(speed);
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(meteor, position.x - dimension.x/2, position.y - dimension.y/2, dimension.x, dimension.y);
	}
	
	public int getScore()
	{
		return score;
	}

}
