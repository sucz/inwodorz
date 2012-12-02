package com.example.event.playerfireevent;

import com.example.event.base.BasicHandlerImpl;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 10:25 AM
 */
public class PlayerFireEventHandler extends BasicHandlerImpl<PlayerFireEventObject, PlayerFireEventListener> {
    public PlayerFireEventHandler(PlayerFireEventListener listener) {
        super(listener);
    }

    @Override
    public void handle(PlayerFireEventObject eventObj) {
        this.listener.fire();
    }
}
