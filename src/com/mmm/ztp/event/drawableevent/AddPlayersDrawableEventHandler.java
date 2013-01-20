package com.mmm.ztp.event.drawableevent;

import com.mmm.ztp.event.base.BasicHandlerImpl;

/**
 * Author: miroslaw
 * Date: 11/30/12
 * Time: 9:38 PM
 */
public class AddPlayersDrawableEventHandler extends BasicHandlerImpl<AddPlayersDrawableEventObject, AddPlayersDrawableEventListener> {
    public AddPlayersDrawableEventHandler(AddPlayersDrawableEventListener listener) {
        super(listener);
    }

    @Override
    public void handle(AddPlayersDrawableEventObject eventObj) {
        listener.addDrawable(eventObj);
    }
}
