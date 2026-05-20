package com.example.myapplication;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class BouncyCubeRenderer implements GLSurfaceView.Renderer {

    public static final int LIGHT = GL10.GL_LIGHT0;

    private Cube cube;
    private float angle = 0f;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glEnable(GL10.GL_DEPTH_TEST);

        initLighting(gl);

        cube = new Cube();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {

        gl.glViewport(0, 0, w, h);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        float ratio = (float) w / h;
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 20);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -8);
        gl.glRotatef(angle, 1, 1, 0);

        angle += 1f;

        cube.draw(gl);
    }

    private void initLighting(GL10 gl) {

        float[] green = {0f, 1f, 0f, 1f};
        float[] position = {0f, 5f, 5f, 1f};

        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(LIGHT);

        gl.glLightfv(LIGHT, GL10.GL_DIFFUSE, makeFloatBuffer(green));
        gl.glLightfv(LIGHT, GL10.GL_POSITION, makeFloatBuffer(position));

        gl.glShadeModel(GL10.GL_SMOOTH);
    }

    public static java.nio.FloatBuffer makeFloatBuffer(float[] arr) {

        java.nio.ByteBuffer bb =
                java.nio.ByteBuffer.allocateDirect(arr.length * 4);

        bb.order(java.nio.ByteOrder.nativeOrder());

        java.nio.FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);

        return fb;
    }
}