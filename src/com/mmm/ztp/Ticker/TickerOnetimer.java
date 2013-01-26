package com.mmm.ztp.Ticker;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.engineEvents.EngineEventListener;
import com.mmm.ztp.event.engineEvents.EngineEventObject;
import com.mmm.ztp.event.tickEvents.TickEventHandler;
import com.mmm.ztp.event.tickEvents.TickEventListener;

/**
 * Klasa licznika czasu, który po zadanej ilości "tików" wykona zadany kod<br>
 * 
 * Przykład użycia jako klasa anonimowa<br>
 * <pre>
 * new Ticker(200) 
 * { 
 * 	public void onDone()
 * 	{ 
 * 		reloadEventSent=false; 
 * 		GameEventBus.getInstance().fireEvent(EngineEventListener.class, new EngineEventObject(EngineEventObject.TYPE_NEXT_LEVEL, ship));  
 * 	}
 * }
 * </pre>
 * 
 * przykład wykona się po 200 tikach (200*16ms czyli po 3.2 sekundy) i wyśle event EngineEvent o potrzebie załadowania następnego poziomu
 * oraz zmieni stan zmiennej reloadEventSent na false (to ochrona przed wielokrotnym tworzeniem tickerów).
 */
public class TickerOnetimer extends Ticker {

	private int ticks=1;
	private int doneTicks=0;
	TickEventHandler handler;
	
	public TickerOnetimer(int ticks)
	{
		this.ticks=ticks;
		this.handler=new TickEventHandler(this);
		GameEventBus.getInstance().tickToEventBus(TickEventListener.class,this.handler);

	}
	@Override
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
