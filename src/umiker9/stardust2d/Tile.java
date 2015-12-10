package umiker9.stardust2d;

import umiker9.stardust2d.graphics.lwjgl2.Texture2D;

/**
 * Created by miker9 on 10/12/2015.
 */
public class Tile {
    private Texture2D texture;
    private double s1;
    private double t1;
    private double s2;
    private double t2;

    public Tile(Texture2D texture, double s1, double t1, double s2, double t2) {
        this.texture = texture;
        this.s1 = s1;
        this.t1 = t1;
        this.s2 = s2;
        this.t2 = t2;
    }

    public Tile(Texture2D texture) {
        this(texture, 0, 0, 1, 1);
    }

    public Texture2D getTexture() {
        return texture;
    }

    public void setTexture(Texture2D texture) {
        this.texture = texture;
    }

    public double getS1() {
        return s1;
    }

    public void setS1(double s1) {
        this.s1 = s1;
    }

    public double getT1() {
        return t1;
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    public double getS2() {
        return s2;
    }

    public void setS2(double s2) {
        this.s2 = s2;
    }

    public double getT2() {
        return t2;
    }

    public void setT2(double t2) {
        this.t2 = t2;
    }

    public double getWidth() {
        return s2 - s1;
    }

    public double getHeight() {
        return t2 - t1;
    }

    public double getPixelWidth() {
        return getWidth() * texture.getWidth();
    }

    public double getPixelHeight() {
        return getHeight() * texture.getHeight();
    }


}
