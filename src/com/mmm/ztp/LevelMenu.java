package com.mmm.ztp;

import com.mmm.ztp.counter.Counter;
import com.mmm.ztp.gameobjects.ships.PlayersShip;
import com.mmm.ztp.weapons.Bullet;
import com.mmm.ztp.weapons.BulletBase;
import com.mmm.ztp.weapons.BulletPercentDecorator;
import com.mmm.ztp.weapons.WeaponSpeedDecorator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LevelMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final LevelMenu obj=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levelmenu);
		final TextView licznik=(TextView) this.findViewById(R.id.credits_account);
		licznik.setText(String.valueOf(Counter.getInstance().getCash()));
		Counter.getInstance().getCash();
		Button speedDecButton=(Button) this.findViewById(R.id.Button01);
		speedDecButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if((Integer.valueOf((String) licznik.getText())-50)>0)
				{
					Counter.getInstance().substractCash(50);
					licznik.setText(String.valueOf(Counter.getInstance().getCash()));
					PlayersShip.getInstantce().setWeapon(new WeaponSpeedDecorator(PlayersShip.getInstantce().getCurrentWeapon()));
				}
				else
					licznik.setTextColor(Color.RED);
				}
		});
		
		Button ammoDec=(Button) this.findViewById(R.id.Button04);
		ammoDec.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if((Integer.valueOf((String) licznik.getText())-50)>0)
				{
					Counter.getInstance().substractCash(50);
					licznik.setText(String.valueOf(Counter.getInstance().getCash()));
					Bullet newAmmo=new BulletPercentDecorator((BulletBase) PlayersShip.getInstantce().getCurrentWeapon().getBulletType());
					PlayersShip.getInstantce().getCurrentWeapon().changeAmmo(newAmmo);
				}
				else
					licznik.setTextColor(Color.RED);
			}
		});
		Button nextLevel=(Button)this.findViewById(R.id.start);
		nextLevel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
				
			} } );
		
	}


}
