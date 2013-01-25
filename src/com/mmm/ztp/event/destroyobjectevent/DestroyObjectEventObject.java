package com.mmm.ztp.event.destroyobjectevent;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.base.SimpleEvent;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/30/12
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DestroyObjectEventObject implements SimpleEvent {
    Drawable toDestroy;

    public DestroyObjectEventObject(Drawable toDestroy) {
        this.toDestroy = toDestroy;
    }

    public Drawable getToDestroy() {
        return toDestroy;
    }
}
