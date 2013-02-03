package com.mmm.ztp.weapons;

public class WeaponAbstractDecorator extends WeaponBase {
	protected Weapon dekorowany;
	WeaponAbstractDecorator(Weapon decoredObject)
	{
		this.dekorowany=decoredObject;
	}
	public Weapon clone()
	{
		Weapon dekorowanyClone=dekorowany.clone();
		WeaponAbstractDecorator clone=new WeaponAbstractDecorator(dekorowanyClone);
		return clone;
	}
	

}
