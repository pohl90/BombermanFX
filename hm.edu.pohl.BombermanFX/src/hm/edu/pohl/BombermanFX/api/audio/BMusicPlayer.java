package hm.edu.pohl.BombermanFX.api.audio;

import javafx.beans.property.DoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BMusicPlayer {

    private final MediaPlayer player;
    private final String source = ClassLoader.getSystemResource(
            "hm/edu/pohl/BombermanFX/api/audio/resources/song04.mp3").toExternalForm();

    public BMusicPlayer() {
        final Media media = new Media(source);
        player = new MediaPlayer(media);
        player.setCycleCount(Integer.MAX_VALUE);
    }

    public void play() {
        player.play();
    }

    public void stop() {
        player.stop();
    }

    public void bindVolume(final DoubleProperty valueProperty) {
        player.volumeProperty().bind(valueProperty);
    }
}
