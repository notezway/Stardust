package umiker9.stardust2d.graphics.lwjgl2;

import org.lwjgl.opengl.GL13;

import static org.lwjgl.opengl.GL11.*;

public class Texture implements GLObject {
    public static final Texture none = new Texture2D(0);

    protected int id;
    protected int target;
    protected TextureData textureData;
    protected boolean initialized;

    public Texture(int target) {
        this(glGenTextures(), target);
    }

    public Texture(int id, int target) {
        this.id = id;
        this.target = target;
    }

    public void bind() {
        bind(GL13.GL_TEXTURE0);
    }

    public void bind(int bank) {
        GL13.glActiveTexture(bank);
        glBindTexture(target, id);
    }


    @Override
    public void destroy() {
        setInitialized(false);
        glDeleteTextures(id);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public TextureData getTextureData() {
        return textureData;
    }

    public void setTextureData(TextureData textureData) {
        this.textureData = textureData;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
