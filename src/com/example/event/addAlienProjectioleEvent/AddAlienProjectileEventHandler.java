package com.example.event.addAlienProjectioleEvent;

import com.example.event.base.BasicHandlerImpl;

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
