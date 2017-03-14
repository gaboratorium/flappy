package com.gaboratorium.mytestgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gaboratorium.mytestgame.MyTestGame;

/**
 * Created by root on 3/6/17.
 */

public class MenuState extends State
{
    private Texture background;
    private Texture playBtn;

    public MenuState(com.gaboratorium.mytestgame.states.GameStateManager gsm)
    {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("play.png");
    }

    @Override
    public void handleInput()
    {
        if (Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(background, 0, 0, MyTestGame.WIDTH, MyTestGame.HEIGHT);
        sb.draw(playBtn, (MyTestGame.WIDTH / 2) - (playBtn.getWidth() / 2), MyTestGame.HEIGHT / 3);
        sb.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
    }
}
