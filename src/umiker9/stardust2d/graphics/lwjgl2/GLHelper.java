package umiker9.stardust2d.graphics.lwjgl2;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GLHelper {
    public static FloatBuffer wrapDirectBuffer(float... data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static FloatBuffer wrapDirectBuffer(float[] data, int offset, int size) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(size);
        buffer.put(data, offset, size);
        buffer.flip();
        return buffer;
    }

    public static IntBuffer wrapDirectBuffer(int... data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static IntBuffer wrapDirectBuffer(int[] data, int offset, int size) {
        IntBuffer buffer = BufferUtils.createIntBuffer(size);
        buffer.put(data, offset, size);
        buffer.flip();
        return buffer;
    }

    public static ByteBuffer wrapDirectBuffer(byte... data) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static ByteBuffer wrapDirectBuffer(byte[] data, int offset, int size) {
        ByteBuffer buffer = BufferUtils.createByteBuffer(size);
        buffer.put(data, offset, size);
        buffer.flip();
        return buffer;
    }
}
