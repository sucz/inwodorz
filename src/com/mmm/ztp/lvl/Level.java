package com.mmm.ztp.lvl;

import java.util.LinkedList;

public class Level 
{	
	//private String fileName;
	private String name;
	private LinkedList<Stage> stages;

	public Level()
	{
		name = "Unknown";
		stages = new LinkedList<Stage>();
	}
	
	public Level(String name)
	{
		name = new String(name);
		stages = new LinkedList<Stage>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public LinkedList<Stage> getStages() {
		return stages;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
