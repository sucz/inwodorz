package com.mmm.ztp.event.addAlienProjectioleEvent;

import com.mmm.ztp.event.base.SimpleListener;


/**
 * Interfejs obiektu nasłuchującego na zdarzenie dodania obiektu obcych
 */
public interface AddAlienProjectileEventListener extends SimpleListener {
    void addDrawable(AddAlienProjectileEventObject object);
}
