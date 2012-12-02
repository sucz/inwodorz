package com.example.movment;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinusSinusMovement extends Movement {
    private float speed = 0.0001f;
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019f;
        c[0] = ((float) Math.sin(temp * 3f)) + 1f;
        c[1] += speed;
    }
}
