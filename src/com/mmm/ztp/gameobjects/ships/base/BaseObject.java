package com.mmm.ztp.gameobjects.ships.base;


import javax.microedition.khronos.opengles.GL10;

import texample.GLText;
import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import com.mmm.ztp.drawable.Drawable;
import com.mmm.ztp.drawable.Hitable;
import com.mmm.ztp.drawable.Pointable;
import com.mmm.ztp.event.GameEventBus;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventListener;
import com.mmm.ztp.event.destroyobjectevent.DestroyObjectEventObject;
import com.mmm.ztp.event.playerfireevent.PlayerFireEventObject;
import com.mmm.ztp.movment.Movement;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseObject implements Drawable, Hitable, Pointable {
    /**
     * Id obiektu
     */
	boolean loaded=false;
	protected GLText glText;
	protected float speed=6.4f;
	protected int hp=100;
	protected int maxHp = 125;
	
    public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
		if(this.move!=null)
			this.move.setSpeed(speed);
	}
	private static int id = Integer.MIN_VALUE;
    protected float size=128f;
    private static float prec = 0.3f;
    protected int points;
    
    
    /**
     * Strategia ruchu obiektu
     */
    protected Movement move=null;

    /**
     * "Wygląd obiektu"
     */
    protected Drawable objectRep;

    /**
     * Pozycja obiektu  (x,y,z)
     */
    protected float coordinates[] = {0, 0, 0};

    /**
     * Punkty życia obiektu
     */
    protected float hitPoints;

    public abstract void onDestruct();

    public float[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(float c1, float c2, float c3) {
		this.coordinates[0]=c1;
		this.coordinates[1]=c2;
	}

	protected BaseObject() {
        id++;
    }

    @Override
    public boolean hittest(BaseObject object) {
    	//TODO poprawić hittest
    	//object - statek alienow
        
//        PointF objBottomLeft = new PointF(object.coordinates[0], object.coordinates[1]);
//        PointF objTopRight = new PointF(object.coordinates[0]+object.size, object.coordinates[1]+object.size);
//        
//        PointF thisBottomLeft = new PointF(this.coordinates[0], this.coordinates[1]);
//        PointF thisTopRight = new PointF(this.coordinates[0]+this.size, this.coordinates[1]+this.size);
        
        PointF objBottomRight = new PointF(object.coordinates[0]+object.size, object.coordinates[1]);
        PointF objTopLeft = new PointF(object.coordinates[0], object.coordinates[1]+object.size);
        
        PointF thisBottomRight = new PointF(this.coordinates[0]+this.size, this.coordinates[1]);
        PointF thisTopLeft = new PointF(this.coordinates[0], this.coordinates[1]+this.size);
        
        if(thisTopLeft.x >= objBottomRight.x || thisBottomRight.x <= objTopLeft.x 
        		|| thisTopLeft.y <= objBottomRight.y || thisBottomRight.y >= objTopLeft.y)
        	return false;
        else
        	return true;

    }

    /**
     * Ustawia typ ruchu jakim się porusza dany obiekt
     *
     * @param move implementacja ruchu
     */
    public void setMovement(Movement move) {
    	move.setSpeed(this.speed);
        this.move = move;
        
    }

    /**
     * Akcje wykonywane przed wyrenderowaniem obiektu
     *
     * @param gl
     */
    protected void onPreRender(GL10 gl) {

    }

    /**
     * Akcje wykonywane po wyrenderowaniu obiektu
     *
     * @param gl
     */
    protected void onPostRender(GL10 gl) {

    }

    public int getId() {
        return id;
    }


    @Override
    public final void draw(GL10 gl) {
    	 
        gl.glMatrixMode(GL10.GL_MODELVIEW); //6 - wybieramy macierz modeli

        onPreRender(gl);
        
        gl.glTranslatef(coordinates[0], coordinates[1], 0); //8 -- przesuwamy do koordynatow
		
        objectRep.draw(gl); //rysujemy
        
        gl.glTranslatef((-1)*coordinates[0], (-1)*coordinates[1], 0); //8 -- przesuwamy z powrotem
        toDraw(gl);
        
        onPostRender(gl);
        
    }

    protected void toDraw(GL10 gl){}
    /**
     * Metda wywoływana przy kolizji z objektem w argumencie
     *
     * @param object obiekt z którym nastąpiła kolizja
     */
    public void onObjectsCollision(BaseObject object)
    {
    	this.decreaseHP(10);
    	Log.d("onObjectsCollsion -> Object HP: ", ""+this.hp);
    	if(this.hp == 0)
    	{
    		GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
    		Log.d("onObjectsCollsion", "Object dead");
    		this.onDestruct();
    	}
    	
    }
    
    public void onObjectsCleanup()
    {
    	Log.d("BaseObject","Czyszczę obiekt na pozycji "+this.coordinates[0]+" : "+this.coordinates[1]);
    	GameEventBus.getInstance().fireEvent(DestroyObjectEventListener.class, new DestroyObjectEventObject(this));
    }
    
    public void load(GL10 gl, Context context)
    {
    	this.loaded=true;
    	this.glText=new GLText(gl,context.getAssets());
    	glText.load("counter-font.ttf", 24, 2, 2);
    	this.objectRep.load(gl,context);
    }

	public void fire(PlayerFireEventObject t) {
	}
	
	@Override
	public float getSize(float size) {
		return this.size;
		
	}
	
	@Override
	public void setSize(float size) {
		this.size=size;
		if(this.move!=null)
			this.move.setSize(size);
		if(this.objectRep!=null)
			objectRep.setSize(size);
		
	}
	public long getPoints()
	{
		return points;
	}
	public void setPoints(int points)
	{
		this.points=points;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void increaseHP(int value)
	{
		this.hp += value;
		if(this.hp > this.maxHp)
		{
			this.hp = this.maxHp;
		}
	}
	
	public void decreaseHP(int value)
	{
		this.hp -= value;
		if(this.hp < 0)
		{
			this.hp = 0;
			//TO DO - DEAD
		}
	}
	public boolean isLoaded()
	{
		return this.loaded;
	}
	
	public Drawable getObjectRep()
	{
		return this.objectRep;
	}
	public void serObjectRep(Drawable obj)
	{
		this.objectRep=obj;
	}
}
