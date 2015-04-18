package edu.hm.pohl.BombermanFX.api.model;

import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Entity extends Rectangle {

    protected Image currentImage;
    private final Image deadImage = BImageProvider.getRestInPeace();
    private String name;
    private boolean isDead;
    
    public Entity(final double x, final double y) {
        setX(x);
        setY(y);
        loadImages();
    }

    public abstract void loadImages();

    public Image getCurrentImage() {
        if (isDead) {
            return deadImage;
        }
        return currentImage;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(final boolean isDead) {
        if (isDead) {
            setWidth(BConstants.ENTITY_DEAD_WIDTH);
            setHeight(BConstants.ENTITY_DEAD_HEIGHT);
        }
        this.isDead = isDead;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
