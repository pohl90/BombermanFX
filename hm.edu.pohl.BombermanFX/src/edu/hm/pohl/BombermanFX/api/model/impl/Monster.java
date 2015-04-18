package edu.hm.pohl.BombermanFX.api.model.impl;

import edu.hm.pohl.BombermanFX.api.engine.util.EntityDirection;
import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.api.model.VulnerableEntity;
import edu.hm.pohl.BombermanFX.utils.BConstants;

public class Monster extends VulnerableEntity {
    
    private int horizontalRunDuration = 100;
    private int verticalRunDuration = 100;
    
    public Monster(final double x, final double y) {
        super(x, y);
        setWidth(BConstants.MONSTER_WIDTH);
        setHeight(BConstants.MONSTER_HEIGHT);
        loadImages();
    }
    
    @Override
    protected int getDefaultLife() {
        return BConstants.MONSTER_LIFE;
    }
    
    @Override
    public void loadImages() {
        frontImages = BImageProvider.getMobFrontImages();
        backImages = BImageProvider.getMobBackImages();
        rightImages = BImageProvider.getMobRightImages();
        leftImages = BImageProvider.getMobLeftImages();
        deadImage = BImageProvider.getMobFrontImages()[0];
        currentImage = frontImages[0];
    }
    
    @Override
    public EntityDirection getVerticalDirection() {
        
        if (verticalRunDuration > 0) {
            verticalRunDuration--;
        } else {
            verticalRunDuration = 100;
            final double random = Math.random();
            if (random > 0.9) {
                verticalDirection = null;
            } else if (random < 0.45) {
                verticalDirection = EntityDirection.UP;
            } else {
                verticalDirection = EntityDirection.DOWN;
            }
        }
        return super.getVerticalDirection();
    }
    
    @Override
    public EntityDirection getHorizontalDirection() {
        
        if (horizontalRunDuration > 0) {
            horizontalRunDuration--;
        } else {
            horizontalRunDuration = 100;
            final double random = Math.random();
            if (random > 0.9) {
                horizontalDirection = null;
            } else if (random < 0.45) {
                horizontalDirection = EntityDirection.LEFT;
            } else {
                horizontalDirection = EntityDirection.RIGHT;
            }
        }
        return super.getHorizontalDirection();
    }
    
    @Override
    public double getDefaultSpeed() {
        return BConstants.MONSTER_SPEED;
    }

    @Override
    protected int getImageDelay() {
        return BConstants.MONSTER_IMAGE_DELAY;
    }
}
