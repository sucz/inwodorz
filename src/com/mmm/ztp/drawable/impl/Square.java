package com.mmm.ztp.drawable.impl;

import android.content.Context;

import com.mmm.ztp.drawable.Drawable;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: miroslaw
 * Date: 11/28/12
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Square implements Drawable {
    private final static Square s = new Square();
    float scale=1f;
    public Square() {
        float vertices[] = {
    			//Vertices according to faces
        		-1.0f, -1.0f, 1.0f, //Vertex 0
        		1.0f, -1.0f, 1.0f,  //v1
        		-1.0f, 1.0f, 1.0f,  //v2
        		1.0f, 1.0f, 1.0f   //v3
        };
        byte maxColor = (byte) 255;
        byte colors[] = //3
                {
                        maxColor, maxColor,
                        0, maxColor,
                        0,
                        maxColor, maxColor, maxColor,
                        0,
                        0,
                        0, maxColor,
                        maxColor,
                        0, maxColor, maxColor
                };
        byte indices[] = //4
            {
        		0,1,3, 
        		0,3,2
            };
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4); //5
        vbb.order(ByteOrder.nativeOrder());
        mFVertexBuffer = vbb.asFloatBuffer();
        mFVertexBuffer.put(vertices);
        mFVertexBuffer.position(0);
        mColorBuffer = ByteBuffer.allocateDirect(colors.length);
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public static Square getSquareTemplate() {
        return s;
    }

    @Override
    public void draw(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_ALPHA_BITS);
    	
        gl.glFrontFace(GL11.GL_CW);
        gl.glVertexPointer(3, GL11.GL_FLOAT, 0, mFVertexBuffer);
        gl.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 0, mColorBuffer);
        gl.glScalef(scale, scale, 1f);
        gl.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_BYTE, mIndexBuffer);
        gl.glFrontFace(GL11.GL_CCW);
        
		gl.glDisableClientState(GL10.GL_ALPHA_BITS);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

    private FloatBuffer mFVertexBuffer;
    private ByteBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;

	@Override
	public void load(GL10 gl, Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScale(float scale) {
		this.scale=scale;
		
	}

	@Override
	public void setSize(float size) {
		// TODO Auto-generated method stub
		
	}
}