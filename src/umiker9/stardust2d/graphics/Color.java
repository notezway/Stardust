package umiker9.stardust2d.graphics;

import umiker9.stardust2d.math.MathUtil;

/**
 * Created by Notezway on 23.11.2015.
 */
public class Color {

    private double r, g, b, a;

    public Color(double r, double g, double b) {
        this(r, g, b, 1);
    }

    public Color(double r, double g, double b, double a) {
        set(r, g, b, a);
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(int r, int g, int b, int a) {
        set(r, g, b, a);
    }

    public Color(int data) {
        set(data);
    }

    public Color(long data) {
        set(data);
    }

    public Color set(double r, double g, double b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        return this;
    }

    public Color set(int r, int g, int b, int a) {
        this.r = r/255.;
        this.g = g/255.;
        this.b = b/255.;
        this.a = a/255.;
        return this;
    }

    public Color set(int data) {
        double[] comp = getComponents(data);
        this.r = comp[0];
        this.g = comp[1];
        this.b = comp[2];
        this.a = comp[3];
        return this;
    }

    public Color set(long data) {
        double[] comp = getComponents(data);
        this.r = comp[0];
        this.g = comp[1];
        this.b = comp[2];
        this.a = comp[3];
        return this;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double getA() {
        return a;
    }

    public Color setR(int r) {
        this.r = r;
        return this;
    }

    public Color setG(int g) {
        this.g = g;
        return this;
    }

    public Color setB(int b) {
        this.b = b;
        return this;
    }

    public Color setA(int a) {
        this.a = a;
        return this;
    }

    public Color mix(Color c) {
        Color ret =  new Color(
                MathUtil.clamp(0, r + c.r, 1),
                MathUtil.clamp(0, g + c.g, 1),
                MathUtil.clamp(0, b + c.b, 1),
                MathUtil.clamp(0, a + c.a, 1)
        );
        return ret;
    }

    public int toInt() {
        return  ((int)(r*0xFF)) << 24 |
                ((int)(g*0xFF)) << 16 |
                ((int)(b*0xFF)) << 8 |
                ((int)(a*0xFF));
    }

    public long toLong() {
        return  ((long)(r*0xFFFF)) << 48 |
                ((long)(g*0xFFFF)) << 32 |
                ((long)(b*0xFFFF)) << 16 |
                ((long)(a*0xFFFF));
    }

    public static double[] getComponents(int color) {
        return new double[] {
                ((color & 0xFF000000) >> 24) /(double)0xFF,
                ((color & 0x00FF0000) >> 16) /(double)0xFF,
                ((color & 0x0000FF00) >> 8)  /(double)0xFF,
                ((color & 0x000000FF))       /(double)0xFF
        };
    }

    public static double[] getComponents(long color) {
        return new double[] {
                ((color & 0xFFFF000000000000L) >> 48) /(double)0xFFFF,
                ((color & 0x0000FFFF00000000L) >> 32) /(double)0xFFFF,
                ((color & 0x00000000FFFF0000L) >> 16) /(double)0xFFFF,
                ((color & 0x000000000000FFFFL))       /(double)0xFFFF
        };
    }

    public static final Color WHITE = new Color(1., 1., 1.);
    public static final Color BLACK = new Color(0., 0., 0.);
    public static final Color RED = new Color(1., 0., 0.);
    public static final Color GREEN = new Color(0., 1., 0.);
    public static final Color BLUE = new Color(0., 0., 1.);
}
