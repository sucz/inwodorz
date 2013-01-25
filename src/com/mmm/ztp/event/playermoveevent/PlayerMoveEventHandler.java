package com.mmm.ztp.event.playermoveevent;

import com.mmm.ztp.event.base.BasicHandlerImpl;

/**
 * Handler do sterowania statkiem na bazie eventObj
 * @author mazdac
 *
 */

public class PlayerMoveEventHandler extends BasicHandlerImpl<PlayerMoveObject, PlayerMoveEventListener> {
    public PlayerMoveEventHandler(PlayerMoveEventListener listener) {
        super(listener);
    }

    @Override
    /** Handle
     * @param eventObj obiekt zawaierający informacje o kierunku sterowania 
     */
    public void handle(PlayerMoveObject eventObj) {
    	if(eventObj.type>0)
    		this.listener.onRight();
    	else
    		this.listener.onLeft();
    }
}
