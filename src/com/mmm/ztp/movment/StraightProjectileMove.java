package com.mmm.ztp.movment;

public class StraightProjectileMove extends Movement {

	public StraightProjectileMove()
	{
		this.speed=12.8f;
	}
	public StraightProjectileMove(float speed)
	{
		this.speed=speed;
	}

    @Override
    public void move(float[] c) {
    	
        c[1] = this.checkBoundaryY(c[1]+speed);
    }
	@Override
	public float checkBoundaryY(float var) {
		if(var+this.size>this.boundTop)
		{
			onBound();
		}
		return var;
	}
	public void onBound() {
	}
	@Override
	public int getId() {
		return 6;
	}
}
