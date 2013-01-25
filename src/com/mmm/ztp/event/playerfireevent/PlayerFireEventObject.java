package com.mmm.ztp.event.playerfireevent;

import com.mmm.ztp.event.base.SimpleEvent;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 10:26 AM
 */
public class PlayerFireEventObject implements SimpleEvent {
	float xy[];
	public PlayerFireEventObject(float[] xy)
	{
		this.xy=xy;
	}
	public float[] getXy()
	{
		return this.xy;
	}
}
