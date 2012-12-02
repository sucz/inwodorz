package com.example.event.aliendrawableevent;

import com.example.event.base.BasicHandlerImpl;

/**
 * Author: miroslaw
 * Date: 11/30/12
 * Time: 9:38 PM
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
