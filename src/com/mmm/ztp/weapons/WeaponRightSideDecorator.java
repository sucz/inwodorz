package com.mmm.ztp.weapons;

import java.lang.reflect.InvocationTargetException;

public class WeaponRightSideDecorator extends WeaponAbstractDecorator {

	Weapon rightSide;
	WeaponRightSideDecorator(Weapon decoredObject) {
		super(decoredObject);
		this.rightSide=dekorowany.clone();
	}
	public void changeAmmo(Bullet newAmmo) {
		this.dekorowany.changeAmmo(newAmmo);
		this.rightSide.changeAmmo(newAmmo);
	}
	public float getRifles() {
		return dekorowany.getRifles()+1;
	}

}
