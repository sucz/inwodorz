package com.mmm.ztp.movment;

public class FullSinusMovement extends Movement {
    private float speed = 0.0002f;
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019f;
        c[0] = ((float) Math.sin(temp * 3f)) * -4.5f;
        c[1] += 0;
    }
}
