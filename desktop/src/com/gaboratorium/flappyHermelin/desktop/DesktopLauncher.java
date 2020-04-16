package com.gaboratorium.flappyHermelin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gaboratorium.flappyHermelin.FlappyHermelin;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyHermelin.WIDTH;
		config.height = FlappyHermelin.HEIGHT;
		config.title = FlappyHermelin.TITLE;
		new LwjglApplication(new FlappyHermelin(), config);
	}
}









