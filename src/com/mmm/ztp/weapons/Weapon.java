package com.mmm.ztp.weapons;

public interface Weapon {
	public void shoot(float[] start, float[] target);
	public void changeAmmo(Bullet newAmmo);
	public float getRifles();
	public Bullet getBulletType();
}
