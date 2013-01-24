package com.mmm.ztp.drawable;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * interfejs do graficznej reprezentacji obiektów
 */
public interface Drawable {
    /**
     * Metoda rysująca obiekt
     *
     * @param gl
     */
    public void draw(GL10 gl);
    public void load(GL10 gl, Context context); //ładuje do pamięci 
	public void setScale(float scale);
	public void setSize(float size);
}
