package hm.edu.pohl.BombermanFX.api.audio;

import hm.edu.pohl.BombermanFX.utils.BConstants;
import javafx.scene.media.AudioClip;

public class BSoundFactory {

    private static final String SOURCE_INTRO = ClassLoader.getSystemResource(
            "hm/edu/pohl/BombermanFX/api/audio/resources/BM_INTRO.mp3").toExternalForm();
    private static final String SOURCE_GAME_START = ClassLoader.getSystemResource(
            "hm/edu/pohl/BombermanFX/api/audio/resources/BM_START.mp3").toExternalForm();
    private static final String SOURCE_PLAYER_WALK = ClassLoader.getSystemResource(
            "hm/edu/pohl/BombermanFX/api/audio/resources/PLAYER_WALK.mp3").toExternalForm();
    private static final String SOURCE_PLAYER_DAMAGED = ClassLoader.getSystemResource(
            "hm/edu/pohl/BombermanFX/api/audio/resources/PLAYER_OUCH.mp3").toExternalForm();
    private static final String SOURCE_EXPLOSION = ClassLoader.getSystemResource(
            "hm/edu/pohl/BombermanFX/api/audio/resources/BOOM.mp3").toExternalForm();
    private static final String SOURCE_POWERUP_PICKUP = ClassLoader.getSystemResource(
            "hm/edu/pohl/BombermanFX/api/audio/resources/POWERUP.mp3").toExternalForm();

    public static AudioClip createIntroClip() {
        final AudioClip audioClip = new AudioClip(SOURCE_INTRO);
        audioClip.volumeProperty().bind(BConstants.volumeProperty);
        return audioClip;
    }
    
    public static AudioClip createGameStartClip() {
        final AudioClip audioClip = new AudioClip(SOURCE_GAME_START);
        audioClip.volumeProperty().bind(BConstants.volumeProperty.divide(3D));
        return audioClip;
    }

    public static AudioClip createPlayerWalkClip() {
        final AudioClip audioClip = new AudioClip(SOURCE_PLAYER_WALK);
        audioClip.volumeProperty().bind(BConstants.volumeProperty.divide(3D));
        return audioClip;
    }
    
    public static AudioClip createPlayerDamagedClip() {
        final AudioClip audioClip = new AudioClip(SOURCE_PLAYER_DAMAGED);
        audioClip.volumeProperty().bind(BConstants.volumeProperty.divide(3D));
        return audioClip;
    }
    
    public static AudioClip createExplosionClip() {
        final AudioClip audioClip = new AudioClip(SOURCE_EXPLOSION);
        audioClip.volumeProperty().bind(BConstants.volumeProperty);
        return audioClip;
    }
    
    public static AudioClip createPowerupPickupClip() {
        final AudioClip audioClip = new AudioClip(SOURCE_POWERUP_PICKUP);
        audioClip.volumeProperty().bind(BConstants.volumeProperty);
        return audioClip;
    }
}
