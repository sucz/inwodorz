package com.mmm.ztp.movment;

import android.util.Log;

public abstract class EnemyMovement extends Movement {

	@Override
	public void move(float[] c) {
		// TODO Auto-generated method stub

	}
	@Override
    public float checkBoundaryY(float var)
    {
    	if(var<(this.boundBottom-50))
    		this.onBound();
		return var;
    }
	
	public abstract void onBound();
	

}
