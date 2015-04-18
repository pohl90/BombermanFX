package hm.edu.pohl.BombermanFX.ui.audio;

import hm.edu.pohl.BombermanFX.api.audio.BMusicPlayer;
import hm.edu.pohl.BombermanFX.utils.BConstants;
import hm.edu.pohl.BombermanFX.utils.BFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class AudioViewer {

    private final BMusicPlayer musicPlayer = new BMusicPlayer();
    private final Node audioViewerNode;
    private final AudioViewerController controller;
    
    public AudioViewer() {
        final FXMLLoader fxmlLoader = BFXMLLoader.loadFXML(getClass(), "audioviewer.fxml");
        audioViewerNode = fxmlLoader.getRoot();
        controller = fxmlLoader.getController();
        controller.setPresenter(this);
        musicPlayer.bindVolume(BConstants.volumeProperty);
        musicPlayer.play();
    }

    public Node getNode() {
        return audioViewerNode;
    }

    public void playMusic() {
        musicPlayer.play();
    }

    public void stopMusic() {
        musicPlayer.stop();
    }
}
