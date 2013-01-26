package com.mmm.ztp.weapons;

public class WeaponAbstractDecorator extends WeaponBase {
	protected Weapon dekorowany;
	WeaponAbstractDecorator(Weapon decoredObject)
	{
		this.dekorowany=decoredObject;
	}

}
