package com.mmm.ztp.services;

import android.media.AsyncPlayer;
import android.util.Log;

public class AudioService implements Service {
	
	AsyncPlayer player;
	public AudioService()
	{
	}
	
	@Override
	public void run() {
		try {
			synchronized(this)
			{
			this.wait();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void onPause() {

	}

	@Override
	public void onResume() {
	}

}
