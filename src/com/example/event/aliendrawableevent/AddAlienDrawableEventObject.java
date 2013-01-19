package com.example.event.aliendrawableevent;

import com.example.event.base.SimpleEvent;
import com.example.gameobjects.ships.base.BaseObject;

/**
 * Obiekt przekazywany do
 *
 * @see AddAlienDrawableEventHandler
 *      podczas wywołania eventu
 */
public class AddAlienDrawableEventObject implements SimpleEvent {
    private BaseObject t;

    public AddAlienDrawableEventObject(BaseObject t) {
        this.t = t;
    }

    public BaseObject getDrawable() {
        return t;
    }
}
