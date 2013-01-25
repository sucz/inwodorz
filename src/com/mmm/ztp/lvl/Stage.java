package com.mmm.ztp.lvl;

import java.util.LinkedList;

import com.mmm.ztp.gameobjects.ships.EnemyShip;

public class Stage {
	private String name;
	LinkedList<EnemyShip> enemyShips;
	
	public Stage() 
	{
		name = "UNKNOWN";
		enemyShips = new LinkedList<EnemyShip>();
	}

	public Stage(String name)
	{
		name = new String(name);
		enemyShips = new LinkedList<EnemyShip>();
	}
	
	public LinkedList<EnemyShip> getShips() 
	{
		return enemyShips;
	}
}
