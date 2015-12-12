package umiker9.stardust2d;

/**
 * Created by miker9 on 11/12/2015.
 */
public class Animation {
    protected TileSet tileset;
    protected int currentFrame;
    protected boolean playing;
    protected boolean looped;
    protected int firstFrame;
    protected int lasFrame;
    protected double animationSpeed;
    protected long frameTime;

    public void update(long delta) {
        if (playing) {
            frameTime += delta;

            if (frameTime / Stardust2D.timePrecission > 1.0 / animationSpeed) {
                currentFrame += 1;
                frameTime -= (1.0 / animationSpeed) * Stardust2D.timePrecission;
            }

            if (currentFrame >= lasFrame) {
                if (looped) {
                    currentFrame = firstFrame;
                } else {
                    playing = false;
                }
            }
        }

    }


    public Tile getCurrentTile() {
        return tileset.getTile(currentFrame);
    }
}
