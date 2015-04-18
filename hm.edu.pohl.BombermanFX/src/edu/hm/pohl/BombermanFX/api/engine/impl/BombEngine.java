package edu.hm.pohl.BombermanFX.api.engine.impl;

import edu.hm.pohl.BombermanFX.api.engine.BEngine;
import edu.hm.pohl.BombermanFX.api.engine.util.Collision;
import edu.hm.pohl.BombermanFX.api.engine.util.EntityDirection;
import edu.hm.pohl.BombermanFX.api.model.Entity;
import edu.hm.pohl.BombermanFX.api.model.impl.Bomb;
import edu.hm.pohl.BombermanFX.api.model.impl.Flame;
import edu.hm.pohl.BombermanFX.api.model.impl.Player;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.geometry.Point2D;

public class BombEngine extends BEngine {

    @Override
    public void perform() {
        bombs.forEach(bomb -> {
            triggerKick(bomb);
            bomb.countDown();
            if (bomb.getCountDown() <= 0) {
                bomb.explode();
                addFlameOf(bomb);
            }
            checkForDamage(bomb);
        });
        bombs.removeIf(Bomb::isDead);
    }

    public void addFlameOf(final Bomb bomb) {
        final double x = bomb.getX();
        final double y = bomb.getY();

        final Flame flame = new Flame(x, y, bomb.getPower());
        flames.add(flame);
    }

    private void checkForDamage(final Bomb bomb) {

        if (bomb.isDead()) {
            return;
        }
        final double blockSize = BConstants.BLOCK;
        for (final Flame flame : flames) {
            final double width = flame.getWidth();
            final double height = flame.getHeight();
            final double deltaX = (blockSize - width) / 2;
            final double deltaY = (blockSize - height) / 2;
            for (final Point2D point : flame.getRelativeExplosionPoints()) {
                final double x = point.getX() * blockSize + deltaX;
                final double y = point.getY() * blockSize + deltaY;
                if (bomb.intersects(x, y, width, height)) {
                    bomb.explode();
                    addFlameOf(bomb);
                    return;
                }
            }

        }
    }

    private void triggerKick(final Bomb bomb) {
        final EntityDirection kickDirection = bomb.getKickDirection();
        if (kickDirection == null) {
            return;
        }

        double x = bomb.getX();
        double y = bomb.getY();

        final double directionModifier = kickDirection.getDirectionModifier();
        final boolean collision;
        final double bombKickSpeed = BConstants.BOMB_KICK_SPEED;
        if (kickDirection.isHorizontal()) {
            if (directionModifier > 0) {
                collision = Collision.hasRightCollision(bomb, bombKickSpeed, kickDirection);
            } else {
                collision = Collision.hasLeftCollision(bomb, -bombKickSpeed, kickDirection);
            }
            x += bombKickSpeed * directionModifier;
        } else {
            if (directionModifier > 0) {
                collision = Collision.hasBottomCollision(bomb, bombKickSpeed, kickDirection);
            } else {
                collision = Collision.hasTopCollision(bomb, -bombKickSpeed, kickDirection);
            }
            y += bombKickSpeed * directionModifier;
        }

        if (!collision) {
            final double width = bomb.getWidth();
            final double height = bomb.getHeight();

            // Check for other bombs
            for (final Bomb otherBomb : bombs) {
                if (otherBomb != bomb && otherBomb.intersects(x, y, width, height)) {
                    bomb.setKickDirection(null);
                    return;
                }
            }

            // Check for monsters
            for (final Entity monster : monsters) {
                if (monster.intersects(x, y, width, height)) {
                    bomb.setKickDirection(null);
                    return;
                }
            }

            // Check for players
            for (final Player player : players) {
                if (player.intersects(x, y, width, height)) {
                    bomb.setKickDirection(null);
                    return;
                }
            }

            if (x != bomb.getX()) {
                bomb.setX(x);
            } else {
                bomb.setY(y);
            }
        } else {
            bomb.setKickDirection(null);
        }
    }
}
