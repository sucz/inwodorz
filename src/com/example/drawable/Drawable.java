package com.example.drawable;

import javax.microedition.khronos.opengles.GL10;

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
    public void draw(GL10 gl);
}
