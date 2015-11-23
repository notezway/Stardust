package umiker9.stardust2d.graphics;

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
        this.r = r + c.r;
        this.g = g + c.g;
        this.b = b + c.b;
        this.a = a + c.a;
        return this;
    }

    public static int getData(int r, int g, int b, int a) {
        return r << 24 | g << 16 | b << 8 | a;
    }

    public static int getData(double r, double g, double b, double a) {
        return (int)(r * 255) << 24 | (int)(g * 255) << 16 | (int)(b * 255) << 8 | (int)(a * 255);
    }

    public static int[] getIntComponents(int color) {
        return new int[] {
                (color & 0xFF000000) >> 24,
                (color & 0x00FF0000) >> 16,
                (color & 0x0000FF00) >> 8,
                (color & 0x000000FF)
        };
    }

    public static double[] getComponents(int color) {
        return new double[] {
                ((color & 0xFF000000) >> 24) /255.,
                ((color & 0x00FF0000) >> 16) /255.,
                ((color & 0x0000FF00) >> 8)  /255.,
                ((color & 0x000000FF))       /255.
        };
    }

    public static final Color WHITE = new Color(1., 1., 1.);
    public static final Color BLACK = new Color(0., 0., 0.);
    public static final Color RED = new Color(1., 0., 0.);
    public static final Color GREEN = new Color(0., 1., 0.);
    public static final Color BLUE = new Color(0., 0., 1.);
}
