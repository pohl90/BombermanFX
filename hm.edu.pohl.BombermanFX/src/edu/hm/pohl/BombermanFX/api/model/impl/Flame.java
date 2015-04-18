package edu.hm.pohl.BombermanFX.api.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.api.model.TemporaryEntity;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Flame extends TemporaryEntity {

    private static final Image[] flameLifeCycle = BImageProvider.getFlameImages();

    private final List<Point2D> relativeExplosionPoints = new ArrayList<>();
    private final int power;
    
    private boolean hasRelativeExplosionPoints;

    public Flame(final double x, final double y, final int power) {
        super(x, y);
        this.power = power;
        setWidth(BConstants.FLAME_WIDTH);
        setHeight(BConstants.FLAME_HEIGHT);
    }
    
    public boolean hasRelativeExplosionPoints() {
        return hasRelativeExplosionPoints;
    }

    public void setHasRelativeExplosionPoints(final boolean hasRelativeExplosionPoints) {
        this.hasRelativeExplosionPoints = hasRelativeExplosionPoints;
    }
    
    public List<Point2D> getRelativeExplosionPoints() {
        return relativeExplosionPoints;
    }

    public int getPower() {
        return power;
    }
    
    @Override
    public void loadImages() {
        currentImage = flameLifeCycle[0];
    }
    
    @Override
    protected void loadLifeCycles() {
        lifeCycles = flameLifeCycle;
    }

    @Override
    protected double getInitialCountDownValue() {
        return BConstants.FLAME_COUNT_DOWN;
    }

    @Override
    protected double getCountDownModifier() {
        return BConstants.FLAME_COUNT_DOWN_MODIFIER;
    }
}
