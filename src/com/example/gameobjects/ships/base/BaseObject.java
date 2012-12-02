package com.example.gameobjects.ships.base;


import com.example.drawable.Drawable;
import com.example.movment.Movement;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseObject implements Drawable {
    /**
     *
     */
    public static final long id = 0;

    /**
     * Strategia ruchu obiektu
     */
    protected Movement move;

    /**
     * "Wygląd obiektu"
     */
    protected Drawable objectRep;

    /**
     * Pozycja obiektu
     */
    protected float coordinates[] = {0, 0, 0};

    protected float hitPoints;

    public abstract void onDestruct();

    public void setMovement(Movement move) {
        this.move = move;
    }

    protected void onPreRender(GL10 gl) {

    }

    protected void onPostRender(GL10 gl) {

    }

    @Override
    public final void draw(GL10 gl) {
        gl.glMatrixMode(GL10.GL_MODELVIEW); //6

        gl.glLoadIdentity(); //7

        onPreRender(gl);

        gl.glTranslatef(coordinates[0], coordinates[1], -4.0f); //8

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); //9
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        objectRep.draw(gl);


        onPostRender(gl);

    }
}
