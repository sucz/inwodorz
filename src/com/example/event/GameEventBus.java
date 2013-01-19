package com.example.event;

import com.example.event.base.BasicHandlerImpl;
import com.example.event.base.SimpleEvent;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Przekierowuje eventy obiektów
 */
public final class GameEventBus {

    private static final GameEventBus gameEventBus = new GameEventBus();
    private static final Map<Class, LinkedList<BasicHandlerImpl>> listeners
            = new ConcurrentHashMap<Class, LinkedList<BasicHandlerImpl>>();

    /**
     * Dodaje obiekt który nasłuchuje na dany event
     *
     * @param eventClass klasa Listenera
     * @param handler    handler obsługujący wywołanie metody w obiekcie nasłuchującym
     */
    public void attachToEventBus(Class eventClass, BasicHandlerImpl handler) {
        if (listeners.get(eventClass) == null)
            listeners.put(eventClass, new LinkedList<BasicHandlerImpl>());

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

    public static synchronized GameEventBus getInstance() {
        return gameEventBus;
    }

    private GameEventBus() {
    }
}
