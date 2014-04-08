package com.pearl.main.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {

	protected Vector2 position;
	protected Vector2 speed;
	protected float scale;
	protected Vector2 dimension;
	protected Vector2 acceleration;

	public AbstractGameObject() {

		position = new Vector2(0, 0);
		speed = new Vector2(0, 0);
		scale = 1;
		dimension = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
	}

	protected abstract void init();

	public abstract void update(float deltaTime);

	public abstract void render(SpriteBatch batch);

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void raiseScale(float dS) {
		this.scale += dS;
	}

	public void setDimension(Vector2 dimension) {
		this.dimension = dimension;

	}

	public void setAccceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Vector2 getPosition() {

		return position;
	}

	public Vector2 getSpeed() {
		return speed;
	}

	public float getScale() {
		return scale;
	}

	public Vector2 getDimension() {
		return dimension;
	}
}
