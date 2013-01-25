package com.mmm.ztp.event.engineEvents;

import com.mmm.ztp.event.base.SimpleEvent;

/*
 * Klasa definiująca zdarzenia adresowane do silnika
 */
public class EngineEventObject implements SimpleEvent {
	//pola
	Object obj;
	int type;
	
	//definicje typów

	public static int TYPE_NEXT_LEVEL=1;
	public static int TYPE_GAMEOVER=2;
	public static int TYPE_EXIT=3;
	public static int TYPE_NEXT_AUDIO=4;
	
	//definicje metod 
	public EngineEventObject(int type, Object obj)
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
