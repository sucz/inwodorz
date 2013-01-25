package com.mmm.ztp.event.destroyobjectevent;

import com.mmm.ztp.event.base.SimpleListener;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/30/12
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DestroyObjectEventListener extends SimpleListener {
    void destroyObject(DestroyObjectEventObject object);
}
