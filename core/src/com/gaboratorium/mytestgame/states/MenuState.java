package com.gaboratorium.mytestgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.gaboratorium.mytestgame.MyTestGame;

/**
 * Created by root on 3/6/17.
 */

public class MenuState extends State
{
    private Texture background;
    private Texture playBtn;
    private Texture ground;
    private Vector2 groundPos1;
    private BitmapFont title;
    private GlyphLayout titleLayout;
    private FreeTypeFontGenerator fontGenerator;

    public MenuState(com.gaboratorium.mytestgame.states.GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, MyTestGame.WIDTH / 2, MyTestGame.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("play.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, MyTestGame.GROUND_Y_OFFSET);
        title = new BitmapFont();

        // Generating font
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("FiraSans-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 18;
        title = fontGenerator.generateFont(parameter);
        titleLayout = new GlyphLayout(title, "Flappy Crappy Bird");

    }

    @Override
    public void handleInput()
    {
        if (Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
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

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), - MyTestGame.GROUND_Y_OFFSET / 2);
        title.draw(sb, titleLayout, cam.viewportWidth / 2 - titleLayout.width / 2, cam.viewportHeight / 2 + 100);
        sb.draw(playBtn,  cam.viewportWidth / 2 - 50, cam.viewportHeight / 2, 100, 60);
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        playBtn.dispose();
        ground.dispose();
        title.dispose();
        fontGenerator.dispose();
        System.out.println("Menu State Disposed");
    }
}
