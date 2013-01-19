package com.example.event.base;


/**
 * Podstawowy handler
 *
 * @param <T> Argument przekazywany podczas wywołania eventu
 * @param <V> Klasa listenera obsługująca dany event
 */
public abstract class BasicHandlerImpl<T extends SimpleEvent, V extends SimpleListener> implements BasicHandler<T> {

    /**
     * Obiekt
     */
    protected V listener;

    protected BasicHandlerImpl(V listener) {
        this.listener = listener;
    }
}
