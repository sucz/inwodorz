package com.mmm.ztp.movment;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class StraightMove extends EnemyMovement {

	public StraightMove()
	{
	}


    @Override
    public void move(float[] c) {
        c[1] = this.checkBoundaryY(c[1]-speed);
    }
	public void onBound() {
	}


	@Override
	public int getId() {
		return 5;
	}
}
