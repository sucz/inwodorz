package com.mmm.ztp;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.drawable.impl.GlareObj;
import com.mmm.ztp.drawable.impl.Square;
import com.mmm.ztp.drawable.impl.TexturedObject;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.addAlienProjectioleEvent.AddAlienProjectileEventHandler;
import com.mmm.ztp.event.addAlienProjectioleEvent.AddAlienProjectileEventListener;
import com.mmm.ztp.event.addAlienProjectioleEvent.AddAlienProjectileEventObject;
import com.mmm.ztp.event.aliendrawableevent.AddAlienDrawableEventHandler;
import com.mmm.ztp.event.aliendrawableevent.AddAlienDrawableEventListener;
import com.mmm.ztp.event.aliendrawableevent.AddAlienDrawableEventObject;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectHandler;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventHandler;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventListener;
import com.mmm.ztp.event.drawableevent.AddPlayersDrawableEventObject;
import com.mmm.ztp.gameobjects.ships.EnemyShip;
import com.mmm.ztp.gameobjects.ships.PlayersShip;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.FullCosinusMovement;
import com.mmm.ztp.movment.FullSinusMovement;
import com.mmm.ztp.movment.UserMove;

/**
 * Implementacja wyświetlania 
 * @author mazdac
 *
 */
public class GameRenderer implements Renderer,AddPlayersDrawableEventListener, DestroyObjectEventListener, AddAlienDrawableEventListener, AddAlienProjectileEventListener
{
	
	float tmp;
	private final LinkedBlockingDeque<BaseObject> playersObjects = new LinkedBlockingDeque<BaseObject>();
    private final LinkedBlockingDeque<BaseObject> alienObjects = new LinkedBlockingDeque<BaseObject>();
    private final LinkedBlockingDeque<BaseObject> alienProjectiles = new LinkedBlockingDeque<BaseObject>(); //pociski obcych
    LinkedList<Drawable> tempObj; //pociski
    private PlayersShip ship;
    Square kwa=new Square();
	
	
	private Context context;
	
	public GameRenderer(Context context) {
		this.context = context;
		
		ship=new PlayersShip(new TexturedObject(R.drawable.ship));
		ship.setMovement(new UserMove());
		ship.setCoordinates(0, 0, 0);

		
		alienObjects.add(new EnemyShip(new TexturedObject(R.drawable.enemy_scout)));
		alienObjects.getLast().setCoordinates(240, 256, 0);
		alienObjects.getLast().setMovement(new FullCosinusMovement());
		//alienObjects.add(new EnemyShip(new TexturedObject(R.drawable.enemy_scout)));
		//alienObjects.getLast().setCoordinates(240, 512, 0);
		//alienObjects.getLast().setMovement(new FullSinusMovement());
		
		
		/*
		
		alienObjects.getLast().setCoordinates(0, 8, 0);
        alienObjects.add(new EnemyShip(new TexturedObject(R.drawable.enemy_scout)));
        alienObjects.getLast().setCoordinates(0, 0, 0);
        */
		
	}


	public void onDrawFrame(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();				

		//Rysowanie
		ship.draw(gl);

		for (Drawable d : playersObjects) {
            d.draw(gl);
        }

        for (Drawable d : alienObjects) {
            d.draw(gl);
        }

        for (Drawable d : alienProjectiles) {
            d.draw(gl);
        }
        hittest();
		
	}

	/**
	 * Implementacja metody Renderera, wykonywana przy zmianie powierzchni, obróceniu ekranu.
	 * Tutaj ustawiam widok jako 2d (Orthof)
	 */
	public void onSurfaceChanged(GL10 gl10, int w, int h) {
		gl10.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl10.glLoadIdentity(); 					//Reset The Projection Matrix
		gl10.glOrthof(0, w, 0, h, -1, 1);
		gl10.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl10.glLoadIdentity(); 					//Reset The Modelview Matrix
    }

	/**
	 * Implementacja metody Renderera, wykonywana przy tworzeniu powierzchni
	 * Tutaj ładujemy tekstury.
	 */
	@Override
	public void onSurfaceCreated(GL10 gl,
			javax.microedition.khronos.egl.EGLConfig config) {
		
		GameEventBus.getInstance().attachToEventBus(AddPlayersDrawableEventListener.class,
                new AddPlayersDrawableEventHandler((AddPlayersDrawableEventListener) this));

        GameEventBus.getInstance().attachToEventBus(DestroyObjectEventListener.class,
                new DestroyObjectHandler(this));

        GameEventBus.getInstance().attachToEventBus(AddAlienDrawableEventListener.class,
                new AddAlienDrawableEventHandler(this));

        GameEventBus.getInstance().attachToEventBus(AddAlienProjectileEventListener.class,
                new AddAlienProjectileEventHandler(this));

        //Tutaj ładujemy tekstury
        for(Drawable alien: alienObjects)
        	alien.load(gl, context);
        
        ship.load(gl, context); //proxy
		GlareObj.getObj().load(gl, context);
        

		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Fast Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST); 
		
	}

	private void hittest() {

        for (BaseObject playersObject : playersObjects) {
            for (BaseObject object : alienObjects) {
                if (playersObject.hittest(object))
                    object.onObjectsCollision(playersObject);
            }
        }

        for (BaseObject object : alienObjects) {
            if (ship.hittest(object))
                object.onObjectsCollision(ship);
        }

        for (BaseObject alienProjectile : alienProjectiles) {
            if (alienProjectile.hittest(ship))
                ship.onObjectsCollision(alienProjectile);
        }
    }

    @Override
    public void destroyObject(DestroyObjectEventObject object) {
        playersObjects.remove(object.getToDestroy());
        alienObjects.remove(object.getToDestroy());
        alienProjectiles.remove(object.getToDestroy());
    }

    @Override
    public void addDrawable(AddPlayersDrawableEventObject object) {
        playersObjects.add(object.getT());
    }

    /**
     *
     */

    @Override
    public void addDrawable(AddAlienDrawableEventObject object) {
        alienObjects.add(object.getDrawable());
    }

    @Override
    public void addDrawable(AddAlienProjectileEventObject object) {
        alienProjectiles.add(object.getDrawable());
    }
}