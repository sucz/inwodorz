package com.mmm.ztp.event.aliendrawableevent;

import com.mmm.ztp.event.base.SimpleListener;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/30/12
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AddAlienDrawableEventListener extends SimpleListener {
    void addDrawable(AddAlienDrawableEventObject object);
}
