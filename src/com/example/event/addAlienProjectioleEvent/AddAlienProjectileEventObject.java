package com.example.event.addAlienProjectioleEvent;

import com.example.event.base.SimpleEvent;
import com.example.gameobjects.ships.base.BaseObject;

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
