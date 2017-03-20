package com.gaboratorium.mytestgame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by root on 3/20/17.
 */

public class Ghost
{
    public Vector3 position;
    public Texture texture;

    public Ghost(int posX, int posY)
    {
        position = new Vector3(posX, posY, 0);
        texture = new Texture("ghost.png");
    }

    public void update(Bird bird)
    {
        position.x = bird.getPosition().x;
    }

    public void dispose()
    {
        texture.dispose();
    }

    public Vector3 getPosition() { return  position; }
}
