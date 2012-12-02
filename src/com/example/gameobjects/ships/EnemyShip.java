package com.example.gameobjects.ships;

import com.example.drawable.Drawable;
import com.example.event.GameEventBus;
import com.example.event.aliendrawableevent.AddAlienDrawableEventListener;
import com.example.event.aliendrawableevent.AddAlienDrawableEventObject;
import com.example.gameobjects.ships.base.BaseObject;
import com.example.movment.Movement;
import com.example.movment.SimpleMove;
import com.example.weapons.TestBullet;

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
        this.objectRep = template;
        coordinates[0] = 1f;
        coordinates[1] = 3f;
        move = movement;
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
            GameEventBus.getInstance().fireEvent(AddAlienDrawableEventListener.class,
                    new AddAlienDrawableEventObject(new TestBullet(new float[]{coordinates[0], coordinates[1], coordinates[2]}, new SimpleMove()))
            );
            GameEventBus.getInstance().fireEvent(AddAlienDrawableEventListener.class,
                    new AddAlienDrawableEventObject(new TestBullet(new float[]{coordinates[0] + 0.2f, coordinates[1], coordinates[2]}, new SimpleMove()))
            );
        }
    }
}
