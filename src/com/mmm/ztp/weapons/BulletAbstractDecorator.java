package com.mmm.ztp.weapons;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;

public abstract class BulletAbstractDecorator extends BulletBase {
	
	BulletBase dekorowany;

	public BulletAbstractDecorator(BulletBase decoredObject)
	{
		super();
		this.dekorowany=decoredObject;
	}

	@Override
	public void onDestruct() {
		dekorowany.onDestruct();
	}

	@Override
	public void onObjectsCollision(BaseObject object) {
		dekorowany.onObjectsCollision(object);
	}
	
	@Override
	public void onObjectCleanup() {
		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this.dekorowany));
		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
	}


}
