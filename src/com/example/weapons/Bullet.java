package com.example.weapons;

import com.example.drawable.Drawable;
import com.example.gameobjects.ships.base.BaseObject;
import com.example.movment.Movement;

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
