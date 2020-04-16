package com.gaboratorium.flappyHermelin.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gaboratorium.flappyHermelin.FlappyHermelin;
import com.gaboratorium.flappyHermelin.helpers.Size;

/**
 * Created by root on 3/6/17.
 */

public class MenuState extends State implements InputProcessor
{
    private Texture background;

    private Texture playBtn;
    private Vector2 playBtnPos;
    private Size playBtnSize;

    private Texture ground;
    private Vector2 groundPos1;
    private BitmapFont title;
    private BitmapFont highScoreType;
    private GlyphLayout highScoreTypeLayout;
    private GlyphLayout titleLayout;

    // New stuff
    private BitmapFont instructionsText;
    private BitmapFont minionsText;
    private BitmapFont coinsText;
    private BitmapFont tapText;
    private GlyphLayout instructionsTextLayout;
    private GlyphLayout minionsTextLayout;
    private GlyphLayout coinsTextLayout;
    private GlyphLayout tapTextLayout;

    private FreeTypeFontGenerator fontGenerator;
    private Sprite playBtnSprite;


    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, FlappyHermelin.WIDTH / 2, FlappyHermelin.HEIGHT / 2);
        background = new Texture("bg_dark.png");

        playBtn = new Texture("playbtn.png");
        playBtnSprite = new Sprite(playBtn);
        playBtnSize = new Size(50, 50);
        playBtnPos = new Vector2(cam.viewportWidth / 2 - playBtnSize.getWidth() / 2, cam.viewportHeight / 2 - 25);

        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, FlappyHermelin.GROUND_Y_OFFSET);
        title = new BitmapFont();
        highScoreType = new BitmapFont();

        // Creating a font generator
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("FiraSans-Regular.otf"));
        FreeTypeFontGenerator fontGeneratorForTitle = new FreeTypeFontGenerator(Gdx.files.internal("Nightmare Before Christmas.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 10;

        // New stuff
        instructionsText = fontGenerator.generateFont(parameter);
        minionsText = fontGenerator.generateFont(parameter);
        coinsText = fontGenerator.generateFont(parameter);
        tapText = fontGenerator.generateFont(parameter);

        instructionsTextLayout = new GlyphLayout(instructionsText, "Instructions");
        minionsTextLayout = new GlyphLayout(minionsText, "Tap on minions to smash them");
        coinsTextLayout = new GlyphLayout(coinsText, "Tap on coins to collect them");
        tapTextLayout = new GlyphLayout(tapText, "Tap anywhere else to flap");


        parameter.size = 34;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;
        parameter.color = new Color(0, 208, 255, 1);
        title = fontGeneratorForTitle.generateFont(parameter);
        parameter.size = 16;
        parameter.color = new Color(1, 1, 1, 1);
        highScoreType = fontGenerator.generateFont(parameter);
        titleLayout = new GlyphLayout(title, FlappyHermelin.TITLE);
        highScoreTypeLayout = new GlyphLayout(highScoreType, "Highscore: " + FlappyHermelin.highscore);

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

        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), - FlappyHermelin.GROUND_Y_OFFSET / 2);
        title.draw(sb, titleLayout, cam.viewportWidth / 2 - titleLayout.width / 2, cam.viewportHeight / 2 + 100);
        highScoreType.draw(sb, highScoreTypeLayout, cam.viewportWidth / 2 - highScoreTypeLayout.width / 2, cam.viewportHeight / 2 + 65);
        sb.draw(playBtnSprite, playBtnPos.x, playBtnPos.y, playBtnSize.getWidth(), playBtnSize.getHeight());

        instructionsText.draw(sb, instructionsTextLayout, cam.viewportWidth / 2 - instructionsTextLayout.width / 2, 155);
        minionsText.draw(sb, minionsTextLayout, cam.viewportWidth / 2 - minionsTextLayout.width / 2, 140);
        coinsText.draw(sb, coinsTextLayout, cam.viewportWidth / 2 - coinsTextLayout.width / 2, 125);
        tapText.draw(sb, tapTextLayout, cam.viewportWidth / 2 - tapTextLayout.width / 2, 110);

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
        Rectangle bounds = new Rectangle(playBtnPos.x, playBtnPos.y, playBtnSize.getWidth(), playBtnSize.getHeight());

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
