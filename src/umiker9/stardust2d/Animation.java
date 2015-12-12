package umiker9.stardust2d;

/**
 * Created by miker9 on 11/12/2015.
 */
public class Animation {
    protected int currentFrame;
    protected boolean playing;
    protected boolean looped;
    protected int firstFrame;
    protected int lasFrame;
    protected double animationSpeed;
    protected double frameTime;
    private TileSet tileset;

    public Animation(TileSet tileSet, int firstFrame, int lasFrame, boolean looped) {

    }

    public Animation(TileSet tileSet, int firstFrame, int lasFrame, int animationSpeed, boolean looped) {
        this.tileset = tileSet;
        this.firstFrame = firstFrame;
        this.lasFrame = lasFrame;
        this.animationSpeed = animationSpeed;
        this.looped = looped;
    }

    public void update(double delta) {
        if (playing) {
            frameTime += delta;

            if (frameTime > 1.0 / animationSpeed) {
                currentFrame += 1;

                frameTime -= (1.0 / animationSpeed);
            }

            if (currentFrame >= lasFrame) {
                if (looped) {
                    reset();
                } else {
                    pause();
                }
            }
        }
    }

    public void play() {
        if (!looped && currentFrame == lasFrame) {
            reset();
        }
        playing = true;
    }

    public void pause() {
        playing = false;
    }

    public void stop() {
        playing = false;
        reset();
    }

    public void reset() {
        currentFrame = firstFrame;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isLooped() {
        return looped;
    }

    public void setLooped(boolean looped) {
        this.looped = looped;
    }

    public double getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(double animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public Tile getCurrentTile() {
        return tileset.getTile(currentFrame);
    }

    public int getCurrentFrame() {
        return currentFrame;
    }
}
