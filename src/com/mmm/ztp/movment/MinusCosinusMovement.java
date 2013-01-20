package com.mmm.ztp.movment;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 9:36 PM
 */
public class MinusCosinusMovement extends Movement {
    private float speed = 0.0002f;
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019f;
        c[0] = ((float) Math.cos(temp * 3f)) + 1f;
        c[1] += speed;
    }
}
