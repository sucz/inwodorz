package com.mmm.ztp.Ticker;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.tickEvents.TickEventHandler;
import com.mmm.ztp.event.tickEvents.TickEventListener;

/**
 * Abstrakcyjna klasa tickera definiująca wspóle pola i ogólne metody tickera
 * @author mazdac
 *
 */
public abstract class Ticker implements TickEventListener {

	protected int ticks=1;
	protected int doneTicks=0;
	TickEventHandler handler;
	
	public Ticker()
	{
		this.handler=new TickEventHandler(this);
	}
	public Ticker(int ticks)
	{
		this.ticks=ticks;
		this.handler=new TickEventHandler(this);
	}
	public void onTickEvent() {
		if(doneTicks<ticks)
			doneTicks++;
		else
		{
			this.onDone();
			GameEventBus.getInstance().removeTicker(TickEventListener.class, this.handler);
		}
	}
	/**
	 * Meetoda którą należy zmodyfikować jeśli chcemy coś wykonać
	 */
	public void onDone()
	{
		
	}

}
