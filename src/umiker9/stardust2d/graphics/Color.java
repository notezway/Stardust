package umiker9.stardust2d.graphics;

/**
 * Created by Notezway on 23.11.2015.
 */
public class Color {

    private int data;

    public Color(double r, double g, double b) {
        data = getData(r, g, b, 1);
    }

    public Color(int r, int g, int b) {
        set(r, g, b, 255);
    }

    public Color(double r, double g, double b, double a) {
        data = getData(r, g, b, a);
    }

    public Color(int r, int g, int b, int a) {
        set(r, g, b, a);
    }

    public Color(int color) {
        data = color;
    }

    public void set(int color) {
        data = color;
    }

    public void set(int r, int g, int b, int a) {
        data = getData(r, g, b, a);
    }

    public void set(double r, double g, double b, double a) {
        data = getData(r, g, b, a);
    }

    public int getData() {
        return data;
    }

    public int[] getComponents() {
        return getComponents(data);
    }

    public double[] getDoubleComponents() {
        return getDoubleComponents(data);
    }

    public int getR() {
        return (data & 0xFF000000) >> 24;
    }

    public int getG() {
        return (data & 0x00FF0000) >> 16;
    }

    public int getB() {
        return (data & 0x0000FF00) >> 8;
    }

    public int getA() {
        return (data & 0x000000FF);
    }

    public double getDR() {
        return ((data & 0xFF000000) >> 24) /255.;
    }

    public double getDG() {
        return ((data & 0x00FF0000) >> 16) /255.;
    }

    public double getDB() {
        return ((data & 0x0000FF00) >> 8) /255.;
    }

    public double getDA() {
        return ((data & 0x000000FF)) /255.;
    }

    public Color setR(int r) {
        data = (data & 0x00FFFFFF) | (r << 24);
        return this;
    }

    public Color setG(int g) {
        data = (data & 0xFF00FFFF) | (g << 16);
        return this;
    }

    public Color setB(int b) {
        data = (data & 0xFFFF00FF) | (b << 8);
        return this;
    }

    public Color setA(int a) {
        data = (data & 0xFFFFFF00) | a;
        return this;
    }

    public Color setR(double r) {
        data = (data & 0x00FFFFFF) | ((int)r*255 << 24);
        return this;
    }

    public Color setG(double g) {
        data = (data & 0xFF00FFFF) | ((int)g*255 << 16);
        return this;
    }

    public Color setB(double b) {
        data = (data & 0xFFFF00FF) | ((int)b*255 << 8);
        return this;
    }

    public Color setA(double a) {
        data = (data & 0xFFFFFF00) | ((int)a*255);
        return this;
    }

    public Color mix(Color c) {
        data = mixColors(data, c.getData());
        return this;
    }

    public static int getData(int r, int g, int b, int a) {
        return r << 24 | g << 16 | b << 8 | a;
    }

    public static int getData(double r, double g, double b, double a) {
        return (int)(r * 255) << 24 | (int)(g * 255) << 16 | (int)(b * 255) << 8 | (int)(a * 255);
    }

    public static int[] getComponents(int color) {
        return new int[] {
                (color & 0xFF000000) >> 24,
                (color & 0x00FF0000) >> 16,
                (color & 0x0000FF00) >> 8,
                (color & 0x000000FF)
        };
    }

    public static double[] getDoubleComponents(int color) {
        return new double[] {
                ((color & 0xFF000000) >> 24) /255.,
                ((color & 0x00FF0000) >> 16) /255.,
                ((color & 0x0000FF00) >> 8)  /255.,
                ((color & 0x000000FF))       /255.
        };
    }

    public static int mixColors(int color1, int color2) {
        return  (((color1 & 0xFF000000) >> 24) + ((color2 & 0xFF000000) >> 24)) |
                (((color1 & 0x00FF0000) >> 16) + ((color2 & 0x00FF0000) >> 16)) |
                (((color1 & 0x0000FF00) >> 8)  + ((color2 & 0x0000FF00) >> 8))  |
                (((color1 & 0x000000FF))       + ((color2 & 0x000000FF)));
    }

    public static Color mixColors(Color color1, Color color2) {
        return color1.mix(color2);
    }

    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
}
