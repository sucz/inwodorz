package com.example.movment;

/**
 * Author: miroslaw
 * Date: 12/2/12
 * Time: 9:05 AM
 */
public class SimpleForwardMove extends Movement {

    private Float speed = 0.1f;

    @Override
    public void move(float[] c) {
        c[1] += speed;
    }
}
