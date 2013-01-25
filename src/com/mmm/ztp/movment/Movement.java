package com.mmm.ztp.movment;

/**
 * Podstawowa klasa ruchu
 */
public abstract class Movement {
    protected float vecX = 0.0f;
    protected float vecY = 0.0f;
    protected float speed=6.4f;
    protected float boundLeft=0f;
    protected float boundRight=480f;
    protected float boundTop=800f;
    protected float boundBottom=0f;
    protected float size=128f;

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
    public float checkBoundaryX(float var)
    {
    	if(var<this.boundLeft)
    		return this.boundLeft;
    	if(var+size>(this.boundRight))
    		return (this.boundRight-size);
    	else
    		return var;
    	
    }
    public float checkBoundaryY(float var)
    {
    	if(var<this.boundBottom)
    		return this.boundBottom;
    	if(var+size>this.boundTop)
    		return this.boundTop;
    	else
    		return var;
    }
}
