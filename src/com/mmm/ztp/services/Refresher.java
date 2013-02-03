package com.mmm.ztp.services;

import android.util.Log;

import com.mmm.ztp.GameEngine;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.base.SimpleEventImpl;
import com.mmm.ztp.event.tickEvents.TickEventListener;

/**
 * Klasa reprezentująća oddzielny wątek służący generacji redenerów oraz czasu (ticków).
 * @author mazdac
 *
 */
public class Refresher extends Thread implements Runnable {

	GameEngine refreshedItem;
	boolean isPaused = false;

	public Refresher(GameEngine item) {
		this.refreshedItem = item;
	}

	/**
	 * Implementacja interfejsu Runnable Funkcja co 16ms (60Hz) przerysowuje
	 * grafikę, robimy tak, gdyż mamy ustawiony tryb renderowania na żądanie
	 * (RENDERMODE_WHEN_DIRTY)
	 */
	@Override
	public void run() {

		do {
			if (isPaused) //jeśłi zmienna uśpienia ustawiona
				try {
					synchronized (this) {
						this.wait();  //uśnij
					}
				} catch (InterruptedException e1) {
					Log.d("Refresher", "Nie można zatrzymać rendereowania!");
					e1.printStackTrace();
				}
			refreshedItem.requestRender();
			GameEventBus.getInstance().tick(TickEventListener.class,
					new SimpleEventImpl()); //generuje "tick" do nasłuchujących tickerów
			try {
				Thread.sleep(16, 0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}

	/**
	 * Metoda pauzująca renderowanie
	 */
	public void onPause() {
		this.isPaused = true;
	}

	/**
	 * Metoda wznawiająca renderowanie
	 */
	public void onResume() {
		this.isPaused = false;
		synchronized (this) {
			this.notify();
		}

	}

}
