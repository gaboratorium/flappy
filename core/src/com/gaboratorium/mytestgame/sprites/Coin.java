package com.gaboratorium.mytestgame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by root on 3/20/17.
 */

public class Coin
{
    private Vector3 position;
    private Texture texture;

    public Coin(float posX, float posY)
    {
        position = new Vector3(posX, posY, 0);
        texture = new Texture("coin.png");
    }

    public Vector3 getPosition() { return position; }

    public Texture getTexture() { return texture; }

    public void update()
    {

    }

    public void explode()
    {
        dispose();
    }

    public void dispose()
    {
        texture.dispose();
    }

}
