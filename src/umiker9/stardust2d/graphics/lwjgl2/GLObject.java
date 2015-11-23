package umiker9.stardust2d.graphics.lwjgl2;

/**
 * Created by miker9
 * On 26/12/2014
 */
public interface GLObject {
    int getId();
    boolean isInitialized();
    void destroy();
}
