package com.pearl.main.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.pearl.main.game.objects.Bomb;
import com.pearl.main.game.objects.Clouds;
import com.pearl.main.game.objects.Explosion;
import com.pearl.main.game.objects.Health;
import com.pearl.main.game.objects.Meteor;
import com.pearl.main.game.objects.MeteorDestroy;
import com.pearl.main.game.objects.Player;
import com.pearl.main.game.objects.Shockwave;
import com.pearl.main.screen.Director;
import com.pearl.main.screen.GameOverScreen;
import com.pearl.main.screen.MenuScreen;
import com.pearl.main.utils.Constants;
import com.badlogic.gdx.math.*;

public class WorldController implements Disposable, InputProcessor {

	private Director game;
	public Clouds clouds;
	public Bomb bomb;
	public Shockwave shockwave;
	public Array<Explosion> explosions;
	public Array<Meteor> meteors;
	public float meteors_duration;
	public float meteors_timer;
	public float min_meteors_duration;
	public Array<Health> healths;
	public float health_duration;
	public float health_timer;
	public float min_health_duration;
	public Array<MeteorDestroy> destroyes;
	public Player player;

	public WorldController(Director game) {
		this.game = game;
		init();
	}

	private void init() {
		clouds = new Clouds();
		bomb = new Bomb();
		shockwave = new Shockwave();
		explosions = new Array<Explosion>();
		destroyes = new Array<MeteorDestroy>();
		meteors = new Array<Meteor>();
		healths = new Array<Health>(); 
		player = Player.getInstance();
		player.reset();
		meteors_duration = 20f;
		meteors_timer = 15;
		min_meteors_duration = 3f;
		health_duration = 20f;
		health_timer = 9f;
		min_health_duration = 6f;

	}

	public void update(float deltaTime) {
		clouds.update(deltaTime);
		bomb.update(deltaTime);
		shockwave.update(deltaTime);
		
		checkExplosionFinish(deltaTime);
		checkMeteorRemove(deltaTime);
		
		meteors_timer += deltaTime;
		meteors_duration -= 0.05;
		if (meteors_duration <= min_meteors_duration) {
			meteors_duration = min_meteors_duration;
		}
		for (int i = 0; i < meteors.size; i++) {
			meteors.get(i).update(deltaTime);
		}
		if (meteors_timer >= meteors_duration) {
			float x = (float) (Math.random() * Constants.DEFAULT_VIEWPORT_WIDTH);
			float y = 0;
			Vector2 position = new Vector2(x, y);
			meteors_timer = 0;
			meteors.add(new Meteor(position));
		}
		
		health_timer +=deltaTime;
		health_duration -=0.05;
		if (health_duration <= min_health_duration)
		{
			health_duration = min_health_duration;
		}
		for (int i = 0; i<healths.size; i++)
		{
			healths.get(i).update(deltaTime);
		}
		if (health_timer>= health_duration)
		{
			float x = (float) (Math.random() * Constants.DEFAULT_VIEWPORT_WIDTH);
			float y = Constants.DEFAULT_VIEWPORT_HEIGHT;
			Vector2 position = new Vector2(x, y);
			health_timer =0;
			healths.add(new Health(position));
			Gdx.app.log("Health", "create health at" + position.toString());
		}
		checkHealthCollision();
		checkMeteoShockwaveCollision();
		checkMeteorDestroyes(deltaTime);
		checkWinLose();
	}

	private void checkMeteorDestroyes(float deltaTime) {
		// TODO Auto-generated method stub
		Iterator<MeteorDestroy> iterators = destroyes.iterator();
		
		while(iterators.hasNext())
		{
			MeteorDestroy iterator = iterators.next();
			if (iterator.isFinish())
			{
				destroyes.removeValue(iterator, true);
			}
			else
			{
				iterator.update(deltaTime);
			}
		}
	}

	private void checkHealthCollision() {
		// TODO Auto-generated method stub
		Iterator<Health> iterators = healths.iterator();
		
		while (iterators.hasNext())
		{
			Health iterator = iterators.next();
			if ((iterator.getPosition().y + iterator.getDimension().y/2) <= 0)
			{
				healths.removeValue(iterator, true);
			}
			else
			{
				if (shockwave.isGrowing())
				{
					Vector2 d = new Vector2(shockwave.getPosition().x - iterator.getPosition().x, shockwave.getPosition().y- iterator.getPosition().y);
					float distance = d.len();
					if (distance <= shockwave.getDimension().x/2)
					{
						healths.removeValue(iterator, true);
						player.addHealth();
						Assets.instance.sounds.health.play();
					}
				}
			}
		}
	}

	private void checkWinLose() {
		// TODO Auto-generated method stub
		if (player.getHealth() <= 0)
		{
			game.setScreen(new GameOverScreen(game));
		}
		
	}

	private void checkMeteorRemove(float deltaTime) {
		// TODO Auto-generated method stub
		Iterator<Meteor> iterators = meteors.iterator();
		while(iterators.hasNext())
		{
			Meteor iterator = iterators.next();
			if ((iterator.getPosition().y - iterator.getDimension().y) > Constants.DEFAULT_VIEWPORT_HEIGHT)
			{
				meteors.removeValue(iterator, true);
				player.decHealth();
				destroyes.add(new MeteorDestroy(iterator.getPosition()));
				Assets.instance.sounds.fire_truck.play();
				if (player.getHealth() <= 0)
				{
					checkWinLose();
				}
			}
		}
	}

	private void checkExplosionFinish(float deltaTime) {
		// TODO Auto-generated method stub
		
		Iterator<Explosion> iterators = explosions.iterator();
		while(iterators.hasNext())
		{
			Explosion iterator = iterators.next();
			if (iterator.isFinish())
			{
				explosions.removeValue(iterator, true);
				
			}
			else
			{
				iterator.update(deltaTime);
			}
		}
		
	}

	private void checkMeteoShockwaveCollision() {
		// TODO Auto-generated method stub
		
		if (shockwave.isGrowing()) {
			Iterator<Meteor> iterators = meteors.iterator();
			
			while (iterators.hasNext()) {
				Meteor iterator = iterators.next();
					
				
					Vector2 d = new Vector2(shockwave.getPosition().x
							- iterator.getPosition().x,
							shockwave.getPosition().y
									- iterator.getPosition().y);
					float distance = d.len();

					if (distance <= shockwave.getDimension().x / 2) {
						
						meteors.removeValue((Meteor) iterator, true);
						explosions.add(new Explosion(iterator.getPosition()));
						Assets.instance.sounds.bomb.play();
						player.addScore(iterator.getScore());
				
				}
			}
			
			
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Assets.instance.sounds.fire_truck.stop();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.ESCAPE) {
			game.setScreen(new MenuScreen(game), null);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (!bomb.isVisible()) {
			bomb.reset(screenX, Constants.DEFAULT_VIEWPORT_HEIGHT - screenY);

			Gdx.app.log(
					"Codirnate",
					screenX
							+ " "
							+ Float.toString(Constants.DEFAULT_VIEWPORT_HEIGHT
									- screenY));

		} else {
			bomb.hide();
			if (bomb.isMaxSize()) {
				// Khoi tao halo de setCollision
				shockwave.reset(bomb.getPosition());
				Gdx.app.log("Shockwave", shockwave.getPosition().toString());
				Assets.instance.sounds.bombRelease.play();

			} else {

				Assets.instance.sounds.bombFail.play();
			}
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
