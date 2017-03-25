package com.gaboratorium.mytestgame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by root on 3/24/17.
 */

public class Minion
{
    private Vector3 position;
    private Texture texture;
    private Animation animation;
    private float width;
    private float height;
    private Rectangle bounds;

    public Minion(float posX, float posY)
    {
        position = new Vector3(posX, posY, 0);
        String texture_filename;
        double rand = Math.floor(Math.random() * 3.5);
        texture_filename = rand > 2 ? "bat_laughing.png" : rand > 1 ? "bat.png" : "pumpkin.png";

        texture = new Texture(texture_filename);
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);
        width = 55;
        height = 55;
        bounds = new Rectangle(posX, posY, width, height);
    }

    public void update(float dt, Bird bird)
    {
        animation.update(dt);
        if (bird.getPosition().y > getPosition().y)
        {
            position.y += .5;
        }
        else
        {
            position.y -= .5;
        }
        bounds.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle player)
    {
        return player.overlaps(bounds);
    }

    public void die()
    {
        dispose();
    }

    public void dispose()
    {
        texture.dispose();
    }

    public TextureRegion getTexture() { return animation.getFrame(); }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public Vector3 getPosition() { return position; }
    public Rectangle getBounds() { return bounds; }
}
