package com.mmm.ztp.weapons;

public class WeaponLeftSideDecorator extends WeaponAbstractDecorator {

	Weapon leftSide;
	WeaponLeftSideDecorator(Weapon decoredObject) {
		super(decoredObject);
		
	}
	public void changeAmmo(Bullet newAmmo) {
		this.dekorowany.changeAmmo(newAmmo);
		this.leftSide.changeAmmo(newAmmo);
	}
	public float getRifles() {
		return dekorowany.getRifles()+1;
	}

}
