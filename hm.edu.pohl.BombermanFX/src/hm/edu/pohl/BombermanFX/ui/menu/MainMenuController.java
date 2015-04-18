package hm.edu.pohl.BombermanFX.ui.menu;

import hm.edu.pohl.BombermanFX.launch.AwesomeIcons;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuController {

    @FXML
    private VBox rootBox;
    @FXML
    private HBox toolbar;
    @FXML
    private Button startButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button exitButton;

    private MainMenu presenter;

    public void initialize() {
        rootBox.setMinSize(1000, 700);
        startButton.setGraphic(AwesomeIcons.createIconLabel(AwesomeIcons.ICON_THUMBS_UP, 40));
        optionsButton.setGraphic(AwesomeIcons.createIconLabel(AwesomeIcons.ICON_EDIT, 40));
        exitButton.setGraphic(AwesomeIcons.createIconLabel(AwesomeIcons.ICON_THUMBS_DOWN, 40));

        startButton.setOnAction(event -> presenter.startGame());
        optionsButton.setOnAction(event -> presenter.showOptions());
        exitButton.setOnAction(event -> presenter.exit());
    }

    public void setPresenter(final MainMenu presenter) {
        this.presenter = presenter;
    }

    public void addToolbarContent(final Node content) {
        toolbar.getChildren().add(content);
    }
}
