package edu.hm.pohl.BombermanFX.api.model;

import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.scene.image.Image;

public abstract class VulnerableEntity extends MoveableEntity {
    
    protected int life;
    protected double immuneToDmgCounter;
    protected Image deadImage = BImageProvider.getRestInPeace();
    private final Image immuneImage = BImageProvider.getFlameImages()[0];

    public VulnerableEntity(final double x, final double y) {
        super(x, y);
        life = getDefaultLife();
    }

    public int getLife() {
        return life;
    }
    
    public void setLife(final int life) {
        this.life = life;
    }

    @Override
    public Image getLastImage() {
        if (!isDead()) {
            indexCounter++;
            indexCounter %= getImageDelay();
            return lastImage;
        }
        return deadImage;
    }

    public void damage() {
        immuneToDmgCounter = BConstants.PLAYER_IMMUNE_COUNT_DOWN;
        life--;
        if (life == 0) {
            setIsDead(true);
        }
    }

    public Image getImmuneImage() {
        return immuneImage;
    }
    
    public boolean isImmuneToDamage() {
        return immuneToDmgCounter > 0;
    }

    public void decrementImmuneToDamageCounter() {
        immuneToDmgCounter -= BConstants.FLAME_COUNT_DOWN_MODIFIER;
    }

    protected abstract int getDefaultLife();
}
