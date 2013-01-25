package com.mmm.ztp.event.destroyobjectevent;

import com.mmm.ztp.event.base.BasicHandlerImpl;

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
