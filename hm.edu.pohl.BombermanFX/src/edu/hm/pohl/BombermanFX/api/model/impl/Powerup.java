package edu.hm.pohl.BombermanFX.api.model.impl;

import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.api.model.Entity;
import edu.hm.pohl.BombermanFX.utils.BConstants;

public class Powerup extends Entity {
    
    private PowerType powerType;

    public Powerup(final double x, final double y) {
        super(x, y);
        setWidth(BConstants.POWERUP_WIDTH);
        setHeight(BConstants.POWERUP_HEIGHT);
    }
    
    @Override
    public void loadImages() {
        powerType = PowerType.generate();
        switch (powerType) {
            case BOMBS:
                currentImage = BImageProvider.getPowerupImages()[0];
                break;
            case FLAMES:
                currentImage = BImageProvider.getPowerupImages()[1];
                break;
            case SPEED:
                currentImage = BImageProvider.getPowerupImages()[2];
                break;
            case SWITCH_CONTROL:
                currentImage = BImageProvider.getPowerupImages()[3];
                break;
            case HEART:
                currentImage = BImageProvider.getPowerupImages()[4];
                break;
            default:
                currentImage = BImageProvider.getPowerupImages()[0];
        }
    }

    public PowerType getPowerType() {
        return powerType;
    }
    
    public enum PowerType {
        BOMBS, FLAMES, SPEED, SWITCH_CONTROL, HEART;

        public static PowerType generate() {
            final double random = Math.random() * PowerType.values().length;
            return PowerType.values()[(int) random];
        }
    }
}
