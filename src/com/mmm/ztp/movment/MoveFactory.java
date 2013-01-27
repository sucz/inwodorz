package com.mmm.ztp.movment;

import android.util.Log;

import com.mmm.ztp.gameobjects.ships.base.BaseObject;

public class MoveFactory {
	public static Movement produce(int id)
	{
		switch (id) {
		case 0: return null;
		case 1: return new FullCosinusMovement();	
		case 2: return new FullSinusMovement();
		case 3: return new MinusCosinusMovement();
		case 4: return new MinusSinusMovement();
		case 5: return new StraightMove();
		case 6: return new StraightProjectileMove();

		default:
			return new FullCosinusMovement();
		}
	}
	public static Movement produce(int id, final BaseObject ship)
	{
		switch (id) {
		case 0: return null;
		case 1: return new FullCosinusMovement(){ public void onBound(){ ship.onObjectCleanup(); } };	
		case 2: return new FullSinusMovement(){ public void onBound(){ ship.onObjectCleanup(); } };
		case 3: return new MinusCosinusMovement(){ public void onBound(){ ship.onObjectCleanup(); } };
		case 4: return new MinusSinusMovement(){ public void onBound(){ ship.onObjectCleanup(); } };
		case 5: return new StraightMove(){ public void onBound(){ ship.onObjectCleanup(); } };
		case 6: return new StraightProjectileMove(){ public void onBound(){ ship.onObjectCleanup(); Log.d("Move", "Usuniato pocisk"); } };
		

		default:
			return new FullCosinusMovement();
		}
	}
}
