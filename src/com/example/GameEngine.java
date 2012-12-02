package com.example;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/25/12
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameEngine extends GLSurfaceView implements Runnable {
    private static final String LOG_TAG = GameEngine.class.getSimpleName();

    private GameRenderer _renderer;
    private boolean quit;

    public GameEngine(Context context) {
        super(context);

        _renderer = new GameRenderer(context);
        setRenderer(_renderer);

        // setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable() {
            public void run() {
                /**
                 * tu obsługa molestowania ekranu  a.k.a sterowanie
                 */

                _renderer.onUserFire();
            }
        });
        return true;
    }


    @Override
    public void run() {

        double t = 0.0;
        double dt = 1 / 60.0;

        double currentTime = System.currentTimeMillis();

        while (!quit) {
            double newTime = System.currentTimeMillis();
            double frameTime = newTime - currentTime;
            currentTime = newTime;

            while (frameTime > 0.0) {
                final double deltaTime = (frameTime > dt ? dt : frameTime);
                frameTime -= deltaTime;
                t += deltaTime;
            }

            requestRender();
        }
    }
}
