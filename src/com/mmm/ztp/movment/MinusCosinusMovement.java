package com.mmm.ztp.movment;

public class MinusCosinusMovement extends EnemyMovement {
    private float speed = 0.0002f;
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019f;
        c[0] = ((float) Math.cos(temp * 3f)) + 1f;
        c[1] += speed;
    }

	@Override
	public void onBound() {
	}

	@Override
	public int getId() {
		return 3;
	}
}
