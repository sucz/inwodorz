package com.mmm.ztp.weapons;

import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;

public class BulletPercentDecorator extends BulletAbstractDecorator {

	public BulletPercentDecorator(BulletBase decoredObject) {
		super(decoredObject);
	}
	public float getObrazenia()
	{
		return this.dekorowany.getObrazenia()*1.1f; //110% obrażeń
	}
	@Override
	public BulletBase clone() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
