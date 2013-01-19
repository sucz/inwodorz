package com.example;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/21/12
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class TheGame extends Activity {
    private GLSurfaceView mGLSurfaceView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GameEngine view = new GameEngine(this);
        //  view.setRenderer(new GameRenderer());
        setContentView(view);
        //   new Thread(view).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}