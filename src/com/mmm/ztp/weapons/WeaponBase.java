package com.mmm.ztp.weapons;

import com.mmm.ztp.Ticker.Ticker;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventListener;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventObject;
import com.mmm.ztp.movment.SimpleForwardMove;

/**
 * Created with IntelliJ IDEA. User: miroslaw Date: 11/29/12 Time: 8:30 PM To
 * change this template use File | Settings | File Templates.
 */
public class WeaponBase implements Weapon {

	private Bullet ammo;
	private int interval = 60; // 60*16ms
	private boolean locked = false; // blokada strzału
	private Ticker zegarek;

	public WeaponBase() {
	}

	public void shoot(float[] startCoords, float[] target) {
		if (!locked) {
			GameEventBus.getInstance().fireEvent(
					AddPlayersDrawableEventListener.class,
					new AddPlayersDrawableEventObject(new BulletTest(
							new float[] { startCoords[0] + 0.2f,
									startCoords[1] + 0.3f, 0 },
							new SimpleForwardMove())));
			new Ticker(this.interval){ public void onDone() { locked=false; }};
			locked = true;

		}
		;
	}

	@Override
	public void changeAmmo(Bullet newAmmo) {
		this.ammo=newAmmo;
	}

	@Override
	public float getRifles() {
		return 1;
	}

	@Override
	public Bullet getBulletType() {
		return ammo;
	}
}
