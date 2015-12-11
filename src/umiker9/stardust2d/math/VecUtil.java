package umiker9.stardust2d.math;

import java.util.Arrays;

/**
 * Created by Notezway on 11.12.2015.
 */
public class VecUtil {

    public static Vector average(Vector... vectors) {
        if(vectors == null || vectors.length < 1) return null;
        int dim = vectors[0].getSize();
        double[] values = new double[dim];
        Arrays.fill(values, 0D);
        for(Vector vec : vectors) {
            for(int i = 0; i < dim; i++) {
                values[i] += vec.getComponent(i);
            }
        }
        for(int i = 0; i < dim; i++) {
            values[i] = values[i] / dim;
        }
        return new Vector(values);
    }

    public static Vec2 average(Vec2... vectors) {
        Vector[] v = new Vector[vectors.length];
        System.arraycopy(vectors, 0, v, 0, v.length);
        Vector res = average(v);
        return new Vec2(res.getComponent(0), res.getComponent(1));
    }

    public static Vec3 average(Vec3... vectors) {
        Vector[] v = new Vector[vectors.length];
        System.arraycopy(vectors, 0, v, 0, v.length);
        Vector res = average(v);
        return new Vec3(res.getComponent(0), res.getComponent(1), res.getComponent(2));
    }

    public static Vec4 average(Vec4... vectors) {
        Vector[] v = new Vector[vectors.length];
        System.arraycopy(vectors, 0, v, 0, v.length);
        Vector res = average(v);
        return new Vec4(res.getComponent(0), res.getComponent(1), res.getComponent(2), res.getComponent(3));
    }

    public static Vector geometricMean(Vector... vectors) {
        if(vectors == null || vectors.length < 1) return null;
        int dim = vectors[0].getSize();
        double[] values = new double[dim];
        Arrays.fill(values, 1D);
        for(Vector vec : vectors) {
            for(int i = 0; i < dim; i++) {
                values[i] *= vec.getComponent(i);
            }
        }
        for(int i = 0; i < dim; i++) {
            values[i] = Math.pow(values[i], 1D/dim);
        }
        return new Vector(values);
    }
}
