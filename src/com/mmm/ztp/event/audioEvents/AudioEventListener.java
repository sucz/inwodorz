package com.mmm.ztp.event.audioEvents;
import com.mmm.ztp.event.base.SimpleListener;

public interface AudioEventListener extends SimpleListener {
	public void onAudioEvent(AudioEventObject obj);
}
