package com.mmm.ztp.event.playermoveevent;

import com.mmm.ztp.event.base.SimpleEvent;

/**
 * Klasa obiektu który zawiera kierunek ruchu
 * @author mazdac
 *
 */
public class PlayerMoveObject implements SimpleEvent {
	int type; // -1 lewo, 1 prawo 
	/**
	 * Konstruktor tworzący obiekt zawierający informacje o kierunku ruchu
	 * @param type kierunek ruchu 1 w prawo, -1 w lewo
	 */
	public PlayerMoveObject(int type)
	{
		this.type=type;
	}
}
