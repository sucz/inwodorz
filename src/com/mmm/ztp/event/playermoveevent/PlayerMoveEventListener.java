package com.mmm.ztp.event.playermoveevent;

import com.mmm.ztp.event.base.SimpleListener;

/**
 * Interfejs do sterowania poziomego (lewo i prawo)
 * @author mazdac
 *
 */
public interface PlayerMoveEventListener extends SimpleListener {
    public void onLeft();
    public void onRight();
}
