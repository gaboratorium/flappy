package com.gaboratorium.flappyHermelin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gaboratorium.flappyHermelin.states.GameStateManager;
import com.gaboratorium.flappyHermelin.states.MenuState;

public class FlappyHermelin extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final int GROUND_Y_OFFSET = -70;
	public static final String TITLE = "Flappy Hermelin";
	public static int highscore;

	private com.gaboratorium.flappyHermelin.states.GameStateManager gsm;
	private SpriteBatch batch;


	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Preferences preferences = Gdx.app.getPreferences("gamePreferences");

		if (!preferences.contains("highScore"))
		{
			preferences.putInteger("highScore", 0);
			preferences.flush();
		}

		highscore = preferences.getInteger("highScore");
		music = Gdx.audio.newMusic(Gdx.files.internal("march_of_the_spoons.mp3"));
		music.setLooping(true);
		music.setVolume(0.4f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
		music.dispose();
	}
}
