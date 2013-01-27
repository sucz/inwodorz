package com.mmm.ztp;

import android.util.Log;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.base.SimpleEventImpl;
import com.mmm.ztp.event.tickEvents.TickEventListener;

public class Refresher extends Thread implements Runnable {
	
	GameEngine refreshedItem;
	boolean isPaused=false;
	
	Refresher(GameEngine item)
	{
		this.refreshedItem=item;
	}
	/**
	 * Implementacja interfejsu Runnable 
	 * Funkcja co 16ms (60Hz) przerysowuje
	 * grafikę, robimy tak, gdyż mamy ustawiony tryb renderowania na żądanie
	 * (RENDERMODE_WHEN_DIRTY)
	 */
	@Override
	public void run() {
		
		do {
			if(isPaused)
				try {
					synchronized(this)
					{
					this.wait();
					}
				} catch (InterruptedException e1) {
					Log.d("Refresher","Nie można zatrzymać rendereowania!");
					e1.printStackTrace();
				}
			refreshedItem.requestRender();
			GameEventBus.getInstance().tick(TickEventListener.class, new SimpleEventImpl());
			try {
				Thread.sleep(16, 0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}
	public void onPause()
	{
		this.isPaused=true;
	}
	
	public void onResume()
	{
		this.isPaused=false;
		synchronized(this)
		{
		this.notify();
		}
		
		
		
		
		
	}
	
	

}
