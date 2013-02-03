package com.mmm.ztp;

import java.net.URISyntaxException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Główne activity uruchamiane przy starcie aplikacji
 * @author mazdac
 *
 */
public class Run extends Activity {

	/** The OpenGL View */
	private GameEngine engine;
	Thread runner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		
		engine=new GameEngine(this);
		
		setContentView(engine);

		
	}

	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("MainActivity", "Uruchamiam gre za 5s");
		engine.onResume();
	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {
		super.onPause();
		engine.onPause();
	}
	
	



	
}