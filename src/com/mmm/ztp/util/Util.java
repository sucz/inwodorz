package com.mmm.ztp.util;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import android.net.Uri;
import android.util.Log;

import com.mmm.ztp.R;

public class Util {
	/**
	 * Metoda tworząca listę id zasobów zawartej w pakiecie
	 * @param musicRescs lista zasobów
	 * @param startsWith string po którym filtrujemy
	 * @return lista identyfkatorów liczbowych zasobów które nas interesują
	 */
	public static List<Integer> generatePlaylist(Field[] musicRescs, String startsWith) {
		List<Integer> tmpList = new LinkedList<Integer>();
		for (Field r : musicRescs) {
			if (r.getName().startsWith(startsWith)) {
				Class<?> c = R.raw.class;
				Field tmp = null;
				try {
					tmp = c.getDeclaredField(r.getName());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
				if (tmp != null)
					try {
						tmpList.add(tmp.getInt(tmp));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
			}

			if (tmpList.size() > 0)
				Log.d("GameEngine->generatePlaylist", "added to playlist: "
						+ tmpList.get(tmpList.size() - 1));
		}
		if(tmpList.size()>0)
			return tmpList;
		else
			return null;
	}
	/**
	 * Generuje Uri do wykorzystania w odczycie plików z wewnętrznych zasobów
	 * @param resourceId id zasoby
	 * @return wygenerowany Uri 
	 */
	public static Uri generateURI(int resourceId)
	{
		return Uri.parse("android.resource://com.mmm.ztp/" + resourceId);
	}
	

}
