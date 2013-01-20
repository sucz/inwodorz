package com.mmm.ztp;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * The initial Android Activity, setting and initiating
 * the OpenGL ES Renderer Class @see Lesson06.java
 * 
 * @author Savas Ziplies (nea/INsanityDesign)
 */
public class Run extends Activity {

	/** The OpenGL View */
	private GameEngine engine;
	Thread runner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Create an Instance with this Activity

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		engine = new GameEngine(this);
		//Set our own Renderer and hand the renderer this Activity Context
		//Set the GLSurface as View to this Activity
		setContentView(engine);
		runner=new Thread(engine);
		runner.start();
		
		
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