package edu.hm.pohl.BombermanFX.api.engine.impl;

import static edu.hm.pohl.BombermanFX.utils.BConstants.BLOCK;
import static edu.hm.pohl.BombermanFX.utils.BConstants.REVERSE_CONTROL_MODIFIER;
import edu.hm.pohl.BombermanFX.api.engine.BEngine;
import edu.hm.pohl.BombermanFX.api.engine.util.Collision;
import edu.hm.pohl.BombermanFX.api.engine.util.EntityDirection;
import edu.hm.pohl.BombermanFX.api.model.MoveableEntity;
import edu.hm.pohl.BombermanFX.api.model.VulnerableEntity;
import edu.hm.pohl.BombermanFX.api.model.impl.Bomb;
import edu.hm.pohl.BombermanFX.api.model.impl.Flame;
import edu.hm.pohl.BombermanFX.api.model.impl.Player;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.geometry.Point2D;

public class EntityEngine extends BEngine {

    @Override
    public void perform() {
        players.parallelStream().forEach(this::triggerMovement);
        monsters.parallelStream().forEach(this::triggerMovement);
        players.parallelStream().forEach(this::checkForDamage);
        monsters.parallelStream().forEach(this::checkForDamage);
    }

    private void checkForDamage(final VulnerableEntity entity) {

        if (entity.isDead()) {
            return;
        }
        final double blockSize = BConstants.BLOCK;
        for (final Flame flame : flames) {
            if (!entity.isImmuneToDamage()) {
                final double width = flame.getWidth();
                final double height = flame.getHeight();
                final double deltaX = (blockSize - width) / 2;
                final double deltaY = (blockSize - height) / 2;
                for (final Point2D point : flame.getRelativeExplosionPoints()) {
                    final double x = point.getX() * blockSize + deltaX;
                    final double y = point.getY() * blockSize + deltaY;
                    if (entity.intersects(x, y, width, height)) {
                        entity.damage();
                        return;
                    }
                }
            }
        }
        if (entity.isImmuneToDamage()) {
            entity.decrementImmuneToDamageCounter();
        }
    }

    private void triggerMovement(final MoveableEntity entity) {

        if (!entity.isDead()) {
            final EntityDirection verticalDirection = entity.getVerticalDirection();
            final EntityDirection horizontalDirection = entity.getHorizontalDirection();

            if (verticalDirection != null) {
                checkVerticalCollision(verticalDirection, entity);
            }
            if (horizontalDirection != null) {
                checkHorizontalCollision(horizontalDirection, entity);
            }

            if (entity instanceof Player) {
                final Player player = (Player) entity;
                if (player.bombToDrop()) {
                    player.setDropBomb(false);
                    final Bomb droppedBomb = addBombOf(player);
                    if (droppedBomb != null) {
                        player.getDroppedBombs().add(droppedBomb);
                    }
                }
                player.getDroppedBombs().removeIf(Bomb::isDead);
            }

            if (entity.getReverseControlCounter() > 0) {
                final double reverseControlCounter = entity.getReverseControlCounter();
                entity.setReverseControlCounter(reverseControlCounter - REVERSE_CONTROL_MODIFIER);
            }
        }
    }

    public Bomb addBombOf(final Player player) {
        final double x = player.getX() + player.getWidth() / 2;
        final double y = player.getY() + player.getHeight() / 2;
        final double width = BConstants.BOMB_WIDTH;
        final double height = BConstants.BOMB_HEIGHT;

        final double levelX = (int) (Math.round(x) / BLOCK) * BLOCK;
        final double levelY = (int) (Math.round(y) / BLOCK) * BLOCK;

        final double bombX = Collision.getCenteredPos(levelX, width);
        final double bombY = Collision.getCenteredPos(levelY, height);

        // Check if there is already a bomb at this spot
        for (final Bomb droppedBomb : bombs) {
            if (droppedBomb.getX() == bombX && droppedBomb.getY() == bombY) {
                return null;
            }
        }
        final Bomb bomb = new Bomb(bombX, bombY, player.getFirePower());
        bombs.add(bomb);
        return bomb;
    }

    private void checkHorizontalCollision(final EntityDirection direction, final MoveableEntity entity) {

        boolean collision = false;
        final double speed = entity.getSpeed(direction);
        if (direction.getDirectionModifier() > 0) {
            collision = Collision.hasRightCollision(entity, speed, direction);
        } else {
            collision = Collision.hasLeftCollision(entity, speed, direction);
        }
        final double x = entity.getX();
        final double y = entity.getY();
        if (!collision) {
            final double width = entity.getWidth();
            final double height = entity.getHeight();
            final double newX = x + speed;
            boolean touchesABomb = false;
            for (final Bomb bomb : bombs) {
                if (!bomb.intersects(entity.getBoundsInParent()) && bomb.intersects(newX, y, width, height)) {
                    touchesABomb = true;
                    if (newX > x) {
                        bomb.setKickDirection(EntityDirection.RIGHT);
                    } else {
                        bomb.setKickDirection(EntityDirection.LEFT);
                    }
                    break;
                }
            }
            if (!touchesABomb) {
                entity.setX(newX);
                entity.incRunCounter(direction);
            }
        } else {
            final double deltaX = Collision.getClosestDistance(entity, speed, entity.getHorizontalDirection());
            if (deltaX != 0) {
                entity.setX(x + deltaX);
                entity.incRunCounter(direction);
            }

        }
    }

    private void checkVerticalCollision(final EntityDirection direction, final MoveableEntity entity) {

        boolean collision = false;
        final double speed = entity.getSpeed(direction);
        if (direction.getDirectionModifier() > 0) {
            collision = Collision.hasBottomCollision(entity, speed, direction);
        } else {
            collision = Collision.hasTopCollision(entity, speed, direction);
        }
        final double x = entity.getX();
        final double y = entity.getY();
        if (!collision) {
            final double width = entity.getWidth();
            final double height = entity.getHeight();
            final double newY = y + speed;
            boolean touchesABomb = false;
            for (final Bomb bomb : bombs) {
                if (!bomb.intersects(entity.getBoundsInParent()) && bomb.intersects(x, newY, width, height)) {
                    touchesABomb = true;
                    if (newY > y) {
                        bomb.setKickDirection(EntityDirection.DOWN);
                    } else {
                        bomb.setKickDirection(EntityDirection.UP);
                    }
                    break;
                }
            }
            if (!touchesABomb) {
                entity.setY(newY);
                entity.incRunCounter(direction);
            }

        } else {
            final double deltaY = Collision.getClosestDistance(entity, speed, entity.getVerticalDirection());
            if (deltaY != 0) {
                entity.setY(y + deltaY);
                entity.incRunCounter(direction);
            }
        }
    }
}
