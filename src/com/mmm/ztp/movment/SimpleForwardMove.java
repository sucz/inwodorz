package com.mmm.ztp.movment;

/**
 * Author: miroslaw
 * Date: 12/2/12
 * Time: 9:05 AM
 */
public class SimpleForwardMove extends Movement {

	public SimpleForwardMove()
	{
		this.speed=12.8f;
	}
	public SimpleForwardMove(float speed)
	{
		this.speed=speed;
	}

    @Override
    public void move(float[] c) {
        c[1] += speed;
    }
}
