package com.mmm.ztp.Ticker;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.tickEvents.TickEventListener;

public class TickerReusable extends Ticker {

	private boolean addedToEventBus = false;

	public TickerReusable(int ticks)
	{
		super(ticks);
	}
	public TickerReusable() {
		super();
	}

	public void use() {
		if (!this.addedToEventBus) {
			this.doneTicks = 0;
			GameEventBus.getInstance().tickToEventBus(TickEventListener.class,
					this.handler);
		}
	}

	public void use(int ticks) {
		if (!this.addedToEventBus) {
			this.doneTicks = 0;
			this.ticks = ticks;
			GameEventBus.getInstance().tickToEventBus(TickEventListener.class,
					this.handler);
		}
	}
	@Override
	public void onTickEvent() {
		super.onTickEvent();
		this.addedToEventBus=false;
	}

}
