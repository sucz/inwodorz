package com.mmm.ztp.event;

import com.mmm.ztp.event.base.BasicHandlerImpl;
import com.mmm.ztp.event.base.SimpleEvent;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Przekierowuje eventy obiektów
 */
public final class GameEventBus {

    private static final GameEventBus gameEventBus = new GameEventBus();
    private static final Map<Class, ConcurrentLinkedQueue<BasicHandlerImpl>> listeners
            = new ConcurrentHashMap<Class, ConcurrentLinkedQueue<BasicHandlerImpl>>();
    private static final Map<Class, ConcurrentLinkedQueue<BasicHandlerImpl>> tickers
    = new ConcurrentHashMap<Class, ConcurrentLinkedQueue<BasicHandlerImpl>>();

    /**
     * Dodaje obiekt który nasłuchuje na dany event
     *
     * @param eventClass klasa Listenera
     * @param handler    handler obsługujący wywołanie metody w obiekcie nasłuchującym
     */
    public void attachToEventBus(Class eventClass, BasicHandlerImpl handler) {
        if (listeners.get(eventClass) == null)
            listeners.put(eventClass, new ConcurrentLinkedQueue<BasicHandlerImpl>());
        listeners.get(eventClass).add(handler);
    }

    /**
     * Przyjmuje wywołanie eventu
     *
     * @param eventClass klasa listenera eventu
     * @param event      obiekt eventu
     */
    public void fireEvent(Class eventClass, SimpleEvent event) {
    	for (BasicHandlerImpl e : listeners.get(eventClass))
    			e.handle(event);
    }
    
    public void removeListener(Class eventClass, BasicHandlerImpl handler) {
        listeners.get(eventClass).remove(handler);
    }
    
    /**
     * Przyjmuje wywołania "tików", metoda niesynchronizowana, gdyż tiki nie mogą zostać wywołane jednocześnie
     * gdyż istnieje tylko jedno źródło tików
     * @param eventClass klasa listenera eventu
     * @param event      obiekt eventu
     */
    public void tick(Class eventClass, SimpleEvent event) {
    	if(tickers.get(eventClass)!=null)
    			for (BasicHandlerImpl e : tickers.get(eventClass))
    				e.handle(event);
    }
    
    public void tickToEventBus(Class eventClass, BasicHandlerImpl handler) {
        if (tickers.get(eventClass) == null)
            tickers.put(eventClass, new ConcurrentLinkedQueue<BasicHandlerImpl>());

        	tickers.get(eventClass).add(handler);
    }
    
    public void removeTicker(Class eventClass, BasicHandlerImpl handler) {
    	tickers.get(eventClass).remove(handler);
    }

    public static synchronized GameEventBus getInstance() {
        return gameEventBus;
    }

    private GameEventBus() {
    }
}
