package com.mmm.ztp.services;
import android.os.SystemClock;
import android.util.Log;

import com.mmm.ztp.GameEngine;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.base.SimpleEventImpl;
import com.mmm.ztp.event.tickEvents.TickEventListener;

public class Refresher extends Thread implements Service  {

	GameEngine refreshedThread;
	long milis=16;
	long sleeptime=16;
	boolean suspended=false;
	
	public Refresher(GameEngine rf)
	{
		this.refreshedThread=rf;
	}
	
	@Override
	public void run() {
		milis=System.currentTimeMillis();

		do {
			milis=System.currentTimeMillis()-milis;
			if(milis>sleeptime)
			{
				if(sleeptime>0)
					sleeptime=sleeptime-(milis-sleeptime);
			}
			else
				sleeptime=16;
			
			GameEventBus.getInstance().tick(TickEventListener.class,
					new SimpleEventImpl());
			try {
				if(sleeptime>0)
					Thread.sleep(sleeptime, 0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(suspended)
				synchronized(this)
				{
					try {
						synchronized(this)
						{
							this.wait();

						}
					} catch (InterruptedException e) {
						Log.d("Refresher", "Cannot stop thread");
						e.printStackTrace();
					}
				}
			refreshedThread.requestRender();
		} while (true);
	}

	@Override
	public void onPause() {
		this.suspended=true;
		
	}

	@Override
	public void onResume() {
		synchronized(this)
		{
			this.suspended=false;
			this.notify();
		}
	}
	

}