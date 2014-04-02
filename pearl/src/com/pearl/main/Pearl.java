package com.pearl.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.pearl.main.game.Assets;
import com.pearl.main.screen.Director;
import com.pearl.main.screen.GameScreen;
import com.pearl.main.screen.MenuScreen;
import com.pearl.main.screen.transition.ScreenTransition;
import com.pearl.main.screen.transition.ScreenTransitionFade;
import com.pearl.main.screen.transition.ScreenTransitionSlice;

public class Pearl extends Director
{

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		Assets.instance.init(new AssetManager());
		float duration = 1f;
		ScreenTransition transition = ScreenTransitionSlice.init(duration, ScreenTransitionSlice.UP_DOWN, 4, Interpolation.exp10Out); 
		setScreen(new MenuScreen(this),transition);
		
	}
	
}
