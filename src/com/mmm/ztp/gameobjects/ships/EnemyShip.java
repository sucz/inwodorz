package com.mmm.ztp.gameobjects.ships;

import javax.microedition.khronos.opengles.GL10;

import texample.GLText;
import android.content.Context;
import android.util.Log;

import com.mmm.ztp.counter.Counter;
import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.addAlienProjectioleEvent.AddAlienProjectileEventListener;
import com.mmm.ztp.event.addAlienProjectioleEvent.AddAlienProjectileEventObject;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.MoveFactory;
import com.mmm.ztp.movment.StraightMove;
import com.mmm.ztp.weapons.BulletTest;


public class EnemyShip extends BaseObject {
    protected int points=1;
    int zonk = 1;
    protected String bonusType = "";
    protected int bonusValue;
    
    public EnemyShip(Drawable template) {
        super();
        this.objectRep = template;
        if(objectRep!=null)
        	objectRep.setSize(this.size);
        coordinates[0] = 0f;
        coordinates[1] = 0f;
        move = null;


    }

    @Override
    public void onPreRender(GL10 gl) {
    	if(this.move!=null)
        move.move(coordinates);
    	Log.d("EnemyShip","Coords: "+this.coordinates[0]+":"+this.coordinates[1]);
    }


    @Override
    protected void onPostRender(GL10 gl) {
        if ((zonk++ % 60) == 0) {
        	
        	BulletTest tmp=new BulletTest(this.coordinates, 5);
            GameEventBus.getInstance().fireEvent(AddAlienProjectileEventListener.class,
                    new AddAlienProjectileEventObject(tmp));
            
        }
    }

    @Override
    public void onObjectsCollision(BaseObject object) {	
    	super.onObjectsCollision(object);
    }

	@Override
	public void onDestruct() {
		Counter.getInstance().add(points);
		if(this.getBonusType().equals("cash"))
			Counter.getInstance().addCash(this.getBonusValue());
	}

	@Override
	public void onObjectCleanup() {
		// TODO Auto-generated method stub
		
	}

	public void setMovement(Integer moveId) {
		this.move=MoveFactory.produce(moveId, this);
		
	}
	
	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public int getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}
	
	@Override
	protected void toDraw(GL10 gl)
	{

		gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
		glText.begin( Math.min((maxHp-hp)/100f, 1.0f), hp/100.0f, 0.0f, 1.0f );
	    glText.draw( String.valueOf(getHp())+"hp", this.coordinates[0], this.coordinates[1]+this.size );
	    glText.end();

	}
	
	public void load(GL10 gl, Context context)
    {
		super.load(gl, context);
    	glText = new GLText( gl, context.getAssets() );
    	glText.load( "counter-font.ttf", 24, 2, 2 );
    }
	
}
