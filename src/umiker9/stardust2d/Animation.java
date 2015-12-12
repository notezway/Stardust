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

    public void update(double delta) {
        if (playing) {
            frameTime += delta;

            if (frameTime > 1.0 / animationSpeed) {
                currentFrame += 1;
                frameTime -= (1.0 / animationSpeed);
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
