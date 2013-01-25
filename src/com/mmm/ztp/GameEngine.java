package com.mmm.ztp;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventListener;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventObject;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventListener;
import com.mmm.ztp.event.playermoveevent.PlayerMoveObject;

/**
 * Silnik, główna część sterująca
 * 
 * @author mazdac
 * 
 */
public class GameEngine extends GLSurfaceView implements Runnable,
		SensorEventListener, OnKeyListener {

	private GameRenderer _renderer;
	
	AsyncPlayer player;
	Context context;
	List<Integer> playlist;
	Random rand=new Random(Calendar.getInstance().getTimeInMillis());

	public GameEngine(Context context) {
		super(context);
		this.context = context;
		this.setFocusableInTouchMode(true);
		this.setOnKeyListener(this);
		
		 
		_renderer = new GameRenderer(context);
		
		setRenderer(_renderer);

		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); 
		SensorManager sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor;
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_GAME);
		playlist = this.generatePlaylist();
		
		player = new AsyncPlayer("GameEngine->AudioPlayer");

	}

	/**
	 * Implementacja onTouchEvent zawiera wszystko co związane z obsługą dotyku,
	 * w naszym przypadku wysyła eventy sterujące statkiem do statku gracza
	 * poprzez GameEventBus
	 */
	public boolean onTouchEvent(final MotionEvent event) {
		// Log.d("GameEngine","MotionEvent x:"+event.getX()+" y:"+event.getY());
		float[] xy = { event.getX(), event.getY() };
		GameEventBus.getInstance().fireEvent(PlayerFireEventListener.class,
				new PlayerFireEventObject(xy));
		return true;
	}

	/**
	 * Implementacja interfejsu Runnable 
	 * Funkcja co 16ms (60Hz) przerysowuje
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
				e.printStackTrace();
			}
		} while (true);
	}

	/**
	 * Implementacja interfejsu SensorEventListenera wywoływana gdy nastąpi
	 * zmiana jakości odczytu z sensora np. w przypadku GPS spadnie dokładność
	 * szacowania pozycji
	 * 
	 * @param sensor
	 *            sensor którego dotyczy zmiana
	 * @param accuracy
	 *            aktualna dokładność
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	/**
	 * Implementacja SensorEventListenera wywoływana przy zmianie stanu
	 * czujników urządzenia
	 * 
	 * @param event
	 *            - obiekt zawierający informacje o evecie, sensorze i zmianie
	 *            wartości
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			if (event.values[0] > 1)
				GameEventBus.getInstance()
						.fireEvent(PlayerMoveEventListener.class,
								new PlayerMoveObject(-1));
			else if (event.values[0] < -1)
				GameEventBus.getInstance().fireEvent(
						PlayerMoveEventListener.class, new PlayerMoveObject(1));
		}
	}

	/**
	 * Implementacja metody dziedziczonych po View, wymagane aby aplikacja
	 * prawidłowo reagowała na zmianę aktwnego activity
	 */
	public void onResume() {
		super.onResume();
		int track=0;
		if(playlist!=null)
		{	
			if(playlist.size()>1)
				track=rand.nextInt(playlist.size());
		
			player.play(context,Uri.parse("android.resource://com.mmm.ztp/" + playlist.get(track)),Boolean.FALSE, AudioManager.STREAM_MUSIC);
		}
	}

	/**
	 * Implementacja metody dziedziczonych po View, wymagane aby aplikacja
	 * prawidłowo reagowała na zmianę aktwnego activity
	 */
	public void onPause() {
		super.onPause();
		player.stop();
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if ((event.getKeyCode() == KeyEvent.KEYCODE_MENU)&&(event.getAction()==KeyEvent.ACTION_DOWN)) {
			Intent menu=new Intent(context,Menu.class);
			context.startActivity(menu);
			return true;
			
		}
		return false;
	}

	/**
	 * Metoda tworząca listę id zasobów muzyki zawartej w pakiecie
	 * @return lista identyfkatorów liczbowych zasobów
	 */
	private List<Integer> generatePlaylist() {
		List<Integer> tmpList = new LinkedList<Integer>();
		Field[] musicRescs = R.raw.class.getFields();
		for (Field r : musicRescs) {
			if (r.getName().startsWith("m_")) {
				Class<?> c = R.raw.class;
				Field tmp = null;
				try {
					tmp = c.getDeclaredField(r.getName());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
				if (tmp != null)
					try {
						tmpList.add(tmp.getInt(tmp));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
			}

			if (tmpList.size() > 0)
				Log.d("GameEngine->generatePlaylist", "added to playlist: "
						+ tmpList.get(tmpList.size() - 1));
		}
		if(tmpList.size()>0)
			return tmpList;
		else
			return null;
	}
}
