package edu.hm.pohl.BombermanFX.api.model;

import javafx.scene.image.Image;

public abstract class TemporaryEntity extends Entity {
    
    protected double countDown;
    protected Image[] lifeCycles;

    public TemporaryEntity(final double x, final double y) {
        super(x, y);
        countDown = getInitialCountDownValue();
        loadLifeCycles();
    }
    
    public double getCountDown() {
        return countDown;
    }

    public void countDown() {
        countDown -= getCountDownModifier();
        currentImage = lifeCycles[(int) countDown % lifeCycles.length];
        if (countDown <= 0) {
            setIsDead(true);
        }
    }
    
    protected abstract void loadLifeCycles();
    
    protected abstract double getInitialCountDownValue();
    
    protected abstract double getCountDownModifier();

}
