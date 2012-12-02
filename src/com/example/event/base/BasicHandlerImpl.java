package com.example.event.base;

/**
 * Author: miroslaw
 * Date: 11/30/12
 * Time: 9:19 PM
 */
public abstract class BasicHandlerImpl<T extends SimpleEvent, V extends SimpleListener> implements BasicHandler<T> {
    protected V listener;

    public BasicHandlerImpl(V listener) {
        this.listener = listener;
    }
}
