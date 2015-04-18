package edu.hm.pohl.BombermanFX.api.model.impl;

import edu.hm.pohl.BombermanFX.api.audio.BSoundFactory;
import edu.hm.pohl.BombermanFX.api.engine.util.EntityDirection;
import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.api.model.TemporaryEntity;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Bomb extends TemporaryEntity {

    private static final AudioClip EXPLOSION_CLIP = BSoundFactory.createExplosionClip();
    private static final Image[] bombLifeCycle = BImageProvider.getBombImages();

    private final int power;
    private EntityDirection kickDirection;

    public Bomb(final double x, final double y, final int power) {
        super(x, y);
        this.power = power;
        setWidth(BConstants.BOMB_WIDTH);
        setHeight(BConstants.BOMB_HEIGHT);
    }

    public void setKickDirection(final EntityDirection kickDirection) {
        this.kickDirection = kickDirection;
    }

    public EntityDirection getKickDirection() {
        return kickDirection;
    }

    public int getPower() {
        return power;
    }
    
    public void explode() {
        setIsDead(true);
        EXPLOSION_CLIP.play();
    }
    
    @Override
    public void loadLifeCycles() {
        lifeCycles = bombLifeCycle;
    }
    
    @Override
    public void loadImages() {
        currentImage = bombLifeCycle[0];
    }

    @Override
    public void countDown() {
        super.countDown();
    }

    @Override
    protected double getInitialCountDownValue() {
        return BConstants.BOMB_COUNT_DOWN;
    }

    @Override
    protected double getCountDownModifier() {
        return BConstants.BOMB_COUNT_DOWN_MODIFIER;
    }
}
