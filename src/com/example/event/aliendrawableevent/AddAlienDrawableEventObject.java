package com.example.event.aliendrawableevent;

import com.example.drawable.Drawable;
import com.example.event.base.SimpleEvent;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/30/12
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddAlienDrawableEventObject implements SimpleEvent {
    Drawable t;

    public AddAlienDrawableEventObject(Drawable t) {
        this.t = t;
    }

    public Drawable getT() {
        return t;
    }
}
