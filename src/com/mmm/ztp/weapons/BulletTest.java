package com.mmm.ztp.weapons;

import android.content.Context;

import com.mmm.ztp.drawable.impl.GlareObj;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.Movement;

import javax.microedition.khronos.opengles.GL10;

/**
 * Author: miroslaw
 * Date: 11/30/12
 * Time: 8:35 PM
 */
public class BulletTest extends BulletBase {

    int TTL = 56;  
    
    public BulletTest(float[] coords, Movement m) {
        super(m);
        this.size = 16f;
        this.coordinates[0] = coords[0]-(this.size/2);
        this.coordinates[1] = coords[1];
        this.objectRep = GlareObj.getObj();
        
    }

    @Override
    public void onDestruct() {
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

	@Override
	public void onObjectsCollision(BaseObject object) {
		// TODO Auto-generated method stub
		
	}
}
