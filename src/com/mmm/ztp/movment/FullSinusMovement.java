package com.mmm.ztp.movment;

public class FullSinusMovement extends Movement {
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019;
        c[0] += ((float) Math.sin(temp)*20);
        c[0]=checkBoundaryX(c[0]);
        c[1] -= this.speed;
    }

}
