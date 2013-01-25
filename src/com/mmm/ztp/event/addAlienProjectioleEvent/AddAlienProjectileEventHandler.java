package com.mmm.ztp.event.addAlienProjectioleEvent;

import com.mmm.ztp.event.base.BasicHandlerImpl;

/**
 * Handler obsługujący
 */
public class AddAlienProjectileEventHandler extends BasicHandlerImpl<AddAlienProjectileEventObject, AddAlienProjectileEventListener> {

    public AddAlienProjectileEventHandler(AddAlienProjectileEventListener listener) {
        super(listener);
    }

    @Override
    public void handle(AddAlienProjectileEventObject eventObj) {
        listener.addDrawable(eventObj);
    }
}
