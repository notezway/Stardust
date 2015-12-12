package umiker9.stardust2d;

/**
 * Created by miker9 on 07/12/2015.
 */
public class FollowingCamera extends Camera {
    private Sprite target;
    private boolean followRotation;

    public FollowingCamera(Sprite target) {
        this.target = target;
    }

    public FollowingCamera(boolean followRotation, Sprite target) {
        this(target);
        this.followRotation = followRotation;
    }

    @Override
    public void update(double delta) {
        this.x = target.getX();
        this.y = target.getY();
        if (followRotation) {
            this.rotation = target.getRotation();
        }
    }

    public boolean doesFollowRotation() {
        return followRotation;
    }

    public void setFollowRotation(boolean followRotation) {
        this.followRotation = followRotation;
    }
}
