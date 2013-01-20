package com.mmm.ztp.movment;

public class FullCosinusMovement extends Movement {
    private float speed = 0.0002f;
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019f;
        c[0] += ((float) Math.cos(temp * 3f)*0.25f);
        c[1] += 0;
    }
}
