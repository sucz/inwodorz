package com.example.drawable;

import com.example.gameobjects.ships.base.BaseObject;

/**
 * Author: miroslaw
 * Date: 1/19/13
 * Time: 2:30 PM
 */
public interface Hitable {
    boolean hittest(BaseObject object);
}
