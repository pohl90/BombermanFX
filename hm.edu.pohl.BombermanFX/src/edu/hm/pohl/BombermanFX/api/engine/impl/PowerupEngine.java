package edu.hm.pohl.BombermanFX.api.engine.impl;

import edu.hm.pohl.BombermanFX.api.audio.BSoundFactory;
import edu.hm.pohl.BombermanFX.api.engine.BEngine;
import edu.hm.pohl.BombermanFX.api.model.impl.Player;
import edu.hm.pohl.BombermanFX.api.model.impl.Powerup;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.scene.media.AudioClip;

public class PowerupEngine extends BEngine {

    private static final AudioClip PICK_UP_CLIP = BSoundFactory.createPowerupPickupClip();

    @Override
    public void perform() {
        players.forEach(player -> {
            powerups.removeIf(powerup -> {
                if (player.intersects(powerup.getBoundsInParent())) {
                    PICK_UP_CLIP.play();
                    addPowerup(player, powerup);
                    return true;
                }
                return false;
            });
        });
    }

    public void addPowerup(final Player player, final Powerup powerup) {
        switch (powerup.getPowerType()) {
        case HEART:
            player.setLife(player.getLife() + 1);
            break;
        case BOMBS:
            player.setMaxBombs(player.getMaxBombs() + 1);
            break;
        case FLAMES:
            player.setFirePower(player.getFirePower() + 1);
            break;
        case SPEED:
            player.setSpeed(player.getSpeed() + BConstants.PLAYER_SPEED_MODIFIER);
            break;
        case SWITCH_CONTROL:
            players.forEach(playerToReverse -> {
                if (playerToReverse != player) {
                    playerToReverse.setReverseControlCounter(BConstants.ENTITY_REVERSE_CONTROL_COUNT_DOWN);
                }
            });
        default:
            break;
        }
    }
}
