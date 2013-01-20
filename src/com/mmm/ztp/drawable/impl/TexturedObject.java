package com.mmm.ztp.drawable.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.mmm.ztp.R;
import com.mmm.ztp.drawable.Drawable;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 11:40 AM
 */
public class TexturedObject implements Drawable {
    
	int res;
    /** The buffer holding the vertices */
	private FloatBuffer vertexBuffer;
	/** The buffer holding the texture coordinates */
	private FloatBuffer textureBuffer;
	/** The buffer holding the indices */
	private ByteBuffer indexBuffer;
	
    private float vertices[] = {
			//Vertices according to faces
    		-1.0f, -1.0f, 1.0f, //Vertex 0
    		1.0f, -1.0f, 1.0f,  //v1
    		-1.0f, 1.0f, 1.0f,  //v2
    		1.0f, 1.0f, 1.0f   //v3
    };
    float texture[] =
        {
                0.0f, 0.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f
        };
    byte indices[] = //4
        {
    		0,1,3, 
    		0,3,2
        };
    private int[] textures = new int[1];
    
    public TexturedObject(int res)
    {
        
    	//
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		//
		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);

		//
		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);
		this.res=res;
    	
    }
    public TexturedObject() {
        
    	//
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		//
		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);

		//
		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);
    }

    public void draw(GL10 gl) {
    	//Bind our only previously generated texture in this case
    			gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
    			gl.glEnable(GL10.GL_BLEND);    
    			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

    			
    			//Point to our buffers
    			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    			gl.glEnableClientState(GL10.GL_ALPHA_BITS);

    			//Set the face rotation
    			gl.glFrontFace(GL10.GL_CCW);
    			
    			//Enable the vertex and texture state
    			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
    			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
    			
    			//Draw the vertices as triangles, based on the Index Buffer information
    			gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);
    			
    			//Disable the client state before leaving
    			gl.glDisableClientState(GL10.GL_ALPHA_BITS);
    			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        
    }
    public void createTexture(GL10 gl, Context context)
    {
    	createTexture( gl,  context,  this.res);
    }

    public int createTexture(GL10 gl, Context context, int resource) {
    	//Get the texture from the Android resource directory
    			InputStream is = context.getResources().openRawResource(resource);
    			Bitmap bitmap = null;
    			try {
    				//BitmapFactory is an Android graphics utility for images
    				bitmap = BitmapFactory.decodeStream(is);

    			} finally {
    				//Always clear and close
    				try {
    					is.close();
    					is = null;
    				} catch (IOException e) {
    				}
    			}

    			//Generate one texture pointer...
    			gl.glGenTextures(1, textures, 0);
    			//...and bind it to our array
    			gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
    			
    			//Create Nearest Filtered Texture
    			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
    			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

    			//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
    			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
    			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
    			
    			//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
    			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    			
    			//Clean up
    			bitmap.recycle();
    			return resource;
    }
	@Override
	public void load(GL10 gl, Context context) {
		this.createTexture(gl, context);
		
	}
}
