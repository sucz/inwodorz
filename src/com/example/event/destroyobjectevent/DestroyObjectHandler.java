package com.example.event.destroyobjectevent;

import com.example.event.base.BasicHandlerImpl;

/**
 * Author: miroslaw
 * Date: 11/30/12
 * Time: 9:20 PM
 */
public class DestroyObjectHandler extends BasicHandlerImpl<DestroyObjectEventObject, DestroyObjectEventListener> {

    public DestroyObjectHandler(DestroyObjectEventListener listener) {
        super(listener);
    }

    @Override
    public void handle(DestroyObjectEventObject eventObj) {
        listener.destroyObject(eventObj);
    }
}
