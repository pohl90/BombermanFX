package edu.hm.pohl.BombermanFX.api.model;

import edu.hm.pohl.BombermanFX.api.engine.util.EntityDirection;
import javafx.scene.image.Image;

public abstract class MoveableEntity extends Entity {
    
    private static final int IMAGE_INDEX_DELAY = 5;
    
    protected EntityDirection verticalDirection;
    protected EntityDirection horizontalDirection;
    
    protected Image[] frontImages;
    protected Image[] backImages;
    protected Image[] rightImages;
    protected Image[] leftImages;
    protected Image lastImage;
    
    private double reverseControlCounter;
    protected int indexCounter;
    protected double speed;
    
    public MoveableEntity(final double x, final double y) {
        super(x, y);
        currentImage = frontImages[0];
        speed = getDefaultSpeed();
    }
    
    public void incRunCounter(final EntityDirection direction) {

        final int index = indexCounter / IMAGE_INDEX_DELAY;
        switch (direction) {
            case UP:
                lastImage = backImages[index];
                break;
            case DOWN:
                lastImage = frontImages[index];
                break;
            case LEFT:
                lastImage = leftImages[index];
                break;
            default:
                lastImage = rightImages[index];
                break;
        }
        currentImage = getLastImage();
    }

    public void setReverseControlCounter(final double value) {
        reverseControlCounter = value;
    }

    public double getReverseControlCounter() {
        return reverseControlCounter;
    }

    public double getSpeed(final EntityDirection direction) {
        if (direction == null) {
            return 0;
        }
        return direction.getDirectionModifier() * speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    public EntityDirection getVerticalDirection() {
        
        if (verticalDirection == null) {
            return null;
        }
        if (reverseControlCounter > 0) {
            return verticalDirection.getReverseDirection();
        }
        return verticalDirection;
    }

    public EntityDirection getHorizontalDirection() {

        if (horizontalDirection == null) {
            return null;
        }
        if (reverseControlCounter > 0) {
            return horizontalDirection.getReverseDirection();
        }
        return horizontalDirection;
    }

    public void setVerticalDirection(final EntityDirection direction) {
        verticalDirection = direction;
    }

    public void setHorizontalDirection(final EntityDirection direction) {
        horizontalDirection = direction;
    }
    
    public abstract Image getLastImage();

    protected abstract int getImageDelay();

    protected abstract double getDefaultSpeed();
    
}
