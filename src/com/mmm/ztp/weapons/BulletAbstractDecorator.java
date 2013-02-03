package com.mmm.ztp.weapons;

import javax.microedition.khronos.opengles.GL10;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;

public abstract class BulletAbstractDecorator extends BulletBase implements Drawable,Cloneable {
	
	BulletBase dekorowany;

	public BulletAbstractDecorator(BulletBase decoredObject)
	{
		super();
		this.dekorowany=decoredObject;
		this.objectRep=dekorowany.getObjectRep();
		this.move=dekorowany.getMove();
		this.coordinates=dekorowany.getCoordinates();
		
	}

	@Override
	public void onDestruct() {
		dekorowany.onDestruct();
	}
	protected void onPreRender(GL10 gl) {
		dekorowany.onPreRender(gl);
	}

	@Override
	public void onObjectsCollision(BaseObject object) {
		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
		dekorowany.onObjectsCollision(object);
	}
	
	@Override
	public void onObjectsCleanup() {
		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this.dekorowany));
		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
	}

}
