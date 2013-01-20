package com.mmm.ztp.gameobjects.ships;

import android.content.Context;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventListener;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventObject;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventHandler;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventListener;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventHandler;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventListener;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.Movement;
import com.mmm.ztp.movment.SimpleForwardMove;
import com.mmm.ztp.movment.UserMove;
import com.mmm.ztp.weapons.TestBullet;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayersShip extends BaseObject implements PlayerFireEventListener, PlayerMoveEventListener {
    float speed = 100f;

    public PlayersShip(Drawable template) {
        super();
        this.objectRep = template;
        coordinates[1] += 1f;
        coordinates[0] += 2f;
        move = new Movement() {
            @Override
            public void move(float[] c) {

            }
        };
        GameEventBus.getInstance().attachToEventBus(
                PlayerMoveEventListener.class, new PlayerMoveEventHandler(this));
        GameEventBus.getInstance().attachToEventBus(
                PlayerFireEventListener.class, new PlayerFireEventHandler(this));
    }

    /**
     * Akcje wykonywane na końcu życia obiektu
     * (to nie jest destruktor)
     */
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
                new AddPlayersDrawableEventObject(new TestBullet(new float[]{coordinates[0] + 0.2f, coordinates[1] + 0.3f, coordinates[2]}, new SimpleForwardMove()))
        );
        GameEventBus.getInstance().fireEvent(AddPlayersDrawableEventListener.class,
                new AddPlayersDrawableEventObject(new TestBullet(new float[]{coordinates[0] - 0.2f, coordinates[1] + 0.3f, coordinates[2]}, new SimpleForwardMove()))
        );
    }

    @Override
    public void onObjectsCollision(BaseObject object) {
        return;//ed methods use File | Settings | File Templates.
    }

	@Override
	public void onLeft() {
		if(this.move instanceof UserMove)
		{
			((UserMove)this.move).Left();
		}
		
	}

	@Override
	public void onRight() {
		if(this.move instanceof UserMove)
		{
			((UserMove)this.move).Right();
		}
		
	}
}
