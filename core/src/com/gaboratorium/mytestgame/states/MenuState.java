package com.gaboratorium.mytestgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gaboratorium.mytestgame.MyTestGame;

import java.awt.geom.Rectangle2D;

/**
 * Created by root on 3/6/17.
 */

public class MenuState extends State implements InputProcessor
{
    private Texture background;
    private Texture playBtn;
    private Texture ground;
    private Vector2 groundPos1;
    private BitmapFont title;
    private BitmapFont highScoreType;
    private GlyphLayout highScoreTypeLayout;
    private GlyphLayout titleLayout;
    private FreeTypeFontGenerator fontGenerator;
    private Sprite playBtnSprite;


    public MenuState(com.gaboratorium.mytestgame.states.GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, MyTestGame.WIDTH / 2, MyTestGame.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("play.png");
        playBtnSprite = new Sprite(playBtn);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, MyTestGame.GROUND_Y_OFFSET);
        title = new BitmapFont();
        highScoreType = new BitmapFont();

        // Creating a font generator
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("FiraSans-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();


        parameter.size = 18;
        title = fontGenerator.generateFont(parameter);
        parameter.size = 14;
        highScoreType = fontGenerator.generateFont(parameter);
        titleLayout = new GlyphLayout(title, "Flappy Crappy Bird");
        highScoreTypeLayout = new GlyphLayout(highScoreType, "Highscore: " + MyTestGame.highscore);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void handleInput(){}

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
        highScoreType.draw(sb, highScoreTypeLayout, cam.viewportWidth / 2 - highScoreTypeLayout.width / 2, cam.viewportHeight / 2 + 65);
        sb.draw(playBtnSprite,  cam.viewportWidth / 2 - 50, cam.viewportHeight / 2 - 25, 100, 60);
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
        highScoreType.dispose();
        fontGenerator.dispose();
        Gdx.input.setInputProcessor(null);
        System.out.println("Menu State Disposed");
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    Vector3 tp = new Vector3();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {

        cam.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        Rectangle bounds = new Rectangle(cam.viewportWidth / 2 - 50, cam.viewportHeight / 2 - 25, 100, 60);

        if (bounds.contains(tp.x, tp.y))
        {
            gsm.set(new PlayState(gsm));
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
