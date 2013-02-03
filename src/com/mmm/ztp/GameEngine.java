package com.mmm.ztp;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AsyncPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Toast;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.audioEvents.AudioEventListener;
import com.mmm.ztp.event.audioEvents.AudioEventObject;
import com.mmm.ztp.event.engineEvents.EngineEventHandler;
import com.mmm.ztp.event.engineEvents.EngineEventListener;
import com.mmm.ztp.event.engineEvents.EngineEventObject;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventListener;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventObject;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventListener;
import com.mmm.ztp.event.playermoveevent.PlayerMoveObject;
import com.mmm.ztp.lvl.Level;
import com.mmm.ztp.lvl.LvLBuilder;
import com.mmm.ztp.lvl.Stage;
import com.mmm.ztp.services.AudioService2;
import com.mmm.ztp.services.Refresher;
import com.mmm.ztp.services.Service;
import com.mmm.ztp.util.Util;

/**
 * Silnik, główna część sterująca
 * 
 * @author mazdac
 * 
 */
public class GameEngine extends GLSurfaceView implements SensorEventListener,
		OnKeyListener, EngineEventListener {

	private GameRenderer _renderer;

	Refresher refresh;
	AsyncPlayer player;
	Context context;
	List<Integer> lvlList;
	String currentLvLName = "";
	String currentStageName = "";
	Iterator lvlIter;
	Iterator stagesIter;
	Service audio;
	Activity parentActivity;

	public GameEngine(Activity activity) {
		super((Context) activity);
		this.parentActivity = activity;
		this.context = (Context) activity;
		this.setFocusableInTouchMode(true);
		this.setOnKeyListener(this);

		_renderer = new GameRenderer(context);

		setRenderer(_renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

		// rejestracja listernerów
		SensorManager sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor;
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_GAME);
		GameEventBus.getInstance().attachToEventBus(EngineEventListener.class,
				new EngineEventHandler(this));

		this.generateLists();

		player = new AsyncPlayer("GameEngine->AudioPlayer");

		LvLBuilder lvLBuilder = new LvLBuilder(lvlList, context);
		lvlIter = lvLBuilder.iterator();
		if (lvlIter.hasNext()) {

			Level lvl = (Level) lvlIter.next();
			currentLvLName = lvl.getName();
			List<Stage> stages = lvl.getStages();

			stagesIter = stages.iterator();
			if (stages != null && stagesIter.hasNext()) {
				Stage stage = (Stage) stagesIter.next();
				currentStageName = stage.getName();
				_renderer.setAlienObjectsList(stage.getShips());
			}
			Log.d("Current Level: ", currentLvLName);
			Log.d("Current Stage: ", currentStageName);
		}
		this.refresh = new Refresher(this);
		((Thread) refresh).start();
		this.audio = new AudioService2(context);
		((Thread) audio).start();

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
		audio.onResume();
	}

	/**
	 * Implementacja metody dziedziczonych po View, wymagane aby aplikacja
	 * prawidłowo reagowała na zmianę aktwnego activity
	 */
	public void onPause() {
		super.onPause();
		audio.onPause();
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if ((event.getKeyCode() == KeyEvent.KEYCODE_MENU)
				&& (event.getAction() == KeyEvent.ACTION_DOWN)) {
			Intent menu = new Intent(context, Menu.class);
			context.startActivity(menu);
			return true;

		} else if ((event.getKeyCode() == KeyEvent.KEYCODE_BACK)
				&& (event.getAction() == KeyEvent.ACTION_UP)) {
			this.onResume();

			return true;
		}
		return false;
	}

	/**
	 * Metoda generująca listy zasobów do dalszego wykorzystania
	 * 
	 */
	private void generateLists() {

		Field[] fileRescs = R.raw.class.getFields();
		Log.d("length fileRescs", String.valueOf(fileRescs.length));
		if (fileRescs.length > 0) {
			lvlList = Util.generatePlaylist(fileRescs, "lvl_");
		}
	}

	/**
	 * Implementacja obsługi zdarzeń które muszą zostać wykonane przez silnik
	 * 
	 * @see com.mmm.ztp.event.engineEvents.EngineEventListener#onEngineEvent(com.mmm.ztp.event.engineEvents.EngineEventObject)
	 */
	@Override
	public void onEngineEvent(EngineEventObject obj) {
		if (obj.getType() == EngineEventObject.TYPE_NEXT_LEVEL) {
			Intent menu = new Intent(context, LevelMenu.class);

			if (stagesIter.hasNext()) {
				this.renderPause();
				Stage stage = (Stage) stagesIter.next();
				currentStageName = stage.getName();
				_renderer.setAlienObjectsList(stage.getShips());

				Log.d("Current Level: ", currentLvLName);
				Log.d("Current Stage: ", currentStageName);

				this.parentActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(
								context,
								"Level: " + currentLvLName + " Stage:"
										+ currentStageName, Toast.LENGTH_SHORT)
								.show();
					}

				});
				this.renderResume();
			} else if (lvlIter.hasNext()) {
				this.renderPause();
				GameEventBus.getInstance().fireEvent(
						AudioEventListener.class,
						new AudioEventObject(AudioEventObject.TYPE_NEXT_LVL,
								null));

				Level lvl = (Level) lvlIter.next();
				currentLvLName = lvl.getName();
				List<Stage> stages = lvl.getStages();
				stagesIter = stages.iterator();

				if (stages != null && stagesIter.hasNext()) {
					Stage stage = (Stage) stagesIter.next();
					currentStageName = stage.getName();
					_renderer.setAlienObjectsList(stage.getShips());
					Log.d("Current Level: ", currentLvLName);
					Log.d("Current Stage: ", currentStageName);
				}
				menu.putExtra("level", currentLvLName);
				menu.putExtra("stage", currentStageName);
				context.startActivity(menu);
				this.renderResume();
			} else {
				this.renderPause();
				GameEventBus.getInstance().fireEvent(
						EngineEventListener.class,
						new EngineEventObject(EngineEventObject.TYPE_GAMEOVER,
								null));
			}
		}
		if (obj.getType() == EngineEventObject.TYPE_GAMEOVER) {
			Intent finish = new Intent(context, GameOver.class);
			context.startActivity(finish);
		}

	}

	public void renderPause() {
		this.refresh.onPause();
	}

	public void renderResume() {
		this.refresh.onResume();
	}

}
