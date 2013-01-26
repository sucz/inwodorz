package com.mmm.ztp.weapons;

import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.Movement;

public abstract class BulletAbstractDecorator extends BulletBase {
	
	BulletBase dekorowany;

	public BulletAbstractDecorator(Movement m) {
		super(m);
	}
	public BulletAbstractDecorator(BulletBase decoredObject)
	{
		super(decoredObject.move);
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


}
