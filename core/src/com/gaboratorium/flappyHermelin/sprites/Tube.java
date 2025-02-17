package com.gaboratorium.flappyHermelin.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by root on 3/12/17.
 */

public class Tube
{
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 140;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop, boundsBottom;

    public Tube(float x)
    {
        topTube = new Texture("toptube_black.png");
        bottomTube = new Texture("bottomtube_black.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube()
    {
        return topTube;
    }

    public Texture getBottomTube()
    {
        return bottomTube;
    }

    public Vector2 getPosTopTube()
    {
        return posTopTube;
    }

    public Vector2 getPosBotTube()
    {
        return posBotTube;
    }

    public void reposition(float x)
    {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player)
    {
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void dispose()
    {
        topTube.dispose();
        bottomTube.dispose();
    }
}
