package com.mmm.ztp.drawable.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import com.mmm.ztp.drawable.Drawable;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Author: miroslaw
 * Date: 12/1/12
 * Time: 1:17 PM
 */
public class GlareObj implements Drawable {
    private static int[] textures = new int[1];
    private static GlareObj obj = new GlareObj();
    private static FloatBuffer mFVertexBuffer;
    private static ByteBuffer mColorBuffer;
    private static ByteBuffer mIndexBuffer;
    private static FloatBuffer mTextureBuffer;

    public static GlareObj getObj() {
        return obj;
    }

    private GlareObj() {
        float textureCoords[] =
                {
                        0.0f, 0.0f,
                        1.0f, 0.0f,
                        0.0f, 1.0f,
                        1.0f, 1.0f
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
                        0, 3, 1,
                        0, 2, 3
                };
        ByteBuffer vbb = ByteBuffer.allocateDirect(textureCoords.length * 4); //5
        vbb.order(ByteOrder.nativeOrder());
        mFVertexBuffer = vbb.asFloatBuffer();
        mFVertexBuffer.put(textureCoords);
        mFVertexBuffer.position(0);
        mColorBuffer = ByteBuffer.allocateDirect(colors.length);
        mColorBuffer.put(colors);
        mColorBuffer.position(0);
        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);

        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoords.length * 4);
        tbb.order(ByteOrder.nativeOrder());
        mTextureBuffer = tbb.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);
    }

    @Override
    public void draw(GL10 gl) {
    	/*
        gl.glScalef(1f, 1f, 0);
        gl.glTranslatef(-0.1f, -0.1f, 0);

        gl.glVertexPointer(2, GL11.GL_FLOAT, 0, mFVertexBuffer);

        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]); //4
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer); //5

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        */
    }


    public static int createTexture(GL10 gl, Context contextRegf, int resource) {
        Bitmap image = BitmapFactory.decodeResource(contextRegf.getResources(),
                resource);
        gl.glGenTextures(1, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, image, 0); // 4
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, // 5a
                GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, // 5b
                GL10.GL_LINEAR
        );
        image.recycle();
        return resource;
    }

	@Override
	public void load(GL10 gl, Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScale(float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSize(float size) {
		// TODO Auto-generated method stub
		
	}
}
