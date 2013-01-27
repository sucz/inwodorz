package com.mmm.ztp.weapons;

import com.mmm.ztp.Ticker.Ticker;
import com.mmm.ztp.Ticker.TickerReusable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventListener;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventObject;
import com.mmm.ztp.movment.MoveFactory;
import com.mmm.ztp.movment.StraightProjectileMove;

/**
 * Created with IntelliJ IDEA. User: miroslaw Date: 11/29/12 Time: 8:30 PM To
 * change this template use File | Settings | File Templates.
 */
public class WeaponBase implements Weapon {

	private Bullet ammo;
	private int interval = 60; // 60*16ms
	private boolean locked = false; // blokada strzału
	private TickerReusable zegarek;

	public WeaponBase() {
		this.zegarek = new TickerReusable(this.interval) {
			public void onDone() {
				locked = false;
			}
		};
	}

	public void shoot(float[] startCoords, float[] target) {
		if (!locked) {
			locked = true;
			if (ammo != null) {
				BulletBase klon = ammo.clone();
				klon.setCoordinates(startCoords[0], startCoords[1], 0);
				GameEventBus.getInstance().fireEvent(
						AddPlayersDrawableEventListener.class,
						new AddPlayersDrawableEventObject(klon));
			}
			zegarek.use();
		}
	}

	@Override
	public void shoot(float[] startCoords, float[] target, int interval) {
		if (!locked) {
			locked = true;
			if (ammo != null) {
				BulletBase klon = ammo.clone();
				klon.setCoordinates(startCoords[0], startCoords[1], 0);
				GameEventBus.getInstance().fireEvent(
						AddPlayersDrawableEventListener.class,
						new AddPlayersDrawableEventObject(klon));
			}
			zegarek.use(interval);
		}
	}

	@Override
	public void changeAmmo(Bullet newAmmo) {
		this.ammo = newAmmo;
	}

	@Override
	public float getRifles() {
		return 1;
	}

	@Override
	public Bullet getBulletType() {
		return ammo;
	}

	@Override
	public int getFireRate() {
		return interval;
	}

}
