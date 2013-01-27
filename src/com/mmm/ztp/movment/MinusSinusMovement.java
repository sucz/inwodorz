package com.mmm.ztp.movment;

public class MinusSinusMovement extends EnemyMovement {
    private float speed = 0.0001f;
    private float temp = 0.0f;

    @Override
    public void move(float[] c) {
        temp += 0.019f;
        c[0] = ((float) Math.sin(temp * 3f)) + 1f;
        c[1] += speed;
    }

	public void onBound() {
	}

	@Override
	public int getId() {
		return 4;
	}
}
