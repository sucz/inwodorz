package com.mmm.ztp.services;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.content.Context;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.mmm.ztp.R;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.audioEvents.AudioEventHandler;
import com.mmm.ztp.event.audioEvents.AudioEventListener;
import com.mmm.ztp.event.audioEvents.AudioEventObject;
import com.mmm.ztp.util.Util;

public class AudioService2 extends Thread implements Service,
		AudioEventListener, SoundPool.OnLoadCompleteListener {

	Context context; // używany do znalezienia ścieżek
	SoundPool player;
	AsyncPlayer musicPlayer;
	boolean loaded = false;
	boolean paused = false;
	Random rand = new Random(Calendar.getInstance().getTimeInMillis());

	List<Integer> dmg_effects;
	List<Integer> enemy_fire_effects;
	List<Integer> fire_effects;
	List<Integer> next_lvl_effects;
	List<Integer> music;
	ConcurrentLinkedQueue<Integer> waiting_events;

	public AudioService2(Context ctx) {
		this.context = ctx;

		player = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		player.setOnLoadCompleteListener(this);
		musicPlayer=new AsyncPlayer("AudioService2->AsyncPlayer");
		GameEventBus.getInstance().attachToEventBus(AudioEventListener.class,
				new AudioEventHandler(this));
		waiting_events = new ConcurrentLinkedQueue<Integer>();
	}

	@Override
	public void run() {
		try {
			while (true) {
				while (paused) // jesli wlaczana pauza
				{
					synchronized (this) {
						this.wait();
					}
				}

				if (!loaded) {
					generateLists();
					List<Integer> tmp = new LinkedList<Integer>();
					for (int i : dmg_effects) {
						tmp.add(player.load(context, i, 1));
					}
					dmg_effects.clear();
					dmg_effects.addAll(tmp);
					tmp.clear();
					for (int i : enemy_fire_effects) {
						tmp.add(player.load(context, i, 1));
					}
					enemy_fire_effects.clear();
					enemy_fire_effects.addAll(tmp);
					tmp.clear();
					for (int i : next_lvl_effects) {
						tmp.add(player.load(context, i, 1));
					}
					next_lvl_effects.clear();
					next_lvl_effects.addAll(tmp);
					tmp.clear();
					for (int i : fire_effects) {
						tmp.add(player.load(context, i, 1));
					}
					fire_effects.clear();
					fire_effects.addAll(tmp);
					tmp.clear();
					musicPlayer.play(context, Util.generateURI(music.get(0)), true, AudioManager.STREAM_MUSIC);
					this.loaded = true;
					this.paused = true;
				}

				synchronized (this) {
					this.wait();

				}
				while (this.waiting_events.size() > 0)
					player.play(this.waiting_events.poll(), 1, 1, 0, 0, 1);
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onPause() {
		synchronized (this.player) {
			this.player.autoPause();
			synchronized(this.musicPlayer)
			{
				//this.musicPlayer.stop();
			}
		}
		this.paused = true;
	}

	@Override
	public synchronized void  onResume() {
		this.paused = false;
		synchronized (this.player) {
			this.player.autoResume();
			//musicPlayer.stop();
			//musicPlayer.play(context, Util.generateURI(music.get(0)), true, AudioManager.STREAM_MUSIC);
		}
		synchronized (this) {
			this.notify();
		}

	}

	@Override
	public void onAudioEvent(AudioEventObject obj) {
		if (!paused) {

			if (obj.getType() == AudioEventObject.TYPE_DMG) {
				int track = 0;
				if (dmg_effects.size() > 1)
					track = rand.nextInt(dmg_effects.size());
				this.waiting_events.add(dmg_effects.get(track));
			}
			if (obj.getType() == AudioEventObject.TYPE_ENEMY_FIRE) {
				int track = 0;
				if (enemy_fire_effects.size() > 1)
					track = rand.nextInt(enemy_fire_effects.size());
				this.waiting_events.add(enemy_fire_effects.get(track));

			}
			if (obj.getType() == AudioEventObject.TYPE_FIRE) {
				int track = 0;
				if (fire_effects.size() > 1)
					track = rand.nextInt(fire_effects.size());
				this.waiting_events.add(fire_effects.get(track));
			}
			if (obj.getType() == AudioEventObject.TYPE_NEXT_LVL) {
				int track = 0;
				if (next_lvl_effects.size() > 1)
					track = rand.nextInt(next_lvl_effects.size());
				this.waiting_events.add(next_lvl_effects.get(track));
			}
			synchronized (this) {
				this.notify();
			}
		}

	}

	private void generateLists() {

		Field[] fileRescs = R.raw.class.getFields();
		Log.d("length fileRescs", String.valueOf(fileRescs.length));
		if (fileRescs.length > 0) {
			dmg_effects = Util.generatePlaylist(fileRescs, "fx_dmg");
			enemy_fire_effects = Util.generatePlaylist(fileRescs, "fx_efire");
			fire_effects = Util.generatePlaylist(fileRescs, "fx_fire");
			next_lvl_effects = Util.generatePlaylist(fileRescs, "fx_lvl");
			music = Util.generatePlaylist(fileRescs, "m_");
		}
	}

	@Override
	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		this.paused = false;
		Log.d("AudioService2", "Ładowanie pliku" + sampleId + " w stanie:"
				+ status);
	}

}
