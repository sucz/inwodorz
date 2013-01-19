package com.example.event.addAlienProjectioleEvent;

import com.example.event.base.SimpleListener;


/**
 * Interfejs obiektu nasłuchującego na zdarzenie dodania obiektu obcych
 */
public interface AddAlienProjectileEventListener extends SimpleListener {
    void addDrawable(AddAlienProjectileEventObject object);
}
