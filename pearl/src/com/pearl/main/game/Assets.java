package com.pearl.main.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.pearl.main.utils.Constants;

public class Assets implements Disposable, AssetErrorListener {

	public static Assets instance = new Assets();
	private AssetManager assetManager;

	/******************************
	 * 
	 * Load Component for Assets
	 * 
	 * ****************************
	 */
	public AssetsMusics music;
	public AssetsBackground background;
	public AssetsMenu menu;
	public AssetsBomb bomb;
	public AssetsShockwave shockwave;
	public AssetsExplosion explosion;
	public AssetsMeteor meteor;
	public AssetsSounds sounds;
	public AssetsHealth health;
	public AssetsPlayer player;
	public AssetsMeteorDestroy meteorDestroy;
	
	
	public class AssetsHealth
	{
		public TextureRegion health;
		public AssetsHealth(TextureAtlas atlas)
		{
			health = atlas.findRegion("health");
		}
	}
	public class AssetsPlayer{
		public TextureRegion health_icon;
		public BitmapFont bmf;
		
		public AssetsPlayer(TextureAtlas atlas)
		{
			health_icon = atlas.findRegion("health_icon");
			bmf = new BitmapFont(Gdx.files.internal("image/font.fnt"),Gdx.files.internal("image/font.png"), false);
		}
	}

	public class AssetsMeteor {
		public TextureRegion meteor;

		public AssetsMeteor(TextureAtlas atlas) {
			meteor = atlas.findRegion("meteor");
		}
	}

	public class AssetsShockwave {
		public TextureRegion shockwave;

		public AssetsShockwave(TextureAtlas atlas) {
			shockwave = atlas.findRegion("shockwave");
		}
	}

	public class AssetsBomb {
		public TextureRegion bomb;
		public TextureRegion sparkle;
		public TextureRegion halo;

		public AssetsBomb(TextureAtlas atlas) {
			bomb = atlas.findRegion("bomb");
			sparkle = atlas.findRegion("sparkle");
			halo = atlas.findRegion("halo");
		}

	}

	public class AssetsExplosion {
		public float duration;
		public Animation explosion;

		public AssetsExplosion(TextureAtlas atlas) {
			duration = 0.025f;
			Array<AtlasRegion> explosionAtlats = new Array<AtlasRegion>();
			String name = "explosion_small";
			String regionName = "";
			for (int i = 1; i <= 6; i++) {
				regionName = name + Integer.toString(i);
				explosionAtlats.add(atlas.findRegion(regionName));

			}
			explosion = new Animation(duration, explosionAtlats);

		}
	}
	
	public class AssetsMeteorDestroy {
		public float duration;
		public Animation explosion;

		public AssetsMeteorDestroy(TextureAtlas atlas) {
			duration = 0.025f;
			Array<AtlasRegion> explosionAtlats = new Array<AtlasRegion>();
			String name = "bomb";
			String regionName = "";
			AtlasRegion region;
			for (int i = 1; i <= 9; i++) {
				regionName = name + Integer.toString(i);
				region = atlas.findRegion(regionName);
				region.flip(false, true);
				explosionAtlats.add(region);

			}
			explosion = new Animation(duration, explosionAtlats);

		}
	}

	public class AssetsMenu {
		public TextureRegion logo;
		public TextureRegion game_over;
		public TextureRegion play;
		public TextureRegion try_again;

		public AssetsMenu(TextureAtlas atlas) {
			logo = atlas.findRegion("skydefease");
			game_over = atlas.findRegion("GameOver");
			play = atlas.findRegion("play");
			try_again = atlas.findRegion("try_again");

		}
	}

	public class AssetsBackground {
		public TextureRegion city_light;
		public TextureRegion city_dark;
		public TextureRegion trees;
		public TextureRegion image_background;
		public TextureRegion cloud;

		public AssetsBackground(TextureAtlas atlas) {
			city_light = atlas.findRegion("city_light");
			city_dark = atlas.findRegion("city_dark");
			trees = atlas.findRegion("trees");
			image_background = new TextureRegion(new Texture(
					Gdx.files.internal("image/bg.png")));
			cloud = atlas.findRegion("cloud");
		}
	}

	public class AssetsMusics {
		public Music background;

		public AssetsMusics(AssetManager as) {
			background = as.get("Music/background.mp3", Music.class);

		}

	}
	
	public class AssetsSounds{
		
		public Sound bombFail;
		public Sound bombRelease;
		public Sound bomb;
		public Sound fire_truck;
		public Sound health;
		
		public AssetsSounds(AssetManager as){
			
			bombFail = as.get("Sound/bombFail.wav", Sound.class);
			bombRelease = as.get("Sound/bombRelease.wav", Sound.class);
			bomb = as.get("Sound/boom.wav", Sound.class);
			fire_truck = as.get("Sound/fire_truck.wav", Sound.class);
			health = as.get("Sound/health.wav", Sound.class);
					
		}
	}
	
	/*******************************
	 * 
	 * Main Assets Class
	 * 
	 * *****************************
	 */
	
	
	
	
	private Assets() {

	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;

		Texture.setEnforcePotImages(false);
		/*
		 * Texture.setAssetManager(assetManager); Resolution[] resolution = {
		 * new Resolution(480, 320, "iphone"), new Resolution(1024, 768,
		 * "ipad"), new Resolution(2048, 1536, "ipadhd") };
		 * ResolutionFileResolver resolver = new ResolutionFileResolver( new
		 * InternalFileHandleResolver(), resolution); textureLoader = new
		 * TextureLoader(resolver); assetManager.setLoader(Texture.class,
		 * textureLoader); assetManager.load("image/bg.png", Texture.class);
		 * assetManager.finishLoading(); background =
		 * assetManager.get("image/bg.png");
		 */

		assetManager.load(Constants.TEXTURE_ATLAS_OBJECT, TextureAtlas.class);
		assetManager.load("Music/background.mp3", Music.class);
		assetManager.load("Sound/boom.wav", Sound.class);
		assetManager.load("Sound/bombFail.wav", Sound.class);
		assetManager.load("Sound/fire_truck.wav", Sound.class);
		assetManager.load("Sound/bombRelease.wav", Sound.class);
		assetManager.load("Sound/health.wav", Sound.class);
		assetManager.finishLoading();

		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECT);

		for (Texture t : atlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		}

		background = new AssetsBackground(atlas);
		menu = new AssetsMenu(atlas);
		music = new AssetsMusics(assetManager);
		bomb = new AssetsBomb(atlas);
		shockwave = new AssetsShockwave(atlas);
		explosion = new AssetsExplosion(atlas);
		meteor = new AssetsMeteor(atlas);
		sounds = new AssetsSounds(assetManager);
		player = new AssetsPlayer(atlas);
		health = new AssetsHealth(atlas);
		meteorDestroy = new AssetsMeteorDestroy(atlas);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sounds.bomb.dispose();
		sounds.bombFail.dispose();
		sounds.bombRelease.dispose();
		sounds.fire_truck.dispose();
		sounds.health.dispose();
		player.bmf.dispose();
		music.background.dispose();
		assetManager.dispose();
	}

	@Override
	public void error(String fileName, Class type, Throwable throwable) {
		// TODO Auto-generated method stub

	}

}
