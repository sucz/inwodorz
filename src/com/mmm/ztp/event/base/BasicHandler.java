package com.mmm.ztp.event.base;

/**
 * Interfejs podstawowego hadlera
 *
 * @param <T> Argument przekazywany podczas wywołania eventu
 */
public interface BasicHandler<T extends SimpleEvent> {
    /**
     * Metoda służąca jako adapter do wywołania eventu
     *
     * @param eventObj argument eventu
     */
    void handle(T eventObj);
}
