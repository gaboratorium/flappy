package com.gaboratorium.flappyHermelin.helpers;

/**
 * Created by root on 3/24/17.
 */

public class Size
{
    private float width;
    private float height;

    public Size(float newWidth, float newHeight)
    {
        width = newWidth;
        height = newHeight;
    }

    public float getWidth() { return width; }
    public float  getHeight() { return height; }

    public void setSize(float newWidth, float newHeight)
    {
        width = newWidth;
        height = newHeight;
    }
}
