package com.mmm.ztp.movment;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserMove extends Movement implements HorizontalControls {

    private boolean enabled=false;
    private int direction=0;
	@Override
	public void move(float[] c) {
		if(enabled)
		{
			c[0]+=((float)direction*speed);
			c[0]=checkBoundaryX(c[0]);
			this.enabled=false;
		}
		
	}
	@Override
	public void Left() {
		this.enabled=true;
		this.direction=-1;
	}
	@Override
	public void Right() {
		this.enabled=true;
		this.direction=1;
	}


}
