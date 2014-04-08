package com.pearl.main.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.pearl.main.game.Assets;

public class Background extends AbstractGameObject implements Disposable{

	private Sprite city_lightS;
	private Sprite city_darkS;
	private Sprite treesS;
	private Sprite image_backgroundS;
	
	
	public Background()
	{
		super();
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		city_lightS =new Sprite( Assets.instance.background.city_light);
		city_darkS = new Sprite(Assets.instance.background.city_dark);
		treesS = new Sprite(Assets.instance.background.trees);
		image_backgroundS = new Sprite(Assets.instance.background.image_background);
	
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(image_backgroundS, 0, 0);
		for (int i = 0; i<2; i++ )
		{

		batch.draw(city_lightS, city_lightS.getRegionWidth()*i, 40);
		batch.draw(city_darkS,city_lightS.getRegionWidth()*i, 0);

		}
		for (int i =0; i<3; i++)
		{
		batch.draw(treesS, treesS.getRegionWidth()*i, 0);
		
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
