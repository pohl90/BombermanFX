package edu.hm.pohl.BombermanFX.api.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.hm.pohl.BombermanFX.api.audio.BSoundFactory;
import edu.hm.pohl.BombermanFX.api.engine.util.EntityDirection;
import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.api.model.VulnerableEntity;
import edu.hm.pohl.BombermanFX.api.model.input.InputListener;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.scene.media.AudioClip;

public class Player extends VulnerableEntity {

    private static final AudioClip RUN_CLIP = BSoundFactory.createPlayerWalkClip();
    private static final AudioClip DAMAGE_CLIP = BSoundFactory.createPlayerDamagedClip();

    private int runCounter = 1;
    
    private int firePower = BConstants.PLAYER_FIREPOWER;
    private int maxBombs = BConstants.PLAYER_BOMBLIMIT;

    private final List<Bomb> droppedBombs = new ArrayList<>();
    private boolean dropBomb;
    
    public Player(final String name, final double x, final double y, final InputListener inputListener) {
        super(x, y);
        setName(name);
        inputListener.setPlayer(this);
        setWidth(BConstants.PLAYER_WIDTH);
        setHeight(BConstants.PLAYER_HEIGHT);
    }

    @Override
    protected int getImageDelay() {
        return BConstants.PLAYER_IMAGE_DELAY;
    }

    @Override
    protected int getDefaultLife() {
        return BConstants.PLAYER_LIFES;
    };

    @Override
    public void loadImages() {
        frontImages = BImageProvider.getFrontImages();
        backImages = BImageProvider.getBackImages();
        rightImages = BImageProvider.getRightImages();
        leftImages = BImageProvider.getLeftImages();
        deadImage = BImageProvider.getRestInPeace();
        currentImage = frontImages[0];
    }

    @Override
    public void incRunCounter(final EntityDirection direction) {
        runCounter++;
        if (runCounter % BConstants.PLAYER_RUN_DELAY == 0) {
            runCounter = 1;
            RUN_CLIP.play();
        }
        super.incRunCounter(direction);
    }

    @Override
    protected double getDefaultSpeed() {
        return BConstants.PLAYER_SPEED;
    }
    
    public int getMaxBombs() {
        return maxBombs;
    }

    public void setMaxBombs(final int maxBombs) {
        this.maxBombs = maxBombs;
    }
    
    public int getFirePower() {
        return firePower;
    }

    public void setFirePower(final int firePower) {
        this.firePower = firePower;
    }

    @Override
    public void damage() {
        super.damage();
        DAMAGE_CLIP.play();
    }
    
    public void setDropBomb(final boolean dropBomb) {
        if (dropBomb && !isImmuneToDamage()) {
            if (droppedBombs.size() < maxBombs) {
                this.dropBomb = true;
            }
        } else {
            this.dropBomb = false;
        }
    }
    
    public boolean bombToDrop() {
        return dropBomb;
    }

    public List<Bomb> getDroppedBombs() {
        return droppedBombs;
    }
}
