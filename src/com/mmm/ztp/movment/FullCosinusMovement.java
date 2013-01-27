package com.mmm.ztp.movment;

public class FullCosinusMovement extends EnemyMovement {
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
    	temp+=0.019;
        c[0] += ((float) Math.cos(temp)*20);
        c[0]=checkBoundaryX(c[0]);
        c[1] -= this.speed;
    }

	@Override
	public void onBound() {
	}

	@Override
	public int getId() {
		return 1;
	}

}
