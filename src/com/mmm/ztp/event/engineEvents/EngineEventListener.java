package com.mmm.ztp.event.engineEvents;
import com.mmm.ztp.event.base.SimpleListener;

public interface EngineEventListener extends SimpleListener {
	public void onEngineEvent(EngineEventObject obj);
}
