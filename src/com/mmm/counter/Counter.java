package com.mmm.counter;

/*
 * Klasa opisująca licznik - wzorzec singleton
 */
public final class Counter {
	long stan=0;
	private static Counter instancja=null;
	public long get()
	{
		synchronized(Counter.class)
		{
			if(instancja==null) //późna inicjalizacja
				instancja=new Counter();
			return instancja.stan;
		}
	}
	public synchronized void add(long points)
	{
		this.stan+=points;
	}
	public synchronized void reset()
	{
		this.stan=0;
	}

}
