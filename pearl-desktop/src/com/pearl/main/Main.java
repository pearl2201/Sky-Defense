package com.pearl.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "pearl";
		cfg.width = 824;
		cfg.height = 568;
		cfg.useGL20 = true;
		
		new LwjglApplication(new Pearl(), cfg);
	}
}
