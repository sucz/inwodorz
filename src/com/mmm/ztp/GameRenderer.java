package com.mmm.ztp;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import javax.microedition.khronos.opengles.GL10;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.drawable.impl.Cube;
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
import com.mmm.ztp.event.playermoveevent.PlayerMoveEventListener;
import com.mmm.ztp.gameobjects.ships.EnemyShip;
import com.mmm.ztp.gameobjects.ships.PlayersShip;
import com.mmm.ztp.gameobjects.ships.base.BaseObject;
import com.mmm.ztp.movment.FullCosinusMovement;
import com.mmm.ztp.movment.FullSinusMovement;
import com.mmm.ztp.movment.HorizontalControls;
import com.mmm.ztp.movment.MinusCosinusMovement;
import com.mmm.ztp.movment.MinusSinusMovement;
import com.mmm.ztp.movment.Movement;
import com.mmm.ztp.movment.SimpleMove;
import com.mmm.ztp.movment.UserMove;

import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.Build;

/**
 * Implementacja wyświetlania 
 * @author mazdac
 *
 */
public class GameRenderer implements Renderer,AddPlayersDrawableEventListener, DestroyObjectEventListener, AddAlienDrawableEventListener, AddAlienProjectileEventListener
{
	
	
	private final LinkedBlockingDeque<BaseObject> playersObjects = new LinkedBlockingDeque<BaseObject>();
    private final LinkedBlockingDeque<BaseObject> alienObjects = new LinkedBlockingDeque<BaseObject>();
    private final LinkedBlockingDeque<BaseObject> alienProjectiles = new LinkedBlockingDeque<BaseObject>(); //pociski obcych
    private TexturedObject texturedObject;
    LinkedList<Drawable> tempObj; //pociski
    private PlayersShip ship;
    private TexturedObject sc;
    
    private Cube kosteczka;
	private float xrot;				//X Rotation ( NEW )
	private float yrot;				//Y Rotation ( NEW )
	private float zrot;				//Z Rotation ( NEW )
	
	
	private Context context;
	
	public GameRenderer(Context context) {
		this.context = context;
		
		kosteczka = new Cube();
		ship=new PlayersShip(new TexturedObject(R.drawable.ship));
		ship.setCoordinates(0, -9, 0);
		ship.setMovement(new UserMove());
		
		alienObjects.add(new EnemyShip(new TexturedObject(R.drawable.enemy_scout)));
		alienObjects.getLast().setCoordinates(0, 0, 0);
		alienObjects.getLast().setMovement(new FullSinusMovement());
		alienObjects.add(new EnemyShip(new TexturedObject(R.drawable.enemy_scout)));
		alienObjects.getLast().setCoordinates(0, 3, 0);
		alienObjects.getLast().setMovement(new FullCosinusMovement());
		alienObjects.add(new EnemyShip(new TexturedObject(R.drawable.enemy_scout)));
		alienObjects.getLast().setCoordinates(0, 6, 0);
		alienObjects.getLast().setMovement(new FullSinusMovement());
		/*
		
		alienObjects.getLast().setCoordinates(0, 8, 0);
        alienObjects.add(new EnemyShip(new TexturedObject(R.drawable.enemy_scout)));
        alienObjects.getLast().setCoordinates(0, 0, 0);
        */
		
	}


	/**
	 * Here we do our drawing
	 */
	public void onDrawFrame(GL10 gl) {

		//Clear Screen And Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();					//Reset The Current Modelview Matrix
		
		//Drawing
		gl.glTranslatef(0.0f, 0.0f, -5f);		//Move 5 units into the screen
		gl.glScalef(0.2f, 0.2f, 0.2f); 			//Scale the Cube to 80 percent, otherwise it would be too large for the screen
				
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
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl10, int i, int i1) {
		/*
        gl10.glViewport(-i, -i1, i * 2, i1 * 2);
        float ratio = (float) i / i1;
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        gl10.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        */
		//TODO poprawić ustawianie widoku, to na górze nie działa poprawnie - ekran cały czarny - to na dole to lekcja 6 nehe for android
		
		gl10.glViewport(0, 0, 480, 800);
		gl10.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl10.glLoadIdentity(); 					//Reset The Projection Matrix
		GLU.gluPerspective(gl10, 45.0f, (float)i / (float)i1, 0.1f, 100.0f);		//Calculate The Aspect Ratio Of The Window
		gl10.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl10.glLoadIdentity(); 					//Reset The Modelview Matrix
    }

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
        kosteczka.loadGLTexture(gl, context);
        for(Drawable alien: alienObjects)
        	alien.load(gl, context);
        ship.load(gl, context); //proxy

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
    public void onUserFire() {
        //GameEventBus.getInstance().fireEvent(PlayerFireEventListener.class, null);
    }

    @Override
    public void addDrawable(AddAlienDrawableEventObject object) {
        alienObjects.add(object.getDrawable());
    }

    @Override
    public void addDrawable(AddAlienProjectileEventObject object) {
        alienProjectiles.add(object.getDrawable());
    }
}