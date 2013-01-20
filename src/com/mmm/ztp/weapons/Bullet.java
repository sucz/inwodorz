package com.mmm.ztp.weapons;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.Movement;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Podstawowa klasa pocisku
 */
public abstract class Bullet extends BaseObject implements Drawable {
    public Bullet(Movement m) {
        move = m;
    }
}
