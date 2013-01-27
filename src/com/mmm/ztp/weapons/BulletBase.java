package com.mmm.ztp.weapons;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.Movement;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Podstawowa klasa pocisku
 */
public abstract class BulletBase extends BaseObject implements Drawable,Bullet {

	protected float damage;
	Movement move;

	public BulletBase(Movement m) {
		m.setSpeed(this.speed);
		move = m;
	}
	public BulletBase(Movement m, float damage) {
		move = m;
		this.damage=damage;
	}

	public void setObrazenia(float damage) {
		this.damage = damage;
	}
	
	public float getObrazenia()
	{
		return this.damage;
	}
	
	
}
