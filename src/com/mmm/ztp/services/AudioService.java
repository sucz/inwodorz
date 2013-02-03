package com.mmm.ztp.services;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.util.Log;

import com.mmm.ztp.R;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.audioEvents.AudioEventHandler;
import com.mmm.ztp.event.audioEvents.AudioEventListener;
import com.mmm.ztp.event.audioEvents.AudioEventObject;
import com.mmm.ztp.util.Util;

public class AudioService implements Service, AudioEventListener  {
	
	Context context; //używany do znalezienia ścieżek
	AsyncPlayer player;
	Random rand=new Random(Calendar.getInstance().getTimeInMillis());
	
	List<Integer> dmg_effects;
	List<Integer> enemy_fire_effects;
	List<Integer> fire_effects;
	List<Integer> next_lvl_effects;
	
	public AudioService(Context ctx)
	{
		this.context=ctx;
		generateLists();
		GameEventBus.getInstance().attachToEventBus(AudioEventListener.class, new AudioEventHandler(this));
	}
	
	@Override
	public void run() {
		try {
			synchronized(this)
			{
			this.wait();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void onPause() {
	}

	@Override
	public void onResume() {
	}
	
/*
		int track=0;
		if(playlist!=null)
		{	
			if(playlist.size()>1)
				track=rand.nextInt(playlist.size());
		
			player.play(context,Util.generateURI(playlist.get(track)),Boolean.FALSE, AudioManager.STREAM_MUSIC);
			
			
			
		}
 */

	@Override
	public void onAudioEvent(AudioEventObject obj) {
		AsyncPlayer tmp=new AsyncPlayer("AudioService");
		if(obj.getType()==AudioEventObject.TYPE_DMG)
		{
			int track=0;
			if(dmg_effects.size()>1)
				track=rand.nextInt(dmg_effects.size()-1);
			tmp.play(context, Util.generateURI(dmg_effects.get(track)), Boolean.FALSE, AudioManager.STREAM_MUSIC);
		}
		if(obj.getType()==AudioEventObject.TYPE_ENEMY_FIRE)
		{
			int track=0;
			if(enemy_fire_effects.size()>1)
				track=rand.nextInt(enemy_fire_effects.size()-1);
			tmp.play(context, Util.generateURI(enemy_fire_effects.get(track)), Boolean.FALSE, AudioManager.STREAM_MUSIC);
		}
		if(obj.getType()==AudioEventObject.TYPE_FIRE)
		{
			int track=0;
			if(fire_effects.size()>1)
				track=rand.nextInt(fire_effects.size()-1);
			tmp.play(context, Util.generateURI(fire_effects.get(track)), Boolean.FALSE, AudioManager.STREAM_MUSIC);
		}
		if(obj.getType()==AudioEventObject.TYPE_NEXT_LVL)
		{
			int track=0;
			if(next_lvl_effects.size()>1)
				track=rand.nextInt(next_lvl_effects.size()-1);
			tmp.play(context, Util.generateURI(next_lvl_effects.get(track)), Boolean.FALSE, AudioManager.STREAM_MUSIC);
		}
		
	}
	
	private void generateLists() {
		
		Field[] fileRescs = R.raw.class.getFields();
		Log.d("length fileRescs", String.valueOf(fileRescs.length));
		if(fileRescs.length > 0)
		{
			dmg_effects = Util.generatePlaylist(fileRescs, "fx_dmg");
			enemy_fire_effects = Util.generatePlaylist(fileRescs, "fx_efire");
			fire_effects = Util.generatePlaylist(fileRescs, "fx_fire");
			next_lvl_effects = Util.generatePlaylist(fileRescs, "fx_lvl");
			
		}
	}

}
