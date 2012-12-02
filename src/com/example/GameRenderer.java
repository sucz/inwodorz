package com.example;

import android.content.Context;
import android.opengl.GLSurfaceView;
import com.example.drawable.Drawable;
import com.example.drawable.impl.GlareObj;
import com.example.drawable.impl.TexturedObject;
import com.example.event.GameEventBus;
import com.example.event.aliendrawableevent.AddAlienDrawableEventHandler;
import com.example.event.aliendrawableevent.AddAlienDrawableEventListener;
import com.example.event.aliendrawableevent.AddAlienDrawableEventObject;
import com.example.event.destroyobjectevent.DestroyObjectEventListener;
import com.example.event.destroyobjectevent.DestroyObjectEventObject;
import com.example.event.destroyobjectevent.DestroyObjectHandler;
import com.example.event.drawableevent.AddPlayersDrawableEventHandler;
import com.example.event.drawableevent.AddPlayersDrawableEventListener;
import com.example.event.drawableevent.AddPlayersDrawableEventObject;
import com.example.event.playerfireevent.PlayerFireEventListener;
import com.example.gameobjects.ships.EnemyShip;
import com.example.gameobjects.ships.PlayersShip;
import com.example.movment.MinusCosinusMovement;
import com.example.movment.MinusSinusMovement;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * User: miroslaw
 * Date: 11/28/12
 * Time: 4:58 PM
 */
public class GameRenderer implements GLSurfaceView.Renderer,
        AddPlayersDrawableEventListener, DestroyObjectEventListener, AddAlienDrawableEventListener {

    private Context context;
    /**
     * statek gracza i jego pociski
     */
    private final LinkedBlockingDeque<Drawable> playersObjects = new LinkedBlockingDeque<Drawable>();

    /**
     * statki obcychich pociski, bonusy dla gracza
     */
    private final LinkedBlockingDeque<Drawable> alienObjects = new LinkedBlockingDeque<Drawable>();

    private TexturedObject texturedObject;
    /**
     * pociski
     */
    LinkedList<Drawable> tempObj;
    private PlayersShip ship;
    private TexturedObject sc;

    public GameRenderer(Context context) {
        this.context = context;
    }

    public void setPlayerVector(float x, float y) {
        ship.setVector(x, y);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        GameEventBus.getInstance().attachToEventBus(AddPlayersDrawableEventListener.class,
                new AddPlayersDrawableEventHandler(this));

        GameEventBus.getInstance().attachToEventBus(DestroyObjectEventListener.class,
                new DestroyObjectHandler(this));

        GameEventBus.getInstance().attachToEventBus(AddAlienDrawableEventListener.class,
                new AddAlienDrawableEventHandler(this));


        gl10.glDisable(GL10.GL_DITHER); //15
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        gl10.glClearColor(0, 0, 0, 0);


        gl10.glEnable(GL10.GL_CULL_FACE);
        gl10.glShadeModel(GL10.GL_SMOOTH);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glEnable(GL10.GL_TEXTURE_2D); //1


        int res = R.drawable.ship;
        texturedObject = new TexturedObject();
        texturedObject.createTexture(gl10, context, res);

        res = R.drawable.enemy_scout;
        sc = new TexturedObject();
        sc.createTexture(gl10, context, res);

        alienObjects.add(new EnemyShip(sc, new MinusCosinusMovement()));
        alienObjects.add(new EnemyShip(sc, new MinusSinusMovement()));

        ship = new PlayersShip(texturedObject);

        res = R.drawable.shoot;
        GlareObj.createTexture(gl10, context, res);

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        gl10.glViewport(-i, -i1, i * 2, i1 * 2);
        float ratio = (float) i / i1;
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        gl10.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); //5
        gl10.glEnable(GL10.GL_BLEND);
        gl10.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);

        for (Drawable d : playersObjects) {
            d.draw(gl10);
        }

        for (Drawable d : alienObjects) {
            d.draw(gl10);
        }

        ship.draw(gl10);
    }


    @Override
    public void destroyObject(DestroyObjectEventObject object) {
        playersObjects.remove(object.getToDestroy());
        alienObjects.remove(object.getToDestroy());
    }

    @Override
    public void addDrawable(AddPlayersDrawableEventObject object) {
        playersObjects.add(object.getT());
    }

    public void onUserFire() {
        GameEventBus.getInstance().fireEvent(PlayerFireEventListener.class, null);
    }

    @Override
    public void addDrawable(AddAlienDrawableEventObject object) {
        alienObjects.add(object.getT());
    }
}

