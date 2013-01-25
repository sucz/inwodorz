package com.mmm.ztp.gameobjects.ships.base;


import android.content.Context;
import android.util.Log;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.drawable.Hitable;
import com.mmm.ztp.drawable.Pointable;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventObject;
import com.mmm.ztp.movment.Movement;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseObject implements Drawable, Hitable, Pointable {
    /**
     * Id obiektu
     */
	protected float speed=9.6f;
    private static int id = Integer.MIN_VALUE;
    protected float size=128f;
    float scale=1f;
    private static float prec = 0.3f;
    protected int points;
    
    
    /**
     * Strategia ruchu obiektu
     */
    protected Movement move;

    /**
     * "Wygląd obiektu"
     */
    protected Drawable objectRep;

    /**
     * Pozycja obiektu  (x,y,z)
     */
    protected float coordinates[] = {0, 0, 0};

    /**
     * Punkty życia obiektu
     */
    protected float hitPoints;

    public abstract void onDestruct();

    public float[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(float c1, float c2, float c3) {
		this.coordinates[0]=c1;
		this.coordinates[1]=c2;
	}

	protected BaseObject() {
        id++;
    }

    @Override
    public boolean hittest(BaseObject object) {
    	//TODO poprawić hittest
        float objDol=object.coordinates[1];
        float objGora=object.coordinates[1]+object.size;
        float objLewo=object.coordinates[0];
        float objPrawo=object.coordinates[0]+object.size;
        float thisDol=coordinates[1];
        float thisGora=coordinates[1]+size;
        float thisLewo=coordinates[0];
        float thisPrawo=coordinates[0]+size;
        if((objGora>=thisDol)&&(objDol<=thisGora)&&(objPrawo>=thisLewo)&&(objLewo<=thisPrawo))
        		return true;
        return false;

    }

    /**
     * Ustawia typ ruchu jakim się porusza dany obiekt
     *
     * @param move implementacja ruchu
     */
    public void setMovement(Movement move) {
    	move.setSpeed(this.speed);
        this.move = move;
        
    }

    /**
     * Akcje wykonywane przed wyrenderowaniem obiektu
     *
     * @param gl
     */
    protected void onPreRender(GL10 gl) {

    }

    /**
     * Akcje wykonywane po wyrenderowaniu obiektu
     *
     * @param gl
     */
    protected void onPostRender(GL10 gl) {

    }

    public int getId() {
        return id;
    }


    @Override
    public final void draw(GL10 gl) {
    	 
        gl.glMatrixMode(GL10.GL_MODELVIEW); //6 - wybieramy macierz modeli

        onPreRender(gl);
        
        gl.glTranslatef(coordinates[0], coordinates[1], 0); //8 -- przesuwamy do koordynatow
		
        objectRep.draw(gl); //rysujemy
        
        gl.glTranslatef((-1)*coordinates[0], (-1)*coordinates[1], 0); //8 -- przesuwamy z powrotem

        onPostRender(gl);
        
    }

    /**
     * Metda wywoływana przy kolizji z objektem w argumencie
     *
     * @param object obiekt z którym nastąpiła kolizja
     */
    public abstract void onObjectsCollision(BaseObject object);
    
    public void load(GL10 gl, Context context)
    {
    	this.objectRep.load(gl,context);
    }

	public void fire(PlayerFireEventObject t) {
		// TODO Auto-generated method stub
		
	}
	
	public void setScale(float scale) {
		coordinates[0]=coordinates[0]*scale;
		coordinates[1]=coordinates[1]*scale;
		move.setSpeed((float)(move.getSpeed()*scale));
		objectRep.setScale(size*scale);
	}
	
	@Override
	public void setSize(float size) {
		// TODO Auto-generated method stub
		
	}
	public long getPoints()
	{
		return points;
	}
	public void setPoints(int points)
	{
		this.points=points;
	}
	
	
}
