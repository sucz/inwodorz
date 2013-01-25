package com.mmm.ztp.event.tickEvents;

import com.mmm.ztp.event.base.BasicHandlerImpl;
import com.mmm.ztp.event.base.SimpleEvent;


public class TickEventHandler extends BasicHandlerImpl<SimpleEvent,TickEventListener> {

	public TickEventHandler(TickEventListener listener) {
		super(listener);
	}

	@Override
	public void handle(SimpleEvent eventObj) {
		this.listener.onTickEvent();
	}


}