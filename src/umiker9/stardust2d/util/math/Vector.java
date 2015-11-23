package umiker9.stardust2d.util.math;

public class Vector {
    protected double[] values;

    public Vector(double... values) {
        this.values = values;
    }

    public Vector(int size) {
        this.values = new double[size];
    }

    public int getSize() {
        if(values != null) {
            return values.length;
        } else {
            return 0;
        }
    }

    public double getComponent(int n) {
        if(n < getSize() && n >= 0) {
            return values[n];
        } else {
            throw new IllegalArgumentException("Out of bounds");
        }
    }

    public void setComponent(int n, double value) {
        if(n < getSize() && n >= 0) {
            values[n] = value;
        } else {
            throw new IllegalArgumentException("Out of bounds");
        }
    }
}
