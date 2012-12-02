package com.example.movment;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Movement {
    protected float vecX = 0.0f;
    protected float vecY = 0.0f;

    public abstract void move(float[] c);

    public void setTarget(float x, float y) {
        vecX = x;
        vecY = y;
    }
}
