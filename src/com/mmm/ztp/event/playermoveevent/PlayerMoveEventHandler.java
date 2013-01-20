package com.mmm.ztp.event.playermoveevent;

import com.mmm.ztp.event.base.BasicHandlerImpl;

public class PlayerMoveEventHandler extends BasicHandlerImpl<PlayerMoveObject, PlayerMoveEventListener> {
    public PlayerMoveEventHandler(PlayerMoveEventListener listener) {
        super(listener);
    }

    @Override
    public void handle(PlayerMoveObject eventObj) {
    	if(eventObj.type>0)
    		this.listener.onRight();
    	else
    		this.listener.onLeft();
    }
}
