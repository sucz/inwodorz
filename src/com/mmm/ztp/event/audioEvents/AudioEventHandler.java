package com.mmm.ztp.event.audioEvents;

import com.mmm.ztp.event.base.BasicHandlerImpl;


public class AudioEventHandler extends BasicHandlerImpl<AudioEventObject, AudioEventListener> {

	public AudioEventHandler(AudioEventListener listener) {
		super(listener);
	}

	@Override
	public void handle(AudioEventObject eventObj) {
		listener.onAudioEvent(eventObj);
		
	}

}