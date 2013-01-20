package com.mmm.ztp.gameobjects.ships.base;


import android.content.Context;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.drawable.Hitable;
import com.mmm.ztp.movment.Movement;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseObject implements Drawable, Hitable {
    /**
     * Id obiektu
     */
    private static int id = Integer.MIN_VALUE;
    private static float prec = 0.3f;
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
		this.coordinates[2]=c3;
	}

	protected BaseObject() {
        id++;
    }

    @Override
    public boolean hittest(BaseObject object) {
        if (object.coordinates[1] < coordinates[1] + prec && object.coordinates[1] > coordinates[1] - prec)
            if (object.coordinates[0] < coordinates[0] + prec && object.coordinates[0] > coordinates[0] - prec)
                return true;
        return false;

    }

    /**
     * Ustawia typ ruchu jakim się porusza dany obiekt
     *
     * @param move implementacja ruchu
     */
    public void setMovement(Movement move) {
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
}
