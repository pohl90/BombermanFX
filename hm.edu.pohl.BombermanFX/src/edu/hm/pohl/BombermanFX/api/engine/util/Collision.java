package edu.hm.pohl.BombermanFX.api.engine.util;

import static edu.hm.pohl.BombermanFX.utils.BConstants.BLOCK;
import static java.lang.Math.round;
import edu.hm.pohl.BombermanFX.api.engine.BEngine;
import edu.hm.pohl.BombermanFX.api.model.Entity;
import edu.hm.pohl.BombermanFX.api.model.MoveableEntity;

/**
 * This class represents the logic for the collisions
 *
 * @author Marc
 *
 */
public class Collision {

    // Static variables for the hexcode of various colors
    public final static int HEX_WHITE = 0xFFFFFFFF;
    public final static int HEX_BLACK = 0xFF000000;
    public final static int HEX_GREEN = 0xFF00FF00;
    public final static int HEX_BLUE = 0xFF0000FF;
    public final static int HEX_RED = 0xFFFF0000;

    public static double getRelativePos(final double pos) {
        return (int) (Math.round(pos) / BLOCK);
    }

    public static double getCenteredPos(final double pos, final double size) {
        return pos + (BLOCK - size) / 2;
    }

    public static boolean hasLeftCollision(final Entity entity, final double speed, final EntityDirection direction) {
        final double x = entity.getX() + speed;
        return hasHorizontalCollision(entity, x);
    }

    public static boolean hasRightCollision(final Entity entity, final double speed, final EntityDirection direction) {
        final double x = entity.getX() + speed + entity.getWidth();
        return hasHorizontalCollision(entity, x);
    }

    public static boolean hasTopCollision(final Entity entity, final double speed, final EntityDirection direction) {
        final double y = entity.getY() + speed;
        return hasVerticalCollision(entity, y);
    }

    public static boolean hasBottomCollision(final Entity entity, final double speed, final EntityDirection direction) {
        final double y = entity.getY() + speed + entity.getHeight();
        return hasVerticalCollision(entity, y);
    }

    public static boolean hasCollision(final int color) {
        return color == HEX_BLACK || color == HEX_WHITE;
    }

    public static double getClosestDistance(final MoveableEntity entity, final double speed,
            final EntityDirection direction) {
        if (direction == null) {
            return 0;
        }
        if (direction.getDirectionModifier() > 0) {
            final double playerCoordinate;

            if (direction.equals(EntityDirection.DOWN)) {
                playerCoordinate = entity.getY() + entity.getHeight();
            } else {
                playerCoordinate = entity.getX() + entity.getWidth();
            }
            final int levelCoordinate = (int) round(playerCoordinate) / (int) BLOCK + 1;
            final double distance = levelCoordinate * BLOCK - playerCoordinate - 1;

            if (distance > 0) {
                return distance;
            }
        } else {

            final double playerCoordinate;

            if (direction.equals(EntityDirection.UP)) {
                playerCoordinate = entity.getY();
            } else {
                playerCoordinate = entity.getX();
            }
            final int levelCoordinate = (int) round(playerCoordinate) / (int) BLOCK - 1;
            final double distance = levelCoordinate * BLOCK + BLOCK + 1 - playerCoordinate;

            if (distance < 0) {
                return distance;
            }
        }

        return 0;
    }

    private static boolean hasHorizontalCollision(final Entity entity, final double x) {
        final int[][] level = BEngine.map.getChosenLevelRGB();
        final double y = entity.getY();
        final int blockSize = (int) BLOCK;
        final int levelX = (int) round(x) / blockSize;
        final int levelYTop = (int) round(y) / blockSize;
        final int levelYBottom = (int) round(y + entity.getHeight()) / blockSize;

        if (levelYTop == levelYBottom) {
            return hasCollision(level[levelYTop][levelX]);
        } else {
            return hasCollision(level[levelYTop][levelX]) || hasCollision(level[levelYBottom][levelX]);
        }
    }

    private static boolean hasVerticalCollision(final Entity entity, final double y) {
        final int[][] level = BEngine.map.getChosenLevelRGB();
        final double x = entity.getX();
        final int blockSize = (int) BLOCK;
        final int levelXLeft = (int) round(x) / blockSize;
        final int levelXRight = (int) round(x + entity.getWidth()) / blockSize;
        final int levelY = (int) round(y) / blockSize;

        if (levelXLeft == levelXRight) {
            return hasCollision(level[levelY][levelXLeft]);
        } else {
            return hasCollision(level[levelY][levelXLeft]) || hasCollision(level[levelY][levelXRight]);
        }
    }

    public static boolean isDestroyable(final int color) {
        return color == HEX_WHITE;
    }

    public static boolean isWall(final int color) {
        return color == HEX_BLACK;
    }
}
