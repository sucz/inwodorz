package com.mmm.ztp.movment;

public class FullSinusMovement extends Movement {
    private float speed = 0.0002f;
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019f;
        c[0] += ((float) Math.sin(temp)*4);
        c[0]=checkBoundaryX(c[0]);
        c[1] += 0;
    }

}
