package com.mmm.ztp.movment;

/**
 * Podstawowa klasa ruchu
 */
public abstract class Movement {
    protected float vecX = 0.0f;
    protected float vecY = 0.0f;
    protected float speed=6.4f;

    public abstract void move(float[] c);

    public void setTarget(float x, float y) {
        vecX = x;
        vecY = y;
    }
	public void setSpeed(float speed)
	{
		this.speed=speed;
	}
	public float getSpeed()
	{
		return this.speed;
	}
}
