package com.mmm.ztp.event.addAlienProjectioleEvent;

import com.mmm.ztp.event.base.SimpleEvent;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;

/**
 * Obiekt przekazywany do
 *
 * @see AddAlienProjectileEventHandler
 *      podczas wywołania eventu
 */
public class AddAlienProjectileEventObject implements SimpleEvent {
    private BaseObject t;

    public AddAlienProjectileEventObject(BaseObject t) {
        this.t = t;
    }

    public BaseObject getDrawable() {
        return t;
    }
}
