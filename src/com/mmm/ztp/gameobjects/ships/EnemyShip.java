package com.mmm.ztp.gameobjects.ships;

import android.content.Context;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.addAlienProjectioleEvent.AddAlienProjectileEventListener;
import com.mmm.ztp.event.addAlienProjectioleEvent.AddAlienProjectileEventObject;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.Movement;
import com.mmm.ztp.movment.SimpleMove;
import com.mmm.ztp.weapons.TestBullet;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 7:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class EnemyShip extends BaseObject {
    float speed = 100f;
    int zonk = 1;

    public EnemyShip(Drawable template, Movement movement) {
        super();
        this.objectRep = template;
        coordinates[0] = 1f;
        coordinates[1] = 2f;
        move = movement;

    }
    
    public EnemyShip(Drawable template) {
        super();
        this.objectRep = template;
        coordinates[0] = 1f;
        coordinates[1] = 2f;
        move = new Movement() {
            @Override
            public void move(float[] c) {

            }
        };


    }
    
    @Override
    public void onDestruct() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPreRender(GL10 gl) {
        move.move(coordinates);
    }


    @Override
    protected void onPostRender(GL10 gl) {

        if ((zonk++ % 60) == 0) {
            GameEventBus.getInstance().fireEvent(AddAlienProjectileEventListener.class,
                    new AddAlienProjectileEventObject(new TestBullet(new float[]{coordinates[0], coordinates[1], coordinates[2]}, new SimpleMove()))
            );
            GameEventBus.getInstance().fireEvent(AddAlienProjectileEventListener.class,
                    new AddAlienProjectileEventObject(new TestBullet(new float[]{coordinates[0] + 0.2f, coordinates[1], coordinates[2]}, new SimpleMove()))
            );
        }
    }

    @Override
    public void onObjectsCollision(BaseObject object) {
        GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
    }
}
