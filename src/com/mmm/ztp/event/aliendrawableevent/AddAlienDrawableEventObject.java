package com.mmm.ztp.event.aliendrawableevent;

import com.mmm.ztp.event.base.SimpleEvent;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;

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
