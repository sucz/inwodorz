package com.mmm.ztp.movment;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMove extends Movement {

	public SimpleMove()
	{
		this.speed=12.8f;
	}


    @Override
    public void move(float[] c) {
        c[1] -= speed;
    }
}
