package com.example.event;

import com.example.event.base.BasicHandlerImpl;
import com.example.event.base.SimpleEvent;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/30/12
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Przekierowuje eventy obiektów
 */
public final class GameEventBus {

    private static final GameEventBus gameEventBus = new GameEventBus();
    private static final Map<Class, ArrayList<BasicHandlerImpl>> listeners
            = new ConcurrentHashMap<Class, ArrayList<BasicHandlerImpl>>();

    /**
     * Dodaje obiekt który nasłuchuje na dany event
     *
     * @param eventClass klasa Listenera
     * @param handler    handler obsługujący wywołanie metody w obiekcie nasłuchującym
     */
    public void attachToEventBus(Class eventClass, BasicHandlerImpl handler) {
        if (listeners.get(eventClass) == null)
            listeners.put(eventClass, new ArrayList<BasicHandlerImpl>(100));

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

    public static synchronized GameEventBus getInstance() {
        return gameEventBus;
    }

    private GameEventBus() {
    }
}
