package umiker9.stardust2d.graphics.lwjgl2;

import static org.lwjgl.opengl.GL11.*;

public class TextureParameters {
    public static final TextureParameters defaultParameters = new TextureParameters();
    public int MIN_FILTER = GL_LINEAR;
    public int MAG_FILTER = GL_LINEAR;
    public int MIPMAP_BASE_LEVEL;
    public int MIPMAP_MAX_LEVEL;
    public boolean GENERATE_MIPMAPS;
    public int WRAP_S = GL_REPEAT;
    public int WRAP_T = GL_REPEAT;
    public int WRAP_R = GL_REPEAT;
    public int BORDER;
    public float BORDER_R;
    public float BORDER_G;
    public float BORDER_B;
    public float BORDER_A;
    public int INTERNAL_FORMAT = GL_RGBA8;
    public float MAX_ANISOTROPY = 1;

    public TextureParameters() {

    }

    public TextureParameters(TextureParameters sample) {
        MIN_FILTER = sample.MIN_FILTER;
        MAG_FILTER = sample.MAG_FILTER;
        MIPMAP_BASE_LEVEL = sample.MIPMAP_BASE_LEVEL;
        MIPMAP_MAX_LEVEL = sample.MIPMAP_MAX_LEVEL;
        GENERATE_MIPMAPS = sample.GENERATE_MIPMAPS;
        WRAP_S = sample.WRAP_S;
        WRAP_T = sample.WRAP_T;
        WRAP_R = sample.WRAP_R;
        BORDER = sample.BORDER;
        BORDER_R = sample.BORDER_R;
        BORDER_G = sample.BORDER_G;
        BORDER_B = sample.BORDER_B;
        BORDER_A = sample.BORDER_A;
        INTERNAL_FORMAT = sample.INTERNAL_FORMAT;
        MAX_ANISOTROPY = sample.MAX_ANISOTROPY;
    }

    public TextureParameters setMinFilter(int MIN_FILTER) {
        this.MIN_FILTER = MIN_FILTER;
        return this;
    }

    public TextureParameters setMagFilter(int MAG_FILTER) {
        this.MAG_FILTER = MAG_FILTER;
        return this;
    }

    public TextureParameters setMipmapMaxLevel(int MIPMAP_MAX_LEVEL) {
        this.MIPMAP_MAX_LEVEL = MIPMAP_MAX_LEVEL;
        return this;
    }

    public TextureParameters setMipmapBaseLevel(int MIPMAP_BASE_LEVEL) {
        this.MIPMAP_BASE_LEVEL = MIPMAP_BASE_LEVEL;
        return this;
    }

    public TextureParameters setGenerateMipmaps(boolean GENERATE_MIPMAPS) {
        this.GENERATE_MIPMAPS = GENERATE_MIPMAPS;
        return this;
    }

    public TextureParameters setWrapS(int WRAP_S) {
        this.WRAP_S = WRAP_S;
        return this;
    }

    public TextureParameters setWrapT(int WRAP_T) {
        this.WRAP_T = WRAP_T;
        return this;
    }

    public TextureParameters setWrapR(int WRAP_R) {
        this.WRAP_R = WRAP_R;
        return this;
    }

    public TextureParameters setBorder(int BORDER) {
        this.BORDER = BORDER;
        return this;
    }

    public TextureParameters setBorderR(float BORDER_R) {
        this.BORDER_R = BORDER_R;
        return this;
    }

    public TextureParameters setBorderG(float BORDER_G) {
        this.BORDER_G = BORDER_G;
        return this;
    }

    public TextureParameters setBorderB(float BORDER_B) {
        this.BORDER_B = BORDER_B;
        return this;
    }

    public TextureParameters setBorderA(float BORDER_A) {
        this.BORDER_A = BORDER_A;
        return this;
    }

    public TextureParameters setInternalFormat(int INTERNAL_FORMAT) {
        this.INTERNAL_FORMAT = INTERNAL_FORMAT;
        return this;
    }

    public TextureParameters setMaxAnisotropy(float MAX_ANISOTROPY) {
        this.MAX_ANISOTROPY = MAX_ANISOTROPY;
        return this;
    }

}
