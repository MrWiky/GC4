package com.example.myapplication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube {

    private FloatBuffer vertexBuffer;
    private FloatBuffer normalBuffer;

    private float[] vertices = {
            -1, -1,  1,   1, -1,  1,   1,  1,  1,  -1,  1,  1,
            -1, -1, -1,  -1,  1, -1,   1,  1, -1,   1, -1, -1
    };

    private float[] normals = {
            0,0,1,  0,0,1,  0,0,1,  0,0,1,
            0,0,-1, 0,0,-1, 0,0,-1, 0,0,-1
    };

    public Cube() {

        ByteBuffer vb = ByteBuffer.allocateDirect(vertices.length * 4);
        vb.order(ByteOrder.nativeOrder());
        vertexBuffer = vb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer nb = ByteBuffer.allocateDirect(normals.length * 4);
        nb.order(ByteOrder.nativeOrder());
        normalBuffer = nb.asFloatBuffer();
        normalBuffer.put(normals);
        normalBuffer.position(0);
    }

    public void draw(GL10 gl) {

        float[] greenMat = {0f, 1f, 0f, 1f};

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK,
                GL10.GL_DIFFUSE,
                BouncyCubeRenderer.makeFloatBuffer(greenMat));

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 8);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }
}