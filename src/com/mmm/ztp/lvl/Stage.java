package com.mmm.ztp.lvl;

import java.util.LinkedList;

import com.mmm.ztp.gameobjects.ships.EnemyShip;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;

public class Stage {
	private String name;
	LinkedList<BaseObject> enemyShips;
	
	public Stage() 
	{
		name = "UNKNOWN";
		enemyShips = new LinkedList<BaseObject>();
	}

	public Stage(String name)
	{
		name = new String(name);
		enemyShips = new LinkedList<BaseObject>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public LinkedList<BaseObject> getShips() 
	{
		return enemyShips;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}

