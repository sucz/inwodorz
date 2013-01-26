package com.mmm.ztp.weapons;

public interface Weapon {
	public void shoot(float[] start, float[] target);
	public void shoot(float[] start, float[] target, int fireRate);
	public int getFireRate();
	public void changeAmmo(Bullet newAmmo);
	public float getRifles();
	public Bullet getBulletType();
}
