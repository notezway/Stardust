package umiker9.stardust2d.graphics.lwjgl2;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

public class Texture2D extends Texture {

    public Texture2D() {
        super(GL_TEXTURE_2D);
    }

    public Texture2D(int id) {
        super(id, GL_TEXTURE_2D);
    }

    public int getWidth() {
        return getTextureData().getImageData().getWidth();
    }

    public int getHeight() {
        return getTextureData().getImageData().getHeight();
    }
}
