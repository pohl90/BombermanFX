package hm.edu.pohl.BombermanFX.ui.audio;

import hm.edu.pohl.BombermanFX.launch.AwesomeIcons;
import hm.edu.pohl.BombermanFX.utils.BConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class AudioViewerController {

	private static final String STYLE_CLASS = "audio-viewer";

	@FXML
	private StackPane audioViewer;
	@FXML
	private HBox rootBox;
	@FXML
	private HBox audioBox;
	@FXML
	private Label title;
	@FXML
	private Button startMusic;
	@FXML
	private Button stopMusic;
	@FXML
	private Slider volumeSlider;

	private AudioViewer presenter;

	public void initialize() {

		audioViewer.getStyleClass().add(STYLE_CLASS);

		title.setGraphic(AwesomeIcons.createIconLabel(AwesomeIcons.ICON_MUSIC, 20));
		startMusic.setGraphic(AwesomeIcons.createIconLabel(AwesomeIcons.ICON_PLAY, 20));
		stopMusic.setGraphic(AwesomeIcons.createIconLabel(AwesomeIcons.ICON_STOP, 20));
		volumeSlider.setMin(0);
		volumeSlider.setMax(1);
		volumeSlider.setValue(BConstants.volumeProperty.get());
		volumeSlider.setShowTickLabels(true);
		volumeSlider.setShowTickMarks(true);
		BConstants.volumeProperty.bind(volumeSlider.valueProperty());

		volumeSlider.setShowTickLabels(true);
		volumeSlider.setShowTickMarks(true);

		startMusic.setOnAction(event -> presenter.playMusic());
		stopMusic.setOnAction(event -> presenter.stopMusic());

		title.setFocusTraversable(false);
		startMusic.setFocusTraversable(false);
		stopMusic.setFocusTraversable(false);
		volumeSlider.setFocusTraversable(false);

		audioBox.visibleProperty().bind(audioViewer.hoverProperty());
	}

	public void setPresenter(final AudioViewer presenter) {
		this.presenter = presenter;
	}
}
