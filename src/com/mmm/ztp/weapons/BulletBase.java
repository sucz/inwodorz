package com.mmm.ztp.weapons;

import javax.microedition.khronos.opengles.GL10;

import com.mmm.ztp.Ticker.TickerOnetimer;
import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.MoveFactory;
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
public abstract class BulletBase extends BaseObject implements Drawable,Bullet,Cloneable {

	protected float damage;

	public BulletBase() {
	}
	
	public BulletBase(Movement m, float damage) {
		this.move = m;
		this.damage=damage;
	}

	public void setObrazenia(float damage) {
		this.damage = damage;
	}
	
	public float getObrazenia()
	{
		return this.damage;
	}
	
	@Override
	public void onObjectsCollision(BaseObject object)
	{
		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
	}
	
	public abstract BulletBase clone();

	public Movement getMove() {
		return this.move;
	}
	
    protected abstract void onPreRender(GL10 gl);
    
	
	
	
	
}
