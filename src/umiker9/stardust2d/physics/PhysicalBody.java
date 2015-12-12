package umiker9.stardust2d.physics;

import umiker9.stardust2d.Sprite;
import umiker9.stardust2d.graphics.lwjgl2.Renderer;
import umiker9.stardust2d.graphics.lwjgl2.ShapesDrawer;
import umiker9.stardust2d.math.Vec2;
import umiker9.stardust2d.math.geometry.CircleShape;
import umiker9.stardust2d.math.geometry.Shape;

/**
 * Created by Notezway on 07.12.2015.
 */
public abstract class PhysicalBody extends Sprite {

    protected Shape[] shapes;
    protected double mass;
    protected double size;

    protected double velX;
    protected double velY;

    protected double accX;
    protected double accY;

    protected double forceX;
    protected double forceY;

    public PhysicalBody(Vec2 pos, double rot, double mass, double size, Shape... shapes) {
        super();
        setPosition(pos);
        this.rotation = rot;
        this.shapes = shapes;
        this.mass = mass;
        this.size = size;
        this.width = size;
        this.height = size;
    }

    public Vec2 getPosition() {
        return new Vec2(x, y);
    }

    public void setPosition(Vec2 position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    public double getSize() {
        return size;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vec2 getVelocity() {
        return new Vec2(velX, velY);
    }

    public void setVelocity(Vec2 velocity) {
        this.velX = velocity.getX();
        this.velY = velocity.getY();
    }

    public Vec2 getAcceleration() {
        return new Vec2(accX, accY);
    }

    public void setAcceleration(Vec2 acceleration) {
        this.accX = acceleration.getX();
        this.accY = acceleration.getY();
    }

    public Vec2 getForce() {
        return new Vec2(forceX, forceY);
    }

    public void applyMomentum(Vec2 momentum) {
        this.velX += momentum.getX() / mass;
        this.velY += momentum.getY() / mass;
    }

    public void applyForce(Vec2 force) {
        this.forceX += force.getX();
        this.forceY += force.getY();
    }

    @Override
    public void update(double delta) {
        accX += forceX / mass;
        accY += forceY / mass;
        velX += accX * delta;
        velY += accY * delta;
        forceX = 0;
        forceY = 0;
        accX = 0;
        accY = 0;
        x += velX * delta;
        y += velY * delta;
    }

    public void draw(Renderer renderer) {
        for(Shape shape : shapes) {
            if (shape instanceof CircleShape) {
                ShapesDrawer.drawCircle(renderer, (CircleShape) shape, 32);
            }
        }
    }

    public boolean doesCollide(PhysicalBody another) {
        for(Shape shape1 : shapes) {
            for(Shape shape2 : another.shapes) {
                if(shape1.doesCollide(shape2, getPosition(), another.getPosition())) return true;
            }
        }
        return false;
    }

    public void onCollision(PhysicalBody another) {
        Collider.onCollide(this, another);
    }
}
