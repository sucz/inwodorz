package com.example.gameobjects.ships.base;


import com.example.drawable.Drawable;
import com.example.drawable.Hitable;
import com.example.movment.Movement;

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
        gl.glMatrixMode(GL10.GL_MODELVIEW); //6

        gl.glLoadIdentity(); //7

        onPreRender(gl);

        gl.glTranslatef(coordinates[0], coordinates[1], -4.0f); //8

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        objectRep.draw(gl);

        onPostRender(gl);
    }

    /**
     * Metda wywoływana przy kolizji z objektem w argumencie
     *
     * @param object obiekt z którym nastąpiła kolizja
     */
    public abstract void onObjectsCollision(BaseObject object);
}
