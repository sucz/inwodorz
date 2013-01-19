package com.example.event.aliendrawableevent;

import com.example.event.base.BasicHandlerImpl;

/**
 * Handler obsługujący
 */
public class AddAlienDrawableEventHandler extends BasicHandlerImpl<AddAlienDrawableEventObject, AddAlienDrawableEventListener> {

    public AddAlienDrawableEventHandler(AddAlienDrawableEventListener listener) {
        super(listener);
    }

    @Override
    public void handle(AddAlienDrawableEventObject eventObj) {
        listener.addDrawable(eventObj);
    }
}
