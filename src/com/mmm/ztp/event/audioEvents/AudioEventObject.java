package com.mmm.ztp.event.audioEvents;

import com.mmm.ztp.event.base.SimpleEvent;

/*
 * Klasa definiująca zdarzenia adresowane do silnika audio
 */
public class AudioEventObject implements SimpleEvent {
	//pola
	Object obj;
	int type;
	
	//definicje typów

	public static int TYPE_FIRE=1;
	public static int TYPE_NEXT_LVL=2;
	public static int TYPE_DMG=3;
	public static int TYPE_ENEMY_FIRE=4;
	
	//definicje metod 
	public AudioEventObject(int type, Object obj)
	{
		this.type=type;
		this.obj=obj;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
