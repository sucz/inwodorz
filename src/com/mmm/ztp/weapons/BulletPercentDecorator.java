package com.mmm.ztp.weapons;

public class BulletPercentDecorator extends BulletAbstractDecorator {

	public BulletPercentDecorator(BulletBase decoredObject) {
		super(decoredObject);
	}
	public float getObrazenia()
	{
		return this.dekorowany.getObrazenia()*1.1f; //110% obrażeń
	}

}
