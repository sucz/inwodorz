package com.mmm.ztp.weapons;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/29/12
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseWeapon {

    private float damage;
    private Bullet bullet;
    private int interval;


    public abstract void shoot();
}
