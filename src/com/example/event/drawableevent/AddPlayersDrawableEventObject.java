package com.example.event.drawableevent;

import com.example.event.base.SimpleEvent;
import com.example.gameobjects.ships.base.BaseObject;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/30/12
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddPlayersDrawableEventObject implements SimpleEvent {
    BaseObject t;

    public AddPlayersDrawableEventObject(BaseObject t) {
        this.t = t;
    }

    public BaseObject getT() {
        return t;
    }
}
