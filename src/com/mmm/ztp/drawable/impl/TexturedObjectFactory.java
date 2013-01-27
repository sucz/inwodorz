package com.mmm.ztp.drawable.impl;

import android.util.Log;
import android.util.SparseArray;

/**
 * Fabryka pyłków TexturedObject
 * @author mazdac
 *
 */
public class TexturedObjectFactory {

	/**
	 * Mapa zawierająca wszystkie użyte tekstury
	 */
	static SparseArray<SparseArray<TexturedObject>> flyweight=new SparseArray<SparseArray<TexturedObject>>();
	
	/**
	 * Metoda wydająca lub tworząca nowy TexturedObject
	 * @param res id tekstury
	 * @param size rozmiar tekstury 
	 * @return wcześniej użyty lub nowy objekt tekstury
	 */
	public static TexturedObject get(int res, int size)
	{
		if((flyweight. indexOfKey(res))<0) //nie mamy tekstury i rozmiaru
		{
			flyweight.put(res,new SparseArray<TexturedObject>()); //tworzymy nowa podmape
			flyweight.get(res).put(size, new TexturedObject(res,size)); //wrzucamy element
		}
		else //mamy teksture
		{
			if((flyweight.get(res).indexOfKey(size))<0) //jesli nie ma tego rozmiaru
					flyweight.get(res).put(size, new TexturedObject(res,size));
		}
		Log.d("TexturedObjectFactory","Unique elements: "+flyweight.size());
		return (flyweight.get(res)).get(size);
		
		
	}
}
