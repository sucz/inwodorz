package com.example.gameobjects.ships;

import com.example.drawable.Drawable;
import com.example.event.GameEventBus;
import com.example.event.drawableevent.AddPlayersDrawableEventListener;
import com.example.event.drawableevent.AddPlayersDrawableEventObject;
import com.example.event.playerfireevent.PlayerFireEventHandler;
import com.example.event.playerfireevent.PlayerFireEventListener;
import com.example.gameobjects.ships.base.BaseObject;
import com.example.movment.MinusSinusMovement;
import com.example.movment.SimpleForwardMove;
import com.example.weapons.TestBullet;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayersShip extends BaseObject implements PlayerFireEventListener {
    float speed = 100f;

    public PlayersShip(Drawable template) {
        this.objectRep = template;
        coordinates[1] += 1f;
        coordinates[0] += 2f;
        move = new MinusSinusMovement();/*new Movement() {
            @Override
            public void move(float[] c) {
                c[0]+=vecX;c[1]+=vecY;
            }
        } ;*/
        GameEventBus.getInstance().attachToEventBus(
                PlayerFireEventListener.class, new PlayerFireEventHandler(this));
    }

    @Override
    public void onDestruct() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void onPreRender(GL10 gl) {
        move.move(coordinates);
    }

    @Override
    protected void onPostRender(GL10 gl) {
        super.onPostRender(gl);
    }

    public void setVector(float x, float y) {
        move.setTarget((x - coordinates[0]) / speed, (y - coordinates[1]) / speed);
    }

    @Override
    public void fire() {
        GameEventBus.getInstance().fireEvent(AddPlayersDrawableEventListener.class,
                new AddPlayersDrawableEventObject(new TestBullet(new float[]{coordinates[0], coordinates[1] + 0.2f, coordinates[2]}, new SimpleForwardMove()))
        );
    }
}
