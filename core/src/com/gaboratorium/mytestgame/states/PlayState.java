package com.gaboratorium.mytestgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.gaboratorium.mytestgame.MyTestGame;
import com.gaboratorium.mytestgame.sprites.Bird;
import com.gaboratorium.mytestgame.sprites.Coin;
import com.gaboratorium.mytestgame.sprites.Tube;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Created by root on 3/7/17.
 */

public class PlayState extends State
{
    // Finals
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    // Objects
    private Bird bird;
    private Array<Tube> tubes;
    private Array<Coin> coins;
    // Textures
    private Texture bg;
    private Texture ground;
    private Texture retryButton;
    // Logic
    private Vector2 groundPos1, groundPos2;
    private Boolean isPlayerDead;
    private int score;



    protected PlayState(GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, MyTestGame.WIDTH / 2, MyTestGame.HEIGHT / 2);
        // Objects
        bird = new Bird(50, 200);
        tubes = new Array<Tube>();
        coins = new Array<Coin>();
        // Textures
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        retryButton = new Texture("play.png");
        //Logic
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, MyTestGame.GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), MyTestGame.GROUND_Y_OFFSET);
        isPlayerDead = false;
        score = 0;


        for (int i = 1; i <= TUBE_COUNT; i++)
        {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput()
    {
        if (Gdx.input.justTouched())
        {
            if (!isPlayerDead)
            {
                Vector3 tp = new Vector3();
                cam.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));

                Boolean interacted = false;
                for (Coin coin: coins)
                {
                    Rectangle2D bounds = new Rectangle2D.Float(coin.getPosition().x, coin.getPosition().y, coin.getTexture().getWidth(), coin.getTexture().getHeight());
                    if (bounds.contains(tp.x, tp.y))
                    {
                        coin.explode();
                        coins.removeIndex(coins.indexOf(coin, true));
                        score++;
                        interacted = true;
                    }
                }

                if (!interacted)
                {
                    bird.jump();
                }
            }
            else
            {
                gsm.set(new PlayState(gsm));
            }

        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();

        if (!isPlayerDead)
        {
            updateGround();
            bird.update(dt);
            cam.position.x = bird.getPosition().x + 80;

            for (int i = 0; i < tubes.size; i++)
            {
                Tube tube = tubes.get(i);
                double camPos = cam.position.x - (cam.viewportWidth / 2);
                double tubePos = tube.getPosTopTube().x + tube.getTopTube().getWidth();

                if (camPos > tubePos)
                {
                    int number = (int) Math.floor(Math.random() * cam.viewportHeight);
                    tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                    coins.add(new Coin(cam.position.x + cam.viewportWidth + 30, number));

                }

                if (tube.collides(bird.getBounds()))
                {
//                    handleDeath();
                }
            }

            if (bird.getPosition().y <= ground.getHeight() + MyTestGame.GROUND_Y_OFFSET)
            {
                handleDeath();
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();


        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), - MyTestGame.GROUND_Y_OFFSET / 2);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        for (Coin coin: coins)
        {
            sb.draw(coin.getTexture(), coin.getPosition().x, coin.getPosition().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        if (isPlayerDead)
        {
            sb.draw(retryButton, cam.position.x - 50, cam.viewportHeight / 2, 100, 60);
        }

        sb.end();
    }

    @Override
    public void dispose()
    {
        ground.dispose();
        bg.dispose();
        bird.dispose();
        retryButton.dispose();
        for (Tube tube: tubes)
        {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void handleDeath()
    {
        isPlayerDead = true;
    }

    private void updateGround()
    {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
        {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
        {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
