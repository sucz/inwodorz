package com.mmm.ztp.gameobjects.ships;

import com.mmm.ztp.counter.Counter;
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


public class EnemyShip extends BaseObject {
    float speed = 100f;
    protected int points=1;
    int zonk = 1;

    public EnemyShip(Drawable template, Movement movement) {
        super();
        this.objectRep = template;
        objectRep.setSize(this.size);
        coordinates[0] = 0f;
        coordinates[1] = 0f;
        move = new Movement() {
            @Override
            public void move(float[] c) {

            }
        };

    }
    
    public EnemyShip(Drawable template) {
        super();
        this.objectRep = template;
        objectRep.setSize(this.size);
        coordinates[0] = 0f;
        coordinates[1] = 0f;
        move = new Movement() {
            @Override
            public void move(float[] c) {

            }
        };


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
    	Counter.getInstance().add(points);
        GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
    }

	@Override
	public void onDestruct() {
		// TODO Auto-generated method stub
		
	}
}
