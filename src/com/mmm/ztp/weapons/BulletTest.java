package com.mmm.ztp.weapons;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.mmm.ztp.R;
import com.mmm.ztp.drawable.impl.TexturedObjectFactory;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.movment.MoveFactory;


public class BulletTest extends BulletBase {
    
    public BulletTest(float[] coords, int moveId, float size) {
    	super();
		move = MoveFactory.produce(moveId,this); //nowy ruch na bazie id
		this.size=size;
		move.setSpeed(this.speed); //ustawiamy prędkość ruchu
		
        
        this.coordinates[0] = coords[0]-(this.size/2);
        this.coordinates[1] = coords[1];
        this.objectRep = TexturedObjectFactory.get(R.drawable.shoot, (int) size);
        
        this.move.setSize(size);
        
    }
    
    
    public BulletTest(float[] coords, int moveId) {
    	super();
    	float size=16f;
    	this.size=16f;
		move = MoveFactory.produce(moveId,this); //nowy ruch na bazie id
		
		move.setSpeed(this.speed); //ustawiamy prędkość ruchu
		
        this.coordinates[0] = coords[0]+(this.size/2);
        this.coordinates[1] = coords[1];
        this.objectRep = TexturedObjectFactory.get(R.drawable.shoot, (int) size);
        this.setSize(size);
        
    }

    @Override
    public void onDestruct() {
    }

    @Override
    protected void onPostRender(GL10 gl) {
    	
    }

    @Override
    protected void onPreRender(GL10 gl) {
    	//Log.d("BulletTest", "Coords:"+this.coordinates[0]+":"+this.coordinates[1]);
        move.move(coordinates);
    }

	@Override
	public void onObjectsCleanup() {
		//Log.d("BulletTest", "BulletDestroyed on "+this.coordinates[0]+":"+this.coordinates[1]);
		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
	}

	@Override
	public BulletBase clone() {
		BulletTest tmp=new BulletTest(this.coordinates, this.move.getId(), this.size);
		return tmp;
	}
}
