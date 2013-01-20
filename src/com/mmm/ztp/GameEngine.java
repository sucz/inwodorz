package com.mmm.ztp;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventHandler;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventListener;
import com.mmm.ztp.event.playermoveevent.PlayerMoveObject;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class GameEngine extends GLSurfaceView implements Runnable {

	private GameRenderer _renderer;
	private boolean quit;
	
    public GameEngine(Context context) {
        super(context);

        _renderer = new GameRenderer(context);
        setRenderer(_renderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); //rozwiazuje problem migania elementów, draw tylko przy zmianie
        
    }
    
    public boolean onTouchEvent(final MotionEvent event) {
    	//Log.d("GameEngine","MotionEvent x:"+event.getX()+" y:"+event.getY());
    	if(event.getX()>240)
    		 GameEventBus.getInstance().fireEvent(PlayerMoveEventListener.class, new PlayerMoveObject(1));
    	else if(event.getX()<240)
    		GameEventBus.getInstance().fireEvent(PlayerMoveEventListener.class, new PlayerMoveObject(-1));
        return true;
    }
	
	@Override
	public void run() {
			do
			{
            requestRender();
            try {
				Thread.sleep(16, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
            while(true);
    }
	
	


}
