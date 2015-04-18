package edu.hm.pohl.BombermanFX.ui;

import edu.hm.pohl.BombermanFX.api.audio.BSoundFactory;
import edu.hm.pohl.BombermanFX.api.engine.BEngine;
import edu.hm.pohl.BombermanFX.api.engine.BEngineHandler;
import edu.hm.pohl.BombermanFX.launch.AwesomeIcons;
import edu.hm.pohl.BombermanFX.ui.audio.AudioViewer;
import edu.hm.pohl.BombermanFX.ui.menu.MainMenu;
import edu.hm.pohl.BombermanFX.ui.properties.PropertiesView;
import edu.hm.pohl.BombermanFX.ui.widgets.BCanvas;
import edu.hm.pohl.BombermanFX.ui.widgets.BToolBar;
import edu.hm.pohl.BombermanFX.utils.BConstants;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BBaseScreen extends VBox {

    private static final AudioClip INTRO_CLIP = BSoundFactory.createIntroClip();
    private static final String STYLE_CLASS = "base-screen";
    private static final PseudoClass GAME_MODE = PseudoClass.getPseudoClass("game-mode");

    private final StackPane toolBarPane = new StackPane();
    private final BorderPane root = new BorderPane();
    private final BToolBar toolBarView = new BToolBar();

    private MainMenu mainMenu;
    private final PropertiesView propertiesView;
    private BEngineHandler engineHandler;

    public BBaseScreen() {
        toolBarView.addLeft(new AudioViewer().getNode());
        toolBarView.addButtonLeft(event -> showMainMenu(), AwesomeIcons.createIconLabel(AwesomeIcons.ICON_HOME, 20));
        toolBarView.addButtonRight(event -> exit(), AwesomeIcons.createIconLabel(AwesomeIcons.ICON_CLOSE, 20));
        toolBarPane.getChildren().add(toolBarView);

        showMainMenu();
        propertiesView = new PropertiesView();

        getChildren().addAll(toolBarPane, root);
        getStyleClass().add(STYLE_CLASS);
        INTRO_CLIP.play();
    }

    public void startGame() {
        stopGame();
        engineHandler = new BEngineHandler();
        BEngine.canvas.setWidth(BConstants.WIDTH);
        BEngine.canvas.setHeight(BConstants.HEIGHT);
        engineHandler.startEngines();

        VBox.setVgrow(BEngine.canvas, Priority.ALWAYS);
        showScreen(BEngine.canvas);
    }

    public void stopGame() {
        if (engineHandler != null) {
            engineHandler.stopEngines();
        }
    }

    public void exit() {
        stopGame();
        Platform.exit();
    }

    public void showScreen(final Node node) {
        if (node instanceof BCanvas) {
            pseudoClassStateChanged(GAME_MODE, true);
        } else {
            pseudoClassStateChanged(GAME_MODE, false);
        }
        root.setCenter(node);
    }

    public void showMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu(this);
        }
        stopGame();
        showScreen(mainMenu.getNode());
    }

    public void showOptions() {
        final Stage newStage = new Stage(StageStyle.UTILITY);
        final StackPane root = new StackPane(propertiesView.getNode());
        final Scene newScene = new Scene(root, 500, 300);
        newStage.setScene(newScene);
        newStage.show();
    }
}
