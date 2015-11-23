package umiker9.stardust2d.graphics.lwjgl2;

/**
 * Created by miker9
 * On 07/01/2015
 */
public interface Storage<K extends GLObject> {
    void add(String name, K object);

    K get(String name);

    void clear();

    int getSize();
}