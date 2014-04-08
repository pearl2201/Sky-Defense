package com.pearl.main.game.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pearl.main.game.Assets;
import com.pearl.main.utils.Constants;

public class Player {

	private static Player instance;
	private static BitmapFont font;
	private static Sprite sprite;
	private static final float scale = 0.5f; 
	private int health;
	private int score;
	
	public static Player getInstance()
	{
		if (instance == null)
		{
			instance = new Player();
		}
		return instance;
	}
	
	
	private Player()
	{
		sprite = new Sprite(Assets.instance.player.health_icon);
		font = Assets.instance.player.bmf;
		reset();
	}
	
	public void reset()
	{
		health = 3;
		score =0;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void addHealth()
	{
		health++;
	}
	
	public void decHealth()
	{
		health--;
	}
	
	public void setScore(int s)
	{
		this.score = s;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void addScore(int s)
	{
		score+=s;
	}
	
	public void render(SpriteBatch batch)
	{
		font.draw(batch, Integer.toString(score), Constants.DEFAULT_VIEWPORT_WIDTH -120, Constants.DEFAULT_VIEWPORT_HEIGHT);
		for (int i =0; i<health; i++)
		{
			batch.draw(sprite, (sprite.getWidth()*scale +10)*i, Constants.DEFAULT_VIEWPORT_HEIGHT - 40, sprite.getWidth()*scale, sprite.getHeight()*scale);
		}
		
	}
}
