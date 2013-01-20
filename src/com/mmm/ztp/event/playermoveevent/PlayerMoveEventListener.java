package com.mmm.ztp.event.playermoveevent;

import com.mmm.ztp.event.base.SimpleListener;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 10:25 AM
 */
public interface PlayerMoveEventListener extends SimpleListener {
    public void onLeft();
    public void onRight();
}
