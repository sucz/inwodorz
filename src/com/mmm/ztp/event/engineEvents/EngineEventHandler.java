package com.mmm.ztp.event.engineEvents;

import com.mmm.ztp.event.base.BasicHandlerImpl;


public class EngineEventHandler extends BasicHandlerImpl<EngineEventObject, EngineEventListener> {

	public EngineEventHandler(EngineEventListener listener) {
		super(listener);
	}

	@Override
	public void handle(EngineEventObject eventObj) {
		this.listener.onEngineEvent(eventObj);
		
	}

}