package com.mmm.ztp.weapons;

import java.lang.reflect.InvocationTargetException;

public class WeaponRightSideDecorator extends WeaponAbstractDecorator {

	Weapon rightSide;
	WeaponRightSideDecorator(Weapon decoredObject) {
		super(decoredObject);
		try {
			rightSide=(Weapon) ((decoredObject).getClass().getConstructors()[0]).newInstance();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void changeAmmo(Bullet newAmmo) {
		this.dekorowany.changeAmmo(newAmmo);
		this.rightSide.changeAmmo(newAmmo);
	}
	public float getRifles() {
		return dekorowany.getRifles()+1;
	}

}
