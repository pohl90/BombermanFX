package edu.hm.pohl.BombermanFX.api.engine.util;

public enum EntityDirection {
    UP(-1), RIGHT(1), DOWN(1), LEFT(-1);
    
    private final int directionModifier;
    
    EntityDirection(final int directionModifier) {
        this.directionModifier = directionModifier;
    }
    
    public boolean isHorizontal() {
        return equals(LEFT) || equals(RIGHT);
    }
    
    public double getDirectionModifier() {
        return directionModifier;
    }
    
    public EntityDirection getReverseDirection() {
        switch (this) {
            case UP:
                return EntityDirection.DOWN;
            case DOWN:
                return EntityDirection.UP;
            case LEFT:
                return EntityDirection.RIGHT;
            case RIGHT:
                return EntityDirection.LEFT;
            default:
                return null;
        }
    }
}
