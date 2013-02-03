package com.mmm.ztp.counter;

import javax.microedition.khronos.opengles.GL10;
import texample.GLText;

import android.content.Context;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;

/*
 * Klasa opisująca licznik - wzorzec singleton
 */
public final class Counter implements Drawable {
	boolean loaded = false;
	long stan = 0;
	long chash = 0;
	private GLText glText;
	private static Counter instancja = null;

	public synchronized static Counter getInstance() {
		if (instancja == null) // późna inicjalizacja
			instancja = new Counter();
		return instancja;
	}
	public synchronized void add(long points) {
		this.stan += points;
	}
	public synchronized void addCash(long cash) {
		this.chash += cash;
	}
	public synchronized void substractCash(long cash) {
		this.chash -= cash;
	}
	public synchronized void reset() {
		this.stan = 0;
		this.chash = 0;
	}
	public synchronized long get() {
		return this.stan;
	}
	public synchronized long getCash() {
		return this.chash;
	}
	@Override
	public void draw(GL10 gl) {
		// gl.glEnable( GL10.GL_TEXTURE_2D );
		// gl.glEnable( GL10.GL_BLEND );
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		glText.begin(1.0f, 1.0f, 1.0f, 1.0f);
		glText.draw("Score: " + String.valueOf(get()), 0, 760);
		glText.end();
		glText.begin(1.0f, 1.0f, 0.0f, 1.0f);
		glText.draw("Cash: " + String.valueOf(getCash()), 175, 760);
		glText.end();
	}
	@Override
	public void load(GL10 gl, Context context) {
		glText = new GLText(gl, context.getAssets());
		glText.load("counter-font.ttf", 24, 2, 2);
		this.loaded = true;
	}
	@Override
	public void setSize(float size) {
	}
	@Override
	public float getSize(float size) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isLoaded() {
		return this.loaded;
	}
}
