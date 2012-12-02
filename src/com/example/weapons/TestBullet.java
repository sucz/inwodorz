package com.example.weapons;

import com.example.drawable.impl.GlareObj;
import com.example.event.GameEventBus;
import com.example.event.destroyobjectevent.DestroyObjectEventListener;
import com.example.event.destroyobjectevent.DestroyObjectEventObject;
import com.example.movment.Movement;

import javax.microedition.khronos.opengles.GL10;

/**
 * Author: miroslaw
 * Date: 11/30/12
 * Time: 8:35 PM
 */
public class TestBullet extends Bullet {

    int TTL = 40;

    public TestBullet(float[] coords, Movement m) {
        super(m);
        this.coordinates[0] = coords[0];
        this.coordinates[1] = coords[1];
        this.objectRep = GlareObj.getObj();
    }

    @Override
    public void onDestruct() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void onPostRender(GL10 gl) {
        if (TTL-- < 0)
            GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));

    }

    @Override
    protected void onPreRender(GL10 gl) {
        move.move(coordinates);
    }
}
