package com.mmm.ztp.weapons;

public class WeaponSpeedDecorator extends WeaponAbstractDecorator implements Weapon {
	float sfactor=0.75f;

	public WeaponSpeedDecorator(Weapon decoredObject) {
		super(decoredObject);
	}
	public void shoot(float[] start, float[] target, int interval) {
		int tmpFireRate=this.dekorowany.getFireRate();
		tmpFireRate=(int) (tmpFireRate*sfactor);
		dekorowany.shoot(start, target, tmpFireRate);
	}
	public void shoot(float[] start, float[] target)
	{
		int tmpFireRate=this.dekorowany.getFireRate();
		tmpFireRate=(int) (tmpFireRate*sfactor);
		dekorowany.shoot(start, target, tmpFireRate);
	}
	public int getFireRate()
	{
		return (int) (dekorowany.getFireRate()*sfactor);
	}
	public void changeAmmo(Bullet newAmmo)
	{
		this.dekorowany.changeAmmo(newAmmo);
	}

}
