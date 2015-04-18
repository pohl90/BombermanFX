package edu.hm.pohl.BombermanFX.api.engine.impl;

import java.util.List;

import edu.hm.pohl.BombermanFX.api.engine.BEngine;
import edu.hm.pohl.BombermanFX.api.engine.util.Collision;
import edu.hm.pohl.BombermanFX.api.model.impl.Flame;
import edu.hm.pohl.BombermanFX.api.model.impl.Powerup;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.geometry.Point2D;

public class FlameEngine extends BEngine {

    @Override
    public void perform() {
        flames.parallelStream().forEach(flame -> {
            flame.countDown();
            computeExplosionPoints(flame);
        });
        flames.removeIf(Flame::isDead);
    }

    public void addPowerupAt(final int levelX, final int levelY) {
        final double x = levelX * BConstants.BLOCK;
        final double y = levelY * BConstants.BLOCK;
        final double width = BConstants.POWERUP_WIDTH;
        final double height = BConstants.POWERUP_HEIGHT;

        final double powerX = Collision.getCenteredPos(x, width);
        final double powerY = Collision.getCenteredPos(y, height);
        final Powerup powerup = new Powerup(powerX, powerY);
        powerups.add(powerup);
    }

    private void computeExplosionPoints(final Flame flame) {

        if (flame.hasRelativeExplosionPoints()) {
            return;
        }

        final double flameX = flame.getX();
        final double flameY = flame.getY();
        final int power = flame.getPower();

        final int levelX = (int) Collision.getRelativePos(flameX);
        final int levelY = (int) Collision.getRelativePos(flameY);

        final List<Point2D> relativeExplosionPoints = flame.getRelativeExplosionPoints();
        relativeExplosionPoints.add(new Point2D(levelX, levelY));

        boolean blockNorth = false;
        boolean blockEast = false;
        boolean blockSouth = false;
        boolean blockWest = false;

        for (int i = 1; i <= power; i++) {

            final int northY = levelY - i;
            final int eastX = levelX + i;
            final int southY = levelY + i;
            final int westX = levelX - i;

            // North
            if (!blockNorth) {
                blockNorth = !checkArea(levelX, northY, relativeExplosionPoints);
            }
            // East
            if (!blockEast) {
                blockEast = !checkArea(eastX, levelY, relativeExplosionPoints);
            }
            // South
            if (!blockSouth) {
                blockSouth = !checkArea(levelX, southY, relativeExplosionPoints);
            }
            // West
            if (!blockWest) {
                blockWest = !checkArea(westX, levelY, relativeExplosionPoints);
            }
        }

        flame.setHasRelativeExplosionPoints(true);
    }

    private boolean checkArea(final int x, final int y, final List<Point2D> points) {

        if (!map.validate(x, y)) {
            return false;
        }

        final int color = map.getRGBAt(x, y);
        if (Collision.isDestroyable(color)) {
            destroyAt(x, y, points);
        } else if (!Collision.isWall(color)) {
            points.add(new Point2D(x, y));
            return true;
        }

        return false;
    }

    private void destroyAt(final int x, final int y, final List<Point2D> points) {
        points.add(new Point2D(x, y));
        map.destroyAt(x, y);

        if (Math.random() > 0.5) {
            addPowerupAt(x, y);
        }
    }
}
