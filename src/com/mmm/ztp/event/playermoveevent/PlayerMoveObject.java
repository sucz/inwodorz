package com.mmm.ztp.event.playermoveevent;

import com.mmm.ztp.event.base.SimpleEvent;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 10:26 AM
 */
public class PlayerMoveObject implements SimpleEvent {
	int type; // -1 lewo, 1 prawo 
	public PlayerMoveObject(int type)
	{
		this.type=type;
	}
}
