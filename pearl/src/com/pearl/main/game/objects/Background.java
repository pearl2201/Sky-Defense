package com.pearl.main.game.objects;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.pearl.main.game.Assets;

public class Background extends AbstractGameObject implements Disposable{

	private TextureRegion city_light;
	private TextureRegion city_dark;
	private TextureRegion trees;
	private TextureRegion image_background;
	
	
	public Background()
	{
		super();
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		city_light = Assets.instance.background.city_light;
		city_dark = Assets.instance.background.city_dark;
		trees = Assets.instance.background.trees;
		image_background = Assets.instance.background.image_background;
	
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(image_background, 0, 0);
		for (int i = 0; i<2; i++ )
		{

		batch.draw(city_light, city_light.getRegionWidth()*i, 40);
		batch.draw(city_dark,city_light.getRegionWidth()*i, 0);

		}
		for (int i =0; i<3; i++)
		{
		batch.draw(trees, trees.getRegionWidth()*i, 0);
		
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
