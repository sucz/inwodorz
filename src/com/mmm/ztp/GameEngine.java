package com.mmm.ztp;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventHandler;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventListener;
import com.mmm.ztp.event.playermoveevent.PlayerMoveObject;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Silnik, główna część sterująca
 * 
 * @author mazdac
 * 
 */
public class GameEngine extends GLSurfaceView implements Runnable {

	private GameRenderer _renderer;
	private boolean quit;

	public GameEngine(Context context) {
		super(context);

		_renderer = new GameRenderer(context);
		setRenderer(_renderer);

		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); // rozwiazuje
															// problem migania
															// elementów, draw
															// tylko przy
															// zmianie

	}

	/**
	 * Implementacja onTouchEvent zawiera wsyztsko co związane z obsługą dotyku,
	 * w naszym przypadku wysyła eventy sterujące statkiem do statku gracza
	 * poprzez GameEventBus
	 */
	public boolean onTouchEvent(final MotionEvent event) {
		// Log.d("GameEngine","MotionEvent x:"+event.getX()+" y:"+event.getY());
		// 240 bo to połowa ekranu
		if (event.getX() > 240)
			GameEventBus.getInstance().fireEvent(PlayerMoveEventListener.class,
					new PlayerMoveObject(1));
		else if (event.getX() < 240)
			GameEventBus.getInstance().fireEvent(PlayerMoveEventListener.class,
					new PlayerMoveObject(-1));
		return true;
	}

	/**
	 * Implementacja interfejsu Runnable Funkcja co 16ms (60Hz) przerysowuje
	 * grafikę, robimy tak, gdyż mamy ustawiony tryb renderowania na żądanie
	 * (RENDERMODE_WHEN_DIRTY)
	 */
	@Override
	public void run() {
		do {
			requestRender();
			try {
				Thread.sleep(16, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}

}
