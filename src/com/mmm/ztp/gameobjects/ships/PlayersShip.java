package com.mmm.ztp.gameobjects.ships;

import javax.microedition.khronos.opengles.GL10;

import texample.GLText;
import android.content.Context;
import android.util.Log;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.audioEvents.AudioEventListener;
import com.mmm.ztp.event.audioEvents.AudioEventObject;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventHandler;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventListener;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventObject;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventHandler;
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventListener;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.UserMove;
import com.mmm.ztp.weapons.BulletTest;
import com.mmm.ztp.weapons.Weapon;
import com.mmm.ztp.weapons.WeaponBase;
import com.mmm.ztp.weapons.WeaponSpeedDecorator;


public class PlayersShip extends BaseObject implements PlayerFireEventListener, PlayerMoveEventListener {
    protected int points=-3000;
    private Weapon weapon; 
    public static PlayersShip instance;

    public PlayersShip(Drawable template) {
        super();
        this.speed=9.6f;
        this.objectRep = template;
        objectRep.setSize(this.size);
        move = new UserMove();
        weapon=new WeaponBase();
        weapon.changeAmmo(new BulletTest(this.coordinates, 6));
        Log.d("PlayerShip", String.valueOf(weapon.getFireRate()));
        GameEventBus.getInstance().attachToEventBus(
                PlayerMoveEventListener.class, new PlayerMoveEventHandler(this));
        GameEventBus.getInstance().attachToEventBus(
                PlayerFireEventListener.class, new PlayerFireEventHandler(this));
        instance=this;

    }
    public Weapon getCurrentWeapon()
    {
    	return this.weapon;
    }
    public void setWeapon(Weapon w)
    {
    	this.weapon=w;
    }
    
    
    
    public static PlayersShip getInstantce()
    {
    	return instance;
    }

    /**
     * Akcje wykonywane na końcu życia obiektu
     * (to nie jest destruktor)
     */
    @Override
    public void onDestruct() {
    }


    @Override
    protected void onPreRender(GL10 gl) {
        move.move(coordinates);
    }

    @Override
    protected void onPostRender(GL10 gl) {
        super.onPostRender(gl);
    }

    public void setVector(float x, float y) {
        move.setTarget((x - coordinates[0]) / speed, (y - coordinates[1]) / speed);
    }

    @Override
    public void fire(PlayerFireEventObject target) {
    	//Log.d("PlayerShip", "Otworzyć ogień w kierunku"+target.getXy()[0]+":"+target.getXy()[1]);
    	float[] middleCords={this.coordinates[0]+(this.size/2),this.coordinates[1]+(this.size/2)};
    	weapon.shoot(middleCords, target.getXy());
        
    }

    @Override
    public void onObjectsCollision(BaseObject object) {
        super.onObjectsCollision(object);
		GameEventBus.getInstance()
		.fireEvent(AudioEventListener.class,
				new AudioEventObject(AudioEventObject.TYPE_DMG,null));
    }

	@Override
	public void onLeft() {
		if(this.move instanceof UserMove)
		{
			((UserMove)this.move).Left();
		}
		
	}

	@Override
	public void onRight() {
		if(this.move instanceof UserMove)
		{
			((UserMove)this.move).Right();
		}
	}
	

	@Override
	protected void toDraw(GL10 gl)
	{
		gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
		glText.begin( Math.min((maxHp-hp)/100f, 1.0f), hp/100.0f, 0.0f, 1.0f );
	    glText.draw( String.valueOf(getHp())+"hp", 400, 760 );
	    glText.end();
	}
	
	public void load(GL10 gl, Context context)
    {
		super.load(gl, context);
    	glText = new GLText( gl, context.getAssets() );
    	glText.load( "counter-font.ttf", 24, 2, 2 );
    }




}
